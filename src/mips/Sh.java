package mips;

public class Sh extends R2ImmLS implements Cloneable{
    Sh(int rtIndex, int rsIndex, int signExtImm, int id) {
        super(rtIndex,rsIndex,signExtImm,id);
        isLoad = false;
        isStore = true;
        category = 2; // halfword
    }
    
    public Sh(Sh lw){
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