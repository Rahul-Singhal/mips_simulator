package mips;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Date;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author rahul
 */
public class mainWindow extends javax.swing.JFrame{
    
    private int tempID;
    /**
     * Creates new form mainWindow
     */
    public mainWindow() {
        tempID = 0;
        initComponents();
        addKeyListener(new KeyListener() {
            public void keyPressed(KeyEvent e) {
                
            }

            public void keyReleased(KeyEvent e) {
                handleKeyPressEvent();
            }

            public void keyTyped(KeyEvent e) {
            }
        });
    }
    
    public void drawNextQueue(ArrayList<Instruction> a){
        stageJPanel1.drawFinishedQueue(a);
        instructionJPanel1.drawFinishedQueue(a);
    }
    
    public void addDummyInstructions(){
        Instruction inst1 = new Instruction(1,1,false,1, 20, false, 10, 20, "add $t1, $t2, $t3", 0);
        Instruction inst2 = new Instruction(4,2,false,1, 20, false, 10, 20, "hey there2", 0);
        Instruction inst3 = new Instruction(4,3,false,1, 20, false, 10, 20, "hey there3", 0);
        Instruction inst4 = new Instruction(4,4,false,1, 20, false, 10, 20, "hey there4", 0);
        Instruction inst5 = new Instruction(4,5,false,1, 20, false, 10, 20, "hey there5", 0);
        Instruction inst6 = new Instruction(4,6,false,1, 20, false, 10, 20, "hey there6", 0);
        Instruction inst7 = new Instruction(4,7,false,1, 20, false, 10, 20, "hey there7", 0);
        Instruction inst8 = new Instruction(4,8,false,1, 20, false, 10, 20, "hey there8", 0);

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
                System.out.println("Drama over, go kill yourself :P");
                return;
        }
        tempID++;
        drawNextQueue(v);
    }
    
    public void handleKeyPressEvent(){
        addDummyInstructions();
//        instructionJPanel1.handleNewInstruction(this.getHeight());
//        this.repaint();
    }
    
    public void handleScrollbarUpdate(int point){
        int height = this.getHeight();
        int width = this.getWidth();
        if(point >= height - 30){
            System.out.println("changed");
            instructionJPanel1.scrollRectToVisible(new Rectangle(0,point+30-height, width, height));
            repaint();
        }
        // code to activate/deactivate scrollbar
        //stageJPanel1.setPreferredSize(new Dimension(1000,1000));
        //stageJPanel1.revalidate();
        //stageJPanel1.repaint();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        instructionJPanel1 = new mips.InstructionJPanel();
        stageJPanel1 = new mips.StageJPanel();
        menuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        openMenuItem = new javax.swing.JMenuItem();
        saveMenuItem = new javax.swing.JMenuItem();
        saveAsMenuItem = new javax.swing.JMenuItem();
        exitMenuItem = new javax.swing.JMenuItem();
        editMenu = new javax.swing.JMenu();
        cutMenuItem = new javax.swing.JMenuItem();
        copyMenuItem = new javax.swing.JMenuItem();
        pasteMenuItem = new javax.swing.JMenuItem();
        deleteMenuItem = new javax.swing.JMenuItem();
        helpMenu = new javax.swing.JMenu();
        contentsMenuItem = new javax.swing.JMenuItem();
        aboutMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        instructionJPanel1.setBackground(new java.awt.Color(148, 133, 118));

        javax.swing.GroupLayout instructionJPanel1Layout = new javax.swing.GroupLayout(instructionJPanel1);
        instructionJPanel1.setLayout(instructionJPanel1Layout);
        instructionJPanel1Layout.setHorizontalGroup(
            instructionJPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 125, Short.MAX_VALUE)
        );
        instructionJPanel1Layout.setVerticalGroup(
            instructionJPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout stageJPanel1Layout = new javax.swing.GroupLayout(stageJPanel1);
        stageJPanel1.setLayout(stageJPanel1Layout);
        stageJPanel1Layout.setHorizontalGroup(
            stageJPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 367, Short.MAX_VALUE)
        );
        stageJPanel1Layout.setVerticalGroup(
            stageJPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 332, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(instructionJPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(stageJPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(instructionJPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(stageJPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(jPanel1);

        fileMenu.setMnemonic('f');
        fileMenu.setText("File");

        openMenuItem.setMnemonic('o');
        openMenuItem.setText("Open");
        fileMenu.add(openMenuItem);

        saveMenuItem.setMnemonic('s');
        saveMenuItem.setText("Save");
        fileMenu.add(saveMenuItem);

        saveAsMenuItem.setMnemonic('a');
        saveAsMenuItem.setText("Save As ...");
        saveAsMenuItem.setDisplayedMnemonicIndex(5);
        fileMenu.add(saveAsMenuItem);

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

        cutMenuItem.setMnemonic('t');
        cutMenuItem.setText("Cut");
        editMenu.add(cutMenuItem);

        copyMenuItem.setMnemonic('y');
        copyMenuItem.setText("Copy");
        editMenu.add(copyMenuItem);

        pasteMenuItem.setMnemonic('p');
        pasteMenuItem.setText("Paste");
        editMenu.add(pasteMenuItem);

        deleteMenuItem.setMnemonic('d');
        deleteMenuItem.setText("Delete");
        editMenu.add(deleteMenuItem);

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMenuItemActionPerformed
        System.exit(0);
    }//GEN-LAST:event_exitMenuItemActionPerformed

    /**
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
        Program prog = new Program("test_files/all_instructions");
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new mainWindow().setVisible(true);
            }
        });

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem aboutMenuItem;
    private javax.swing.JMenuItem contentsMenuItem;
    private javax.swing.JMenuItem copyMenuItem;
    private javax.swing.JMenuItem cutMenuItem;
    private javax.swing.JMenuItem deleteMenuItem;
    private javax.swing.JMenu editMenu;
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenu helpMenu;
    private mips.InstructionJPanel instructionJPanel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem openMenuItem;
    private javax.swing.JMenuItem pasteMenuItem;
    private javax.swing.JMenuItem saveAsMenuItem;
    private javax.swing.JMenuItem saveMenuItem;
    private mips.StageJPanel stageJPanel1;
    // End of variables declaration//GEN-END:variables


}
