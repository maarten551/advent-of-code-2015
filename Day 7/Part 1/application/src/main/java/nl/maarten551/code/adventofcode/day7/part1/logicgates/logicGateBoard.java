package nl.maarten551.code.adventofcode.day7.part1.logicgates;

import java.util.HashMap;

public class LogicGateBoard {
    HashMap<String, Character> emulatedMemory = new HashMap<String, Character>(); //Char is being used because Java doesn't support unsigned shorts :| and char is simply exactly that

    public LogicGateBoard() {
        this.emulatedMemory.put("b", (char) 3176);
    }

    public Character getEmulatedMemoryValue(String address) {
        return emulatedMemory.get(address);
    }

    public void setEmulatedMemoryValue(String address, char value) {
        if(this.getEmulatedMemoryValue(address) == null) {
            this.emulatedMemory.put(address, value);
        }
    }

    public void printMemory() {
        for(String address : this.emulatedMemory.keySet()) {
            System.out.println(String.format("%s : %d", address, (int)this.emulatedMemory.get(address)));
        }
    }
}
