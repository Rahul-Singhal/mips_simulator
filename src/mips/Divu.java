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
public class Divu extends R3Mult implements Cloneable{
    Divu(int rdIndex, int rsIndex, int rtIndex, int id) {
        super(rdIndex,rsIndex,rtIndex,id);
        isDiv = true;
        isMult = false;
    }
    
    public Divu(Divu m){
        super(m);
    }
    
    public void calculate(){
        product = a/b;
    }
    
    @Override
    public boolean execute(int pc){
        if(stageToExecute == 6 && forwardingEnabled) calculate();
        else if(stageToExecute == 10 && !forwardingEnabled) calculate();
        return super.execute(pc);
    }
}
