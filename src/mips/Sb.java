package mips;

/**
 *
 * @author vedratn
 */
public class Sb extends R2ImmLS implements Cloneable{
    Sb(int rtIndex, int rsIndex, int signExtImm, int id) {
        super(rtIndex,rsIndex,signExtImm,id);
        isLoad = false;
        isStore = true;
        category = 1; // byte
    }
    
    /**
     *
     * @param lw
     */
    public Sb(Sb lw){
        super(lw);
    }
    
    /**
     *
     */
    public void calculate(){
        sum = b+signExtImm;
    }
    
    /**
     *
     * @param pc
     * @return
     */
    @Override
    public boolean execute(int pc){
        if(SystemVars.getStageType(stageToExecute) == SystemVars.stageType.EX) calculate();
        return super.execute(pc);
    }
}