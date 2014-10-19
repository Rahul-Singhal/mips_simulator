
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
public class Sll extends R2Imm implements Cloneable{

    /**
     *
     * @param rdIndex
     * @param rsIndex
     * @param imm
     * @param id
     */
    public Sll(int rdIndex, int rsIndex, int imm, int id) {
        super(rdIndex, rsIndex, imm, id);
    }
    
    /**
     *
     * @param sll
     */
    public Sll(Sll sll){
        super(sll);
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
