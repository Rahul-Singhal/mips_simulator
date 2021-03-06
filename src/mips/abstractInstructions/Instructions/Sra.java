package mips.abstractInstructions.Instructions;
import mips.SystemVars;
import mips.abstractInstructions.R2Imm;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * <p> Implements Sra Instruction (Shift Right Arithmetic with shift amount in immediate value) </p>
 * @author rahul
 */
public class Sra extends R2Imm implements Cloneable{

    /**
     * <p> Constructor, Takes as input index of input and output registers in {@link mips.SystemVars#registers} and its own unique id</p>
     * <p> Calls the super Constructor of {@link mips.abstractInstructions.R2Imm} </p>
     * @param rdIndex index of destination register
     * @param rsIndex index of source register 1
     * @param imm immediate shift amount
     * @param id unique integral id 
     */
    public Sra(int rdIndex, int rsIndex, int imm, int id) {
        super(rdIndex, rsIndex, imm, id);
    }
    
    /**
     * Copy Constructor, calls super Copy Constructor of {@link mips.abstractInstructions.R2Imm}
     * @param sra instruction to be copied
     */
    public Sra(Sra sra){
        super(sra);
    }
    
    /**
     * <p> Only child specific logic in execution of Sra instruction </p>
     * <p> Calculates value of sum by shifting a right by shift amount given in register </p>
     */
    public void calculate(){
       sum = a >> b;
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
