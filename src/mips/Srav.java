
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
public class Srav extends R3 implements Cloneable{

    public Srav(int rdIndex, int rsIndex, int rtIndex, int id) {
        super(rdIndex, rsIndex, rtIndex, id);
    }
    
    public Srav(Srav srav){
        super(srav);
    }
    
    public void calculate(){
       sum = a >> b;
    }
    
    public boolean execute(int pc){
        if(SystemVars.getStageType(stageToExecute) == SystemVars.stageType.EX) calculate();
        return super.execute(pc);
    }
}
