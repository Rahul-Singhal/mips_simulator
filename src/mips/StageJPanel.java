/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mips;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author rahul
 */
public class StageJPanel extends JPanel{
    private int instHeight;
    private int instWidth;
    private int maxIdDrawn;
    ArrayList<ArrayList<Instruction>> allInstructions;
    private int tempID;
    
    public StageJPanel(){
        allInstructions = new ArrayList<ArrayList<Instruction>>();
        instHeight = 20;
        instWidth = 50;
    }
    
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        for(int column = 0; column<allInstructions.size(); column++){
            for(int row = 0 ; row<allInstructions.get(column).size(); row++){
                drawInstruction(allInstructions.get(column).get(row), column, row, g);
            }
        }
    }
    
    public void drawFinishedQueue(ArrayList<Instruction> v){
        int column = allInstructions.size();
        allInstructions.add(v);
        for(int row = 0 ; row<v.size(); row++){
            drawInstruction(v.get(row),column, row, null);
        }
    }
    
    public void drawInstruction(Instruction instruction, int column, int row, Graphics g){
        int presentStage = instruction.getPresentStage();
        if(presentStage == 0) return;
        if(g == null) g = this.getGraphics();
        g.setColor(Color.red);
        g.fillRect((instWidth+2)*column, (instHeight+20)*instruction.getId(), instWidth, instHeight);
    }
}
