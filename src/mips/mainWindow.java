package mips;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import javax.swing.CellRendererPane;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

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
    private InstructionJPanel instructionJPanel1;
    private static Program prog = null;
    
    private static boolean UIChecking = false;
    /**
     * Creates new form mainWindow
     */
    public mainWindow() {
        SystemVars initSysVars = new SystemVars();
        preferredHeight = 0;
        ttid = 3;
        tempID = 0;
        initComponents();
        instructionJPanel1 = new InstructionJPanel(stageJPanel1.getHeight());
        addKeyListener(new KeyListener() {
            public void keyPressed(KeyEvent e) {
            }

            public void keyReleased(KeyEvent e) {
                if(prog != null && e.getKeyCode() == 32){
                    drawNextQueue(prog.execute());
                }
            }

            public void keyTyped(KeyEvent e) {
            }
        });
        jScrollPane1.setRowHeaderView(instructionJPanel1);
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
    
    public void handleKeyPressEvent(){
//        if (UIChecking) 
//            addDummyInstructions();
//        else 
//            addInstructions();
        
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
        jScrollPane1 = new javax.swing.JScrollPane();
        stageJPanel1 = new mips.StageJPanel();
        menuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        openMenuItem = new javax.swing.JMenuItem();
        saveAsMenuItem = new javax.swing.JMenuItem();
        exitMenuItem = new javax.swing.JMenuItem();
        editMenu = new javax.swing.JMenu();
        pipelineMenuItem = new javax.swing.JMenuItem();
        ForwardingCheckBox = new javax.swing.JCheckBoxMenuItem();
        FastBranchingTextBox = new javax.swing.JCheckBoxMenuItem();
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

        ForwardingCheckBox.setText("Forwarding");
        ForwardingCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ForwardingCheckBoxActionPerformed(evt);
            }
        });
        editMenu.add(ForwardingCheckBox);

        FastBranchingTextBox.setText("Fast Branching");
        FastBranchingTextBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FastBranchingTextBoxActionPerformed(evt);
            }
        });
        editMenu.add(FastBranchingTextBox);

        menuBar.add(editMenu);

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
            File file = openFileChooser.getSelectedFile();
            if (!UIChecking) {
                prog = new Program(file.getAbsolutePath());
                prog.init();
            } 
            System.out.println("File " + file.getName() + " opened!");
        } else {
            System.out.println("File access cancelled by user.");
        }
    }//GEN-LAST:event_openMenuItemActionPerformed

    private void IFFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IFFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_IFFieldActionPerformed

    private void EXFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EXFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_EXFieldActionPerformed

    private void IDFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IDFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_IDFieldActionPerformed

    private void MEMFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MEMFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_MEMFieldActionPerformed

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
            if(!fileName.substring(fileName.length() - 3).equals("jpg")){
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
        JOptionPane.showMessageDialog(
            null, 
            "Restarting the program!", 
            "Pipeline configured ",
            JOptionPane.INFORMATION_MESSAGE
        );
        SystemVars resetSystemVars = new SystemVars();   
    }
    
    private void ForwardingCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ForwardingCheckBoxActionPerformed
        SystemVars.forwardingEnabled = !SystemVars.forwardingEnabled;
        resetProgram();
    }//GEN-LAST:event_ForwardingCheckBoxActionPerformed

    private void FastBranchingTextBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FastBranchingTextBoxActionPerformed
        SystemVars.fastBranching = !SystemVars.fastBranching;
        resetProgram();
    }//GEN-LAST:event_FastBranchingTextBoxActionPerformed

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
        prog = new Program("test_files/simpleTest");
        prog.init();
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new mainWindow().setVisible(true);
            }
        });

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
    private javax.swing.JMenuItem contentsMenuItem;
    private javax.swing.JMenu editMenu;
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JFileChooser openFileChooser;
    private javax.swing.JMenuItem openMenuItem;
    private javax.swing.JFrame pipelineDepthEditor;
    private javax.swing.JMenuItem pipelineMenuItem;
    private javax.swing.JMenuItem saveAsMenuItem;
    private javax.swing.JFileChooser saveImageChooser;
    private mips.StageJPanel stageJPanel1;
    // End of variables declaration//GEN-END:variables

}
