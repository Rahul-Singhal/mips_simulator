package mips;


import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author vedratn
 */
public class InstructionJPanel extends JPanel{
    private int tempCount = 0;
    
    public void paint(Graphics g){
        update(g);
    }
    
    public void update(Graphics g){
        int size = mainWindow.allInstructions.size();
        g.setColor(Color.red);
        g.fillRect(50,50*(tempCount++),50,100);
    }
}
