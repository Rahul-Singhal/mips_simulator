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

import java.util.*;

/**
 *   <p>Implements Register class for the various registers used in MIPS assembly code</p>
 *   @author anmol
 */

class Register {
    // Constructor, sets the initial value of register with id = id as value.
    
    /**
     *  Unique id for each register 
     */
    public int id;
    
    /**
     *  Stores the mapping of a register name with id
     */
    public final static Map<String, Integer> registerMap = new HashMap<String, Integer>();
    
    /**
     *  Stores the mapping of id with a register name
     */
    public final static Map<Integer, String> registerMapInverse = new HashMap<Integer, String>();
    static {
        registerMap.put("$zero", 0);
        registerMap.put("$at", 1);
        registerMap.put("$v0", 2);
        registerMap.put("$v1", 3);
        registerMap.put("$a0", 4);
        registerMap.put("$a1", 5);
        registerMap.put("$a2", 6);
        registerMap.put("$a3", 7);
        registerMap.put("$t0", 8);
        registerMap.put("$t1", 9);
        registerMap.put("$t2", 10);
        registerMap.put("$t3", 11);
        registerMap.put("$t4", 12);
        registerMap.put("$t5", 13);
        registerMap.put("$t6", 14);
        registerMap.put("$t7", 15);
        registerMap.put("$s0", 16);
        registerMap.put("$s1", 17);
        registerMap.put("$s2", 18);
        registerMap.put("$s3", 19);
        registerMap.put("$s4", 20);
        registerMap.put("$s5", 21);
        registerMap.put("$s6", 22);
        registerMap.put("$s7", 23);
        registerMap.put("$t8", 24);
        registerMap.put("$t9", 25);
        registerMap.put("$k0", 26);
        registerMap.put("$k1", 27);
        registerMap.put("$gp", 28);
        registerMap.put("$sp", 29);
        registerMap.put("$fp", 30);
        registerMap.put("$ra", 31);
        for(Map.Entry<String, Integer> entry : registerMap.entrySet()){
            registerMapInverse.put(entry.getValue(), entry.getKey());
        }
    }
    
    /**
     *  Returns the id of the register corresponding to it's name
     *  @param reg name of the register
     *  @return Integer
     */
    public static Integer registerToInteger(String reg) {
        return registerMap.get(reg);
    }
    
    /**
     *  Default constructor for register class
     */
    public Register(int id, int value){
        this.id = id;
	this.value = value;
	this.blockingInstructions = new ArrayList<>();
	this.lastForwarder = -1;
    }
    
    /**
     *  Stores the value in register
     */
    int value;
    
    // bool valid; // true if the value written is valid
    
    /**
     *  Stores the list of all the instruction id's blocking this register
     */
    ArrayList<Integer> blockingInstructions ;
    
     /**
     *  Instruction id in {@link mips.Instruction} which has stalled the register
     *  else stores which instruction wrote into the register last
     */
    int instructionId; 
     
    /**
     *  Number in {@link mips.Stage} stage which wrote into the register
    */
    int instructionStage; 
    
    
    /**
     *  true: if no instructions are blocking this register
     *  false: of some instructions are blocking this register
     *  @return boolean
     */
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
    }
    
    
    public static void removeAll(ArrayList<Integer> a, int val){
        for(int i = 0 ;i<a.size(); i++){
           if(a.get(i) == val) {
               a.remove(i);
               i--;
           }
        }
    }
    
    void unforwardIt(int instructionId){
        removeAll(forwardingInstructions, instructionId);
//        forwardingInstructions.remove(instructionId);
    }
    
    /**
     *  true: if there are some instructions are to be forwadded
     *  false: no instructions need to be forwadded
     *  @return boolean
     */
    boolean isForwarded(){
        if(forwardingInstructions.isEmpty()){
		return false;
        }
	return true;
    }

    //  Should be called at every stage of the instruction which needs to writes to that particular instruction
    
    /**
     *  Stalls the register corresponding to the instruction id which is writing into it.
     *  @param instructionId Instruction Id of the instruction
     */
    void stallRegister(int instructionId){
        this.instructionId = instructionId;
        blockingInstructions.add(instructionId);
    }
    
    /**
     *  Un-Stalls the register corresponding to the instruction id and stores the value in the register
     * @param value value to be stored in the register
     * @param instructionId Instruction Id of the instruction
     */
    void unstallRegister(int value, int instructionId){
        // cout<<"remove "<<instructionId<<"  from "<<id<<"after writing value = "<<value<<endl;
	// cout<<"size before "<<blockingInstructions.size()<<endl;
        removeAll(blockingInstructions, instructionId);
//        blockingInstructions.remove(instructionId);
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
    /**
     *  Un-Stalls the register corresponding to the instruction id
     * @param instructionId Instruction Id of the instruction
     */
    void unstall(int instructionId){
        // //cout<<instructionId<<" unstalls "<<id<<". size= "<<blockingInstructions.size()<<endl;
	// //cout<<blockingInstructions.front()<<":"<<blockingInstructions.back()<<endl;
        removeAll(blockingInstructions, instructionId);
//        blockingInstructions.remove(instructionId);
	// //cout<<"size= "<<blockingInstructions.size()<<endl;
    }
    //	returns a pair <status, value>
    //pair <bool, int> read();
}
