package mips.abstractInstructions.Instructions;
import mips.SystemVars;
import mips.abstractInstructions.R3;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *  <p> Implements unsigned add instruction class. </p>
 * @author rahul
 */
public class Addu extends R3 implements Cloneable{

    /**
     * <p> Constructor, Takes as input index of input and output registers in {@link mips.SystemVars#registers} and its own unique id</p>
     * <p> Calls the super Constructor of {@link mips.abstractInstructions.R3} </p>
     * @param rdIndex index of destination register
     * @param rsIndex index of source register 1
     * @param rtIndex index of source register 2
     * @param id unique integral id     
     */
    public Addu(int rdIndex, int rsIndex, int rtIndex, int id) {
        super(rdIndex, rsIndex, rtIndex, id);
    }
    
    /**
     *  Copy Constructor, calls super Copy Constructor of {@link mips.abstractInstructions.R3} 
     * @param addu instruction to be copied
     */
    public Addu(Addu addu){
        super(addu);
    }
    
    /**
     * <p> Only child specific logic in execution of Addu instruction </p>
     * <p>Calculates value of sum by adding a and b</p>
     */
    public void calculate(){
       sum = a + b;
    }
    
    /**
     * <p> Calls execute of {@link mips.abstractInstructions.R3} </p>
     * <p> Calculation of sum is required in EX Stage, so during that call {@link #calculate()} is called before {@link mips.abstractInstructions.R3#execute(int) } </p>
     * 
     * @param pc current program counter i.e. index of {@link mips.Instruction} in {@link mips.Program#code}
     * @return boolean { true: successful execution, false: unsuccessful, instruction stalled} 
     */
    public boolean execute(int pc){
        if(SystemVars.getStageType(stageToExecute) == SystemVars.stageType.EX) calculate();
        return super.execute(pc);
    }
}
