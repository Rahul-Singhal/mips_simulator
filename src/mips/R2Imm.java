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
public class R2Imm extends Instruction implements Cloneable{
    int rdIndex;
    int rsIndex;
    int immediate;
    int sum; // This won't actually be mathematical sum, just a name for the result of the operation
    int a, b;
    public R2Imm(int rdIndex, int rsIndex, int imm, int id){
        super(); // Calling the Instruction() constructor for initialization
        this.rdIndex = rdIndex;
	this.rsIndex = rsIndex;
	this.immediate = imm;
	this.id = id;
    }
    
    // copy constructor
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
    
    boolean execute(int pc){
        forwarded = false;
        stalled = false;
        
        if(!stages.get(stageToExecute).isFree()){
            stallInstructionStageBusy();
            return false;
        } else {
            switch(stageToExecute){
            case 1:
            case 2:
            case 7:
            case 8:
            case 9:
                executeOrdinaryStep();
                break;
            case 3:
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
            case 4:
                if (forwardingEnabled) {
                    registers.get(rdIndex).forwardIt(id, clockCycle);
                    registers.get(rdIndex).unstallRegister(sum, id);
                }
                stages.get(presentStage).setFree();
                presentStage = stageToExecute;
                stages.get(presentStage).setInstruction(id);
                /*Stage to execute will be MEM1 which is stage 7*/
                stageToExecute += 3;
                return true;
            case 10:
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
