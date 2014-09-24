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
public class R3Mult extends Instruction implements Cloneable {

    int rdIndex;
    int rsIndex;
    int rtIndex;
    int product;
    int a, b;

    boolean isMult;
    boolean isDiv;

    R3Mult(int rdIndex, int rsIndex, int rtIndex, int id) {
        super(); // Calling the Instruction() constructor for initialization
        this.rdIndex = rdIndex;
        this.rsIndex = rsIndex;
        this.rtIndex = rtIndex;
        this.id = id;
        this.display = String.format(
            "%s %s, %s, %s", 
            this.getInstructionName(),
            Register.registerMapInverse.get(rdIndex),
            Register.registerMapInverse.get(rsIndex),
            Register.registerMapInverse.get(rtIndex)
        );
    }

    // TODO: implement this and store the result in registers hi and lo
    R3Mult(int rdIndex, int rsIndex, int id) {

    }

    R3Mult(R3Mult i) {
        this.stageToExecute = i.stageToExecute;
        this.presentStage = i.presentStage;
        this.stalled = i.stalled;
        this.stallingInstructionId = i.stallingInstructionId;
        this.stallingRegister = i.stallingRegister;
        this.forwarded = i.forwarded;
        this.forwardedFromInstructionId = i.forwardedFromInstructionId;
        this.forwardedFromInstructionStage = i.forwardedFromInstructionStage;
        this.display = i.display;
        this.id = i.id;
        this.rdIndex = i.rdIndex;
        this.rsIndex = i.rsIndex;
        this.rtIndex = i.rtIndex;
        this.product = i.product;
        this.a = i.a;
        this.b = i.b;
        this.isDiv = i.isDiv;
        this.isMult = i.isMult;
    }

    public R3Mult clone() {
        return (R3Mult) super.clone();
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
                    return true;
                case ID:
                    stages.get(presentStage).setFree();
                    presentStage = stageToExecute;
                    stages.get(presentStage).setInstruction(id);
                    boolean rsBusy = false;
                    boolean rtBusy = false;
                    if(!registers.get(rsIndex).isValid()){
                        rsBusy = true;
                    }
                    if(!registers.get(rtIndex).isValid()){
                        rtBusy = true;
                    }
                    if(rsBusy && rtBusy){
                        if(registers.get(rsIndex).instructionId > registers.get(rtIndex).instructionId){
                            stallInstructionRegisterBusy(rsIndex);
                            return false;
                        } else {
                            stallInstructionRegisterBusy(rtIndex);
                            return false;
                        }
                    } else if(rsBusy){
                            stallInstructionRegisterBusy(rsIndex);
                            return false;
                    } else if(rtBusy){
                            stallInstructionRegisterBusy(rtIndex);
                            return false;
                    } else {
                        registers.get(rdIndex).stallRegister(id);
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
                        a = registers.get(rsIndex).value;
                        b = registers.get(rtIndex).value;
                        if(isMult)
                            stageToExecute += (stageDepths[2]+1);
                        else
                            stageToExecute += (stageDepths[2] + stageDepths[3] + 1);
                        stalled = false;
                        return true;
                    }
                case MULT:
                    if (isMult) {
                        stages.get(presentStage).setFree();
                        presentStage = stageToExecute;
                        stages.get(presentStage).setInstruction(id);
                        if (forwardingEnabled) {
                            registers.get(rdIndex).forwardIt(id, clockCycle);
                            registers.get(rdIndex).unstallRegister(product, id);
                        }
                        stageToExecute += (stageDepths[3] + 1);
                    }
                    return true;
                case DIV:
                    if (isDiv) {
                        stages.get(presentStage).setFree();
                        presentStage = stageToExecute;
                        stages.get(presentStage).setInstruction(id);
                        if (forwardingEnabled) {
                            registers.get(rdIndex).forwardIt(id, clockCycle);
                            registers.get(rdIndex).unstallRegister(product, id);
                        }
                        stageToExecute += 1;
                    }
                    return true;
                case WB:
                    registers.get(rdIndex).unforwardIt(id);
                    if (!forwardingEnabled) {
                        registers.get(rdIndex).unstallRegister(product, id);
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

    void unstall() {
        registers.get(rdIndex).unstall(id);
    }

}
