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
public class R3Mult extends Instruction implements Cloneable{
    int rdIndex;
    int rsIndex;
    int rtIndex;
    int product;
    int presentSubStage;
    int a, b;
    
    R3Mult(int rdIndex, int rsIndex, int rtIndex, int id){
        super(); // Calling the Instruction() constructor for initialization
        this.rdIndex = rdIndex;
        this.rsIndex = rsIndex;
        this.rtIndex = rtIndex;
        this.id = id;
        this.presentSubStage = 0;
    }
    
    //implement this and store the result in registers hi and lo
    R3Mult(int rdIndex, int rsIndex, int id){
        
    }
    
    R3Mult(R3Mult i){
        this.stageToExecute = i.stageToExecute;
        this.presentStage = i.presentStage;
        this.stalled = i.stalled;
        this.stallingInstructionId = i.stallingInstructionId;
        this.stallingRegister = i.stallingRegister;
        this.forwarded =i.forwarded;
        this.forwardedFromInstructionId = i.forwardedFromInstructionId;
        this.forwardedFromInstructionStage = i.forwardedFromInstructionStage;
        this.display = i.display;
        this.id = i.id;
        this.rdIndex = i.rdIndex;
        this.rsIndex = i.rsIndex;
        this.rtIndex = i.rtIndex;
        this.product = i.product;
        this.a = i.a;
        this.b = i.b;
        this.presentSubStage = 0;
    }
    
    public R3Mult clone(){
        return (R3Mult)super.clone();
    }
    
    boolean execute(int pc){
        return false;
    }
    
    void unstall(){
        registers.get(rdIndex).unstall(id); 
    }
    
}
