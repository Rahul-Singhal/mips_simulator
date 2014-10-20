package mips;

/**
 * Implements Sh Instruction
 * @author vedratn
 */
public class Sh extends R2ImmLS implements Cloneable{
    /**
     * <p> Constructor, calls {@link mips.R2ImmLS#R2ImmLS(int, int, int, int) } </p>
     * <p> Sets the value of isLoad and isStore to be used in {@link mips.R2ImmLS#execute(int)} </p>
     * @param rtIndex register index
     * @param rsIndex register index having memory address
     * @param signExtImm offset to be added to memory address
     * @param id unique integral id
     */
    Sh(int rtIndex, int rsIndex, int signExtImm, int id) {
        super(rtIndex,rsIndex,signExtImm,id);
        isLoad = false;
        isStore = true;
        category = 2; // halfword
    }
    
    /**
     *  Copy Constructor, calls super Copy Constructor of {@link mips.R2ImmLS} 
     * @param lw instruction to be copied
     */
    public Sh(Sh lw){
        super(lw);
    }
    
    /**
     * <p> Only child specific logic in execution of Sh instruction </p>
     * <p> Calculates memory address by adding base address and offset </p>
     */
    public void calculate(){
        sum = b+signExtImm;
    }
    
    /**
     * <p> Calls execute of {@link mips.R2ImmLS} </p>
     * <p> Calculation of sum is required in EX Stage, so during that call {@link #calculate()} is called before {@link mips.R2Imm#execute(int) } </p>
     * 
     * @param pc current program counter i.e. index of {@link mips.Instruction} in {@link mips.Program#code}
     * @return boolean { true: successful execution, false: unsuccessful, instruction stalled} 
     */
    @Override
    public boolean execute(int pc){
        if(SystemVars.getStageType(stageToExecute) == SystemVars.stageType.EX) calculate();
        return super.execute(pc);
    }
}