/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mips;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.BoundedRangeModel;
import javax.swing.JPanel;

/**
 *
 * @author rahul
 */
public class StageJPanel extends JPanel{
    private int instHeight;
    private int instWidth;
    private int maxIdDrawn;
    private int offsetFromTop;
    private int leftPaneShift;
    private Font guiFont;
    ArrayList<ArrayList<Instruction>> allInstructions;
    private HashMap<Integer, Pair<String, Color>> stagePropertiesMap;
    
    public StageJPanel(){
        allInstructions = new ArrayList<ArrayList<Instruction>>();
        instHeight = 20;
        instWidth = 60;
        leftPaneShift = 20;
        guiFont = SystemVars.guiFont;
        stagePropertiesMap = new HashMap<Integer, Pair<String, Color>>();
        offsetFromTop = SystemVars.offsetFromTop;
        buildStagePropertiesMap();
    }
    
    public void buildStagePropertiesMap(){
        int totalDifferentStages = SystemVars.getTotalDifferentStages();
        HashMap<SystemVars.stageType, Color> colorMap = SystemVars.stageColorMap;
        HashMap<Integer, String> nameMap = SystemVars.stageNameMap;
        for(int i = 1; i<=totalDifferentStages; i++){
            stagePropertiesMap.put(i,new Pair<String, Color>(nameMap.get(i), colorMap.get(SystemVars.baseStageMap.get(i))));
        }
    }
    
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        for(int column = 0; column<allInstructions.size(); column++){
            drawHeader(column, g);
            for(int row = 0 ; row<allInstructions.get(column).size(); row++){
                drawInstruction(allInstructions.get(column).get(row), column, row, g);
            }
        }
    }
    
    public void drawFinishedQueue(ArrayList<Instruction> v, int frameWidth, int frameHeight){
        int column = allInstructions.size();
        drawHeader(column, null);
        allInstructions.add(v);
        for(int row = 0 ; row<v.size(); row++){
            drawInstruction(v.get(row),column, row, null);
        }
    }
    
    public void drawInstruction(Instruction instruction, int column, int row, Graphics g){
        int presentStage = instruction.getPresentStage();
        if(presentStage == 0) return;
        if(g == null) g = this.getGraphics();
        Pair<String, Color> stageProperties = stagePropertiesMap.get(presentStage);
        g.setColor(stageProperties.second);
        g.fillRect(leftPaneShift + (instWidth+2)*column, offsetFromTop + (instHeight+20)*instruction.getId(), instWidth, instHeight);
        g.setColor(Color.black);
        g.setFont(guiFont);
        String label = stageProperties.first;
        FontMetrics fm = g.getFontMetrics();
        int stringWidth = fm.getStringBounds(label, g).getBounds().width;
        g.drawString(label, leftPaneShift+(instWidth+2)*column + (instWidth-stringWidth)/2, offsetFromTop + 15 + (instHeight+20)*instruction.getId());
    }
    
    public void doSomething(){
        this.setPreferredSize(new Dimension(1000,1000));
        this.revalidate();
        repaint();
    }
    
    public void drawHeader(int column, Graphics g){
        if(g == null) g = this.getGraphics();
        int[] xPoints = {
            leftPaneShift + (instWidth+2)*column,
            leftPaneShift + (instWidth+2)*column,
            leftPaneShift + (instWidth+2)*column + instWidth,
            leftPaneShift + (instWidth+2)*column + instWidth
        };
        int[] yPoints = {
            offsetFromTop - 35,
            offsetFromTop - 25,
            offsetFromTop - 25,
            offsetFromTop - 35
        };
        g.drawPolyline(xPoints, yPoints, 4);
        g.setFont(guiFont);
        String label = ""+column;
        FontMetrics fm = g.getFontMetrics();
        int stringWidth = fm.getStringBounds(label, g).getBounds().width;
        g.drawString(label,leftPaneShift + (instWidth+2)*column + (instWidth-stringWidth)/2 ,offsetFromTop - 30);
    }
   
}
