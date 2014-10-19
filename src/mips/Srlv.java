
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
public class Srlv extends R3 implements Cloneable{

    /**
     *
     * @param rdIndex
     * @param rsIndex
     * @param rtIndex
     * @param id
     */
    public Srlv(int rdIndex, int rsIndex, int rtIndex, int id) {
        super(rdIndex, rsIndex, rtIndex, id);
    }
    
    /**
     *
     * @param srlv
     */
    public Srlv(Srlv srlv){
        super(srlv);
    }
    
    /**
     *
     */
    public void calculate(){
       sum = a >>> b;
    }
    
    /**
     *
     * @param pc
     * @return
     */
    public boolean execute(int pc){
        if(SystemVars.getStageType(stageToExecute) == SystemVars.stageType.EX) calculate();
        return super.execute(pc);
    }
}
