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
public class R1 extends Instruction implements Cloneable {

    int rsIndex;
    int destPc;
    boolean isLink;
    String label;

    public R1(int rsIndex, int id) {
        this.rsIndex = rsIndex;
        this.id = id;
    }

    public R1(R1 i) {
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
        this.address = i.address;
        // this.rtIndex = i.rtIndex;
        this.rsIndex = i.rsIndex;
        this.isLink = i.isLink;
    }

    @Override
    boolean execute(int pc) {
        forwarded = false;
        stalled = false;

        if (!stages.get(stageToExecute).isFree()) {
            stallInstructionStageBusy();
            return false;
        } else {
            switch (stageToExecute) {
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
                    if (!registers.get(rsIndex).isValid()) {
                        stalled = true;
                        stallingRegister = rsIndex;
                        stallingInstructionId = registers.get(rsIndex).instructionId;
                        rStalls++;
                        return false;
                    } else {
                        if (isLink) {
                            registers.get(31).stallRegister(id);
                        }
                        stageToExecute++;
                        stalled = false;
                        destPc = registers.get(rsIndex).value;
                        int lastTime = -1;
                        if (registers.get(rsIndex).isForwarded()) {
                            forwarded = true;
                            lastTime = registers.get(rsIndex).lastForwarderTime;
                            forwardedFromInstructionId = registers.get(rsIndex).lastForwarder;
                        }
                        if (fastBranching) {
                            if (true) {
                                programCounter = destPc - 1;
                                branchChanged = true;
                            }
                        }
                        return true;
                    }
                case 4:
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
                case 10:
                    if(isLink && !forwardingEnabled){
                        registers.get(31).unstallRegister(address+1, id);
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

    void unstall(int instructionId) {
        if(isLink){
            registers.get(31).unstall(instructionId);
        }
    }
}
