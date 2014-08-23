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
public class Mult extends R3Mult implements Cloneable{
    Mult(int rdIndex, int rsIndex, int rtIndex, int id) {
        super(rdIndex,rsIndex,rtIndex,id);
        isMult = true;
        isDiv = false;
    }
    
    public Mult(Mult m){
        super(m);
    }
    
    public void calculate(){
        product = a*b;
    }
    
    public boolean execute(int pc){
        if(stageToExecute == 5 && forwardingEnabled) calculate();
        else if(stageToExecute == 10 && !forwardingEnabled) calculate();
        return super.execute(pc);
    }
}
