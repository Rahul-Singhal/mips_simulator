/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mips;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author rahul
 */
public class StageJPanel extends JPanel{
    private int tempCount = 0;
    
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        // mainWindow.currStages is acessible, cool
        // int size = mainWindow.allStages.size();
        g.setColor(Color.red);
        g.fillRect((tempCount++)*50,0,50,100);
    }
}
