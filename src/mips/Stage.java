/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mips;

/**
 *   <p>Implements Stage class for the various stages of the pipeline</p>
 *   <p>Includes all the functions, members and variables useful for every stage</p>
 *   @author anmol
 */
class Stage {

    /**
     *  Number of stages in the pipeline
     */
    int number;

    /**
     *  Id in {@link mips.Instruction} of the instruction being executed. -1 if no instruction
     */
    int instructionId;
    
    /**
     *  true: if the stage is free
     *  false: if the stage is not free
     *  @return boolean
     */
    boolean isFree(){
       	if (instructionId == -1)
            return true;
	return false;
    }

    /**
     *  Assigns the id of the instruction being executed in this stage
     *  @param instructionId Id of the instruction
     */
    void setInstruction(int instructionId){
        /*if(number == 5)
	//cout<<instructionId<<endl;*/
	this.instructionId = instructionId;
    }
    
    /**
     *  Default constructor of the stage
     *  @param number Stage Number
     */
    public Stage(int number){
        this.number= number;
	this.instructionId = -1;
    }
    
    /**
     *  Another Constructor, assigns an instruction and stage number to the stage
     *  @param number Stage Number
     *  @param Id Instruction ID
     */
    public Stage(int number, int Id){
        this.number = number;
	this.instructionId = Id;
    }
    
    /**
     *  Frees the stage by changing instruction id to -1
     */
    void setFree(){
        instructionId = -1;
    }
}
