
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
public class And extends R3 implements Cloneable{

    /**
     *
     * @param rdIndex
     * @param rsIndex
     * @param rtIndex
     * @param id
     */
    public And(int rdIndex, int rsIndex, int rtIndex, int id) {
        super(rdIndex, rsIndex, rtIndex, id);
    }
    
    /**
     *
     * @param and
     */
    public And(And and){
        super(and);
    }
    
    /**
     *
     */
    public void calculate(){
       sum = a & b;
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
