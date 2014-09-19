/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mips;

/**
 *
 * @author vedratn
 */
public class Bne extends R2Iden implements Cloneable{
    public Bne(int rsIndex, int rtIndex, String label, int id) {
        super(rsIndex,rtIndex,label,id);
    }
    
    public Bne(Bne b){
        super(b);
    }
    
    public void calculate(){
        if (a != b){
            branchTaken = true;
        }else{
            branchTaken = false;
        }
    }
    
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
