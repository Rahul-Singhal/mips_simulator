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
public class RImm extends Instruction implements Cloneable {

    int rdIndex;
    int immediate;

    RImm(int rdIndex, int immediate, int id) {
        super(); // Calling the Instruction() constructor for initialization
        this.rdIndex = rdIndex;
        this.immediate = immediate;
        this.id = id;
        this.display = String.format(
            "%s %s, %d", 
            this.getInstructionName(),
            Register.registerMapInverse.get(rdIndex),
            this.immediate
        );

    }

    RImm(RImm i) {
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
        this.rdIndex = i.rdIndex;
        this.immediate = i.immediate;
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
                    return true;
                case ID:
                    registers.get(rdIndex).stallRegister(id);
                    return true;
                case EX:
                    if (forwardingEnabled) {
                        registers.get(rdIndex).forwardIt(id, clockCycle);
                        registers.get(rdIndex).unstallRegister(immediate, id); // TODO : Will it ever return false?
                    }
                    // registers.get(rdIndex).write(sum,id,stageToExecute); // TODO : Will it ever return false?
                    stages.get(presentStage).setFree();
                    presentStage = stageToExecute;
                    stages.get(presentStage).setInstruction(id);
                    /*Stage to execute will be MEM1 which is stage 7*/
                    stageToExecute += 3;
                    return true;
                case WB:
                    registers.get(rdIndex).unforwardIt(id);
                    if (!forwardingEnabled) {
                        registers.get(rdIndex).unstallRegister(immediate, id);
                    }
                    stages.get(presentStage).setFree();
                    presentStage = stageToExecute;
                    stages.get(presentStage).setInstruction(id);
                    stageToExecute = -1;
					//////cout << "WB completed -->";
                    // Instruction completed, so stage number is now invalid.
                    return true;
            }
            return false;
        }
    }

    @Override
    void unstall(int instructionId) {
        if (!registers.get(rdIndex).isValid()) {
            registers.get(rdIndex).unstall(instructionId);
        }
    }

    public RImm clone() {
        return (RImm) super.clone();
    }

}
