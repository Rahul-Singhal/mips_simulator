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
        if(stageToExecute == 5 && forwardingEnabled) calculate();
        else if(stageToExecute == 10 && !forwardingEnabled) calculate();
        return super.execute(pc);
    }
}