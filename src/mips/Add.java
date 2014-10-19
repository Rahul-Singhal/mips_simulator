
package mips;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *  <p> Implements add instruction class. </p>
 *  <p> Two input registers, One output registers </p>
 * @author rahul
 */
public class Add extends R3 implements Cloneable{

    /**
     *  Takes as input index of input and output registers in {@link mips.SystemVars#registers} and its own unique id
     * 
     * @param rdIndex
     * @param rsIndex
     * @param rtIndex
     * @param id
     */
    public Add(int rdIndex, int rsIndex, int rtIndex, int id) {
        super(rdIndex, rsIndex, rtIndex, id);
    }
    
    /**
     *
     * @param add
     */
    public Add(Add add){
        super(add);
    }
    
    /**
     *
     */
    public void calculate(){
       sum = a + b;
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
