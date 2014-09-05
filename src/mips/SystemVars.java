/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mips;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author vedratn
 */
public class SystemVars {
    static boolean programOver;
    static ArrayList <Register> registers = new ArrayList<Register>(){
        {
            add(new Register(0,0));
            add(new Register(0,0));
            add(new Register(0,0));
            add(new Register(0,0));
            add(new Register(0,0));
            add(new Register(0,0));
            add(new Register(0,0));
            add(new Register(0,0));
            add(new Register(0,0));
            add(new Register(0,0));
            add(new Register(0,0));
            add(new Register(0,0));
            add(new Register(0,0));
            add(new Register(0,0));
            add(new Register(0,0));
            add(new Register(0,0));
            add(new Register(0,0));
            add(new Register(0,0));
            add(new Register(0,0));
            add(new Register(0,0));
            add(new Register(0,0));
            add(new Register(0,0));
            add(new Register(0,0));
            add(new Register(0,0));
            add(new Register(0,0));
            add(new Register(0,0));
            add(new Register(0,0));
            add(new Register(0,0));
            add(new Register(0,0));
            add(new Register(0,0));
            add(new Register(0,0));
            add(new Register(0,0));
        }
    };
    static ArrayList <Stage> stages = new ArrayList<Stage>(){
        {
            add(new Stage(0));
            add(new Stage(0));
            add(new Stage(0));
            add(new Stage(0));
            add(new Stage(0));
            add(new Stage(0));
            add(new Stage(0));
            add(new Stage(0));
            add(new Stage(0));
            add(new Stage(0));
            add(new Stage(0));
        }
    };
    
    
    static int programCounter = 1; // holds the index of the instruction which will be inserted 
    static boolean forwardingEnabled = true;
    static int multSubStages = 4;
    static boolean fastBranching = false;
    static Memory memory = new Memory();
    static Map<String, Integer> labelMap = new HashMap<>();
    static int rStalls = 0;
    static int sStalls = 0;
    static int clockCycle = 0;
    static boolean branchChanged = false;
    // generalization variables
    // default depths for 8 stage pipelines
    static int totalDepth = 8;
    static enum stageType{IF, ID, EX, MULT, DIV, MEM, WB, DUMMY};
    static int[] stageDepths = {2,1,1,1,1,3,1};
    static HashMap<Integer, stageType> stageMap = new HashMap();
    
    public static stageType getStageType(Integer index){
        if(stageMap.containsKey(index)) return stageMap.get(index);
        // stageMap not yet calculated. do it now
        int[] cumArray = new int[7];
        cumArray[0] = stageDepths[0];
        for(int i = 1; i<7; i++){
            cumArray[i] = stageDepths[i] + cumArray[i-1];
        }
        int x = 0;
        for(stageType sType: stageType.values()){
            stageMap.put(cumArray[x], sType);
            x++;
        }
        for(int i = 1; i<=totalDepth+2; i++){
            if(!stageMap.containsKey(i)) stageMap.put(i, stageType.DUMMY);
        }  
        return stageMap.get(index);
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
}
