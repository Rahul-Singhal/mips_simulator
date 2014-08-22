
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

    public Sllv(int rdIndex, int rsIndex, int rtIndex, int id) {
        super(rdIndex, rsIndex, rtIndex, id);
    }
    
    public Sllv(Sllv sllv){
        super(sllv);
    }
    
    public void calculate(){
       sum = a << b;
    }
    
    public boolean execute(int pc){
        if(stageToExecute == 4) calculate();
        return super.execute(pc);
    }
}