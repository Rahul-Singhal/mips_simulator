/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mips;

/**
 *
 * @author vedratn
 * @param <F>
 * @param <S>
 */
public class Pair<F,S> {
    F first;
    S second;
    
    /**
     *
     * @param f
     * @param s
     */
    public Pair(F f, S s){
        first = f;
        second = s;
    }
}
