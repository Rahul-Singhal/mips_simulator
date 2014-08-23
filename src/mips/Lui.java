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
    
    public Lui(Lui l){
        super(l);
    }
    
    public void calculate(){
        immediate = immediate << 16;
    }
    
    public boolean execute(int pc){
        if(stageToExecute == 4 && forwardingEnabled) calculate();
        else if(stageToExecute == 10 && !forwardingEnabled) calculate();
        return super.execute(pc);
    }
}
