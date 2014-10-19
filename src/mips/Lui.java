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
public class Lui extends RImm implements Cloneable{
    Lui(int rdIndex, int immediate, int id) {
        super(rdIndex,immediate,id); // Calling the RImm() constructor for initialization
    }
    
    /**
     *
     * @param l
     */
    public Lui(Lui l){
        super(l);
    }
    
    /**
     *
     */
    public void calculate(){
        immediate = immediate << 16;
    }
    
    /**
     *
     * @param pc
     * @return
     */
    public boolean execute(int pc){
        if(SystemVars.getStageType(stageToExecute) == SystemVars.stageType.EX && forwardingEnabled) calculate();
        else if(SystemVars.getStageType(stageToExecute) == SystemVars.stageType.WB && !forwardingEnabled) calculate();
        return super.execute(pc);
    }
}
