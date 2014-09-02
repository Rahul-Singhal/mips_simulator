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
public class J extends R0 implements Cloneable{
    public J(String l, int i) {
        super(l,i);
    }
    
    public J(J j){
        super(j);
    }
    
}
