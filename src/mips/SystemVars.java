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
}
