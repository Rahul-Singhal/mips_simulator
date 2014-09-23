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
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JPanel;

/**
 *
 * @author rahul
 */
public class StageJPanel extends JPanel {

    private int instHeight;
    private int instWidth;
    private int offsetFromTop;
    private int leftPaneShift;
    private Font guiFont;
    private int barb = 10;
    private double phi = Math.PI / 12;
    ArrayList<ArrayList<Instruction>> allInstructions;
    private HashMap<Integer, Pair<String, Color>> stagePropertiesMap;
    private int idStage;
    private int preferredWidth;

    public StageJPanel() {
        allInstructions = new ArrayList<ArrayList<Instruction>>();
        instHeight = 20;
        instWidth = 60;
        leftPaneShift = 20;
        guiFont = SystemVars.guiFont;
        stagePropertiesMap = new HashMap<Integer, Pair<String, Color>>();
        offsetFromTop = SystemVars.offsetFromTop;
        buildStagePropertiesMap();
       //idStage = SystemVars.reverseStageTypeMap.get(SystemVars.stageType.ID);
        preferredWidth = 0;
    }
    
    public void resetSystem(){
        allInstructions = new ArrayList<ArrayList<Instruction>>();
        stagePropertiesMap = new HashMap<Integer, Pair<String, Color>>();
        buildStagePropertiesMap();
    }

    public void buildStagePropertiesMap() {
        int totalDifferentStages = SystemVars.getTotalDifferentStages();
        HashMap<SystemVars.stageType, Color> colorMap = SystemVars.stageColorMap;
        HashMap<Integer, String> nameMap = SystemVars.stageNameMap;
        for (int i = 1; i <= totalDifferentStages; i++) {
            stagePropertiesMap.put(i, new Pair<String, Color>(nameMap.get(i), colorMap.get(SystemVars.baseStageMap.get(i))));
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int column = 0; column < allInstructions.size(); column++) {
            drawHeader(column, g);
            for (int row = 0; row < allInstructions.get(column).size(); row++) {
                drawInstruction(allInstructions.get(column).get(row), column, row, g);
            }
        }
    }

    public int drawFinishedQueue(ArrayList<Instruction> v, int frameWidth, int frameHeight) {
        int column = allInstructions.size();
        drawHeader(column, null);
        
        allInstructions.add(v);
        for (int row = 0; row < v.size(); row++) {
            drawInstruction(v.get(row), column, row, null);
        }
        return preferredWidth;
    }

    public void drawInstruction(Instruction instruction, int column, int row, Graphics g) {
        Instruction tempInstruction;
        int presentStage = instruction.getPresentStage();
        if (presentStage == 0) {
            return;
        }
        if (g == null) {
            g = this.getGraphics();
        }
        Pair<String, Color> stageProperties = stagePropertiesMap.get(presentStage);
        g.setColor(stageProperties.second);
        g.fillRect(leftPaneShift + (instWidth + 2) * column, offsetFromTop + (instHeight + 20) * instruction.getId(), instWidth, instHeight);
        g.setColor(Color.black);
        g.setFont(guiFont);
        String label = stageProperties.first;
        FontMetrics fm = g.getFontMetrics();

        if (!instruction.getStalled() && !instruction.getForwarded()) {
            int stringWidth = fm.getStringBounds(label, g).getBounds().width;
            g.drawString(label, leftPaneShift + (instWidth + 2) * column + (instWidth - stringWidth) / 2, offsetFromTop + 15 + (instHeight + 20) * instruction.getId());
        }

        if (instruction.getStalled() && !instruction.getForwarded()) {
            label = "STALL";
            int stringWidth = fm.getStringBounds(label, g).getBounds().width;
            g.drawString(label, leftPaneShift + (instWidth + 2) * column + (instWidth - stringWidth) / 2, offsetFromTop + 15 + (instHeight + 20) * instruction.getId());

            if (instruction.getStallingInstructionId() != -1) {
                boolean checkLast = false;
                for (int q = 0; q < allInstructions.get(column - 1).size(); q++) {
                    tempInstruction = allInstructions.get(column - 1).get(q);
                    if (tempInstruction.id == instruction.id) {
                        if (tempInstruction.getStalled() && tempInstruction.getPresentStage() == presentStage) {
                            checkLast = true;
                            break;
                        } else {
                            checkLast = false;
                            break;
                        }
                    }
                }

                if (!checkLast) {
                    //draw arrow
                    int alpha = 1;
                    int beta;
                    // stage to look for is ID
                    boolean myFlag = false;
                    int stallInstId = instruction.getStallingInstructionId();
                    while (alpha <= column) {
                        int maxLeng = allInstructions.get(column - alpha).size();
                        for (beta = 0; beta < maxLeng; beta++) {
                            if (allInstructions.get(column - alpha).get(beta).getId() == stallInstId && allInstructions.get(column - alpha).get(beta).getPresentStage() == idStage) {
                                myFlag = true;
                                break;
                            } else if (allInstructions.get(column - alpha).get(beta).getId() > stallInstId) {
                                myFlag = false;
                                break;
                            }
                        }
                        if (myFlag) {
                            break;
                        }
                        alpha++;
                    }
                    drawArrow(column, instruction.getId(), alpha, -instruction.getStallingInstructionId() + instruction.id, false, g);
                }
            }
        }
        if (instruction.getForwarded()) {
            //draw arrow to display forwarding
            int stringWidth = fm.getStringBounds(label, g).getBounds().width;
            g.drawString(label, leftPaneShift + (instWidth + 2) * column + (instWidth - stringWidth) / 2, offsetFromTop + 15 + (instHeight + 20) * instruction.getId());
            drawArrow(column, instruction.getId(), 1, instruction.getId() - instruction.getForwardedFromInstructionId(), true, g);
        }
    }

    public void drawHeader(int column, Graphics g) {
        if (g == null) {
            g = this.getGraphics();
        }
        int[] xPoints = {
            leftPaneShift + (instWidth + 2) * column,
            leftPaneShift + (instWidth + 2) * column,
            leftPaneShift + (instWidth + 2) * column + instWidth,
            leftPaneShift + (instWidth + 2) * column + instWidth
        };
        int[] yPoints = {
            offsetFromTop - 45,
            offsetFromTop - 35,
            offsetFromTop - 35,
            offsetFromTop - 45
        };
        if (preferredWidth == 0) {
            preferredWidth = this.getWidth();
        }
        if (preferredWidth < leftPaneShift + (instWidth + 2) * column + instWidth + 50) {
            preferredWidth += 150;
            this.setPreferredSize(new Dimension(preferredWidth, this.getHeight()));
            revalidate();
            repaint();
        }
        g.setColor(Color.black);
        g.drawPolyline(xPoints, yPoints, 4);
        g.setFont(guiFont);
        String label = "" + column;
        FontMetrics fm = g.getFontMetrics();
        int stringWidth = fm.getStringBounds(label, g).getBounds().width;
        g.drawString(label, leftPaneShift + (instWidth + 2) * column + (instWidth - stringWidth) / 2, offsetFromTop - 40);
    }

    public void drawArrow(int column, int row, int back, int up, boolean forwardArrow, Graphics g) {
        if (g == null) {
            g = this.getGraphics();
        }
        if(forwardArrow) column++;
        int column0 = column - back;
        int row0 = row - up;
        int x1 = leftPaneShift + (instWidth + 2) * column0 + instWidth / 2;
        int x2 = leftPaneShift + (instWidth + 2) * column + instWidth / 2;
        int y1 = offsetFromTop + (instHeight + 20) * row0 + instHeight;
        int y2 = offsetFromTop + (instHeight + 20) * row;
        if (forwardArrow) {
            g.setColor(Color.red);
            x2 -= instWidth / 2;
        } else {
            g.setColor(Color.black);
        }
        g.drawLine(x1, y1, x2, y2);
        Graphics2D g2 = (Graphics2D) g;
        double theta = Math.atan2(y2 - y1, x2 - x1);
        double x = x2 - barb * Math.cos(theta + phi);
        double y = y2 - barb * Math.sin(theta + phi);
        g2.draw(new Line2D.Double(x2, y2, x, y));
        x = x2 - barb * Math.cos(theta - phi);
        y = y2 - barb * Math.sin(theta - phi);
        g2.draw(new Line2D.Double(x2, y2, x, y));
    }

}
