package mips.abstractInstructions.Instructions;
import mips.SystemVars;
import mips.abstractInstructions.R2Imm;

/**
 * Implements Addiu Instruction class
 * @author vedratn
 */
public class Addiu extends R2Imm implements Cloneable{

    /**
     * <p> Constructor, Takes as input index of input and output registers in {@link mips.SystemVars#registers} and its own unique id</p>
     * <p> Calls the super Constructor of {@link mips.abstractInstructions.R2Imm} </p>
     * @param rdIndex index of destination register
     * @param rsIndex index of source register 1
     * @param imm immediate input value
     * @param id unique integral id 
     */
    public Addiu(int rdIndex, int rsIndex, int imm, int id) {
        super(rdIndex, rsIndex, imm, id);
    }
    
    /**
     * Copy Constructor, calls super Copy Constructor of {@link mips.abstractInstructions.R2Imm} 
     * @param addui instruction to be coped
     */
    public Addiu(Addiu addui){
        super(addui);
    }
    
    /**
     * <p> Only child specific logic in execution of Addiu instruction </p>
     * <p> Calculates value of sum by adding a and b(immediate value) </p>
     */
    public void calculate(){
       sum = a + b;
    }
    
    /**
     * <p> Calls execute of {@link mips.abstractInstructions.R2Imm} </p>
     * <p> Calculation of sum is required in EX Stage, so during that call {@link #calculate()} is called before {@link mips.abstractInstructions.R2Imm#execute(int) } </p>
     * 
     * @param pc current program counter i.e. index of {@link mips.Instruction} in {@link mips.Program#code}
     * @return boolean { true: successful execution, false: unsuccessful, instruction stalled} 
     */
    public boolean execute(int pc){
        if(SystemVars.getStageType(stageToExecute) == SystemVars.stageType.EX) calculate();
        return super.execute(pc);
    }
}

