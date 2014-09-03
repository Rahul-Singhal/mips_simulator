
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
public class Andi extends R2Imm implements Cloneable{

    public Andi(int rdIndex, int rsIndex, int imm, int id) {
        super(rdIndex, rsIndex, imm, id);
    }
    
    public Andi(Andi andi){
        super(andi);
    }
    
    public void calculate(){
       sum = a & b;
    }
    
    public boolean execute(int pc){
        if(SystemVars.getStageType(stageToExecute) == SystemVars.stageType.EX) calculate();
        return super.execute(pc);
    }
}
