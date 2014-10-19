
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
public class Sltu extends R3 implements Cloneable{

    /**
     *
     * @param rdIndex
     * @param rsIndex
     * @param rtIndex
     * @param id
     */
    public Sltu(int rdIndex, int rsIndex, int rtIndex, int id) {
        super(rdIndex, rsIndex, rtIndex, id);
    }
    
    /**
     *
     * @param sltu
     */
    public Sltu(Sltu sltu){
        super(sltu);
    }
    
    /* http://www.javamex.com/java_equivalents/unsigned_arithmetic.shtml */

    /**
     *
     */
    
    public void calculate(){
       long unsignedA = a & 0xffffffffL;
       long unsignedB = b & 0xffffffffL;
       if(unsignedA < unsignedB) sum = 1;
       else sum = 0;
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
