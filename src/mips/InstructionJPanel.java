package mips;


import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
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
    ArrayList<Pair<Integer, String>> instStrings;
    
    
    public InstructionJPanel(){
        super();
        instStrings = new ArrayList<Pair<Integer, String>>();
        instHeight = 20;
        instWidth = 60;
        offsetFromTop = SystemVars.offsetFromTop;
        guiFont = SystemVars.guiFont;
    }
    
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        drawHeaders(g);
        g.setFont(guiFont);
        for(int i=0 ; i<instStrings.size(); i++){
            g.drawString(instStrings.get(i).second,10,offsetFromTop + 15+(instHeight+20)*instStrings.get(i).first);
        }
    }
    
    public void drawFinishedQueue(ArrayList<Instruction> v, int frameHeight){
        Graphics g = this.getGraphics();
        for(int i = 0 ; i<v.size(); i++){
            if(v.get(i).getPresentStage() == 1){
                handleNewInstruction(v.get(i), frameHeight);
            }
        }
    }
    
    public void handleNewInstruction(Instruction inst, int frameHeight){
        int totalInstructions = instStrings.size();
        instStrings.add(new Pair<Integer, String>(inst.getId(), inst.getDisplayString()));
        totalInstructions++;
        // draw only new one or repaint
        frameHeight -= 60;
        if(frameHeight < 20*totalInstructions){
            this.scrollRectToVisible(new Rectangle(0, 20*totalInstructions + 30 - frameHeight, this.getWidth(), frameHeight));
            this.setPreferredSize(new Dimension(this.getWidth(), 20*totalInstructions + 30));
            this.revalidate();
            repaint();
            return;
        }
        Graphics g = this.getGraphics();
        g.setFont(guiFont);
        g.drawString(inst.getDisplayString(),10,offsetFromTop + 15+(instHeight+20)*inst.getId());        
    }
    
    public void drawHeaders(Graphics g){
        if(g == null) g = this.getGraphics();
        g.setFont(guiFont);
        g.drawString("CLOCK TICKS",15,offsetFromTop - 30);
        g.drawLine(10,offsetFromTop - 25,120,offsetFromTop - 25);
        g.drawString("INSTRUCTIONS",15,offsetFromTop - 10);
    }
}
