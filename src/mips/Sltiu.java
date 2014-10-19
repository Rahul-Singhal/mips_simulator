
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
public class Sltiu extends R2Imm implements Cloneable{

    /**
     *
     * @param rdIndex
     * @param rsIndex
     * @param imm
     * @param id
     */
    public Sltiu(int rdIndex, int rsIndex, int imm, int id) {
        super(rdIndex, rsIndex, imm, id);
    }
    
    /**
     *
     * @param sltiu
     */
    public Sltiu(Sltiu sltiu){
        super(sltiu);
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
