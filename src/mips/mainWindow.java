package mips;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.CellRendererPane;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

class imageSaveFilter extends javax.swing.filechooser.FileFilter {

    @Override
    public boolean accept(File file) {
        return file.isDirectory() || file.getAbsolutePath().endsWith(".jpg");
    }

    @Override
    public String getDescription() {
        return "Images (*.jpg)";
    }
}


/**
 *
 * @author rahul
 */
public class mainWindow extends javax.swing.JFrame{
    
    private int tempID;
    private int ttid;
    private int preferredHeight;
    private int preferredWidth;
    private static String programFile = null;
    private InstructionJPanel instructionJPanel1;
    private static Program prog = null;
    
    private static boolean UIChecking = false;
    /**
     * Creates new form mainWindow
     */
    public mainWindow() {
        SystemVars s = new SystemVars();
        preferredHeight = 0;
        ttid = 3;
        tempID = 0;
        initComponents();
        instructionJPanel1 = new InstructionJPanel(stageJPanel1.getHeight());
        jScrollPane1.setRowHeaderView(instructionJPanel1);
        jScrollPane1.getVerticalScrollBar().setUnitIncrement(20);
        jScrollPane1.getHorizontalScrollBar().setUnitIncrement(20);
        setKeyboardKeys();
    }
    
    public void runOneCycle(){
        if(prog != null){
            if(!SystemVars.programOver)drawNextQueue(prog.execute());
        }
    }
    
    public void runAllCycles(){
        int i = 0;
        for (i = 0; i < 200; i++) {
            if (prog != null) {
                if (!SystemVars.programOver) {
                    drawNextQueue(prog.execute());
                } else {
                    break;
                }
            } else {
                break;
            }
        }
        if (i == 200) {
            JOptionPane.showMessageDialog(
                null,
                "Ran 200 cycles. This program could possibly be an infinite loop.",
                "Warning!",
                JOptionPane.INFORMATION_MESSAGE
            );
        }
    }
    
    
    Action allCyclesAction = new AbstractAction() {
        public void actionPerformed(ActionEvent e) {
            runAllCycles();
        }
    };
    
    Action nextCycleAction = new AbstractAction() {
        public void actionPerformed(ActionEvent e) {
            runOneCycle();
        }
    };
    
    Action registerStateBarAction = new AbstractAction() {
        public void actionPerformed(ActionEvent e) {
            registerStateMenuItemActionPerformed(null);
        }
    };
    
    public void setKeyboardKeys(){
        InputMap im = jScrollPane1.getInputMap(JComponent.WHEN_FOCUSED);
        ActionMap am = jScrollPane1.getActionMap();
        im.put(KeyStroke.getKeyStroke("N"),"nextCycleAction");
        im.put(KeyStroke.getKeyStroke("A"),"allCyclesAction");
        im.put(KeyStroke.getKeyStroke("R"),"registerStateBarAction");
        am.put("nextCycleAction",nextCycleAction);
        am.put("allCyclesAction",allCyclesAction);
        am.put("registerStateBarAction",registerStateBarAction);
    }
    
    public void drawNextQueue(ArrayList<Instruction> a){
        int newPreferredWidth = stageJPanel1.drawFinishedQueue(a, this.getWidth()-instructionJPanel1.getWidth(), this.getHeight());
        int newPreferredHeight = instructionJPanel1.drawFinishedQueue(a, this.getHeight());
        if(preferredHeight != newPreferredHeight){
            preferredHeight = newPreferredHeight;
            instructionJPanel1.setPreferredSize(new Dimension(instructionJPanel1.getWidth(), preferredHeight));
            stageJPanel1.setPreferredSize(new Dimension(stageJPanel1.getWidth(), preferredHeight));
        }
        if(preferredWidth != newPreferredWidth){
            preferredWidth = newPreferredWidth;
            stageJPanel1.setPreferredSize(new Dimension(preferredWidth, preferredHeight));
        }
        stageJPanel1.revalidate();
    }
    
    public void addDummyInstructions(){
        Instruction inst1 = new Instruction(1,1,false,1, 20, false, 10, 20, "add $t1, $t2, $t3", 0);
        Instruction inst2 = new Instruction(4,2,false,1, 20, false, 10, 20, "hey there2", 0);
        Instruction inst3 = new Instruction(4,3,false,1, 20, false, 10, 20, "hey there3", 0);
        Instruction inst4 = new Instruction(4,4,false,1, 20, false, 10, 20, "hey there4", 0);
        Instruction inst5 = new Instruction(4,7,false,1, 20, false, 10, 20, "hey there5", 0);
        Instruction inst6 = new Instruction(4,8,false,1, 20, false, 10, 20, "hey there6", 0);
        Instruction inst7 = new Instruction(4,9,false,1, 20, false, 10, 20, "hey there7", 0);
        Instruction inst8 = new Instruction(4,10,false,1, 20, false, 10, 20, "hey there8", 0);
        

        Instruction inst11 = new Instruction(1,1,false,1, 20, false, 10, 20, "add $t4, $t1, $t3", 1);
        Instruction inst21 = new Instruction(4,2,false,1, 20, false, 10, 20, "hey there2", 1);
        
        Instruction instxx = new Instruction(1,1,false,1, 20, false, 10, 20, "sll $t4, $t1, $t3", 2);
        Instruction inst311 = new Instruction(4,3,true,0, 20, false, 10, 20, "hey there2", 1);
        Instruction inst312 = new Instruction(4,3,true,0, 20, false, 10, 20, "hey there2", 1);
        Instruction inst313 = new Instruction(4,3,true,0, 20, false, 10, 20, "hey there2", 1);
        Instruction inst314 = new Instruction(4,3,true,0, 20, false, 10, 20, "hey there2", 1);

        Instruction inst31 = new Instruction(4,3,false,1, 20, false, 10, 20, "hey there3", 1);
        Instruction inst41 = new Instruction(4,4,false,1, 20, false, 10, 20, "hey there4", 1);
        Instruction inst51 = new Instruction(4,5,false,1, 20, false, 10, 20, "hey there5", 1);
        Instruction inst61 = new Instruction(4,6,false,1, 20, false, 10, 20, "hey there6", 1);
        Instruction inst71 = new Instruction(4,7,false,1, 20, false, 10, 20, "hey there7", 1);
        Instruction inst81 = new Instruction(4,8,false,1, 20, false, 10, 20, "hey there8", 1);
        
        ArrayList<Instruction> v = new ArrayList<Instruction>();
        v.clear();
        switch(tempID){
            case 0:
                v.add(inst1);
                break;
            case 1:
                v.add(inst2);
                v.add(inst11);
                break;
            case 2:
                v.add(inst3);
                v.add(inst21);
                v.add(instxx);
                break;
            case 3:
                v.add(inst4);
                v.add(inst311);
                break;
            case 4:
                v.add(inst5);
                v.add(inst312);
                break;
            case 5:
                v.add(inst6);
                v.add(inst313);
                break;
            case 6:
                v.add(inst7);
                v.add(inst314);
                break;
            case 7:
                v.add(inst8);
                v.add(inst31);
                break;
            case 8:
                v.add(inst41);
                break;
            case 9:
                v.add(inst51);
                break;
            case 10:
                v.add(inst61);
                break;
            case 11:
                v.add(inst71);
                break;
            case 12:
                v.add(inst81);
                break;
            default:
                instxx = new Instruction(1,1,false,1, 20, false, 10, 20, "sll $t4, $t1, $t3", ttid);
                ttid++;
                v.add(instxx);
                break;
        }
        tempID++;
        drawNextQueue(v);
    }
    
    public void addInstructions(){
        prog.execute();
        drawNextQueue(prog.currInstructions);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        openFileChooser = new javax.swing.JFileChooser();
        pipelineDepthEditor = new javax.swing.JFrame();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        IFField = new javax.swing.JTextField();
        EXField = new javax.swing.JTextField();
        IDField = new javax.swing.JTextField();
        MEMField = new javax.swing.JTextField();
        DIVField = new javax.swing.JTextField();
        MULTField = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        WBField = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        saveImageChooser = new javax.swing.JFileChooser();
        registerStatePanel = new javax.swing.JFrame();
        jPanel2 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        regiterNameLabel1 = new javax.swing.JLabel();
        registerNameLabel2 = new javax.swing.JLabel();
        registerNameLabel3 = new javax.swing.JLabel();
        registerNameLabel4 = new javax.swing.JLabel();
        registerNameLabel5 = new javax.swing.JLabel();
        registerNameLabel6 = new javax.swing.JLabel();
        registerNameLabel7 = new javax.swing.JLabel();
        registerNameLabel8 = new javax.swing.JLabel();
        registerNameLabel17 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        registerNameLabel18 = new javax.swing.JLabel();
        registerNameLabel19 = new javax.swing.JLabel();
        registerNameLabel20 = new javax.swing.JLabel();
        registerNameLabel21 = new javax.swing.JLabel();
        registerNameLabel22 = new javax.swing.JLabel();
        registerNameLabel23 = new javax.swing.JLabel();
        registerNameLabel24 = new javax.swing.JLabel();
        registerNameLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        registerNameLabel10 = new javax.swing.JLabel();
        registerNameLabel11 = new javax.swing.JLabel();
        registerNameLabel12 = new javax.swing.JLabel();
        registerNameLabel13 = new javax.swing.JLabel();
        registerNameLabel14 = new javax.swing.JLabel();
        registerNameLabel15 = new javax.swing.JLabel();
        registerNameLabel16 = new javax.swing.JLabel();
        registerNameLabel25 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        registerNameLabel26 = new javax.swing.JLabel();
        registerNameLabel27 = new javax.swing.JLabel();
        registerNameLabel28 = new javax.swing.JLabel();
        registerNameLabel29 = new javax.swing.JLabel();
        registerNameLabel30 = new javax.swing.JLabel();
        registerNameLabel31 = new javax.swing.JLabel();
        registerNameLabel32 = new javax.swing.JLabel();
        registerValueLabel1 = new javax.swing.JLabel();
        registerValueLabel2 = new javax.swing.JLabel();
        registerValueLabel3 = new javax.swing.JLabel();
        registerValueLabel4 = new javax.swing.JLabel();
        registerValueLabel5 = new javax.swing.JLabel();
        registerValueLabel6 = new javax.swing.JLabel();
        registerValueLabel7 = new javax.swing.JLabel();
        registerValueLabel8 = new javax.swing.JLabel();
        registerValueLabel9 = new javax.swing.JLabel();
        registerValueLabel10 = new javax.swing.JLabel();
        registerValueLabel11 = new javax.swing.JLabel();
        registerValueLabel12 = new javax.swing.JLabel();
        registerValueLabel13 = new javax.swing.JLabel();
        registerValueLabel14 = new javax.swing.JLabel();
        registerValueLabel15 = new javax.swing.JLabel();
        registerValueLabel16 = new javax.swing.JLabel();
        registerValueLabel17 = new javax.swing.JLabel();
        registerValueLabel18 = new javax.swing.JLabel();
        registerValueLabel19 = new javax.swing.JLabel();
        registerValueLabel20 = new javax.swing.JLabel();
        registerValueLabel21 = new javax.swing.JLabel();
        registerValueLabel22 = new javax.swing.JLabel();
        registerValueLabel23 = new javax.swing.JLabel();
        registerValueLabel24 = new javax.swing.JLabel();
        registerValueLabel25 = new javax.swing.JLabel();
        registerValueLabel26 = new javax.swing.JLabel();
        registerValueLabel27 = new javax.swing.JLabel();
        registerValueLabel28 = new javax.swing.JLabel();
        registerValueLabel29 = new javax.swing.JLabel();
        registerValueLabel30 = new javax.swing.JLabel();
        registerValueLabel31 = new javax.swing.JLabel();
        registerValueLabel32 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        stageJPanel1 = new mips.StageJPanel();
        menuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        openMenuItem = new javax.swing.JMenuItem();
        saveAsMenuItem = new javax.swing.JMenuItem();
        exitMenuItem = new javax.swing.JMenuItem();
        editMenu = new javax.swing.JMenu();
        pipelineMenuItem = new javax.swing.JMenuItem();
        resetMenuItem = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        nextCycleMenuItem = new javax.swing.JMenuItem();
        allCyclesMenuItem = new javax.swing.JMenuItem();
        showMenu = new javax.swing.JMenu();
        registerStateMenuItem = new javax.swing.JMenuItem();
        memoryStateMenuItem = new javax.swing.JMenuItem();
        settingsMenuItem = new javax.swing.JMenu();
        ForwardingCheckBox = new javax.swing.JCheckBoxMenuItem();
        FastBranchingTextBox = new javax.swing.JCheckBoxMenuItem();
        jMenu1 = new javax.swing.JMenu();
        predictTakenButton = new javax.swing.JMenuItem();
        predictNotTakenButton = new javax.swing.JMenuItem();
        historyBasedBranchButton = new javax.swing.JMenuItem();
        helpMenu = new javax.swing.JMenu();
        contentsMenuItem = new javax.swing.JMenuItem();
        aboutMenuItem = new javax.swing.JMenuItem();

        openFileChooser.setDialogTitle("Open Mips Module");

        pipelineDepthEditor.setLocationByPlatform(true);
        pipelineDepthEditor.setMinimumSize(new java.awt.Dimension(500, 300));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Pipeline Depths", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel1.setText("Stage");

        jLabel2.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel2.setText("Depth");

        jLabel3.setText("IF");

        jLabel4.setText("ID");

        jLabel5.setText("EX");

        jLabel6.setText("MEM");

        IFField.setText("2");
        IFField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IFFieldActionPerformed(evt);
            }
        });

        EXField.setText("1");
        EXField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EXFieldActionPerformed(evt);
            }
        });

        IDField.setText("1");
        IDField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IDFieldActionPerformed(evt);
            }
        });

        MEMField.setText("3");
        MEMField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MEMFieldActionPerformed(evt);
            }
        });

        DIVField.setText("5");
        DIVField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DIVFieldActionPerformed(evt);
            }
        });

        MULTField.setText("5");
        MULTField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MULTFieldActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel12.setText("Stage");

        jLabel13.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel13.setText("Depth");

        jLabel14.setText("WB");

        WBField.setText("1");
        WBField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                WBFieldActionPerformed(evt);
            }
        });

        jLabel15.setText("MULT");

        jLabel16.setText("DIV");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)))
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addGap(59, 59, 59)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(MEMField, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(EXField, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(IFField, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(IDField, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 83, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addComponent(jLabel15)))
                    .addComponent(jLabel16))
                .addGap(47, 47, 47)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(DIVField, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(WBField, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(MULTField, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(56, 56, 56))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(jLabel13))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(WBField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(MULTField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16)
                            .addComponent(DIVField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(IFField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(IDField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(EXField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(MEMField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
        );

        jButton1.setText("Cancel");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Ok");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pipelineDepthEditorLayout = new javax.swing.GroupLayout(pipelineDepthEditor.getContentPane());
        pipelineDepthEditor.getContentPane().setLayout(pipelineDepthEditorLayout);
        pipelineDepthEditorLayout.setHorizontalGroup(
            pipelineDepthEditorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pipelineDepthEditorLayout.createSequentialGroup()
                .addContainerGap(314, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50))
            .addGroup(pipelineDepthEditorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pipelineDepthEditorLayout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        pipelineDepthEditorLayout.setVerticalGroup(
            pipelineDepthEditorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pipelineDepthEditorLayout.createSequentialGroup()
                .addContainerGap(258, Short.MAX_VALUE)
                .addGroup(pipelineDepthEditorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap())
            .addGroup(pipelineDepthEditorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pipelineDepthEditorLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(54, Short.MAX_VALUE)))
        );

        saveImageChooser.setAcceptAllFileFilterUsed(false);
        saveImageChooser.setDialogType(javax.swing.JFileChooser.SAVE_DIALOG);
        saveImageChooser.setFileFilter(new imageSaveFilter());

        registerStatePanel.setLocationByPlatform(true);
        registerStatePanel.setMinimumSize(new java.awt.Dimension(800, 370));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Register Values", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12))); // NOI18N

        jLabel7.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel7.setText("Register");

        jLabel8.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel8.setText("Value");

        regiterNameLabel1.setText("$zero");

        registerNameLabel2.setText("$at");

        registerNameLabel3.setText("$v0");

        registerNameLabel4.setText("$v1");

        registerNameLabel5.setText("$a0");

        registerNameLabel6.setText("$a1");

        registerNameLabel7.setText("$a2");

        registerNameLabel8.setText("$a3");

        registerNameLabel17.setText("$s0");

        jLabel9.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel9.setText("Register");

        jLabel10.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel10.setText("Value");

        registerNameLabel18.setText("$s1");

        registerNameLabel19.setText("$s2");

        registerNameLabel20.setText("$s3");

        registerNameLabel21.setText("$s4");

        registerNameLabel22.setText("$s5");

        registerNameLabel23.setText("$s6");

        registerNameLabel24.setText("$s7");

        registerNameLabel9.setText("$t0");

        jLabel11.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel11.setText("Register");

        jLabel17.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel17.setText("Value");

        registerNameLabel10.setText("$t1");

        registerNameLabel11.setText("$t2");

        registerNameLabel12.setText("$t3");

        registerNameLabel13.setText("$t4");

        registerNameLabel14.setText("$t5");

        registerNameLabel15.setText("$t6");

        registerNameLabel16.setText("$t7");

        registerNameLabel25.setText("$t8");

        jLabel18.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel18.setText("Register");

        jLabel19.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel19.setText("Value");

        registerNameLabel26.setText("$t9");

        registerNameLabel27.setText("$k0");

        registerNameLabel28.setText("$k1");

        registerNameLabel29.setText("$gp");

        registerNameLabel30.setText("$sp");

        registerNameLabel31.setText("$fp");

        registerNameLabel32.setText("$ra");

        registerValueLabel1.setText("$zero");

        registerValueLabel2.setText("$zero");

        registerValueLabel3.setText("$zero");

        registerValueLabel4.setText("$zero");

        registerValueLabel5.setText("$zero");

        registerValueLabel6.setText("$zero");

        registerValueLabel7.setText("$zero");

        registerValueLabel8.setText("$zero");

        registerValueLabel9.setText("$zero");

        registerValueLabel10.setText("$zero");

        registerValueLabel11.setText("$zero");

        registerValueLabel12.setText("$zero");

        registerValueLabel13.setText("$zero");

        registerValueLabel14.setText("$zero");

        registerValueLabel15.setText("$zero");

        registerValueLabel16.setText("$zero");

        registerValueLabel17.setText("$zero");

        registerValueLabel18.setText("$zero");

        registerValueLabel19.setText("$zero");

        registerValueLabel20.setText("$zero");

        registerValueLabel21.setText("$zero");

        registerValueLabel22.setText("$zero");

        registerValueLabel23.setText("$zero");

        registerValueLabel24.setText("$zero");

        registerValueLabel25.setText("$zero");

        registerValueLabel26.setText("$zero");

        registerValueLabel27.setText("$zero");

        registerValueLabel28.setText("$zero");

        registerValueLabel29.setText("$zero");

        registerValueLabel30.setText("$zero");

        registerValueLabel31.setText("$zero");

        registerValueLabel32.setText("$zero");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(48, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(regiterNameLabel1)
                    .addComponent(registerNameLabel2)
                    .addComponent(registerNameLabel3)
                    .addComponent(registerNameLabel4)
                    .addComponent(registerNameLabel5)
                    .addComponent(registerNameLabel6)
                    .addComponent(registerNameLabel7)
                    .addComponent(registerNameLabel8))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(registerValueLabel8)
                    .addComponent(registerValueLabel7)
                    .addComponent(registerValueLabel6)
                    .addComponent(registerValueLabel5)
                    .addComponent(registerValueLabel4)
                    .addComponent(jLabel8)
                    .addComponent(registerValueLabel1)
                    .addComponent(registerValueLabel2)
                    .addComponent(registerValueLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addComponent(registerNameLabel9)
                    .addComponent(registerNameLabel10)
                    .addComponent(registerNameLabel11)
                    .addComponent(registerNameLabel12)
                    .addComponent(registerNameLabel13)
                    .addComponent(registerNameLabel14)
                    .addComponent(registerNameLabel15)
                    .addComponent(registerNameLabel16))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(registerValueLabel16)
                    .addComponent(registerValueLabel15)
                    .addComponent(registerValueLabel14)
                    .addComponent(registerValueLabel13)
                    .addComponent(registerValueLabel12)
                    .addComponent(jLabel17)
                    .addComponent(registerValueLabel9)
                    .addComponent(registerValueLabel10)
                    .addComponent(registerValueLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(registerNameLabel17)
                    .addComponent(registerNameLabel18)
                    .addComponent(registerNameLabel19)
                    .addComponent(registerNameLabel20)
                    .addComponent(registerNameLabel21)
                    .addComponent(registerNameLabel22)
                    .addComponent(registerNameLabel23)
                    .addComponent(registerNameLabel24))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(registerValueLabel24)
                    .addComponent(registerValueLabel23)
                    .addComponent(registerValueLabel22)
                    .addComponent(registerValueLabel21)
                    .addComponent(registerValueLabel20)
                    .addComponent(jLabel10)
                    .addComponent(registerValueLabel17)
                    .addComponent(registerValueLabel18)
                    .addComponent(registerValueLabel19))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel18)
                    .addComponent(registerNameLabel25)
                    .addComponent(registerNameLabel26)
                    .addComponent(registerNameLabel27)
                    .addComponent(registerNameLabel28)
                    .addComponent(registerNameLabel29)
                    .addComponent(registerNameLabel30)
                    .addComponent(registerNameLabel31)
                    .addComponent(registerNameLabel32))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(registerValueLabel32)
                    .addComponent(registerValueLabel31)
                    .addComponent(registerValueLabel30)
                    .addComponent(registerValueLabel29)
                    .addComponent(registerValueLabel28)
                    .addComponent(jLabel19)
                    .addComponent(registerValueLabel25)
                    .addComponent(registerValueLabel26)
                    .addComponent(registerValueLabel27))
                .addContainerGap(50, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel18)
                            .addComponent(jLabel19))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(registerNameLabel25)
                            .addComponent(registerValueLabel25))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(registerNameLabel26)
                            .addComponent(registerValueLabel26))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(registerNameLabel27)
                            .addComponent(registerValueLabel19)
                            .addComponent(registerValueLabel27))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(registerNameLabel28)
                            .addComponent(registerValueLabel20)
                            .addComponent(registerValueLabel28))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(registerNameLabel29)
                            .addComponent(registerValueLabel21)
                            .addComponent(registerValueLabel29))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(registerNameLabel30)
                            .addComponent(registerValueLabel22)
                            .addComponent(registerValueLabel30))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(registerNameLabel31)
                            .addComponent(registerValueLabel23)
                            .addComponent(registerValueLabel31))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(registerNameLabel32)
                            .addComponent(registerValueLabel24)
                            .addComponent(registerValueLabel32)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(jLabel17))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(registerNameLabel9)
                            .addComponent(registerValueLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(registerNameLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(registerNameLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(registerNameLabel12)
                            .addComponent(registerValueLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(registerNameLabel13)
                            .addComponent(registerValueLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(registerNameLabel14)
                            .addComponent(registerValueLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(registerNameLabel15)
                            .addComponent(registerValueLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(registerNameLabel16)
                            .addComponent(registerValueLabel8)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(registerNameLabel17)
                            .addComponent(registerValueLabel17))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(registerNameLabel18)
                            .addComponent(registerValueLabel10)
                            .addComponent(registerValueLabel18))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(registerNameLabel19)
                            .addComponent(registerValueLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(registerNameLabel20)
                            .addComponent(registerValueLabel12))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(registerNameLabel21)
                            .addComponent(registerValueLabel13))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(registerNameLabel22)
                            .addComponent(registerValueLabel14))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(registerNameLabel23)
                            .addComponent(registerValueLabel15))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(registerNameLabel24)
                            .addComponent(registerValueLabel16)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(regiterNameLabel1)
                            .addComponent(registerValueLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(registerNameLabel2)
                            .addComponent(registerValueLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(registerNameLabel3)
                            .addComponent(registerValueLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(registerNameLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(registerNameLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(registerNameLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(registerNameLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(registerNameLabel8)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton4.setText("Ok");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout registerStatePanelLayout = new javax.swing.GroupLayout(registerStatePanel.getContentPane());
        registerStatePanel.getContentPane().setLayout(registerStatePanelLayout);
        registerStatePanelLayout.setHorizontalGroup(
            registerStatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(registerStatePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, registerStatePanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55))
        );
        registerStatePanelLayout.setVerticalGroup(
            registerStatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, registerStatePanelLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel2.getAccessibleContext().setAccessibleName("Register Values");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.X_AXIS));

        stageJPanel1.setBackground(new java.awt.Color(242, 241, 241));

        javax.swing.GroupLayout stageJPanel1Layout = new javax.swing.GroupLayout(stageJPanel1);
        stageJPanel1.setLayout(stageJPanel1Layout);
        stageJPanel1Layout.setHorizontalGroup(
            stageJPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 597, Short.MAX_VALUE)
        );
        stageJPanel1Layout.setVerticalGroup(
            stageJPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 383, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(stageJPanel1);

        getContentPane().add(jScrollPane1);

        fileMenu.setMnemonic('f');
        fileMenu.setText("File");

        openMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        openMenuItem.setMnemonic('o');
        openMenuItem.setText("Open");
        openMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(openMenuItem);

        saveAsMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        saveAsMenuItem.setMnemonic('a');
        saveAsMenuItem.setText("Save As Image");
        saveAsMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveAsMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(saveAsMenuItem);

        exitMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.CTRL_MASK));
        exitMenuItem.setMnemonic('x');
        exitMenuItem.setText("Exit");
        exitMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        editMenu.setMnemonic('e');
        editMenu.setText("Edit");

        pipelineMenuItem.setMnemonic('d');
        pipelineMenuItem.setText("Pipeline");
        pipelineMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pipelineMenuItemActionPerformed(evt);
            }
        });
        editMenu.add(pipelineMenuItem);

        resetMenuItem.setMnemonic('d');
        resetMenuItem.setText("Reset");
        resetMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetMenuItemActionPerformed(evt);
            }
        });
        editMenu.add(resetMenuItem);

        menuBar.add(editMenu);

        jMenu2.setText("Run");

        nextCycleMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, 0));
        nextCycleMenuItem.setText("Next Cycle");
        nextCycleMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextCycleMenuItemActionPerformed(evt);
            }
        });
        jMenu2.add(nextCycleMenuItem);

        allCyclesMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, 0));
        allCyclesMenuItem.setText("All Cycles");
        allCyclesMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                allCyclesMenuItemActionPerformed(evt);
            }
        });
        jMenu2.add(allCyclesMenuItem);

        menuBar.add(jMenu2);

        showMenu.setText("Show");

        registerStateMenuItem.setText("Register State");
        registerStateMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registerStateMenuItemActionPerformed(evt);
            }
        });
        showMenu.add(registerStateMenuItem);

        memoryStateMenuItem.setText("Memory State");
        showMenu.add(memoryStateMenuItem);

        menuBar.add(showMenu);

        settingsMenuItem.setMnemonic('e');
        settingsMenuItem.setText("Settings");

        ForwardingCheckBox.setText("Forwarding");
        ForwardingCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ForwardingCheckBoxActionPerformed(evt);
            }
        });
        settingsMenuItem.add(ForwardingCheckBox);

        FastBranchingTextBox.setText("Fast Branching");
        FastBranchingTextBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FastBranchingTextBoxActionPerformed(evt);
            }
        });
        settingsMenuItem.add(FastBranchingTextBox);

        jMenu1.setText("Branching Strategy");

        predictTakenButton.setText("Predict Taken");
        predictTakenButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                predictTakenButtonActionPerformed(evt);
            }
        });
        jMenu1.add(predictTakenButton);

        predictNotTakenButton.setText("Predict Not Taken");
        predictNotTakenButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                predictNotTakenButtonActionPerformed(evt);
            }
        });
        jMenu1.add(predictNotTakenButton);

        historyBasedBranchButton.setText("History Based");
        historyBasedBranchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                historyBasedBranchButtonActionPerformed(evt);
            }
        });
        jMenu1.add(historyBasedBranchButton);

        settingsMenuItem.add(jMenu1);

        menuBar.add(settingsMenuItem);

        helpMenu.setMnemonic('h');
        helpMenu.setText("Help");

        contentsMenuItem.setMnemonic('c');
        contentsMenuItem.setText("Contents");
        helpMenu.add(contentsMenuItem);

        aboutMenuItem.setMnemonic('a');
        aboutMenuItem.setText("About");
        helpMenu.add(aboutMenuItem);

        menuBar.add(helpMenu);

        setJMenuBar(menuBar);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMenuItemActionPerformed
        System.exit(0);
    }//GEN-LAST:event_exitMenuItemActionPerformed

    private void pipelineMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pipelineMenuItemActionPerformed
        pipelineDepthEditor.setLocation(this.getLocation());
        IFField.setText(""+SystemVars.stageDepths[0]);
        IDField.setText(""+SystemVars.stageDepths[1]);
        EXField.setText(""+SystemVars.stageDepths[2]);
        MULTField.setText(""+SystemVars.stageDepths[3]);
        DIVField.setText(""+SystemVars.stageDepths[4]);
        MEMField.setText(""+SystemVars.stageDepths[5]);
        WBField.setText(""+SystemVars.stageDepths[6]); 
        pipelineDepthEditor.setVisible(true);
    }//GEN-LAST:event_pipelineMenuItemActionPerformed

    private void openMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openMenuItemActionPerformed
        int returnVal =openFileChooser.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            resetProgram();
            File file = openFileChooser.getSelectedFile();
            if (!UIChecking) {
                programFile = file.getAbsolutePath();
                prog = new Program(file.getAbsolutePath());
                prog.init();
            } 
            System.out.println("File " + file.getName() + " opened!");
        } else {
            System.out.println("File access cancelled by user.");
        }
    }//GEN-LAST:event_openMenuItemActionPerformed

    private boolean validatePositiveNumber(String input){
        return Pattern.matches("\\d+", input);
    }
    
    private void showErrorDialogue(String stageName){
        JOptionPane.showMessageDialog(
            null, 
            "Invalid "+ stageName +" stage depth input. Re-check!", 
            "Error ",
            JOptionPane.INFORMATION_MESSAGE
        );
    }
    @SuppressWarnings("empty-statement")
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        int newIF, newID, newEX, newMEM, newMULT, newDIV, newWB;
        
        String str = IFField.getText();
        if(validatePositiveNumber(str)) newIF = Integer.parseInt(str);
        else {
            showErrorDialogue("IF");
            return;
        }
        
        str = IDField.getText();
        if(validatePositiveNumber(str)) newID = Integer.parseInt(str);
        else {
            showErrorDialogue("ID");
            return;
        }
        
        str = EXField.getText();
        if(validatePositiveNumber(str)) newEX = Integer.parseInt(str);
        else {
            showErrorDialogue("EX");
            return;
        }
        
        str = MEMField.getText();
        if(validatePositiveNumber(str)) newMEM = Integer.parseInt(str);
        else {
            showErrorDialogue("MEM");
            return;
        }
        
        str = WBField.getText();
        if(validatePositiveNumber(str)) newWB = Integer.parseInt(str);
        else {
            showErrorDialogue("WB");
            return;
        }
        
        str = MULTField.getText();
        if(validatePositiveNumber(str)) newMULT = Integer.parseInt(str);
        else {
            showErrorDialogue("MULT");
            return;
        }
        
        str = DIVField.getText();
        if(validatePositiveNumber(str)) newDIV = Integer.parseInt(str);
        else {
            showErrorDialogue("DIV");
            return;
        }
        
        pipelineDepthEditor.setVisible(false);
        SystemVars.stageDepths = new int[]{newIF,newID,newEX,1,1,newMEM,newWB};
        SystemVars.multSubStages = newMULT;
        SystemVars.divSubStages = newDIV;
        resetProgram();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void saveAsMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveAsMenuItemActionPerformed
        saveImageChooser.setVisible(true);
        if (saveImageChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            File imageFile = saveImageChooser.getSelectedFile();
            String fileName = imageFile.getName();
            if(fileName.length() <= 3 || !fileName.substring(fileName.length() - 3).equals("jpg")){
                imageFile = new File(imageFile.getAbsoluteFile()+".jpg");
            }
            int width = preferredWidth == 0 ? stageJPanel1.getWidth() : preferredWidth;
            int height = preferredHeight == 0 ? stageJPanel1.getHeight() : preferredWidth;
            BufferedImage image = new BufferedImage(width+100, height, BufferedImage.TYPE_INT_RGB);  
            Graphics2D imageGraphics = image.createGraphics();
            Rectangle oldBounds = jScrollPane1.getBounds();
            jScrollPane1.setBounds(new Rectangle(width+100, height));
            jScrollPane1.paintAll(imageGraphics);  
            jScrollPane1.setBounds(oldBounds);
            jScrollPane1.revalidate();
            try {    
                ImageIO.write(image, "jpg", imageFile);
            } catch (IOException ex) {
                Logger.getLogger(mainWindow.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_saveAsMenuItemActionPerformed
    
    private void resetProgram(){
//        JOptionPane.showMessageDialog(
//            null, 
//            "Restarting the program!", 
//            "Pipeline configured ",
//            JOptionPane.INFORMATION_MESSAGE
//        );
        SystemVars.resetSystem();
        stageJPanel1.resetSystem();
        instructionJPanel1.resetSystem();
        this.repaint();
        prog = new Program(programFile);
        prog.init();
    }
    
    private void ForwardingCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ForwardingCheckBoxActionPerformed
        SystemVars.forwardingEnabled = !SystemVars.forwardingEnabled;
        resetProgram();
    }//GEN-LAST:event_ForwardingCheckBoxActionPerformed

    private void FastBranchingTextBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FastBranchingTextBoxActionPerformed
        SystemVars.fastBranching = !SystemVars.fastBranching;
        resetProgram();
    }//GEN-LAST:event_FastBranchingTextBoxActionPerformed

    private void predictTakenButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_predictTakenButtonActionPerformed
        if(SystemVars.branchStrategy != SystemVars.branchStrategyType.TAKEN){
            SystemVars.branchStrategy = SystemVars.branchStrategyType.TAKEN;
            resetProgram();
        }
    }//GEN-LAST:event_predictTakenButtonActionPerformed

    private void predictNotTakenButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_predictNotTakenButtonActionPerformed
        if(SystemVars.branchStrategy != SystemVars.branchStrategyType.NOTTAKEN){
            SystemVars.branchStrategy = SystemVars.branchStrategyType.NOTTAKEN;
            resetProgram();
        }
    }//GEN-LAST:event_predictNotTakenButtonActionPerformed

    private void historyBasedBranchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_historyBasedBranchButtonActionPerformed
        if(SystemVars.branchStrategy != SystemVars.branchStrategyType.HISTORY){
            SystemVars.branchStrategy = SystemVars.branchStrategyType.HISTORY;
            resetProgram();
        }
    }//GEN-LAST:event_historyBasedBranchButtonActionPerformed

    private void resetMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetMenuItemActionPerformed
        this.resetProgram();
    }//GEN-LAST:event_resetMenuItemActionPerformed

    private void nextCycleMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextCycleMenuItemActionPerformed
        runOneCycle();
    }//GEN-LAST:event_nextCycleMenuItemActionPerformed

    private void allCyclesMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_allCyclesMenuItemActionPerformed
        runAllCycles();
    }//GEN-LAST:event_allCyclesMenuItemActionPerformed

    private void MEMFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MEMFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_MEMFieldActionPerformed

    private void EXFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EXFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_EXFieldActionPerformed

    private void IDFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IDFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_IDFieldActionPerformed

    private void IFFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IFFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_IFFieldActionPerformed

    private void DIVFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DIVFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DIVFieldActionPerformed

    private void MULTFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MULTFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_MULTFieldActionPerformed

    private void WBFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_WBFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_WBFieldActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        pipelineDepthEditor.setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        registerStatePanel.setVisible(false);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void registerStateMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registerStateMenuItemActionPerformed
        registerStatePanel.setLocation(this.getLocation());
        ArrayList <Register> registers = SystemVars.registers;
        ArrayList <JLabel> registerValueLabels = new ArrayList<JLabel>(Arrays.asList(
            registerValueLabel1,
            registerValueLabel2,
            registerValueLabel3,
            registerValueLabel4,
            registerValueLabel5,
            registerValueLabel6,
            registerValueLabel7,
            registerValueLabel8,
            registerValueLabel9,
            registerValueLabel10,
            registerValueLabel11,
            registerValueLabel12,
            registerValueLabel13,
            registerValueLabel14,
            registerValueLabel15,
            registerValueLabel16,
            registerValueLabel17,
            registerValueLabel18,
            registerValueLabel19,
            registerValueLabel20,
            registerValueLabel21,
            registerValueLabel22,
            registerValueLabel23,
            registerValueLabel24,
            registerValueLabel25,
            registerValueLabel26,
            registerValueLabel27,
            registerValueLabel28,
            registerValueLabel29,
            registerValueLabel30,
            registerValueLabel31,
            registerValueLabel32
        ));
        for(int i = 0 ; i<32; i++){
            registerValueLabels.get(i).setText("0x" + String.format("%08x", (0xFFFFFFFF & registers.get(i).value)).toUpperCase());
        }
        registerStatePanel.setVisible(true);
    }//GEN-LAST:event_registerStateMenuItemActionPerformed

    
    /** TODO add your handling code here:
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(mainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(mainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(mainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(mainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new mainWindow().setVisible(true);
            }
        });
        // default open file for testing
        programFile = "test_files/TestLS4.s";
        prog = new Program(programFile);
        prog.init();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField DIVField;
    private javax.swing.JTextField EXField;
    private javax.swing.JCheckBoxMenuItem FastBranchingTextBox;
    private javax.swing.JCheckBoxMenuItem ForwardingCheckBox;
    private javax.swing.JTextField IDField;
    private javax.swing.JTextField IFField;
    private javax.swing.JTextField MEMField;
    private javax.swing.JTextField MULTField;
    private javax.swing.JTextField WBField;
    private javax.swing.JMenuItem aboutMenuItem;
    private javax.swing.JMenuItem allCyclesMenuItem;
    private javax.swing.JMenuItem contentsMenuItem;
    private javax.swing.JMenu editMenu;
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JMenuItem historyBasedBranchButton;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JMenuItem memoryStateMenuItem;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem nextCycleMenuItem;
    private javax.swing.JFileChooser openFileChooser;
    private javax.swing.JMenuItem openMenuItem;
    private javax.swing.JFrame pipelineDepthEditor;
    private javax.swing.JMenuItem pipelineMenuItem;
    private javax.swing.JMenuItem predictNotTakenButton;
    private javax.swing.JMenuItem predictTakenButton;
    private javax.swing.JLabel registerNameLabel10;
    private javax.swing.JLabel registerNameLabel11;
    private javax.swing.JLabel registerNameLabel12;
    private javax.swing.JLabel registerNameLabel13;
    private javax.swing.JLabel registerNameLabel14;
    private javax.swing.JLabel registerNameLabel15;
    private javax.swing.JLabel registerNameLabel16;
    private javax.swing.JLabel registerNameLabel17;
    private javax.swing.JLabel registerNameLabel18;
    private javax.swing.JLabel registerNameLabel19;
    private javax.swing.JLabel registerNameLabel2;
    private javax.swing.JLabel registerNameLabel20;
    private javax.swing.JLabel registerNameLabel21;
    private javax.swing.JLabel registerNameLabel22;
    private javax.swing.JLabel registerNameLabel23;
    private javax.swing.JLabel registerNameLabel24;
    private javax.swing.JLabel registerNameLabel25;
    private javax.swing.JLabel registerNameLabel26;
    private javax.swing.JLabel registerNameLabel27;
    private javax.swing.JLabel registerNameLabel28;
    private javax.swing.JLabel registerNameLabel29;
    private javax.swing.JLabel registerNameLabel3;
    private javax.swing.JLabel registerNameLabel30;
    private javax.swing.JLabel registerNameLabel31;
    private javax.swing.JLabel registerNameLabel32;
    private javax.swing.JLabel registerNameLabel4;
    private javax.swing.JLabel registerNameLabel5;
    private javax.swing.JLabel registerNameLabel6;
    private javax.swing.JLabel registerNameLabel7;
    private javax.swing.JLabel registerNameLabel8;
    private javax.swing.JLabel registerNameLabel9;
    private javax.swing.JMenuItem registerStateMenuItem;
    private javax.swing.JFrame registerStatePanel;
    private javax.swing.JLabel registerValueLabel1;
    private javax.swing.JLabel registerValueLabel10;
    private javax.swing.JLabel registerValueLabel11;
    private javax.swing.JLabel registerValueLabel12;
    private javax.swing.JLabel registerValueLabel13;
    private javax.swing.JLabel registerValueLabel14;
    private javax.swing.JLabel registerValueLabel15;
    private javax.swing.JLabel registerValueLabel16;
    private javax.swing.JLabel registerValueLabel17;
    private javax.swing.JLabel registerValueLabel18;
    private javax.swing.JLabel registerValueLabel19;
    private javax.swing.JLabel registerValueLabel2;
    private javax.swing.JLabel registerValueLabel20;
    private javax.swing.JLabel registerValueLabel21;
    private javax.swing.JLabel registerValueLabel22;
    private javax.swing.JLabel registerValueLabel23;
    private javax.swing.JLabel registerValueLabel24;
    private javax.swing.JLabel registerValueLabel25;
    private javax.swing.JLabel registerValueLabel26;
    private javax.swing.JLabel registerValueLabel27;
    private javax.swing.JLabel registerValueLabel28;
    private javax.swing.JLabel registerValueLabel29;
    private javax.swing.JLabel registerValueLabel3;
    private javax.swing.JLabel registerValueLabel30;
    private javax.swing.JLabel registerValueLabel31;
    private javax.swing.JLabel registerValueLabel32;
    private javax.swing.JLabel registerValueLabel4;
    private javax.swing.JLabel registerValueLabel5;
    private javax.swing.JLabel registerValueLabel6;
    private javax.swing.JLabel registerValueLabel7;
    private javax.swing.JLabel registerValueLabel8;
    private javax.swing.JLabel registerValueLabel9;
    private javax.swing.JLabel regiterNameLabel1;
    private javax.swing.JMenuItem resetMenuItem;
    private javax.swing.JMenuItem saveAsMenuItem;
    private javax.swing.JFileChooser saveImageChooser;
    private javax.swing.JMenu settingsMenuItem;
    private javax.swing.JMenu showMenu;
    private mips.StageJPanel stageJPanel1;
    // End of variables declaration//GEN-END:variables

}
