/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//Status index
/*
	0: 	ready to be read ( no forwarding, value stored in register )
	1:	waiting to be written
	2:	forwarded value available
*/

package mips;

import java.awt.List;
import java.util.ArrayList;

/**
 *
 * @author vedratn
 */
class Register {
    // Constructor, sets the initial value of register with id = id as value.
    
    public Register(int id, int value){
        this.id = id;
	this.value = value;
	this.blockingInstructions = new ArrayList<>();
	this.lastForwarder = -1;
    }

    static int id;

    int value; // stores the value in register
    // bool valid; // true if the value written is valid
    ArrayList<Integer> blockingInstructions ;
    int instructionId; // if value is not valid, it stores which instruction has stalled the register, 
                                                                             //else stores which instruction wrote into the register last
    int instructionStage; // which instruction stage wrote into the register, if not WB (stage 8) then we can get insights about forwarding
    boolean isValid(){
        if(blockingInstructions.isEmpty()){
		// cout<<"validity for "<<id<<" return true"<<endl;
		return true;
	}
	// cout<<"validity for "<<id<<" return false"<<endl;
	return false;
    }
    

    int lastForwarder;
    int lastForwarderTime;
    ArrayList<Integer> forwardingInstructions = new ArrayList<>();
    void forwardIt(int instructionId, int time){
        lastForwarderTime = time;
	lastForwarder = instructionId;
        forwardingInstructions.add(instructionId);
	//forwardingInstructions.push_back(instructionId);
    }
    void unforwardIt(int instructionId){
        forwardingInstructions.remove(instructionId);
    }
    boolean isForwarded(){
        if(forwardingInstructions.isEmpty()){
		return false;
        }
	return true;
    }

    //  Should be called at every stage of the instruction which needs to writes to that particular instruction
    void stallRegister(int instructionId){
        this.instructionId = instructionId;
	// cout<<"add "<<instructionId<<" to "<<id<<endl;
	blockingInstructions.add(instructionId);
    }
    
    void unstallRegister(int instructionId, int value){
        // cout<<"remove "<<instructionId<<"  from "<<id<<"after writing value = "<<value<<endl;
	// cout<<"size before "<<blockingInstructions.size()<<endl;
	blockingInstructions.remove(instructionId);
	this.value = value;
	// cout<<"size after "<<blockingInstructions.size()<<endl;
    }
    
    
    /*##################################################################################
    WHY WAS THE FOLLOWING WRITE DEFINITION COMMENTED IN THE FINAL CODE ??????????
    ##################################################################################*/
    
    // 	called to write back at the WB stage, returns true if value could be written, else false
    /*
bool Register::write(int value, int instructionId, int instructionStage){
	//////cout<<value<<":"<<instructionId<<":"<<instructionStage<<endl;
	if (valid) {
		valid = true;
		this->instructionId = instructionId;
		this->instructionStage = instructionStage;
		this->value = value; 
		//////cout<<"valid"<<" "<<valid<<":"<<value<<"return true"<<endl;
		return true;
	}
	else if (instructionId == this->instructionId){
		this->instructionId = instructionId;
		this->instructionStage = instructionStage;
		this->value = value; 
		valid = true;
		//////cout<<"not valid but my own instruction"<<" "<<valid<<":"<<value<<"return true"<<endl;
		return true;
	}
	else {
		//////cout<<"invalid return false"<<endl;
		return false;
	}
}
*/

    /*set valid bit to true , needed when we have to reverse the effect of someinstruction*/
    void unstall(int instructionId){
        // //cout<<instructionId<<" unstalls "<<id<<". size= "<<blockingInstructions.size()<<endl;
	// //cout<<blockingInstructions.front()<<":"<<blockingInstructions.back()<<endl;
	blockingInstructions.remove(instructionId);
	// //cout<<"size= "<<blockingInstructions.size()<<endl;
    }
    //	returns a pair <status, value>
    //pair <bool, int> read();
}
