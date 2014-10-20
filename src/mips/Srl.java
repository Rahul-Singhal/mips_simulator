
package mips;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *  <p>Implements Srl Instruction (Shift right logical with shift amount coming from immediate value)</p>
 * @author rahul
 */
public class Srl extends R2Imm implements Cloneable{

    /**
     * <p> Constructor, Takes as input index of input and output registers in {@link mips.SystemVars#registers} and its own unique id</p>
     * <p> Calls the super Constructor of {@link mips.R3} </p>
     * @param rdIndex index of destination register
     * @param rsIndex index of source register 1
     * @param imm index of source register 2
     * @param id unique integral id 
     */
    public Srl(int rdIndex, int rsIndex, int imm, int id) {
        super(rdIndex, rsIndex, imm, id);
    }
    
    /**
     * Copy Constructor, calls super Copy Constructor of {@link mips.R3}
     * @param srl instruction to be copied
     */
    public Srl(Srl srl){
        super(srl);
    }
    
    /**
     * <p> Only child specific logic in execution of Srl instruction </p>
     * <p> Calculates value of sum by shifting a right by shift amount given in register </p>
     */
    public void calculate(){
       sum = a >>> b;
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
