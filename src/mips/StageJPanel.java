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
 * @author vedratn
 */
public class StageJPanel extends JPanel{
    private int tempCount = 0;
    
    public void paint(Graphics g){
        update(g);
    }
    
    public void update(Graphics g){
        // mainWindow.currStages is acessible, cool
        int size = mainWindow.allStages.size();
        g.setColor(Color.red);
        g.fillRect((tempCount++)*50,0,50,100);
    }
}
