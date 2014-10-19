/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mips;

/**
 *
 * @author vedratn
 */
public class Jal extends R0 implements Cloneable{

    /**
     *
     * @param l
     * @param i
     */
    public Jal(String l, int i) {
        super(l,i);
        isLink = true;
    }
    
    /**
     *
     * @param j
     */
    public Jal(J j){
        super(j);
    }
    
}
