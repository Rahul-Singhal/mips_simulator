
package mips;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author rahul
 */
public class Slti extends R2Imm implements Cloneable{

    public Slti(int rdIndex, int rsIndex, int imm, int id) {
        super(rdIndex, rsIndex, imm, id);
    }
    
    public Slti(Slti slti){
        super(slti);
    }
    
    public void calculate(){
       if(a < b) sum = 1;
       else sum = 0;
    }
    
    public boolean execute(int pc){
        if(SystemVars.getStageType(stageToExecute) == SystemVars.stageType.EX) calculate();
        return super.execute(pc);
    }
}
