/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mips;

/**
 * Implements Beq Instruction
 * @author vedratn
 */
public class Beq extends R2Iden implements Cloneable{

    /**
     * <p> Constructor, Takes as input index of input registers in {@link mips.SystemVars#registers}, label to jump to and its own unique id</p>
     * <p> Calls the super Constructor of {@link mips.R2Iden} </p>
     * @param rsIndex source register index
     * @param rtIndex destination register index
     * @param label code label to go to in case of brach change
     * @param id unique integer id
     */
    public Beq(int rsIndex, int rtIndex, String label, int id) {
        super(rsIndex,rtIndex,label,id);
    }
    
    /**
     *  Copy Constructor, calls super Copy Constructor of {@link mips.R2Iden} 
     * @param b instruction to be copied
     */
    public Beq(Beq b){
        super(b);
    }
    
    /**
     * <p> Only child specific logic in execution of Beq instruction </p>
     * <p> Calculates the predicate branchTaken depending on the equality relation between a and b </p>
     */
    public void calculate(){
        if(a == b){
            branchTaken = true;
        }else{
            branchTaken = false;
        }
    }
    
    /**
     * <p> Calls execute of {@link mips.R2Iden} </p>
     * <p> Calculation of "branchPredicate" is required in ID/EX Stage, so during that call {@link #calculate()} is called before {@link mips.R2Iden#execute(int) } </p>
     * 
     * @param pc current program counter i.e. index of {@link mips.Instruction} in {@link mips.Program#code}
     * @return boolean { true: successful execution, false: unsuccessful, instruction stalled} 
     */
    public boolean execute(int pc){
        if(SystemVars.getStageType(stageToExecute) == SystemVars.stageType.ID && fastBranching){
            if(super.execute(pc)){
                calculate();
                updateProgramCounterAfterPredicateCalculation();
                return true;
            } else {
                return false;
            } 
        }
        if(SystemVars.getStageType(stageToExecute) == SystemVars.stageType.EX) calculate();
        return super.execute(pc);
    }
}
