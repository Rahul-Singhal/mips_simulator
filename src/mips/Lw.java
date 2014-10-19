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
public class Lw extends R2ImmLS implements Cloneable{
    Lw(int rtIndex, int rsIndex, int signExtImm, int id) {
        super(rtIndex,rsIndex,signExtImm,id);
        isLoad = true;
        isStore = false;
        category = 0; // word
    }
    
    /**
     *
     * @param lw
     */
    public Lw(Lw lw){
        super(lw);
    }
    
    /**
     *
     */
    public void calculate(){
        sum = a+b;
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
