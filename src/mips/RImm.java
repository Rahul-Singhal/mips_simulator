/*
 * To change this license header, choose RImmcense Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mips;

/**
 *
 * @author vedratn
 */
public class RImm extends Instruction implements Cloneable{
    	int rdIndex;
	int immediate;

	RImm(int rdIndex, int immediate, int id){
            super(); // Calling the Instruction() constructor for initialization
            this.rdIndex = rdIndex;
            this.immediate = immediate;
            this.id = id;
        }
        
	RImm(RImm i){
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
            this.immediate = i.immediate;
        }
        
        @Override
	boolean execute(int pc){
            return false;
        }
        
        @Override
	void unstall(int instructionId){
            if(!registers.get(rdIndex).isValid())
		registers.get(rdIndex).unstall(instructionId);
        }
        
	public RImm clone(){
            return (RImm) super.clone();
        }

}
