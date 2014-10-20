/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mips;

/**
 * Implements unconditional jump and link Instruction
 * @author vedratn
 */
public class Jal extends R0 implements Cloneable{

    /**
     * <p> Constructor, Takes as input label to jump to and its own unique id</p>
     * <p> Sets isLink to true to be used in {@link mips.R0#execute(int) } </p>
     * <p> Calls the super Constructor of {@link mips.R0} </p> 
     * @param l label of code to jump to
     * @param i unique integral id
     */
    public Jal(String l, int i) {
        super(l,i);
        isLink = true;
    }
    
    /**
     *  Copy Constructor, calls super Copy Constructor of {@link mips.R0} 
     * @param j instruction to be copied
     */
    public Jal(J j){
        super(j);
    }
    
}
