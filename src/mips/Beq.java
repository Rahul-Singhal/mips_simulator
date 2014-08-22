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
public class Beq extends R2Iden implements Cloneable{
    public Beq(int rsIndex, int rtIndex, String label, int id) {
        super(rsIndex,rtIndex,label,id);
    }
    
    public Beq(Beq b){
        super(b);
    }
    
    public void calculate(){
        if(a == b){
            branchTaken = true;
        }else{
            branchTaken = false;
        }
    }
    
    public boolean execute(int pc){
        if(stageToExecute == 3 && fastBranching) calculate();
        else if(stageToExecute == 4 && !fastBranching) calculate();
        return super.execute(pc);
    }
}
