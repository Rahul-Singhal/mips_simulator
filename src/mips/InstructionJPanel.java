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
    ArrayList<Pair<Integer, String>> instStrings;
    
    
    public InstructionJPanel(){
        instStrings = new ArrayList<Pair<Integer, String>>();
        instStrings.clear();
        // for testing purpose
        addDummyInstructions();
    }
    
    public void addDummyInstructions(){
        for(int i = 0 ; i<10; i++){
            instStrings.add(new Pair<Integer, String>(i, "Instruction"+i));
        }
    }
    
    public void paintComponent(Graphics g){
        System.out.println("paint component called");
        super.paintComponent(g);
        for(int i=0 ; i<instStrings.size(); i++){
            g.drawString(instStrings.get(i).second,10,20*(i+1));
        }
    }
    
    public void handleNewInstruction(int frameHeight){
        // for now adding new dummy instruction, pass instruction as param later
        System.out.println("new added");
        int totalInstructions = instStrings.size();
        Pair<Integer, String> dummyInstr = new Pair<Integer, String>(totalInstructions, "Instruction"+totalInstructions);
        instStrings.add(dummyInstr);
        totalInstructions++;
        // draw only new one or repaint
        frameHeight -= 60;
        if(frameHeight < 20*totalInstructions){
            System.out.println("overflow " + this.getHeight());
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
