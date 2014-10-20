/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mips;

/**
 * Implements Sw Instruction
 * @author vedratn
 */
public class Sw extends R2ImmLS implements Cloneable{
    /**
     * <p> Constructor, calls {@link mips.R2ImmLS#R2ImmLS(int, int, int, int) } </p>
     * <p> Sets the value of isLoad and isStore to be used in {@link mips.R2ImmLS#execute(int)} </p>
     * @param rtIndex register index
     * @param rsIndex register index having memory address
     * @param signExtImm offset to be added to memory address
     * @param id unique integral id
     */
    Sw(int rtIndex, int rsIndex, int signExtImm, int id) {
        super(rtIndex,rsIndex,signExtImm,id);
        isLoad = false;
        isStore = true;
        category = 0; // word
    }
    
    /**
     *  Copy Constructor, calls super Copy Constructor of {@link mips.R2ImmLS} 
     * @param lw instruction to be copied
     */
    public Sw(Sw lw){
        super(lw);
    }
    
    /**
     * <p> Only child specific logic in execution of Sw instruction </p>
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
    public boolean execute(int pc){
        if(SystemVars.getStageType(stageToExecute) == SystemVars.stageType.EX) calculate();
        return super.execute(pc);
    }
}
