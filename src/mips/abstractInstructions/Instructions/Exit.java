package mips.abstractInstructions.Instructions;
import mips.abstractInstructions.R0;


/**
 * Implements exit Instruction
 * @author rahul
 */
public class Exit extends R0 implements Cloneable{

    /**
     * <p> Constructor, Takes as input its own unique id</p>
     * <p> Calls the super Constructor of {@link mips.abstractInstructions.R0} </p> 
     * @param i unique integral id
     */
    public Exit(int i) {
        super("Exit", i);
    }
    
    /**
     *  Copy Constructor, calls super Copy Constructor of {@link mips.abstractInstructions.R0} 
     * @param e instruction to be copied
     */
    public Exit(Exit e){
        super(e);
    }
    
}
