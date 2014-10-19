/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mips;

/**
 *  <p> Implements common functionalities of all 2 register, 1 immediate input Instruction classes. </p>
 * <p> 1 input registers, 1 output register and 1 immediate input</p>
 * 
 * @author vedratn
 */
public class R2Imm extends Instruction implements Cloneable{
    int rdIndex;
    int rsIndex;
    int immediate;
    int sum; // This won't actually be mathematical sum, just a name for the result of the operation
    int a, b;

    /**
     *  <p> Constructor, Takes as input index of input and output registers in {@link mips.SystemVars#registers}, immediate input value and its own unique id</p>
     * <p> Sets the display string to be displayed on GUI when <b>this</b> is being executed </p>
     * 
     * @param rdIndex  destination register index
     * @param rsIndex  source register index
     * @param imm   immediate input
     * @param id    integer unique id
     */
    public R2Imm(int rdIndex, int rsIndex, int imm, int id){
        super(); // Calling the Instruction() constructor for initialization
        this.rdIndex = rdIndex;
	this.rsIndex = rsIndex;
	this.immediate = imm;
	this.id = id;
        this.display = String.format(
            "%s %s, %s, %d", 
            this.getInstructionName(),
            Register.registerMapInverse.get(rdIndex),
            Register.registerMapInverse.get(rsIndex),
            imm
        );
}
    
    // copy constructor

    /**
     * Copy Constructor
     * @param i {@link mips.Instruction} to be copied
     */
        public R2Imm(R2Imm i){
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
	this.immediate = i.immediate;
	this.sum = i.sum;
	this.a = i.a;
	this.b = i.b;
    }
    
    public R2Imm clone(){
        return (R2Imm) super.clone();
    }
    
    void unstall(){
        registers.get(rdIndex).unstall(id);
    }
    
    /**
     *  <p> Main execution logic of all 2 register+ 1 immediate argument type Instructions </p>
     *  <p> Only logic specific to the children classes is calculation of "sum",
     *  which is a result of the arithmetic calculations specific to the children </p>
     * 
     * @param pc current program counter i.e. index of {@link mips.Instruction} in {@link mips.Program#code}
     * @return boolean { true: successful execution, false: unsuccessful, instruction stalled} 
     */
    boolean execute(int pc){
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
                if(!registers.get(rsIndex).isValid()){
                    stallInstructionRegisterBusy(rsIndex);
                    return false;
                }
                else {
                    registers.get(rdIndex).stallRegister(id);
                    a = registers.get(rsIndex).value;
                    b = immediate;
                    if (registers.get(rsIndex).isForwarded()) {
                        forwarded = true;
                        forwardedFromInstructionId = registers.get(rsIndex).lastForwarder;
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
