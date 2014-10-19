
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
public class Sllv extends R3 implements Cloneable{

    /**
     *
     * @param rdIndex
     * @param rsIndex
     * @param rtIndex
     * @param id
     */
    public Sllv(int rdIndex, int rsIndex, int rtIndex, int id) {
        super(rdIndex, rsIndex, rtIndex, id);
    }
    
    /**
     *
     * @param sllv
     */
    public Sllv(Sllv sllv){
        super(sllv);
    }
    
    /**
     *
     */
    public void calculate(){
       sum = a << b;
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
