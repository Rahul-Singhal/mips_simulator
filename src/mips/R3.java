/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mips;

/**
 * <p> Implements common functionalities of all 3 register classes except Mult and Div. </p>
 * <p> Two input registers and one output register </p>
 * @author vedratn
 */
public class R3 extends Instruction implements Cloneable{
    int rdIndex;
    int rsIndex;
    int rtIndex;
    int sum; // This won't actually be mathematical sum, just a name for the result of the operation
    int a, b;

    /**
     * <p> Constructor, Takes as input index of input and output registers in {@link mips.SystemVars#registers} and its own unique id</p>
     * <p> Sets the display string to be displayed on GUI when <b>this</b> is being executed </p>
     * @param rdIndex index of destination register
     * @param rsIndex index of source register 1
     * @param rtIndex index of source register 2
     * @param id
     */
    public R3(int rdIndex, int rsIndex, int rtIndex, int id){
        super(); // Calling the Instruction() constructor for initialization
        this.rdIndex = rdIndex;
	this.rsIndex = rsIndex;
	this.rtIndex = rtIndex;
	this.id = id;
        this.display = String.format(
            "%s %s, %s, %s", 
            this.getInstructionName(),
            Register.registerMapInverse.get(rdIndex),
            Register.registerMapInverse.get(rsIndex),
            Register.registerMapInverse.get(rtIndex)
        );
    }
    
    // copy constructor

    /**
     *  Copy Constructor
     * @param i instruction to be copied
     */
        public R3(R3 i){
        this.stageToExecute = i.stageToExecute;
	this.presentStage = i.presentStage;
	this.stalled = i.stalled;
	this.stallingInstructionId = i.stallingInstructionId;
	this.stallingRegister = i.stallingRegister;
	this.forwarded =i.forwarded;
	this.address=i.address;
	this.forwardedFromInstructionId = i.forwardedFromInstructionId;
	this.forwardedFromInstructionStage = i.forwardedFromInstructionStage;
	this.display = i.display;
	this.id = i.id;
	this.rdIndex = i.rdIndex;
	this.rsIndex = i.rsIndex;
	this.rtIndex = i.rtIndex;
	this.sum = i.sum;
	this.a = i.a;
	this.b = i.b;
    }
    
 
    public R3 clone(){
        return (R3)super.clone();
    }
    
    /**
     * Unstalls the register which was being written into by <b>this</b> Instruction
     */
    void unstall(){
        registers.get(rdIndex).unstall(id);
    }
    
    /**
     *  <p> Main execution logic of all the three register Instructions </p>
     *  <p> Only logic specific to the children classes is calculation of "sum",
     *  which is a result of the arithmetic calculations specific to the children </p>
     * 
     * @param pc current program counter i.e. index of {@link mips.Instruction} in {@link mips.Program#code}
     * @return boolean { true: successful execution, false: unsuccessful, instruction stalled} 
     */
    public boolean execute(int pc){
        forwarded = false;
        stalled = false;
        
        if(!stages.get(stageToExecute).isFree()){
            stallInstructionStageBusy();
            return false;
        } else {
            SystemVars.stageType sType = SystemVars.getStageType(stageToExecute);
            switch(sType){
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
                    registers.get(rdIndex).stallRegister(id);
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
                    return true;
                }
            case EX:
                if (forwardingEnabled) {
                    registers.get(rdIndex).forwardIt(id, clockCycle);
                    registers.get(rdIndex).unstallRegister(sum, id);
                }
                stages.get(presentStage).setFree();
                presentStage = stageToExecute;
                stages.get(presentStage).setInstruction(id);
                /*Stage to execute will be MEM1 which is stage 7*/
                stageToExecute += (stageDepths[3] + stageDepths[4] + 1);
                return true;
            case WB:
                registers.get(rdIndex).unforwardIt(id);
                if (!forwardingEnabled) {
                    registers.get(rdIndex).unstallRegister(sum, id);
                }
                stages.get(presentStage).setFree();
                presentStage = stageToExecute;
                stages.get(presentStage).setInstruction(id);
                stageToExecute = -1;
                return true;
            }
            return false;
        }
    }
}