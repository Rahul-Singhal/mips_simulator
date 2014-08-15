import java.util.*;

public class Instruction {

  public String name;
  public Instruction(String instr_name) {
    name = instr_name;
  }

  public void print() {
    System.out.println(name);
  }
}