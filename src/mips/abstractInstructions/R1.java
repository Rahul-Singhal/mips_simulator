/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mips.abstractInstructions;

import mips.Instruction;
import mips.Register;
import mips.SystemVars;

/**
 * <p> Implements common functionalities of Jr and Jalr classes. </p>
 * <p> 1 input register</p>
 
 * @author vedratn
 */
public class R1 extends Instruction implements Cloneable {

    int rsIndex;
    int destPc;
    public boolean isLink;
    String label;

    /**
     * Constructor
     * 
     * @param rsIndex index of register having destination address of next instruction
     * @param id unique integral id
     */
    public R1(int rsIndex, int id) {
        this.rsIndex = rsIndex;
        this.id = id;
        this.display = String.format(
            "%s %s", 
            this.getInstructionName(),
            Register.registerMapInverse.get(rsIndex)
        );

    }

    /**
     *  Copy Constructor
     * @param i input instructor
     */
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

    /**
     *  <p> Main execution logic of Jr and Jalr Instructions </p>
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
    
    /**
     * Unstalls register 31 which was being written into incase of Jalr
     * @param instructionId id of instruction which is unstalling the register
     */
    void unstall(int instructionId) {
        if(isLink){
            registers.get(31).unstall(instructionId);
        }
    }
    
    @Override
    public R1 clone() {
        return (R1) super.clone();
    }
    
}
