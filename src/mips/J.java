/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mips;

/**
 * Implements unconditional jump Instruction
 * @author vedratn
 */
public class J extends R0 implements Cloneable{

    /**
     * <p> Constructor, Takes as input label to jump to and its own unique id</p>
     * <p> Calls the super Constructor of {@link mips.R0} </p> 
     * @param l label of code to jump to
     * @param i unique integral id
     */
    public J(String l, int i) {
        super(l,i);
    }
    
    /**
     *  Copy Constructor, calls super Copy Constructor of {@link mips.R0} 
     * @param j instruction to be copied
     */
    public J(J j){
        super(j);
    }
    
}
