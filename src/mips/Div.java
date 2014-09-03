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
public class Div extends R3Mult implements Cloneable{
    Div(int rdIndex, int rsIndex, int rtIndex, int id) {
        super(rdIndex,rsIndex,rtIndex,id);
        isDiv = true;
        isMult = false;
    }
    
    public Div(Div m){
        super(m);
    }
    
    public void calculate(){
        product = a/b;
    }
    
    public boolean execute(int pc){
        if(SystemVars.getStageType(stageToExecute) == SystemVars.stageType.DIV && forwardingEnabled) calculate();
        else if(SystemVars.getStageType(stageToExecute) == SystemVars.stageType.WB && !forwardingEnabled) calculate();
        return super.execute(pc);
    }
}
