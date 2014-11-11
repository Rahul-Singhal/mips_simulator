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
 *  <p> Main execution logic of all load and store type Instructions </p>
 *  <p> Only logic specific to the children classes is calculation of destination address </p>
 * 
 * @author vedratn
 */
public class R2ImmLS extends Instruction implements Cloneable {

    public int rsIndex;  // Source
    public int rtIndex;  // Destination
    public int signExtImm;
    public int a, b, sum;
    
    // Store and load do completely different things, so to ensure execute works
    // for both of them i'll use the following two booleans and category which will
    // be set in the children constructors.
    public boolean isLoad;
    public boolean isStore;
    public Integer category; // 0 : WORD, 1 : BYTE, 2 : HALFWORD

    /**
     * Constructor
     * @param rtIndex register index
     * @param rsIndex register index having memory address
     * @param signExtImm offset to be added to memory address
     * @param id unique integral id
     */
    public R2ImmLS(int rtIndex, int rsIndex, int signExtImm, int id) {
        super(); // Calling the Instruction() constructor for initialization
        this.rsIndex = rsIndex;
        this.rtIndex = rtIndex;
        this.signExtImm = signExtImm;
        this.id = id;
        this.display = String.format(
            "%s %s, %s, %d", 
            this.getInstructionName(),
            Register.registerMapInverse.get(rtIndex),
            Register.registerMapInverse.get(rsIndex),
            this.signExtImm
        );
    }

    /**
     * Copy Constructor
     * 
     * @param i instructor to be copied
     */
    public R2ImmLS(R2ImmLS i) {
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
        this.rsIndex = i.rsIndex;
        this.rtIndex = i.rtIndex;
        this.signExtImm = i.signExtImm;
        this.sum = i.sum;
        this.a = i.a;
        this.b = i.b;
        this.isLoad = i.isLoad;
        this.isStore = i.isStore;
        this.category = i.category;
    }

    /**
     *  <p> Main execution logic of all load and store Instructions </p>
     *  <p> Only logic specific to the children classes is calculation of memory address </p>
     *  <p> different memory methods are called depending on type of instructor (Load/Store) and granularity of data (Word/HaldWord/Byte) </p>
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
                    executeOrdinaryStep();
                    return true; 
                case ID:
                    stages.get(presentStage).setFree();
                    presentStage = stageToExecute;
                    stages.get(presentStage).setInstruction(id);
                    // either values are forwarded, or normally stored
                    if (!registers.get(rsIndex).isValid()) {
                        // forwarded value
                        // stages.get(presentStage).setInstruction(id);
                        stalled = true;
                        stallingRegister = rsIndex;
                        stallingInstructionId = registers.get(rsIndex).instructionId;
                        rStalls++;
                        //cout << "rt register not readable --> for sw register " << rsIndex << endl;
                        return false;
                    } else if (isStore && !registers.get(rtIndex).isValid()) {
                        // when rtIndex is not available without forwarding
                        // stages.get(presentStage).setInstruction(id);
                        stalled = true;
                        stallingRegister = rtIndex;
                        stallingInstructionId = registers.get(rtIndex).instructionId;
                        rStalls++;
                        //cout << "rt register not readable --> for sw register " << rtIndex << endl;
                        return false;
                    } else {
                        if (isLoad) {
                            // stall register
                            registers.get(rtIndex).stallRegister(id);
                            a = registers.get(rsIndex).value;
                            b = signExtImm;
                        } else {
                            a = registers.get(rtIndex).value;
                            b = registers.get(rsIndex).value;
                        }
                        int lastTime = -1;
                        if (registers.get(rsIndex).isForwarded()) {
                            forwarded = true;
                            lastTime = registers.get(rsIndex).lastForwarderTime;
                            forwardedFromInstructionId = registers.get(rsIndex).lastForwarder;
                        }
                        if (isStore) {
                            if (registers.get(rtIndex).isForwarded()) {
                                forwarded = true;
                                if (registers.get(rtIndex).lastForwarderTime > lastTime) {
                                    forwardedFromInstructionId = registers.get(rtIndex).lastForwarder;
                                }
                            }
                        }
                        stageToExecute++;
                        stalled = false;
                        return true;
                    }
                case EX:
                    //sum = a + b; done in children
                    // registers.get(rdIndex).write(sum,id,stageToExecute); // TODO : Will it ever return false?
                    stages.get(presentStage).setFree();
                    presentStage = stageToExecute;
                    stages.get(presentStage).setInstruction(id);
                    /*Stage to execute will be MEM1 which is stage 7*/
                    stageToExecute += (stageDepths[3] + stageDepths[4] + 1);
                    //////cout << "EX stage done -->" ;
                    return true;
                case MEM:
                    if(checkMemoryStall()){
                        stallInstructionMemoryMiss();
                        return false;
                    }
                    if (isStore) {
                        if(category == 0){
                            // WORD
                            memory.storeWord(sum, a);
                        }else if(category == 1){
                            // BYTE
                            memory.storeByte(sum, (char)(a & 0xFF));
                        }else if(category == 2){
                            // HALF WORD   
                            memory.storeHalfWord(sum, a);
                        }
                    } else {
                        if (forwardingEnabled) {
                            registers.get(rtIndex).forwardIt(id, clockCycle);
                            if(category == 0){
                            // WORD
                                registers.get(rtIndex).unstallRegister(memory.loadWord(sum), id);
                            }else if(category == 1){
                                // BYTE
                                registers.get(rtIndex).unstallRegister(memory.loadByte(sum), id);
                            }else if(category == 2){
                                // HALF WORD   
                                registers.get(rtIndex).unstallRegister(memory.loadHalfWord(sum), id);
                            }else if(category == 3){
                                // UNSIGNED BYTE
                                registers.get(rtIndex).unstallRegister(memory.loadUnsignedByte(sum), id);
                            }else if(category == 4){
                                // UNSIGNED HALFWORD
                                registers.get(rtIndex).unstallRegister(memory.loadUnsignedHalfWord(sum), id);
                            }
                        }
                    }
                    stages.get(presentStage).setFree();
                    presentStage = stageToExecute;
                    stages.get(presentStage).setInstruction(id);
                    stageToExecute++;
                    return true;
                case WB:
                    if (isLoad) {
                        registers.get(rtIndex).unforwardIt(id);
                        if (!forwardingEnabled) {
                            if(category == 0){
                            // WORD
                                registers.get(rtIndex).unstallRegister(memory.loadWord(sum), id);
                            }else if(category == 1){
                                // BYTE
                                registers.get(rtIndex).unstallRegister(memory.loadByte(sum), id);
                            }else if(category == 2){
                                // HALF WORD   
                                registers.get(rtIndex).unstallRegister(memory.loadHalfWord(sum), id);
                            }else if(category == 3){
                                // UNSIGNED BYTE
                                registers.get(rtIndex).unstallRegister(memory.loadUnsignedByte(sum), id);
                            }else if(category == 4){
                                // UNSIGNED HALFWORD
                                registers.get(rtIndex).unstallRegister(memory.loadUnsignedHalfWord(sum), id);
                            }
                        }
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

    // This function is different for store and load, so override in inherited classes
    @Override
    public void unstall() {
        if (category == 0) {
            registers.get(rtIndex).unstall(id);
        } else if (category == 1) {
            registers.get(rtIndex).unstall(id);
        } else if (category == 2) {
            registers.get(rtIndex).unstall(id);
        } else if (category == 3) {
            registers.get(rtIndex).unstall(id);
        } else if (category == 4) {
            registers.get(rtIndex).unstall(id);
        }
    }

    @Override
    public R2ImmLS clone() {
        return (R2ImmLS) super.clone();
    }
}