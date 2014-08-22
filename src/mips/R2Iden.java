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
public class R2Iden extends Instruction implements Cloneable{
    	int rsIndex;
	int rtIndex;
	// I assume the following variable will hold the address to the next instruction
	// if the branch is taken
	int destPc;
	int a, b;
	String label;

	public R2Iden(int rsIndex, int rtIndex, String label, int id){
            super(); // Calling the Instruction() constructor for initialization
            this.rsIndex = rsIndex;
            this.rtIndex = rtIndex;
            this.destPc = labelMap.get(label);
            this.id = id;
            this.label = label;
        }
        
	public R2Iden(R2Iden i){
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
            this.rtIndex = i.rtIndex;
            this.rsIndex = i.rsIndex;
            this.destPc = labelMap.get(i.label);
            this.a = i.a;
            this.b = i.b;
            this.label = i.label;
        }
	
        @Override
	boolean execute(int pc){
            return false;
        }
        
        @Override
	void unstall(int instructionId){
            return ;
        }
	
        @Override
        public R2Iden clone(){
            return (R2Iden) super.clone();
        }
}
