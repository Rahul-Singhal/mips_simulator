
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
public class Ori extends R2Imm implements Cloneable{

    /**
     *
     * @param rdIndex
     * @param rsIndex
     * @param imm
     * @param id
     */
    public Ori(int rdIndex, int rsIndex, int imm, int id) {
        super(rdIndex, rsIndex, imm, id);
    }
    
    /**
     *
     * @param ori
     */
    public Ori(Ori ori){
        super(ori);
    }
    
    /**
     *
     */
    public void calculate(){
       sum = a | b;
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
