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
    
    //USED TO RETURN POINTER IN C++, DEFINITION MODIFIED TO RETURN NEW OBJECT 
    @Override
    public R2Imm clone(){
        return (R2Imm) super.clone();
    }
    
    void unstall(){
        registers.get(rdIndex).unstall(id);
    }
    
    @Override
    boolean execute(int pc){
        return false;
    }
}
