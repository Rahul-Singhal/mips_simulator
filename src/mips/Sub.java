
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
public class Sub extends R3 implements Cloneable{

    public Sub(int rdIndex, int rsIndex, int rtIndex, int id) {
        super(rdIndex, rsIndex, rtIndex, id);
    }
    
    public Sub(Sub add){
        super(add);
    }
    
    public void calculate(){
       sum = a - b;
    }
    
    public boolean execute(int pc){
        if(stageToExecute == 4) calculate();
        return super.execute(pc);
    }
}
