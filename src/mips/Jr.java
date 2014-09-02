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
public class Jr extends R1 implements Cloneable{
    public Jr(int rsIndex, int id) {
        super(rsIndex,id);
        this.isLink = false;
    }
    
    public Jr(Jr jr){
        super(jr);
    }
}
