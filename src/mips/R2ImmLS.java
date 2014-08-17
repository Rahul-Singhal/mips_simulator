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
public class R2ImmLS extends Instruction{
    	int rsIndex;  // Source
	int rtIndex;  // Destination
	int signExtImm;
	int a, b, sum;
	String address;
	boolean label;

	R2ImmLS(int rtIndex, int rsIndex, int signExtImm, int id){
            super(); // Calling the Instruction() constructor for initialization
            this.rsIndex = rsIndex;
            this.rtIndex = rtIndex;
            this.signExtImm = signExtImm;
            this.id = id;
            this.label = false;
        }
        
	R2ImmLS(int rtIndex, String address, int signExtImm, int id){
            this.rtIndex = rtIndex;
            this.address = address;
            this.signExtImm = signExtImm;
            this.id = id;
            this.label = true;
        }
        
	R2ImmLS(R2ImmLS i){
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
            this.rsIndex = i.rsIndex;
            this.rtIndex = i.rtIndex;
            this.signExtImm = i.signExtImm;
            this.sum = i.sum;
            this.a = i.a;
            this.b = i.b;
            this.label = i.label;
        }
        
	boolean execute(int pc){
            return false;
        }
        
        @Override   // This function is different for store and load, so override in inherited classes
	void unstall(int instructionId){
            return;
        }
        /*
	R2ImmLS * clone();
        */
	
}
