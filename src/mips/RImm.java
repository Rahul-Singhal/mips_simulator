/*
 * To change this license header, choose RImmcense Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mips;

/**
 *  <p> Implements execution for Instruction Lui</p>
 *  <p> One destination register index, immediate value to be loaded </p>
 * @author vedratn
 */
public class RImm extends Instruction implements Cloneable {

    int rdIndex;
    int immediate;

    /**
     * Constructor
     * @param rdIndex Destination register
     * @param immediate immediate value to be loaded
     * @param id unique integral identity
     */
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

    /**
     * Copy Constructor
     * 
     * @param i Instruction to be copied
     */
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

    /**
     *  <p> Main execution logic of Lui Instruction </p>
     *  <p> Only logic specific to the children classes is bit shift operations on immediate </p>
     * 
     * @param pc current program counter i.e. index of {@link mips.Instruction} in {@link mips.Program#code}
     * @return boolean { true: successful execution, false: unsuccessful, instruction stalled} 
     */
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

    /**
     * Unstalls register rdIndex which was being written into incase of Lui
     */
    @Override
    void unstall() {
        if (!registers.get(rdIndex).isValid()) {
            registers.get(rdIndex).unstall(id);
        }
    }

    public RImm clone() {
        return (RImm) super.clone();
    }

}
