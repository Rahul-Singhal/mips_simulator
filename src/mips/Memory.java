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
  
    char[] store = new char[10485760];
    int freePointer; 
    Map<String, Pair<Integer, Integer> > memoryMap = new HashMap<>();

    public Memory(){
        freePointer = 0;
    }

    void storeWord(int address, int word){
        if (address >= 0 && address + 4 <= store.length) {
            store[address + 3] = (char) ((word >> 0) & 0xFF);
            store[address + 2] = (char) ((word >> 8) & 0xFF);
            store[address + 1] = (char) ((word >> 16) & 0xFF);
            store[address] = (char) ((word >> 24) & 0xFF);
        } else {
            System.out.printf("Memory out of bounds exception! %d\n", address);
            System.exit(0);
        }
    }

    // Mars checks word and halfword boundaries for these functions. 
    // Should we also do that?
    void storeHalfWord(int address, int word){
        if (address >= 0 && address + 4 <= store.length) {
            store[address + 3] = (char) ((word >> 0) & 0xFF);
            store[address+2] = (char) ((word >> 8) & 0xFF);
        } else {
            System.out.printf("Memory out of bounds exception! %d\n", address);
            System.exit(0);
        }
    }
    
    int loadWord(int address){
        if (address >= 0 && address + 4 <= store.length) {
            int Int32 = 0;
            Int32 = (Int32 << 8) + store[address];
            Int32 = (Int32 << 8) + store[address + 1];
            Int32 = (Int32 << 8) + store[address + 2];
            Int32 = (Int32 << 8) + store[address + 3];
            return Int32;
        } else {
            System.out.printf("Memory out of bounds exception! %d\n", address);
            System.exit(0);
        }
        // returning garbage here, program should never reach here      
        return 0;
    }

    int loadHalfWord(int address){
        if (address >= 0 && address + 4 <= store.length) {
            int Int32 = 0;
            if((int)store[address+3] >= 128 )
                Int32 = -1;
            Int32 = (Int32 << 8) + store[address+2];
            Int32 = (Int32 << 8) + store[address+3];
            return Int32;
        } else {
            System.out.printf("Memory out of bounds exception! %d\n", address);
            System.exit(0);
        }
        // returning garbage here, program should never reach here      
        return 0;
    }
    
    int loadUnsignedHalfWord(int address){
        if (address >= 0 && address + 4 <= store.length) {
            int Int32 = 0;
            Int32 = (Int32 << 8) + store[address+2];
            Int32 = (Int32 << 8) + store[address+3];
            return Int32;
        } else {
            System.out.printf("Memory out of bounds exception! %d\n", address);
            System.exit(0);
        }
        // returning garbage here, program should never reach here      
        return 0;
    }
    
    void storeByte(int address, char memByte){
      /// CHANGE IN DEFINITION : REPLACE BYTE BY MEM_BYTE
        if (address >= 0 && address + 1 <= store.length) {
            store[address+3] = memByte;
        } else {
            System.out.printf("Memory out of bounds exception! %d\n", address);
            System.exit(0);
        }

    }

    int loadUnsignedByte(int address){
        if (address >= 0 && address+4 <= store.length) {
            int Int32 = 0;
            Int32 = (Int32 << 8) + store[address+3];
            return Int32;
        } else {
            System.out.printf("Memory out of bounds exception! %d\n", address);
            System.exit(0);
        }
        // returning garbage here, program should never reach here      
        return 0;
    }
    
    int loadByte(int address){
        if (address >= 0 && address+4 <= store.length) {
            int Int32 = 0;
            if((int)store[address+3] >= 128)
                Int32 = -1;
            Int32 = (Int32 << 8) + store[address+3];
            return Int32;
        } else {
            System.out.printf("Memory out of bounds exception! %d\n", address);
            System.exit(0);
        }
        // returning garbage here, program should never reach here      
        return 0;
    }

    int loadAddress(String label){
        return memoryMap.get(label).second;
    }
    
    int storeAscii(String label, String ascii){
        int place = freePointer;
        if (freePointer + ascii.length() <= store.length) {
            int i = 0;
            while (i < ascii.length()) {
                store[freePointer] = ascii.charAt(i);
                i++;
                freePointer++;
            }
            memoryMap.put(label, new Pair<>(1, place));
            return place;
        } else {
            System.out.printf("Memory out of bounds exception!\n");
            System.exit(0);
        }
      // returning garbage 0 here, program should never reach here      
        return 0;
    }

    int storeAsciiz(String label, String asciiz){
        int place = freePointer;
        if (freePointer + asciiz.length() <= store.length) {
            int i = 0;
            while (i < asciiz.length()) {
                store[freePointer] = asciiz.charAt(i);
                i++;
                freePointer++;
            }
            store[freePointer] = '\0';
            freePointer++;
            memoryMap.put(label, new Pair<>(2, place));
            return place;
        } else {
            System.out.printf("Memory out of bounds exception!\n");
            System.exit(0);
        }
        // returning garbage here, program should never reach here      
        return 0;
    }

    String getString(int address){
        if (address > 0 && address < store.length) {
            int stringLength = 0;
            while (stringLength+address < store.length - 1 && store[stringLength+address] != '\0') {
                stringLength++;
            }
            String str = new String(store, address, stringLength);
            return str;
        } else {
            System.out.printf("Memory out of bounds exception!\n");
            System.exit(0);
        }
        // returning garbage here, program should never reach here      
        return new String();
    }

    int storeWords(String label, ArrayList<Integer> v){
        int place = freePointer;
        if (freePointer + v.size() * 4 <= store.length) {
            int i = 0;
            while (i < v.size()) {
                storeWord(freePointer, v.get(i));
                freePointer += 4;
                i++;
            }
            memoryMap.put(label, new Pair<>(7, place));
            return place;
        } else {
            System.out.printf("Memory out of bounds exception!\n");
            System.exit(0);
        }
        // returning garbage here, program should never reach here      
        return 0;
    }

    int allocateSpace(String label, int count){
        int place = freePointer;
        if (freePointer + count <= store.length) {
            freePointer += count;
            memoryMap.put(label, new Pair<>(8, place));
            return place;
        } else {
            System.out.printf("Memory out of bounds exception!\n");
            System.exit(0);
        }
        // returning garbage here, program should never reach here      
        return 0;
    }

    int storeBytes(String label, ArrayList<Character> v){
        int place = freePointer;
        if (freePointer + v.size() <= store.length) {
            int i = 0;
            while (i < v.size()) {
                storeByte(freePointer, v.get(i));
                freePointer += 1;
                i++;
            }
            memoryMap.put(label, new Pair<>(3, place));
            return place;
        } else {
            System.out.printf("Memory out of bounds exception!\n");
            System.exit(0);
        }
        // returning garbage here, program should never reach here      
        return 0;
    }
}
