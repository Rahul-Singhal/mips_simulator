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
public class R0 extends Instruction implements Cloneable {

    int destPc;
    String label;
    boolean isLink;

    public R0(String l, int i) {
        label = l;
        id = i;
    }

    public R0(R0 i) {
        this.stageToExecute = i.stageToExecute;
        this.presentStage = i.presentStage;
        this.stalled = i.stalled;
        this.stallingInstructionId = i.stallingInstructionId;
        this.stallingRegister = i.stallingRegister;
        this.forwarded = i.forwarded;
        this.address = i.address;
        this.forwardedFromInstructionId = i.forwardedFromInstructionId;
        this.forwardedFromInstructionStage = i.forwardedFromInstructionStage;
        this.display = i.display;
        this.id = i.id;
        // this.rtIndex = i.rtIndex;
        // this.rsIndex = i.rsIndex;
        this.label = i.label;
        this.isLink = i.isLink;
    }

    boolean execute(int pc) {
        forwarded = false;
        stalled = false;

        if (!stages.get(stageToExecute).isFree()) {
            stallInstructionStageBusy();
            return false;
        } else {
            SystemVars.stageType sType = SystemVars.getStageType(stageToExecute);
            switch (sType) {
                case DUMMY:
                case IF:
                case MEM:
                    executeOrdinaryStep();
                    break;
                case ID:
                    destPc = labelMap.get(label);
                    stages.get(presentStage).setFree();
                    presentStage = stageToExecute;
                    stages.get(presentStage).setInstruction(id);
                    if(isLink){
                        registers.get(31).stallRegister(id); 
                    }
                    stageToExecute++;
                    stalled = false;
                    if (fastBranching) {
                        programCounter = destPc - 1;
                        branchChanged = true;
                    }
                    return true;
                case EX:
                    if(isLink && forwardingEnabled){
			registers.get(31).unstallRegister(address+1, id);
                    }
                    if (!fastBranching) {
                        programCounter = destPc - 1;
                        branchChanged = true;
                    }
                    stages.get(presentStage).setFree();
                    presentStage = stageToExecute;
                    stages.get(presentStage).setInstruction(id);
                    stageToExecute += 3;
                    return true;
                case WB:
                    if(isLink && !forwardingEnabled){
                        registers.get(31).unstallRegister(address+1, id); // TODO : Will it ever return false?
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

    @Override
    void unstall(int instructionId) {
        if(isLink){
            registers.get(31).unstall(instructionId);
        }
    }
    
    @Override
    public R0 clone() {
        return (R0) super.clone();
    }
}
