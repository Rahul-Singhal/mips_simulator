package mips;


import java.awt.Dimension;
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
    ArrayList<Pair<Integer, String>> instStrings;
    
    
    public InstructionJPanel(){
        super();
        instStrings = new ArrayList<Pair<Integer, String>>();
        instHeight = 20;
        instWidth = 50;
    }
    
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        for(int i=0 ; i<instStrings.size(); i++){
            g.drawString(instStrings.get(i).second,10,15+(instHeight+20)*instStrings.get(i).first);
        }
    }
    
    public void drawFinishedQueue(ArrayList<Instruction> v){
        Graphics g = this.getGraphics();
        for(int i = 0 ; i<v.size(); i++){
            if(v.get(i).getPresentStage() == 1){
                Instruction inst = v.get(i);
                instStrings.add(new Pair<Integer, String>(inst.getId(), inst.getDisplayString()));
                g.drawString(inst.getDisplayString(),10,15+(instHeight+20)*instStrings.get(i).first);
            }
        }
    }
    
    public void handleNewInstruction(int frameHeight){
        // for now adding new dummy instruction, pass instruction as param later
        int totalInstructions = instStrings.size();
        Pair<Integer, String> dummyInstr = new Pair<Integer, String>(totalInstructions, "Instruction"+totalInstructions);
        instStrings.add(dummyInstr);
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
        g.drawString("Instruction"+(totalInstructions -1),10,20*totalInstructions);
        
        
        
    }
}
