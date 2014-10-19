/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mips;

/**
 *  <p> Main execution logic of all 2 register+ 1 identifier(label) argument type Instructions </p>
 *  <p> Only logic specific to the children classes is calculation of branch predicate  </p>
 * 
 * @author vedratn
 */
public class R2Iden extends Instruction implements Cloneable {

    int rsIndex;
    int rtIndex;
    // I assume the following variable will hold the address to the next instruction
    // if the branch is taken
    int destPc;
    int a, b;
    boolean branchTaken;
    String label;

    /**
     *  Constructor
     * 
     * @param rsIndex source register index
     * @param rtIndex destination register index
     * @param label code label to go to in case of brach change
     * @param id unique integer id
     */
    public R2Iden(int rsIndex, int rtIndex, String label, int id) {
        super(); // Calling the Instruction() constructor for initialization
        this.rsIndex = rsIndex;
        this.rtIndex = rtIndex;
        this.id = id;
        this.label = label;
        this.display = String.format(
            "%s %s, %s, %s", 
            this.getInstructionName(),
            Register.registerMapInverse.get(rsIndex),
            Register.registerMapInverse.get(rtIndex),
            label
        );
    }

    /**
     *  Copy Constructor
     * 
     * @param i Instruction to be copied
     */
    public R2Iden(R2Iden i) {
        this.stageToExecute = i.stageToExecute;
        this.presentStage = i.presentStage;
        this.stalled = i.stalled;
        this.stallingInstructionId = i.stallingInstructionId;
        this.stallingRegister = i.stallingRegister;
        this.forwarded = i.forwarded;
        this.address = i.address;
        this.forwardedFromInstructionId = i.forwardedFromInstructionId;
        this.forwardedFromInstructionStage = i.forwardedFromInstructionStage;
        this.display = i.display;
        this.id = i.id;
        this.rtIndex = i.rtIndex;
        this.rsIndex = i.rsIndex;
        this.a = i.a;
        this.b = i.b;
        this.label = i.label;
        this.branchTaken = i.branchTaken;
    }

    /**
     *  <p> Main execution logic of bne and beq branch Instructions </p>
     *  <p> Only logic specific to the children classes is calculation of calculation of branch predicate </p>
     * 
     * @param pc current program counter i.e. index of {@link mips.Instruction} in {@link mips.Program#code}
     * @return boolean { true: successful execution, false: unsuccessful, instruction stalled} 
     */
    
    @Override
    boolean execute(int pc) {
        forwarded = false;
        stalled = false;
        if (!stages.get(stageToExecute).isFree()) {
            stallInstructionStageBusy();
            return false;
        } else {
            if(this.getPresentStage() == 0){
                 updateProgramCounterBeforePredicateCalculation();
            }
            SystemVars.stageType sType = SystemVars.getStageType(stageToExecute);
            switch (sType) {
                case DUMMY:
                case IF:
                case MEM:
                    executeOrdinaryStep();
                    return true;
                case ID:
                    stages.get(presentStage).setFree();
                    presentStage = stageToExecute;
                    stages.get(presentStage).setInstruction(id);
                    boolean rsBusy = false;
                    boolean rtBusy = false;
                    if(!registers.get(rsIndex).isValid()){
                        rsBusy = true;
                    }
                    if(!registers.get(rtIndex).isValid()){
                        rtBusy = true;
                    }
                    if(rsBusy && rtBusy){
                        if(registers.get(rsIndex).instructionId > registers.get(rtIndex).instructionId){
                            stallInstructionRegisterBusy(rsIndex);
                            return false;
                        } else {
                            stallInstructionRegisterBusy(rtIndex);
                            return false;
                        }
                    } else if(rsBusy){
                            stallInstructionRegisterBusy(rsIndex);
                            return false;
                    } else if(rtBusy){
                            stallInstructionRegisterBusy(rtIndex);
                            return false;
                    } else {
                        a = registers.get(rsIndex).value;
                        b = registers.get(rtIndex).value;
                        int lastTime = -1;
                        if (registers.get(rsIndex).isForwarded()) {
                            forwarded = true;
                            lastTime = registers.get(rsIndex).lastForwarderTime;
                            forwardedFromInstructionId = registers.get(rsIndex).lastForwarder;
                        }
                        if (registers.get(rtIndex).isForwarded()) {
                            forwarded = true;
                            if (registers.get(rtIndex).lastForwarderTime > lastTime) {
                                forwardedFromInstructionId = registers.get(rtIndex).lastForwarder;
                            }
                        }
                        stageToExecute++;
                        stalled = false;
                    }
                    return true; 
                case EX:
                    if(!fastBranching){
                        updateProgramCounterAfterPredicateCalculation();
                    }
                    stages.get(presentStage).setFree();
                    presentStage = stageToExecute;
                    stages.get(presentStage).setInstruction(id);
                    /*Stage to execute will be MEM1 which is stage 7*/
                    stageToExecute += (stageDepths[3] + stageDepths[4] + 1);
                    return true;
                case WB:
                    stages.get(presentStage).setFree();
                    presentStage = stageToExecute;
                    stages.get(presentStage).setInstruction(id);
                    stageToExecute = -1;
                    return true;
            }
            return false;
        }
    }
    
    @Override
    void unstall() {
        registers.get(rsIndex).unstall(id);
    }

    @Override
    public R2Iden clone() {
        return (R2Iden) super.clone();
    }
    
    /**
     *  <p>Main logic body handling consequences of branch strategies</p>
     *  <p> Depending on branch strategy branch is either taken or not </p>
     *  <p> Later in the execution when the predicate is calculated, it is deciphered whether the decision made was correct or not </p>
     *  <p> If incorrect, pipeline is flushed and its effects nullified. Also the branch strategy is corrected if possible </p>
     */
    public void updateProgramCounterAfterPredicateCalculation(){
        if (branchTaken) {
            if (branchStrategy == branchStrategyType.TAKEN) {
                branchChanged = false;
            } else if (branchStrategy == branchStrategyType.NOTTAKEN) {
                programCounter = fallbackInstructionMap.get(this.id);
                branchChanged = true;
            } else {
                if(SystemVars.branchHistory.get(this.address % historySize).get(0)){
                    // we predicted taken
                    branchChanged = false;
                } else {
                    // we predicted not taken
                    programCounter = fallbackInstructionMap.get(this.id);
                    branchChanged = true;
                }
                SystemVars.branchHistory.get(this.address % historySize).set(0, true);

            }
        } else {
            if (branchStrategy == branchStrategyType.TAKEN) {
                programCounter = fallbackInstructionMap.get(this.id);
                branchChanged = true;
            } else if (branchStrategy == branchStrategyType.NOTTAKEN) {
                branchChanged = false;
            } else {
                if(SystemVars.branchHistory.get(this.address % historySize).get(0)){
                    // we predicted taken
                    programCounter = fallbackInstructionMap.get(this.id);
                    branchChanged = true;
                } else {
                    // we predicted not taken
                    branchChanged = false;
                }
                SystemVars.branchHistory.get(this.address % historySize).set(0, false);
            }
        }
    }
    
    /**
     *  <p>Main logic body deciding effect of branch strategies</p>
     *  <p> Depending on branch strategy branch is either taken or not and program counter is updated </p>
     */
    public void updateProgramCounterBeforePredicateCalculation(){
        if(branchStrategy == branchStrategyType.TAKEN){
            fallbackInstructionMap.put(this.id, programCounter);
            programCounter = (labelMap.get(label)-1);
        } else if(branchStrategy == branchStrategyType.NOTTAKEN){
            fallbackInstructionMap.put(this.id, labelMap.get(label)-1);
        } else {
            if(SystemVars.branchHistory.get(this.address % historySize).get(0)){
                // branch was taken last time
                fallbackInstructionMap.put(this.id, programCounter);
                programCounter = (labelMap.get(label)-1);
            } else {
                // branch was not taken last time
                fallbackInstructionMap.put(this.id, labelMap.get(label)-1);
            }
        }
    }
}
