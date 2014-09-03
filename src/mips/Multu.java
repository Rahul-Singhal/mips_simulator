package mips;

/**
 *
 * @author vedratn
 */
public class Multu extends R3Mult implements Cloneable{
    Multu(int rdIndex, int rsIndex, int rtIndex, int id) {
        super(rdIndex,rsIndex,rtIndex,id);
        isMult = true;
        isDiv = false;
    }
    
    public Multu(Multu m){
        super(m);
    }
    
    public void calculate(){
        product = a*b;
    }
    
    public boolean execute(int pc){
        if(SystemVars.getStageType(stageToExecute) == SystemVars.stageType.MULT && forwardingEnabled) calculate();
        else if(SystemVars.getStageType(stageToExecute) == SystemVars.stageType.WB && !forwardingEnabled) calculate();
        return super.execute(pc);
    }
}