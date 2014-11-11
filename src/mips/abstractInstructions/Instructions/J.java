package mips.abstractInstructions.Instructions;
import mips.abstractInstructions.R0;


/**
 * Implements unconditional jump Instruction
 * @author vedratn
 */
public class J extends R0 implements Cloneable{

    /**
     * <p> Constructor, Takes as input label to jump to and its own unique id</p>
     * <p> Calls the super Constructor of {@link mips.abstractInstructions.R0} </p> 
     * @param l label of code to jump to
     * @param i unique integral id
     */
    public J(String l, int i) {
        super(l,i);
    }
    
    /**
     *  Copy Constructor, calls super Copy Constructor of {@link mips.abstractInstructions.R0} 
     * @param j instruction to be copied
     */
    public J(J j){
        super(j);
    }
    
}
