package mips.abstractInstructions.Instructions;
import mips.SystemVars;
import mips.abstractInstructions.R2Imm;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Implements the SetLessThanImmediateUnsigned Instruction Class
 * @author rahul
 */
public class Sltiu extends R2Imm implements Cloneable{

    /**
     * <p> Constructor, Takes as input index of input and output registers in {@link mips.SystemVars#registers} and its own unique id</p>
     * <p> Calls the super Constructor of {@link mips.abstractInstructions.R2Imm} </p>
     * @param rdIndex index of destination register
     * @param rsIndex index of source register 1
     * @param imm immediate input value
     * @param id unique integral id 
     */
    public Sltiu(int rdIndex, int rsIndex, int imm, int id) {
        super(rdIndex, rsIndex, imm, id);
    }
    
    /**
     * Copy Constructor, calls super Copy Constructor of {@link mips.abstractInstructions.R2Imm}
     *
     * @param sltiu instruction to be copied
     */
    public Sltiu(Sltiu sltiu){
        super(sltiu);
    }
    
    /* http://www.javamex.com/java_equivalents/unsigned_arithmetic.shtml */

    /**
     * <p> Only child specific logic in execution of Sltiu instruction </p>
     * <p> Calculates value of sum depending on the inequality relation between a and b(immediate value)</p>
     */
    
    public void calculate(){
       long unsignedA = a & 0xffffffffL;
       long unsignedB = b & 0xffffffffL;
       if(unsignedA < unsignedB) sum = 1;
       else sum = 0;
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
