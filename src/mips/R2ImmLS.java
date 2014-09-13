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
public class R2ImmLS extends Instruction implements Cloneable {

    int rsIndex;  // Source
    int rtIndex;  // Destination
    int signExtImm;
    int a, b, sum;
    String address;
    boolean label;

    // Store and load do completely different things, so to ensure execute works
    // for both of them i'll use the following two booleans and category which will
    // be set in the children constructors.
    boolean isLoad;
    boolean isStore;
    Integer category; // 0 : WORK, 1 : BYTE, 2 : HALFWORD

    R2ImmLS(int rtIndex, int rsIndex, int signExtImm, int id) {
        super(); // Calling the Instruction() constructor for initialization
        this.rsIndex = rsIndex;
        this.rtIndex = rtIndex;
        this.signExtImm = signExtImm;
        this.id = id;
        this.label = false;
        this.display = String.format(
            "%s %s, %s, %d", 
            this.getInstructionName(),
            Register.registerMapInverse.get(rtIndex),
            Register.registerMapInverse.get(rsIndex),
            this.signExtImm
        );
    }

    // TODO(ved) : Is this even being used somewhere? If not, remove. 
    // TODO(ved) : Remove the variable 'address'. There is a variable called address in Instruction class also.
    /*
    R2ImmLS(int rtIndex, String address, int signExtImm, int id) {
        this.rtIndex = rtIndex;
        this.address = address;
        this.signExtImm = signExtImm;
        this.id = id;
        this.label = true;
    }*/

    R2ImmLS(R2ImmLS i) {
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
        this.rsIndex = i.rsIndex;
        this.rtIndex = i.rtIndex;
        this.signExtImm = i.signExtImm;
        this.sum = i.sum;
        this.a = i.a;
        this.b = i.b;
        this.label = i.label;
        this.isLoad = i.isLoad;
        this.isStore = i.isStore;
        this.category = i.category;
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
                    executeOrdinaryStep();
                    return true; 
                case ID:
                    stages.get(presentStage).setFree();
                    presentStage = stageToExecute;
                    stages.get(presentStage).setInstruction(id);
                    // either values are forwarded, or normally stored
                    if (!label) {
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
                                a = memory.loadAddress(address);
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
                    } else {
                        if (isLoad) {
                            registers.get(rtIndex).stallRegister(id);
                            a = memory.loadAddress(address);
                            b = signExtImm;
                            // stages.get(presentStage).setFree();
                            // presentStage = stageToExecute;
                            // stages.get(presentStage).setInstruction(id);
                            stageToExecute++;
                            stalled = false;
                            return true;
                        } else {

                            if (!registers.get(rtIndex).isValid()) {
                                // when rtIndex is not available without forwarding
                                // stages.get(presentStage).setInstruction(id);
                                stalled = true;
                                stallingRegister = rtIndex;
                                stallingInstructionId = registers.get(rtIndex).instructionId;
                                rStalls++;
                                //cout << "rt register not readable --> for sw register " << rtIndex << endl;

                                return false;
                            } else {
                                // registers.get(rtIndex).stallRegister(id); 
                                a = registers.get(rtIndex).value;
                                if (registers.get(rtIndex).isForwarded()) {
                                    forwarded = true;
                                    forwardedFromInstructionId = registers.get(rtIndex).lastForwarder;
                                }
                                b = memory.loadAddress(address);
                                stageToExecute++;
                                stalled = false;
                                return true;
                            }
                        }
                    }
                case EX:
                    //sum = a + b; done in children
                    // registers.get(rdIndex).write(sum,id,stageToExecute); // TODO : Will it ever return false?
                    stages.get(presentStage).setFree();
                    presentStage = stageToExecute;
                    stages.get(presentStage).setInstruction(id);
                    /*Stage to execute will be MEM1 which is stage 7*/
                    stageToExecute += 3;
                    //////cout << "EX stage done -->" ;
                    return true;
                case MEM:
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

    @Override   // This function is different for store and load, so override in inherited classes
    void unstall() {
        return;
    }

    @Override
    public R2ImmLS clone() {
        return (R2ImmLS) super.clone();
    }

}
