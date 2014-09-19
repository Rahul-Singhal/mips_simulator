/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mips;

/**
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
                    if (!registers.get(rsIndex).isValid()) {
                        stallInstructionRegisterBusy(rsIndex);
                        return false;
                    } else if (!registers.get(rtIndex).isValid()) {
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
                    updateProgramCounterAfterPredicateCalculation();
                    stages.get(presentStage).setFree();
                    presentStage = stageToExecute;
                    stages.get(presentStage).setInstruction(id);
                    /*Stage to execute will be MEM1 which is stage 7*/
                    stageToExecute += 3;
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
    
    public void updateProgramCounterAfterPredicateCalculation(){
        if (branchTaken) {
            if (branchStrategy == branchStrategyType.TAKEN) {
                programCounter = this.id;
                branchChanged = false;
            } else if (branchStrategy == branchStrategyType.NOTTAKEN) {
                programCounter = fallbackInstructionMap.get(this.id);
                branchChanged = true;
            } else {
                //TODO(Rahul)
            }
        } else {
            if (branchStrategy == branchStrategyType.TAKEN) {
                programCounter = fallbackInstructionMap.get(this.id);
                branchChanged = true;
            } else if (branchStrategy == branchStrategyType.NOTTAKEN) {
                programCounter = this.id;
                branchChanged = false;
            } else {
                //TODO(Rahul)
            }
        }
    }
    
    public void updateProgramCounterBeforePredicateCalculation(){
        System.out.println("fallback set " + (labelMap.get(label)-1));
        if(branchStrategy == branchStrategyType.TAKEN){
            fallbackInstructionMap.put(this.id, programCounter);
            programCounter = (labelMap.get(label)-1);
        } else if(branchStrategy == branchStrategyType.NOTTAKEN){
            fallbackInstructionMap.put(this.id, labelMap.get(label)-1);
        } else {
            //TODO(Rahul)
        }
    }
}
