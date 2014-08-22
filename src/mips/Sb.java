package mips;

public class Sb extends R2ImmLS implements Cloneable{
    Sb(int rtIndex, int rsIndex, int signExtImm, int id) {
        super(rtIndex,rsIndex,signExtImm,id);
        isLoad = false;
        isStore = true;
        category = 1; // byte
    }
    
    public Sb(Sb lw){
        super(lw);
    }
    
    public void calculate(){
        sum = b+signExtImm;
    }
    
    @Override
    public boolean execute(int pc){
        if(stageToExecute == 4) calculate();
        return super.execute(pc);
    }
}