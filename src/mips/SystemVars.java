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
 *
 * @author vedratn
 */
public class SystemVars {
    static boolean programOver;
    static int totalStages = 11;
    static int totalRegisters = 32;
    static ArrayList <Register> registers = new ArrayList<Register>(){
        {
            for (int i = 0; i < totalRegisters; i++) {
                add(new Register(i,0));
            }            
        }
    };
    static ArrayList <Stage> stages = new ArrayList<Stage>(){
        {
            
            for (int i = 0; i < totalStages; i++) {
                add(new Stage(0));
            } 
        }
    };
    
    static int programCounter = 1; // holds the index of the instruction which will be inserted 
    static boolean forwardingEnabled = true;
    static int multSubStages = 4;
    static int divSubStages = 4;
    static boolean fastBranching = false;
    static Memory memory = new Memory();
    static Map<String, Integer> labelMap = new HashMap<>();
    static int rStalls = 0;
    static int sStalls = 0;
    static int historyBits = 1;
    static int historySize = 1024;
    static int clockCycle = 0;
    static boolean branchChanged = false;
    static branchStrategyType branchStrategy = branchStrategyType.TAKEN;
    static enum branchStrategyType {TAKEN, NOTTAKEN, HISTORY};  
    static ArrayList<BitSet> branchHistory = new ArrayList<BitSet>(){
        {
            for (int i = 0; i < historySize; i++) {
                add(new BitSet(historyBits));
            }
        }
    }; 
    // generalization variables
    // default depths for 8 stage pipelines
    static int totalDepth = 8;
    static enum stageType {IF, ID, EX, MULT, DIV, MEM, WB, DUMMY};
    static int[] stageDepths = {2,1,1,1,1,3,1};
    static HashMap<Integer, stageType> stageMap = new HashMap();
    static HashMap<stageType, Integer> reverseStageTypeMap= new HashMap();
    static HashMap<Integer, stageType> baseStageMap = new HashMap();
    static HashMap<Integer, String> stageNameMap = new HashMap();
    static int offsetFromTop = 80;
    static Font guiFont = new Font("Georgia", Font.BOLD, 11);
    
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
        for(int i = 1; i<=totalDepth+2; i++){
            if(!stageMap.containsKey(i)) stageMap.put(i, stageType.DUMMY);
        }  
        return stageMap.get(index);
    }
    
    public void buildBaseStageMap(){
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
    
    public void buildStageNameMap(){
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
    
    public static void resetPipelineDepth(int[] depths){
        // depths is an array of length 5
        System.arraycopy(depths, 0, stageDepths, 0, 3);
        // mult and div
        depths[3] = 1;
        depths[4] = 1;
        for(int i = 5; i<7; i++) stageDepths[i] = depths[i-2];
        stageMap.clear();
    }
    
    public static int getTotalDifferentStages(){
        int sum = 0;
        for(int i = 0; i<stageDepths.length; i++){
            sum += stageDepths[i];
        }
        return sum;
    }
    
    // gui vars
    static HashMap<stageType, Color> stageColorMap = new HashMap<stageType, Color>();
    
    // constructor
    public SystemVars(){
        buildReverseStageTypeMap();
        buildBaseStageMap();
        buildStageNameMap();
        stageColorMap.put(stageType.IF, new Color((float)0.372549,(float)0.619608,(float)0.62745));
        stageColorMap.put(stageType.ID, new Color((float)1,(float)0.498039,(float)0.313725));
        stageColorMap.put(stageType.EX, new Color((float)0.662745,(float)0.662745,(float)0.662745));
        stageColorMap.put(stageType.MEM, new Color((float)0,(float)0.74902,(float)1));
        stageColorMap.put(stageType.WB, new Color((float)1,(float)0.647059,(float)0));
        stageColorMap.put(stageType.MULT, new Color((float)0.803922,(float)0.360784,(float)0.360784));
        stageColorMap.put(stageType.DIV, new Color((float)0.737255,(float)0.560784,(float)0.560784));
    }
}
