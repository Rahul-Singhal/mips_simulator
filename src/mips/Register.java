/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mips;

import java.util.*;

/**
 *   <p>Implements Register class for the various registers used in MIPS assembly code</p>
 *   @author anmol
 */

public class Register {
    
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
     *  Returns the id of the register corresponding to its name
     *  @param reg name of the register
     *  @return Integer
     */
    public static Integer registerToInteger(String reg) {
        return registerMap.get(reg);
    }
    
    /**
     *  Default constructor for register class
     *  Sets the initial value of register with id = id as value.
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
    public int value;
    
    /**
     *  Stores the list of all the {@link mips.Instruction} IDs blocking this register
     */
    public ArrayList<Integer> blockingInstructions ;
    
     /**
     *  ID of {@link mips.Instruction} which has stalled the register
     *  else stores which instruction wrote into the register last
     */
    public int instructionId; 
 
    /**
     * ID of {@link mips.Instruction} that forwarded value of this Register
     */
    public int lastForwarder;
    
    /**
     * Clock cycle time when the value of this Register was last forwarded.
     */
    public int lastForwarderTime;
    
    /**
     * Stores the list of all the {@link mips.Instruction} IDs that are forwarding value from this register 
     */
    public ArrayList<Integer> forwardingInstructions = new ArrayList<>();
        
//    Unused variable
//    int instructionStage; 
    
    /**
     *  true: if no instructions are blocking this register
     *  false: of some instructions are blocking this register
     *  @return boolean
     */
    public boolean isValid(){
        if(blockingInstructions.isEmpty()){
		return true;
	}
	return false;
    }
    
    /**
     * Adds {@link mips.Instruction} that is forwarding value in this Register
     * @param instructionId id of the {@link mips.Instruction} 
     * @param time clock cycle time
     */
    public void forwardIt(int instructionId, int time){
        lastForwarderTime = time;
	lastForwarder = instructionId;
        forwardingInstructions.add(instructionId);
    }
    
    /**
     * Function to remove all occurrences of a value from an ArrayList
     * @param a Arraylist to remove from
     * @param val value to be removed
     */
    public static void removeAll(ArrayList<Integer> a, int val){
        for(int i = 0 ;i<a.size(); i++){
           if(a.get(i) == val) {
               a.remove(i);
               i--;
           }
        }
    }
    
    /**
     * Remove all occurrences of a forwarding instruction for the Register
     * @param instructionId ID of instruction to remove
     */
    public void unforwardIt(int instructionId){
        removeAll(forwardingInstructions, instructionId);
    }
    
    /**
     *  true: if there are some instructions are to be forwadded
     *  false: no instructions need to be forwadded
     *  @return boolean
     */
    public boolean isForwarded(){
        if(forwardingInstructions.isEmpty()){
		return false;
        }
	return true;
    }
    
    /**
     *  Stalls the register corresponding to the instruction id which is writing into it.
     * Is called at every stage of the instruction which needs to writes to a Register 
     * @param instructionId Instruction Id of the instruction
     */
    public void stallRegister(int instructionId){
        this.instructionId = instructionId;
        blockingInstructions.add(instructionId);
    }
    
    /**
     *  Un-Stalls the register corresponding to the instruction id and stores the value in the register
     * @param value value to be stored in the register
     * @param instructionId Instruction Id of the instruction
     */
    public void unstallRegister(int value, int instructionId){
        removeAll(blockingInstructions, instructionId);
	this.value = value;
    }
    
    /**
     *  Un-Stalls the register corresponding to the instruction id
     * @param instructionId Instruction Id of the instruction
     */
    public void unstall(int instructionId){
        removeAll(blockingInstructions, instructionId);
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
}
