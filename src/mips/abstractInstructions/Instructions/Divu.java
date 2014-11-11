package mips.abstractInstructions.Instructions;
import mips.SystemVars;
import mips.abstractInstructions.R3Mult;

/**
 *  Implements Divu Instruction 
 * @author vedratn
 */
public class Divu extends R3Mult implements Cloneable{
    /**
     * <p> Constructor, Takes as input index of input and output registers in {@link mips.SystemVars#registers} and its own unique id</p>
     * <p> Calls the super Constructor of {@link mips.abstractInstructions.R3Mult} </p>
     * @param rdIndex index of destination register
     * @param rsIndex index of source register 1
     * @param rtIndex index of source register 2
     * @param id unique integral id     
     */
    public Divu(int rdIndex, int rsIndex, int rtIndex, int id) {
        super(rdIndex,rsIndex,rtIndex,id);
        isMult = false;
        isDiv = true;
    }
    
    /**
     *  Copy Constructor, calls super Copy Constructor of {@link mips.abstractInstructions.R3Mult} 
     * @param m instruction to be copied
     */
    public Divu(Divu m){
        super(m);
    }
    
    /**
     * <p> Only child specific logic in execution of Divu instruction </p>
     * <p> Calculates value of sum by dividing a and b </p>
     */
    public void calculate(){
        product = a/b;
    }
    
    /**
     * <p> Calls execute of {@link mips.abstractInstructions.R3Mult} </p>
     * <p> Calculation of sum is required in EX Stage, so during that call {@link #calculate()} is called before {@link mips.abstractInstructions.R3Mult#execute(int) } </p>
     * 
     * @param pc current program counter i.e. index of {@link mips.Instruction} in {@link mips.Program#code}
     * @return boolean { true: successful execution, false: unsuccessful, instruction stalled} 
     */
    @Override
    public boolean execute(int pc){
        if(SystemVars.getStageType(stageToExecute) == SystemVars.stageType.DIV && forwardingEnabled) calculate();
        else if(SystemVars.getStageType(stageToExecute) == SystemVars.stageType.WB && !forwardingEnabled) calculate();
        return super.execute(pc);
    }
}
