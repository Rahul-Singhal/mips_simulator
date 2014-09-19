/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mips;

import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.antlr.v4.runtime.*;

/**
 *
 * @author rahul
 */
/*


CURRENT STAGES:
0: stage before any stage(completely abstract)
1: IF1
2: IF2
3: ID
4: EX : \
5: MULT : - - > these three are parallel(independent). Order between them doesn't matter
6: DIV : /
7: MEM1
8: MEM2
9: MEM3
10: WB
Because of this the next stage for each instruction at any stage wouldn't just be currentStage++
rather it would depend on the instruction as well as which stage is it in right now
*/

public class Program extends SystemVars{

    private int instrId;
    private ArrayList <ArrayList <Instruction>> sepInstructions;
    private int prevPc;
    private int nextPc;
    private Boolean flush;
    private int idStage;
    //  Parser parser;
    ArrayList <Instruction> code;
    ArrayList <Instruction> codeSnippet;
    ArrayList <Instruction> currInstructions;


    public Program(String filename){
        try {
            // Invoke parser
            ANTLRInputStream input = new ANTLRFileStream(filename);
            MipsLexer lexer = new MipsLexer(input);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            MipsParser parser = new MipsParser(tokens);
            RuleContext tree = parser.prog();
     
            parser.checkLabels();
            memory = parser.getMemory();
            codeSnippet = parser.getInstructions();
            labelMap = parser.getLabelMap();
            
            System.out.println("Total Instructions: " + codeSnippet.size());
            Iterator it = labelMap.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pairs = (Map.Entry)it.next();
                System.out.println("Label " + pairs.getKey() + ": instruction index = " + pairs.getValue());
            }
            
            // TODO: fetch labelMap, and execute instructions accordingly
            code = new ArrayList <Instruction>();
            int addr = 0;
            for(Instruction instruction : codeSnippet){
                Instruction clonedInstruction = instruction.clone();
                clonedInstruction.id = 0;
                clonedInstruction.presentStage = 0;
                clonedInstruction.stageToExecute = 1;
                clonedInstruction.address = addr;
                code.add(clonedInstruction);
                addr++;
            }
            sepInstructions = new ArrayList <ArrayList <Instruction>>(totalStages);
            for(int i = 0; i < totalStages; i++){
                sepInstructions.add(new ArrayList<Instruction>());
            }
        } catch (IOException ex) {
            Logger.getLogger(Program.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }
    
    void init(){
        // setting the stages
        for (int i = 0; i < totalStages; i++) {
            stages.get(i).number = i;
        }
        // initialising the registers once and for all
        for (int i = 0; i < totalRegisters; i++) {
            if (i == 29) {
//                registers.get(i).id = i;
                registers.get(i).value = 10485756;
            } else {
//                registers.get(i).id = i;
                registers.get(i).value = 0;
            }
        }
        
        programCounter = 0;
        instrId = 0;
        clockCycle = 0;
        if (code.isEmpty()) {
            System.out.println("Nothing to simulate!");
            System.exit(0);
        }
        code.get(programCounter).id = instrId;
        code.get(programCounter).presentStage = 0;
        code.get(programCounter).stageToExecute = 1;
        
        currInstructions = new ArrayList<Instruction>();
        currInstructions.add(code.get(programCounter));
        idStage = SystemVars.reverseStageTypeMap.get(SystemVars.stageType.ID);
    }
    
    void reset(){
        code.clear();
        this.init();
    }
    

    ArrayList <Instruction> execute(){
        clockCycle++;
        flush = false;
        
        //STABLE SORT
        for (Instruction instruction : currInstructions) {
            sepInstructions.get(instruction.stageToExecute).add(instruction);
        }
        
        // Run the code for one clock cycle
        prevPc = programCounter;
        for (int i = totalStages - 1; i >= 1; i--){
            for (int j = 0; j < sepInstructions.get(i).size(); j++){
                branchChanged = false;
                sepInstructions.get(i).get(j).execute(programCounter);
                if (branchChanged) {
                    /*It means that some branch change instruction has been executed*/
                    if (programCounter != sepInstructions.get(i).get(j).address){
                        flush = true;
                    }
                }
            }
        }
        // store the processed instructions in a separate arrayList to be used 
        // to draw graphics
        ArrayList<Instruction> returnInstructions = new ArrayList<>();
        for(Instruction instruction : currInstructions){
            returnInstructions.add(instruction.clone());
        }
        nextPc = programCounter;
//        System.out.println("The value of dest pc is" + flush + "and prog. count is " + programCounter);
        if (flush) {
            /*setting stages free*/
            stages.get(0).setFree();
            stages.get(1).setFree();
            stages.get(2).setFree();
            stages.get(3).setFree();

            /*complications!
             if predicate is calculated in EX then there is a chance
             that some register was stalled due to the next instruction in the ID stage
             we need to set that register free*/
            /*solution
             The only instruction which can do this is the next instruction(addr), so we need
             to unstall its destination register, this destination register's name could
             vary depending on the instruction, so we'll have to add this functionality
             of unstalling in the individual classes where the dest reg is known
             */
            Instruction instruction;
            int i = 0;
            for(i = 0; i<currInstructions.size(); i++){
                instruction = currInstructions.get(i);
                if(instruction.presentStage == idStage){
//                    if(!fastBranching){
//                        instruction.unstall();
//                    } 
//                    else i++;
                    i++;
                    break;
                }
            }
            /*removing elements after the branch instructions from the queue*/
            while (i<currInstructions.size()) {
                currInstructions.remove(i);
                i++;
            }
        }
        /*removing completed instructions*/
        Instruction instruction;
        for(int i = 0; i < currInstructions.size(); ){
            instruction = currInstructions.get(i);
            if(instruction.stageToExecute == -1){
                currInstructions.remove(i);
            } else {
                i++;
            }
        }
                    
        if (!programOver && stages.get(0).isFree() && programCounter+1< code.size()) {
            programCounter++;
            instrId++;
            instruction = code.get(programCounter).clone();
            instruction.id = instrId;
            instruction.presentStage = 0;
            instruction.stageToExecute = 1;
            currInstructions.add(instruction);
        }

        // Setting the stages free
        for(Stage stage : stages) {
            stage.setFree();
        }
        // clearing the sorted buckets for the next cycle
        for (int i = 1; i < totalStages; i++) {
            sepInstructions.get(i).clear();
        }
        return returnInstructions;
    }

    void executeAll(){
        while(!currInstructions.isEmpty()){
            this.execute();
        }
    }
};
