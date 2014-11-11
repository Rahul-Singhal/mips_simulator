package mips.abstractInstructions;
import mips.Instruction;
import mips.SystemVars;

/**
 * <p> Implements common functionalities of J and Jal classes. </p>
 * 
 * @author vedratn
 */
public class R0 extends Instruction implements Cloneable {

    int destPc;
    String label;
    public boolean isLink;

    /**
     * Constructor
     * @param l label of code to jump to
     * @param i unique integral id
     */
    public R0(String l, int i) {
        label = l;
        id = i;
        this.display = String.format(
            "%s %s", 
            this.getInstructionName(),
            label
        );
    }

    /**
     * Copy Constructor
     * @param i Instruction to be copied
     */
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

    /**
     *  <p> Main execution logic of J and Jal Instructions </p>
     *  <p> Only logic specific to the children classes is definition of variable {@link mips.abstractInstructions.R1#isLink} </p>
     * 
     * @param pc current program counter i.e. index of {@link mips.Instruction} in {@link mips.Program#code}
     * @return boolean { true: successful execution, false: unsuccessful, instruction stalled} 
     */
    @Override
    public boolean execute(int pc) {
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
                    stageToExecute += (stageDepths[3] + stageDepths[4] + 1);
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

    /**
     * Unstalls register 31 which was being written into incase of Jal
     */
    @Override
    public void unstall() {
        if(isLink){
            registers.get(31).unstall(id);
        }
    }
    
    @Override
    public R0 clone() {
        return (R0) super.clone();
    }
}
