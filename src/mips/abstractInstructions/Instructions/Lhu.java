package mips.abstractInstructions.Instructions;
import mips.SystemVars;
import mips.abstractInstructions.R2ImmLS;

/**
 * Implements Lhu Instruction
 * @author vedratn
 */
public class Lhu extends R2ImmLS implements Cloneable{
    /**
     * <p> Constructor, calls {@link mips.abstractInstructions.R2ImmLS#R2ImmLS(int, int, int, int) } </p>
     * <p> Sets the value of isLoad and isStore to be used in {@link mips.abstractInstructions.R2ImmLS#execute(int)} </p>
     * @param rtIndex register index
     * @param rsIndex register index having memory address
     * @param signExtImm offset to be added to memory address
     * @param id unique integral id
     */
    public Lhu(int rtIndex, int rsIndex, int signExtImm, int id) {
        super(rtIndex,rsIndex,signExtImm,id);
        isLoad = true;
        isStore = false;
        category = 4; // unsigned halfword
    }
    
    /**
     *  Copy Constructor, calls super Copy Constructor of {@link mips.abstractInstructions.R2ImmLS} 
     * @param lw instruction to be copied
     */
    public Lhu(Lhu lw){
        super(lw);
    }
    
    /**
     * <p> Only child specific logic in execution of Lhu instruction </p>
     * <p> Calculates memory address by adding base address and offset </p>
     */
    public void calculate(){
        sum = a+b;
    }
    
    /**
     * <p> Calls execute of {@link mips.abstractInstructions.R2ImmLS} </p>
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
