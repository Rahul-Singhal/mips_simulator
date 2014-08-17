/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mips;

/**
 *
 * @author vedratn
 */
public class Instruction {
    
    public Instruction(){
        stageToExecute = 1;
	presentStage = 0;
	stalled = false;
	stallingInstructionId = 0;
	stallingRegister = 0;
	forwarded =false;
	forwardedFromInstructionId = 0;
	forwardedFromInstructionStage = 0;
	display = "default";
	id = 0;
    }
    
    public Instruction(Instruction i){
        // It USED TO BE &I IN C++, IT WAS THE COPY CONSTRUCTOR
        this.stageToExecute = i.stageToExecute;
	this.presentStage = i.presentStage;
	this.stalled = i.stalled;
	this.stallingInstructionId = i.stallingInstructionId;
	this.stallingRegister = i.stallingRegister;
	this.forwarded =i.forwarded;
	this.address=i.address;
	this.forwardedFromInstructionId = i.forwardedFromInstructionId;
	this.forwardedFromInstructionStage = i.forwardedFromInstructionStage;
	this.display = i.display;
	this.id = i.id;
    }
    
    public Instruction(int s2ex, int ps, boolean stall, int stallInstId, int stallReg, boolean forw, int forwfromInstId, int forwStage, String disp, int i){
        stageToExecute = s2ex;
	presentStage = ps;
	stalled = stall;
	stallingInstructionId = stallInstId;
	stallingRegister = stallReg;
	forwarded =forw;
	forwardedFromInstructionId = forwfromInstId;
	forwardedFromInstructionStage = forwStage;
	display = disp;
	id = i;
    }
    
    void init(){
        stageToExecute = 1;
        presentStage = 0;
        stalled = false;
        display = "";
    }
    
    boolean execute(int pc){
        return false;
    }
    
    void unstall(int instructionId){
        return;
    }
    
    /*#######################################################################
    virtual Instruction * clone(); kya karein iska ?
    #######################################################################*/
    
    // eigth stage
    int stageToExecute;
    int presentStage;
    boolean stalled;
    int stallingInstructionId;
    int stallingRegister;
    boolean forwarded;
    int forwardedFromInstructionId;
    int forwardedFromInstructionStage;
    String display;
    int address;
    int id;


    int getStageToExecute(){
        return this.stageToExecute;
    }
    
    int getPresentStage(){
        return this.presentStage;
    }
    
    boolean getStalled(){
        return this.stalled;
    }
    
    int getStallingInstructionId(){
        return this.stallingInstructionId;
    }
    
    int getStallingRegister(){
        return this.stallingRegister;
    }
    
    boolean getForwarded(){
        return this.forwarded;
    }
    
    int getForwardedFromInstructionId(){
        return this.forwardedFromInstructionId;
    }
    
    int getForwardedFromInstructionStage(){
        return this.forwardedFromInstructionStage;
    }
    
    String getDisplayString(){
        return display;
    }
    
    int getId(){
        return this.id;
    }
    
}
