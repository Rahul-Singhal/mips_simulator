/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mips;

import java.util.Random;

/**
 *   <p>Parent class of all the instructions </p>
 *   <p>Includes the common functions, members and abstractions needed in all the children classes </p>
 * @author vedratn
 */
public class Instruction extends SystemVars implements Cloneable {

    /**
     *  Index in {@link mips.SystemVars#stages} of the next stage to be executed 
     */
    public int stageToExecute;

    /**
     *  Index in {@link mips.SystemVars#stages} of the current stage
     */
    public int presentStage;

    /**
     *  Boolean variable denoting whether instruction is stalled or not
     */
    public boolean stalled;

    /**
     *  If stalled is true, this holds the {@link mips.Instruction#id} of the stalling Instruction
     */
    public int stallingInstructionId;

    /**
     *  If stall is due to a register dependency, this holds the index of that {@link mips.Register} in {@link mips.SystemVars#registers}
     */
    public int stallingRegister;

    /**
     *  If instruction is using a forwarded register, this boolean is true, else false
     */
    public boolean forwarded;

    /**
     *  If instruction is using a forwarded register,  this holds the {@link mips.Instruction#id} of the Instruction
     *  which forwarded the value
     */
    public int forwardedFromInstructionId;

    /**
     *  If instruction is using a forwarded register,  this holds the index of the {@link mips.Stage} from which 
     *  the value was forwarded
     */
    public int forwardedFromInstructionStage;

    /**
     *  holds the unparsed characters which parse to give <b>this</b> {@link mips.Instruction}.
     */
    public String display;

    /**
     *  holds the index of {@link mips.Instruction} in {@link mips.Program#code} of <b>this</b> {@link mips.Instruction}
     */
    public int address;

    /**
     *  holds the unique identity assigned to this instance of <b>this</b> {@link mips.Instruction} when inserting into {@link mips.Program#currInstructions} {@link mips.Program#execute()}
     */
    public int id;
    
    /**
     *  No of cycles this instruction has already stalled due to memory miss
     */
    public int memoryStalls;


    /**
     *  empty Constructor, initializes with default values
     */
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
        memoryStalls = 0;
    }

    // IS THE COPY CONSTRUCTOR NEEDED WHEN WE HAVE CLONE???

    /**
     *  Copy Constructor
     * @param i
     */
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
        this.memoryStalls = i.memoryStalls;
    }

    /**
     *  Another Constructor, initializing all the member values individually
     * @param s2ex next stage to be executed
     * @param ps present stage
     * @param stall currently stalled or not
     * @param stallInstId stalling instruction id
     * @param stallReg index of stalling register
     * @param forw forwarded or not
     * @param forwfromInstId id of instruction forwarding value
     * @param forwStage id of stage forwarding value
     * @param disp display string
     * @param i id
     */
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
        memoryStalls = 0;
    }

    public void init() {
        stageToExecute = 1;
        presentStage = 0;
        stalled = false;
        display = "";
    }
    
    public boolean execute(int pc) {
        return false;
    }

    public void unstall() {
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
    public int getStageToExecute() {
        return this.stageToExecute;
    }

    public int getPresentStage() {
        return this.presentStage;
    }

    public boolean getStalled() {
        return this.stalled;
    }

    public int getStallingInstructionId() {
        return this.stallingInstructionId;
    }

    public int getStallingRegister() {
        return this.stallingRegister;
    }

    public boolean getForwarded() {
        return this.forwarded;
    }

    public int getForwardedFromInstructionId() {
        return this.forwardedFromInstructionId;
    }

    public int getForwardedFromInstructionStage() {
        return this.forwardedFromInstructionStage;
    }

    public String getDisplayString() {
        return display;
    }

    public int getId() {
        return this.id;
    }

    /**
     *  Common Abstraction from all the execution cycles of an {@link mips.Instruction}
     */
    public void executeOrdinaryStep() {
        stages.get(presentStage).setFree();
        presentStage = stageToExecute;
        System.out.println(presentStage + " this is it");
        stages.get(presentStage).setInstruction(id);
        stageToExecute++;
        stalled = false;
    }

    /**
     *  Stalls <b>this</b> {@link mips.Instruction} when a resource/data dependency is detected
     */
    public void stallInstructionStageBusy() {
        stages.get(presentStage).setInstruction(id);
        stalled = true;
        stallingInstructionId = -1;
        sStalls++;
    }

    /**
     *  When a register is to be written into, it's access is locked using this function
     *  at the start of ID stage
     * @param rIndex stalling register
     */
    public void stallInstructionRegisterBusy(int rIndex) {
        stalled = true;
        stallingRegister = rIndex;
        stallingInstructionId = registers.get(rIndex).instructionId;
        rStalls++;
    }
    
    /**
     *  Stalls <b>this</b> {@link mips.Instruction} when a Memory Miss is detected
     */
    public void stallInstructionMemoryMiss() {
        stages.get(presentStage).setInstruction(id);
        stalled = true;
        stallingInstructionId = -2;
        SystemVars.totalMemoryStalls += 1;

    }
    
    /**
     *  true: if branch is taken
     *  false: if not taken
     * @return boolean
     */
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

    /**
     *  returns string holding name of instruction
     * @return String
     */
    public String getInstructionName() {
        return this.getClass().getSimpleName().toLowerCase();
    }
    
    /**
     *  returns true or false depending on whether the instruction should 
     *  stall due to a memory miss.
     * @return boolean
     */
    public boolean checkMemoryStall(){
        if(SystemVars.memoryMissRate > 0 && SystemVars.memoryMissStalls > 0){
            if (memoryStalls == 0) {
                Random rand = new Random();
                int randomNum = rand.nextInt(100) + 1;
                if (randomNum <= SystemVars.memoryMissRate) {
                    // the instruction has to stall
                    memoryStalls++;
                    return true;
                }
            } else if(memoryStalls < SystemVars.memoryMissStalls){
                memoryStalls++;
                return true;
            }
        }
        return false;
    }
}
