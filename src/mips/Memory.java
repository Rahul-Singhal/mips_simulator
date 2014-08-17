/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mips;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author vedratn
 */
class Memory {
  
  ArrayList<Character> store = new ArrayList<Character>();
  int freePointer; 
  Map<String, Pair<Integer, Integer> > memoryMap = new HashMap<>();
  
  public Memory(){
        /*store.assign(10485760,(char)0);
        freePointer = 0;*/
      store = new ArrayList<>(10000);
      for(int i = 0; i < 10000; i++){
          store.add((char)0);
      }
  }

  void storeWord(int address, int word){
      
  }
  
  int loadWord(int address){
      return 0;
  }

  void storeByte(int address, char memByte){
    /// CHANGE IN DEFINITION : REPLACE BYTE BY MEM_BYTE
      
  }
  
  char loadByte(int address){
      return '0';
  }

  int loadAddress(String label){
      return 0;
  }
  int storeAscii(String label, String ascii){
      return 0;
  }
  
  int storeAsciiz(String label, String asciiz){
      return 0;
  }
  
  String getString(int address){
      return "VED";
  }

  int storeWords(String label, ArrayList<Integer> v){
      return 0;
  }
  
  int allocateSpace(String label, int count){
      return 0;
  }

  int storeBytes(String label, ArrayList<Character> v){
      return 0;
  }
}
