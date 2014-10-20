/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mips;

/**
 * Implements unconditional jump to register address and link Instruction 
 * @author vedratn
 */
public class Jalr extends R1 implements Cloneable{

    /**
     * <p> Constructor, Takes as input register index having destination address and its own unique id</p>
     * <p> Calls the super Constructor of {@link mips.R1} </p> 
     * <p> Sets isLink to true to be used in {@link mips.R1#execute(int) }
     * @param rsIndex label of code to jump to
     * @param id unique integral id

     */
    public Jalr(int rsIndex, int id) {
        super(rsIndex,id);
        this.isLink = true;
    }
    
    /**
     *  Copy Constructor, calls super Copy Constructor of {@link mips.R1} 
     * @param jr instruction to be copied
     */
    public Jalr(Jr jr){
        super(jr);
    }
}
