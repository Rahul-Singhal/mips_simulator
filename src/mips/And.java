
package mips;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Implements Binary "And" Instruction 
 * @author rahul
 */
public class And extends R3 implements Cloneable{

    /**
     * <p> Constructor, Takes as input index of input and output registers in {@link mips.SystemVars#registers} and its own unique id</p>
     * <p> Calls the super Constructor of {@link mips.R3} </p>
     * @param rdIndex index of destination register
     * @param rsIndex index of source register 1
     * @param rtIndex index of source register 2
     * @param id unique integral id
     */
    public And(int rdIndex, int rsIndex, int rtIndex, int id) {
        super(rdIndex, rsIndex, rtIndex, id);
    }
    
    
     /**
     *  Copy Constructor, calls super Copy Constructor of {@link mips.R3} 
     * @param and instruction to be copied
     */
    public And(And and){
        super(and);
    }
    
    /**
     * <p> Only child specific logic in execution of And instruction </p>
     * <p>Calculates value of sum by taking bitwise and of a and b</p>
     */
    public void calculate(){
       sum = a & b;
    }
    
    /**
     * <p> Calls execute of {@link mips.R3} </p>
     * <p> Calculation of sum is required in EX Stage, so during that call {@link #calculate()} is called before {@link mips.R3#execute(int) } </p>
     * 
     * @param pc current program counter i.e. index of {@link mips.Instruction} in {@link mips.Program#code}
     * @return boolean { true: successful execution, false: unsuccessful, instruction stalled} 
     */
    public boolean execute(int pc){
        if(SystemVars.getStageType(stageToExecute) == SystemVars.stageType.EX) calculate();
        return super.execute(pc);
    }
}
