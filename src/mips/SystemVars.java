/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mips;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashMap;
import java.util.Map;

/**
 *   <p>Stores various system variables which remain constant throughout the simulation</p>
 *   @author anmol
 */
public class SystemVars {
    
    /**
     *  stores whether the program finished execution or not
     */
    public static boolean programOver;
    
    /**
     *  Number of stages of execution of an instruction 
     */
    static int totalStages = 17;
    
    /**
     *  Rate of Memory Misses in Percentage
     *  Default value is -1(No memory miss)
     */
    public static int memoryMissRate = 0;
    
    /**
     *  No of cycles to stall for in case of a memory miss
     */
    public static int memoryMissStalls = 2;
    
    /**
     *  Total stalls due to memory misses
     */
    public static int totalMemoryStalls = 0;
    
    /**
     *  Total number of registers that can be used in system 
     */
    static int totalRegisters = 32;
    
    /**
     *  List of all the registers that can be used
     */
    public static ArrayList <Register> registers = new ArrayList<Register>(){
        {
            for (int i = 0; i < totalRegisters; i++) {
                add(new Register(i,0));
            }            
        }
    };
    
    
    /**
     *  List of all the stages of execution of an instruction
     */
    public static ArrayList <Stage> stages = new ArrayList<Stage>(){
        {
            
            for (int i = 0; i < totalStages; i++) {
                add(new Stage(0));
            } 
        }
    };
    
    /**
     *  Points to the next instruction to be executed
     */
    public static int programCounter = 0; // holds the index of the instruction which will be inserted 
    
    /**
     *  stores whether forwadding is enabled or not
     */
    public static boolean forwardingEnabled = false;
    
    /**
     *  stores whether fast branching is enabled or not
     */
    public static boolean fastBranching = false;
    
    /**
     *  Memory which will be used by the system for simulation
     */
    public static Memory memory = new Memory();
    
    /**
     *  Map of the labels in the program with the corresponding array index constructed while parsing
     */
    public static Map<String, Integer> labelMap = new HashMap<>();
    
    /**
     *  Stores the number of 
     */
    public static int rStalls = 0;
    
    /**
     *  Stores the number of 
     */
    public static int sStalls = 0;
    
    /**
     *  Number of history bits to be used while branch prediction
     */
    
    public static int historyBits = 1;
    
    /**
     *  Stores the size of the branch history array
     */
    public static int historySize = 1024;
    
    /**
     *  Count of the number of clock cycles
     */
    public static int clockCycle = 0;
    
    /**
     *  Stores whether branch changed or not
     */
    public static boolean branchChanged = false;
    
    public static HashMap<Integer, Integer> fallbackInstructionMap = new HashMap<Integer, Integer>();
    
    /**
     *  Stores the default branch strategy to be used for branching instructions
     */
    public static branchStrategyType branchStrategy = branchStrategyType.NOTTAKEN;
    
    public static enum branchStrategyType {TAKEN, NOTTAKEN, HISTORY};  
    
   
    public static ArrayList<BitSet> branchHistory = new ArrayList<BitSet>(){
        {
            for (int i = 0; i < historySize; i++) {
                add(new BitSet(historyBits));
            }
        }
    }; 
    // generalization variables
    // default depths for 8 stage pipelines
    public static enum stageType {IF, ID, EX, MULT, DIV, MEM, WB, DUMMY};
    public static int[] stageDepths = {2,1,1,4,4,3,1};
    static HashMap<Integer, stageType> stageMap = new HashMap();
    public static HashMap<stageType, Integer> reverseStageTypeMap= new HashMap();
    public static HashMap<Integer, stageType> baseStageMap = new HashMap();
    public static HashMap<Integer, String> stageNameMap = new HashMap();
    public static int offsetFromTop = 80;
    public static int sideBarWidth = 180;
    public static Font guiFont = new Font("Arial", Font.BOLD, 11);
    
    /**
     *
     * @param index
     * @return
     */
    public static stageType getStageType(Integer index){
        if(stageMap.containsKey(index)) return stageMap.get(index);
        // stageMap not yet calculated. do it now
        int[] cumArray = new int[7];
        cumArray[0] = stageDepths[0];
        for(int i = 1; i < 7; i++){
            cumArray[i] = stageDepths[i] + cumArray[i-1];
        }
        int x = 0;
        for(stageType sType: stageType.values()){
            if (x < 7) {
                stageMap.put(cumArray[x], sType);
                x++;
            }
        }
        for(int i = 1; i<totalStages; i++){
            if(!stageMap.containsKey(i)) stageMap.put(i, stageType.DUMMY);
        }  
        return stageMap.get(index);
    }
    
    /**
     *
     */
    public static void buildBaseStageMap(){
        int x = 0;
        int j = 1;
        for(stageType sType: stageType.values()){
            if(sType == stageType.DUMMY) continue;
            for(int i = 0; i<stageDepths[x]; i++){
                baseStageMap.put(j, sType);
                j++;
            }
            x++;
        }
    }
    
    /**
     *
     */
    public static void buildReverseStageTypeMap(){
        int index = 0; 
        int sum = 0;
        for(stageType sType: stageType.values()){
            if(sType == stageType.DUMMY) continue;
            sum += stageDepths[index];
            reverseStageTypeMap.put(sType, sum);
            index++;
        }
    }
    
    /**
     *
     */
    public static void buildStageNameMap(){
        int x = 0;
        int j = 1;
        String stageName;
        for(stageType sType: stageType.values()){
            if(sType == stageType.DUMMY) continue;
            for(int i = 1; i<=stageDepths[x]; i++){
                stageName = sType.toString();
                if(stageDepths[x] != 1) stageName += i;
                stageNameMap.put(j, stageName);
                j++;
            }
            x++;
        }
    }
    
    /**
     *
     * @return
     */
    public static int getTotalDifferentStages(){
        int sum = 0;
        for(int i = 0; i<stageDepths.length; i++){
            sum += stageDepths[i];
        }
        return sum;
    }
    
    // gui vars
    public static HashMap<stageType, Color> stageColorMap = new HashMap<stageType, Color>();
    
    /**
     *
     */
    public static void resetSystem(){
        fallbackInstructionMap = new HashMap<Integer, Integer>();
        programOver = false;
        programCounter = 0;
        totalMemoryStalls = 0;
        memory = new Memory();
        labelMap = new HashMap<>();
        rStalls = 0;
        sStalls = 0;
        clockCycle = 0;
        totalStages = 1;
        for(int i = 0 ; i<stageDepths.length; i++){
            totalStages += stageDepths[i];
        }
        registers = new ArrayList<Register>(){
            {
                for (int i = 0; i < 32; i++) {
                    add(new Register(i,0));
                }            
            }
        };
        stages = new ArrayList<Stage>(){
            {
                for (int i = 0; i < totalStages; i++) {
                    add(new Stage(0));
                } 
            }
        };
        branchHistory = new ArrayList<BitSet>(){
            {
                for (int i = 0; i < historySize; i++) {
                    add(new BitSet(historyBits));
                }
            }
        }; 
        stageMap.clear();
        buildReverseStageTypeMap();
        buildBaseStageMap();
        buildStageNameMap();
    }
    
    // constructor

    /**
     *
     */
    public SystemVars(){
        resetSystem();
        stageColorMap.put(stageType.IF, new Color((float)0.372549,(float)0.619608,(float)0.62745));
        stageColorMap.put(stageType.ID, new Color((float)1,(float)0.498039,(float)0.313725));
        stageColorMap.put(stageType.EX, new Color((float)0,(float)0.78,(float)1));
        stageColorMap.put(stageType.MEM, new Color((float)0.662745,(float)0.662745,(float)0.662745));
        stageColorMap.put(stageType.WB, new Color((float)1,(float)0.647059,(float)0));
        stageColorMap.put(stageType.MULT, new Color((float)0.803922,(float)0.360784,(float)0.360784));
        stageColorMap.put(stageType.DIV, new Color((float)0.737255,(float)0.560784,(float)0.560784));
    }
}
