package mips;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JPanel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author rahul
 */
public class InstructionJPanel extends JPanel{
    private int instHeight;
    private int instWidth;
    private int offsetFromTop;
    private Font guiFont;
    private int preferredHeight;
    ArrayList<Pair<Integer, String>> instStrings;
    
    
    public InstructionJPanel(int height){
        super();
        instStrings = new ArrayList<Pair<Integer, String>>();
        instHeight = 20;
        instWidth = 60;
        offsetFromTop = SystemVars.offsetFromTop;
        guiFont = SystemVars.guiFont;
        preferredHeight = 0;
        this.setPreferredSize(new Dimension(140,height));
    }
    
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        drawHeaders(g);
        g.setFont(guiFont);
        for(int i=0 ; i<instStrings.size(); i++){
            g.drawString(instStrings.get(i).second,10,offsetFromTop + 15+(instHeight+20)*instStrings.get(i).first);
        }
    }
    
    public int drawFinishedQueue(ArrayList<Instruction> v, int frameHeight){
        Graphics g = this.getGraphics();
        for(int i = 0 ; i<v.size(); i++){
            if(v.get(i).getPresentStage() == 1){
                handleNewInstruction(v.get(i), frameHeight);
            }
        }
        return preferredHeight;
    }
    
    public void handleNewInstruction(Instruction inst, int frameHeight){
        int totalInstructions = instStrings.size();
        instStrings.add(new Pair<Integer, String>(inst.getId(), inst.getDisplayString()));
        totalInstructions++;
        // draw only new one or repaint
        if(preferredHeight == 0) preferredHeight = this.getHeight();
        if(preferredHeight < (instHeight+20)*totalInstructions + 80){
            preferredHeight += 150;
        }
        Graphics g = this.getGraphics();
        g.setFont(guiFont);
        g.drawString(inst.getDisplayString(),10,offsetFromTop + 15+(instHeight+20)*inst.getId());        
    }
    
    public void drawHeaders(Graphics g){
        if(g == null) g = this.getGraphics();
        g.setFont(guiFont);
        g.setColor(new Color(193, 193, 193));
        g.fillRect(0, 0, 140, offsetFromTop-10);
        g.setColor(Color.black);
        g.drawString("CLOCK TICKS",20,offsetFromTop - 50);
        g.drawLine(10,offsetFromTop - 45,120,offsetFromTop - 45);
        g.drawString("INSTRUCTIONS",20,offsetFromTop - 30);
    }
}
