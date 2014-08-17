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
class Stage {

    //Stage Number (eight stage)
    int number;

    // Id of the instruction being executed. -1 if no instruction
    int instructionId;

    // whether stage if free or not
    boolean isFree(){
       	if(instructionId==-1)
		return true;
	return false;
    }

    
    void setInstruction(int instructionId){
        /*if(number == 5)
	//cout<<instructionId<<endl;*/
	this.instructionId = instructionId;
    }
    
    // Constructors
    public Stage(int number){
        this.number= number;
	this.instructionId = -1;
    }

    public Stage(int number, int Id){
        this.number = number;
	this.instructionId = Id;
    }

    void setFree(){
        instructionId = -1;
    }
}
