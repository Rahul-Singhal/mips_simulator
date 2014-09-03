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
public class R2Iden extends Instruction implements Cloneable {

    int rsIndex;
    int rtIndex;
    // I assume the following variable will hold the address to the next instruction
    // if the branch is taken
    int destPc;
    int a, b;
    boolean branchTaken;
    String label;

    public R2Iden(int rsIndex, int rtIndex, String label, int id) {
        super(); // Calling the Instruction() constructor for initialization
        this.rsIndex = rsIndex;
        this.rtIndex = rtIndex;
        this.destPc = labelMap.get(label);
        this.id = id;
        this.label = label;
    }

    public R2Iden(R2Iden i) {
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
        this.rtIndex = i.rtIndex;
        this.rsIndex = i.rsIndex;
        this.destPc = labelMap.get(i.label);
        this.a = i.a;
        this.b = i.b;
        this.label = i.label;
        this.branchTaken = i.branchTaken;
    }

    @Override
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
                    stages.get(presentStage).setFree();
                    presentStage = stageToExecute;
                    stages.get(presentStage).setInstruction(id);
                    if (!registers.get(rsIndex).isValid()) {
                        stallInstructionRegisterBusy(rsIndex);
                        return false;
                    } else if (!registers.get(rtIndex).isValid()) {
                        stallInstructionRegisterBusy(rtIndex);
                        return false;
                    } else {
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
                        if (fastBranching) {
                            if (branchTaken) {
                                programCounter = destPc - 1;
                                branchChanged = true;
                            }
                        }
                    }
                case EX:
                    if (!fastBranching) {
                        if (branchTaken) {
                            programCounter = destPc - 1;
                            branchChanged = true;
                        }
                    }
                    stages.get(presentStage).setFree();
                    presentStage = stageToExecute;
                    stages.get(presentStage).setInstruction(id);
                    /*Stage to execute will be MEM1 which is stage 7*/
                    stageToExecute += 3;
                    return true;
                case WB:
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
        return;
    }

    @Override
    public R2Iden clone() {
        return (R2Iden) super.clone();
    }
}
