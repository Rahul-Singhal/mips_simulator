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
public class Jalr extends R1 implements Cloneable{
    public Jalr(int rsIndex, int id) {
        super(rsIndex,id);
        this.isLink = true;
    }
    
    public Jalr(Jr jr){
        super(jr);
    }
}