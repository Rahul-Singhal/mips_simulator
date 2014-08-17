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
public class R3 extends Instruction{
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
    
    //USED TO RETURN POINTER IN C++, DEFINITION MODIFIED TO RETURN NEW OBJECT 
/*    
    public R3 clone(){
        this(this);
    }
    */
    
    void unstall(){
        registers.get(rdIndex).unstall(id);
    }
    
    @Override
    boolean execute(int pc){
        return false;
    }
}
