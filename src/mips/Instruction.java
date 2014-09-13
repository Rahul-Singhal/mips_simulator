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
public class Instruction extends SystemVars implements Cloneable {

    public int stageToExecute;
    public int presentStage;
    public boolean stalled;
    public int stallingInstructionId;
    public int stallingRegister;
    public boolean forwarded;
    public int forwardedFromInstructionId;
    public int forwardedFromInstructionStage;
    public String display;
    public int address;
    public int id;

    public Instruction() {
        stageToExecute = 1;
        presentStage = 0;
        stalled = false;
        stallingInstructionId = 0;
        stallingRegister = 0;
        forwarded = false;
        forwardedFromInstructionId = 0;
        forwardedFromInstructionStage = 0;
        display = "default";
        id = 0;
    }

    // IS THE COPY CONSTRUCTOR NEEDED WHEN WE HAVE CLONE???
    public Instruction(Instruction i) {
        // It USED TO BE &I IN C++, IT WAS THE COPY CONSTRUCTOR
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
    }

    public Instruction(int s2ex, int ps, boolean stall, int stallInstId, int stallReg, boolean forw, int forwfromInstId, int forwStage, String disp, int i) {
        stageToExecute = s2ex;
        presentStage = ps;
        stalled = stall;
        stallingInstructionId = stallInstId;
        stallingRegister = stallReg;
        forwarded = forw;
        forwardedFromInstructionId = forwfromInstId;
        forwardedFromInstructionStage = forwStage;
        display = disp;
        id = i;
    }

    void init() {
        stageToExecute = 1;
        presentStage = 0;
        stalled = false;
        display = "";
    }

    boolean execute(int pc) {
        return false;
    }

    void unstall() {
        return;
    }

    public Instruction clone() {
        try {
            return (Instruction) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    // eigth stage
    int getStageToExecute() {
        return this.stageToExecute;
    }

    int getPresentStage() {
        return this.presentStage;
    }

    boolean getStalled() {
        return this.stalled;
    }

    int getStallingInstructionId() {
        return this.stallingInstructionId;
    }

    int getStallingRegister() {
        return this.stallingRegister;
    }

    boolean getForwarded() {
        return this.forwarded;
    }

    int getForwardedFromInstructionId() {
        return this.forwardedFromInstructionId;
    }

    int getForwardedFromInstructionStage() {
        return this.forwardedFromInstructionStage;
    }

    String getDisplayString() {
        return display;
    }

    int getId() {
        return this.id;
    }

    public void executeOrdinaryStep() {
        stages.get(presentStage).setFree();
        presentStage = stageToExecute;
        stages.get(presentStage).setInstruction(id);
        stageToExecute++;
        stalled = false;
    }

    public void stallInstructionStageBusy() {
        stages.get(presentStage).setInstruction(id);
        stalled = true;
        stallingInstructionId = -1;
        sStalls++;
    }

    public void stallInstructionRegisterBusy(int rIndex) {
        stalled = true;
        stallingRegister = rIndex;
        stallingInstructionId = registers.get(rIndex).instructionId;
        rStalls++;
    }
    
    public boolean checkBranchChange() {
        if (branchStrategy == SystemVars.branchStrategyType.TAKEN) {
            return true;
        }
        else if (branchStrategy == SystemVars.branchStrategyType.HISTORY) {
            return branchHistory.get(programCounter % historySize).get(0); 
        }
        else {
            return false;
        } // Assuming default strategy NOTTAKEN
    }

    public String getInstructionName() {
        return this.getClass().getSimpleName().toLowerCase();
    }
}
