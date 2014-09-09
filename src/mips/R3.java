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
public class R3 extends Instruction implements Cloneable{
    int rdIndex;
    int rsIndex;
    int rtIndex;
    int sum; // This won't actually be mathematical sum, just a name for the result of the operation
    int a, b;
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
    
    void unstall(){
        registers.get(rdIndex).unstall(id);
    }
    
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
                if(!registers.get(rsIndex).isValid()){
                    stallInstructionRegisterBusy(rsIndex);
                    return false;
                }
                else if(!registers.get(rtIndex).isValid()){
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
                stageToExecute += 3;
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