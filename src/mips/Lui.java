/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mips;

/**
 * Implements Lui Instruction
 * @author vedratn
 */
public class Lui extends RImm implements Cloneable{
    /**
     * <p> Constructor, Takes as input index of output register in {@link mips.SystemVars#registers}, value to be loaded and its own unique id</p>
     * <p> Calls the super Constructor of {@link mips.RImm} </p>
     * @param rdIndex destination register index
     * @param immediate immediate value to be loaded
     * @param id unique integral id
     */
    Lui(int rdIndex, int immediate, int id) {
        super(rdIndex,immediate,id); // Calling the RImm() constructor for initialization
    }
    
    /**
     *  Copy Constructor, calls super Copy Constructor of {@link mips.RImm} 
     * @param l instruction to be copied
     */
    public Lui(Lui l){
        super(l);
    }
    
    /**
     * <p> Only child specific logic in execution of Lui instruction </p>
     * <p> Shifts value of immediate input left by 16 bits </p>
     */
    public void calculate(){
        immediate = immediate << 16;
    }
    
    /**
     * <p> Calls execute of {@link mips.RImm} </p>
     * <p> Calculation of immediate is required in EX Stage, so during that call {@link #calculate()} is called before {@link mips.RImm#execute(int) } </p>
     * 
     * @param pc current program counter i.e. index of {@link mips.Instruction} in {@link mips.Program#code}
     * @return boolean { true: successful execution, false: unsuccessful, instruction stalled} 
     * @return
     */
    public boolean execute(int pc){
        if(SystemVars.getStageType(stageToExecute) == SystemVars.stageType.EX && forwardingEnabled) calculate();
        else if(SystemVars.getStageType(stageToExecute) == SystemVars.stageType.WB && !forwardingEnabled) calculate();
        return super.execute(pc);
    }
}
