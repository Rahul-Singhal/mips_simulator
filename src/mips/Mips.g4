grammar Mips;

options {
    language = Java;
}

@header {
//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
//!!!                                                            !!!
//!!! THIS CODE IS AUTOMATICALLY GENERATED! DO NOT MODIFY!       !!!
//!!! Please refer to file Mips.g4 for grammar documentation.     !!!
//!!!                                                            !!!
//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

package mips;
import java.util.*;
}

@parser::members {
/**
 * Array to hold instructions while parsing the input MIPS code.
 */
private final ArrayList<Instruction> instructions = new ArrayList<>();

/**
 * Map to store the instruction index of a code block with its label.
 */
private final HashMap<String, Integer> labelMap = new HashMap<>();

/**
 * Map to maintain a record of the code block labels used
 * along with the line in which they are used. 
 * Used to check whether all used labels are present in the code or not.
 */
private final HashMap<String, Integer> usedLabels = new HashMap<>();

/**
 * Map to store the names of data variables declared. 
 * Used to prevent double declaration of same variable 
 * and to check the existence of a variable name.
 */
private final HashMap<String, Integer> dataVarsMap = new HashMap<>();

/**
 * Instance of Memory class to store values of data variables.
 */
private final Memory memory = new Memory();

/**
 * Variable to maintain the number of instructions parsed.
 */
private Integer instructionIndex = 0;

/**
 * Adds a data variable to dataVarsMap
 * Gives an error if the data variable was already declared.
 */
private void addDataVariable(String name, int line) {
    if (dataVarsMap.get(name) == null) {
        dataVarsMap.put(name, 0);
    } else {
        System.out.println("Error in line " + line + ", variable \"" + name + "\" already exists.");
        System.exit(0);
    }
}

/**
 * Adds a label to labelMap with the corresponding instruction number.
 * Gives an error if the label was already declared.
 */
private void addLabel(String name, int num, int line) {
    if (labelMap.get(name) == null) {
        labelMap.put(name, num);
    } else {
        System.out.println("Error in line " + line + ", label \"" + name + "\" already exists.");
        System.exit(0);
    }
}

/**
 * Checks whether all the used labels have been declared with their code blocks or not.
 */
public void checkLabels() {
    // Check labels in text section
    Iterator it = usedLabels.entrySet().iterator();
    while (it.hasNext()) {
        Map.Entry pairs = (Map.Entry)it.next();
        if (labelMap.get(pairs.getKey()) == null) {
            System.out.println("Error in line " + pairs.getValue() + ", label \"" + pairs.getKey() + "\" does not exist.");
            System.exit(0);                
        }
    }
}

/**
 * Returns object of Memory class with values of data variables stored.
 * @return Memory
 */
public Memory getMemory() {
    return memory;
}

/**
 * Returns parsed instruction list
 * @return ArrayList<Instruction>
 */
public ArrayList<Instruction> getInstructions() {
    return instructions;
}

/**
 * Returns map of label with corresponding instruction numbers.
 * @return HashMap<String, Integer>
 */
public HashMap<String, Integer> getLabelMap() {
    return labelMap;
}
}


/** 
  GRAMMAR BEGINS
*/

prog    : (data)? text
        ;

/** 
  DATA SECTION
*/

data    : '.data' var_list 
        ;

var_list    : (var_decl)* 
            ; 


var_decl  : 
    ascii_var
    | asciiz_var
    | word_var 
    | space_var 
    | byte_var
    ;

ascii_var   : 
    l=IDENTIFIER ':' '.ascii' s=null_terminated_str
        {
            memory.storeAscii($l.text, $s.str);
            addDataVariable($l.text, $l.getLine());
        }
    ;

asciiz_var  : 
    l=IDENTIFIER ':' '.asciiz' s=quoted_str 
        {
            memory.storeAsciiz($l.text, $s.str);
            addDataVariable($l.text, $l.getLine());
        }
    ;

word_var    : 
    l=IDENTIFIER ':' '.word' int_list 
        {
            memory.storeWords($l.text, $int_list.vec);
            addDataVariable($l.text, $l.getLine());
        }
    | l=IDENTIFIER ':' '.word' 
        {
            memory.storeWords($l.text, new ArrayList<Integer>());
            addDataVariable($l.text, $l.getLine());
        }
    ;

int_list returns [ArrayList<Integer> vec]  : 
    a=integer 
        {
            Integer num = Integer.valueOf($a.text);
            $vec = new ArrayList<Integer>();      
            $vec.add(num);
        }
    | list=int_list ',' a=integer
        {
            Integer num = Integer.valueOf($a.text);
            $list.vec.add(num);
            $vec = $list.vec; 
        }
    ;

space_var   : 
    l=IDENTIFIER ':' '.space' a=POSINTEGER 
        {
            memory.allocateSpace($l.text, Integer.valueOf($a.text));
            addDataVariable($l.text, $l.getLine());
        }
    ;

byte_var  : 
    l=IDENTIFIER ':' '.byte' byte_list
        {
            memory.storeBytes($l.text, $byte_list.vec);
            addDataVariable($l.text, $l.getLine());
        }
    | l=IDENTIFIER ':' '.byte'
        {
            memory.storeBytes($l.text, new ArrayList<Character>());
            addDataVariable($l.text, $l.getLine());
        }
    ;

byte_list  returns [ArrayList<Character> vec]  : 
    b=byte_val
        {
            $vec = new ArrayList<Character>();
            $vec.add($b.val);
        } 
    | list=byte_list ',' b=byte_val
        {
            $list.vec.add($b.val);
            $vec = $list.vec; 
        }
    ;

byte_val returns [Character val] :
    '\'' a=. '\''
        {
            $val = $a.text.charAt(0); 
        }
    | i=integer
        {
            $val = (char) (Integer.valueOf($i.text) % 256);
        }
    ;

/**
  TEXT SECTION
*/

text    : '.text' label_list ;

label_list  : (label)* ;

label   : 
    l=IDENTIFIER ':' stmt_list 
        {
            addLabel($l.text, instructionIndex, $l.getLine());
            instructionIndex = instructions.size();
        }
    ;

stmt_list   : (stmt)* ;

stmt    : add_stmt
        | addi_stmt
        | addiu_stmt
        | addu_stmt
        | mult_stmt
        | multu_stmt
        | div_stmt
        | divu_stmt
        | sub_stmt
        | subu_stmt
        | and_stmt
        | andi_stmt
        | nor_stmt
        | or_stmt
        | ori_stmt
        | xor_stmt
        | xori_stmt
        | sll_stmt
        | sllv_stmt
        | srl_stmt
        | srlv_stmt
        | sra_stmt
        | srav_stmt
        | slt_stmt
        | slti_stmt
        | sltiu_stmt
        | sltu_stmt
        | beq_stmt
        | bne_stmt
        | blt_stmt
        | bgt_stmt
        | ble_stmt
        | bge_stmt
        | j_stmt
        | jal_stmt
        | jr_stmt
        | jalr_stmt
        | move_stmt
        | lb_stmt
        | lbu_stmt
        | lh_stmt
        | lhu_stmt
        | lui_stmt
        | lw_stmt
        | li_stmt
        | la_stmt
        | sb_stmt
        | sw_stmt
        | sh_stmt
        | exit_stmt
        ;

/**
  ARITHMETIC 
*/

add_stmt  : 'add' rd=REG (',')? rs=REG (',')? rt=REG 
    {
        Add instr = new Add(
            Register.registerToInteger($rd.text),
            Register.registerToInteger($rs.text),
            Register.registerToInteger($rt.text),
            0
        );
        instructions.add(instr);
    };
    

addi_stmt : 'addi' rd=REG (',')? rs=REG (',')? i=integer 
    {
        Addi instr = new Addi(
            Register.registerToInteger($rd.text),
            Register.registerToInteger($rs.text),
            Integer.valueOf($i.text),
            0
        );
        instructions.add(instr);
    };


addiu_stmt : 'addiu' rd=REG (',')? rs=REG (',')? i=integer 
    {
        Addiu instr = new Addiu(
            Register.registerToInteger($rd.text),
            Register.registerToInteger($rs.text),
            Integer.valueOf($i.text),
            0
        );
        instructions.add(instr);
    };


addu_stmt : 'addu' rd=REG (',')? rs=REG (',')? rt=REG 
    {
        Addu instr = new Addu(
            Register.registerToInteger($rd.text),
            Register.registerToInteger($rs.text),
            Register.registerToInteger($rt.text),
            0
        );
        instructions.add(instr);
    };

sub_stmt  : 'sub' rd=REG (',')? rs=REG (',')? rt=REG  
    {
        Sub instr = new Sub(
            Register.registerToInteger($rd.text),
            Register.registerToInteger($rs.text),
            Register.registerToInteger($rt.text),
            0
        );
        instructions.add(instr);
    }; 

subu_stmt : 'subu' rd=REG (',')? rs=REG (',')? rt=REG  
    {
        Subu instr = new Subu(
            Register.registerToInteger($rd.text),
            Register.registerToInteger($rs.text),
            Register.registerToInteger($rt.text),
            0
        );
        instructions.add(instr);
    }; 

mult_stmt  : 'mult' rd=REG (',')? rs=REG (',')? rt=REG  
    {
        Mult instr = new Mult(
            Register.registerToInteger($rd.text),
            Register.registerToInteger($rs.text),
            Register.registerToInteger($rt.text),
            0
        );
        instructions.add(instr);
    };

multu_stmt : 'multu' rd=REG (',')? rs=REG (',')? rt=REG  
    {
        Multu instr = new Multu(
            Register.registerToInteger($rd.text),
            Register.registerToInteger($rs.text),
            Register.registerToInteger($rt.text),
            0
        );
        instructions.add(instr);
    };

div_stmt  : 'div' rd=REG (',')? rs=REG (',')? rt=REG  
    {
        Div instr = new Div(
            Register.registerToInteger($rd.text),
            Register.registerToInteger($rs.text),
            Register.registerToInteger($rt.text),
            0
        );
        instructions.add(instr);
    };

divu_stmt : 'divu' rd=REG (',')? rs=REG (',')? rt=REG  
    {
        Divu instr = new Divu(
            Register.registerToInteger($rd.text),
            Register.registerToInteger($rs.text),
            Register.registerToInteger($rt.text),
            0
        );
        instructions.add(instr);
    };


/**
  LOGICAL 
*/

and_stmt  : 'and' rd=REG (',')? rs=REG (',')? rt=REG  
    {
        And instr = new And(
            Register.registerToInteger($rd.text),
            Register.registerToInteger($rs.text),
            Register.registerToInteger($rt.text),
            0
        );
        instructions.add(instr);
    };

andi_stmt : 'andi' rd=REG (',')? rs=REG (',')? i=integer 
    {
        Andi instr = new Andi(
            Register.registerToInteger($rd.text),
            Register.registerToInteger($rs.text),
            Integer.valueOf($i.text),
            0
        );
        instructions.add(instr);
    };

nor_stmt  : 'nor' rd=REG (',')? rs=REG (',')? rt=REG  
    {
        Nor instr = new Nor(
            Register.registerToInteger($rd.text),
            Register.registerToInteger($rs.text),
            Register.registerToInteger($rt.text),
            0
        );
        instructions.add(instr);
    };

or_stmt : 'or' rd=REG (',')? rs=REG (',')? rt=REG  
    {
        Or instr = new Or(
            Register.registerToInteger($rd.text),
            Register.registerToInteger($rs.text),
            Register.registerToInteger($rt.text),
            0
        );
        instructions.add(instr);
    };

ori_stmt : 'ori' rd=REG (',')? rs=REG (',')? i=integer 
    {
        Ori instr = new Ori(
            Register.registerToInteger($rd.text),
            Register.registerToInteger($rs.text),
            Integer.valueOf($i.text),
            0
        );
        instructions.add(instr);
    };

xor_stmt : 'xor' rd=REG (',')? rs=REG (',')? rt=REG  
    {
        Xor instr = new Xor(
            Register.registerToInteger($rd.text),
            Register.registerToInteger($rs.text),
            Register.registerToInteger($rt.text),
            0
        );
        instructions.add(instr);
    };

xori_stmt : 'xori' rd=REG (',')? rs=REG (',')? i=integer 
    {
        Xori instr = new Xori(
            Register.registerToInteger($rd.text),
            Register.registerToInteger($rs.text),
            Integer.valueOf($i.text),
            0
        );
        instructions.add(instr);
    };


/**
  BIT SHIFTING
*/

sll_stmt : 'sll' rd=REG (',')? rs=REG (',')? i=POSINTEGER  
    {
        Sll instr = new Sll(
            Register.registerToInteger($rd.text),
            Register.registerToInteger($rs.text),
            Integer.valueOf($i.text),
            0
        );
        instructions.add(instr);
    };

sllv_stmt : 'sllv' rd=REG (',')? rs=REG (',')? rt=REG  
    {
        Sllv instr = new Sllv(
            Register.registerToInteger($rd.text),
            Register.registerToInteger($rs.text),
            Register.registerToInteger($rt.text),
            0
        );
        instructions.add(instr);
    };

srl_stmt : 'srl' rd=REG (',')? rs=REG (',')? i=POSINTEGER 
    {
        Srl instr = new Srl(
            Register.registerToInteger($rd.text),
            Register.registerToInteger($rs.text),
            Integer.valueOf($i.text),
            0
        );
        instructions.add(instr);
    };

srlv_stmt : 'srlv' rd=REG (',')? rs=REG (',')? rt=REG  
    {
        Srlv instr = new Srlv(
            Register.registerToInteger($rd.text),
            Register.registerToInteger($rs.text),
            Register.registerToInteger($rt.text),
            0
        );
        instructions.add(instr);
    };

sra_stmt : 'sra' rd=REG (',')? rs=REG (',')? i=POSINTEGER 
    {
        Sra instr = new Sra(
            Register.registerToInteger($rd.text),
            Register.registerToInteger($rs.text),
            Integer.valueOf($i.text),
            0
        );
        instructions.add(instr);
    };

srav_stmt : 'srav' rd=REG (',')? rs=REG (',')? rt=REG  
    {
        Srav instr = new Srav(
            Register.registerToInteger($rd.text),
            Register.registerToInteger($rs.text),
            Register.registerToInteger($rt.text),
            0
        );
        instructions.add(instr);
    };

/***************************/

slt_stmt : 'slt' rd=REG (',')? rs=REG (',')? rt=REG  
    {
        Slt instr = new Slt(
            Register.registerToInteger($rd.text),
            Register.registerToInteger($rs.text),
            Register.registerToInteger($rt.text),
            0
        );
        instructions.add(instr);
    };

slti_stmt : 'slti' rd=REG (',')? rs=REG (',')? i=integer 
    {
        Slti instr = new Slti(
            Register.registerToInteger($rd.text),
            Register.registerToInteger($rs.text),
            Integer.valueOf($i.text),
            0
        );
        instructions.add(instr);
    };

sltiu_stmt : 'sltiu' rd=REG (',')? rs=REG (',')? i=integer 
    {
        Sltiu instr = new Sltiu(
            Register.registerToInteger($rd.text),
            Register.registerToInteger($rs.text),
            Integer.valueOf($i.text),
            0
        );
        instructions.add(instr);
    };

sltu_stmt : 'sltu' rd=REG (',')? rs=REG (',')? rt=REG  
    {
        Sltu instr = new Sltu(
            Register.registerToInteger($rd.text),
            Register.registerToInteger($rs.text),
            Register.registerToInteger($rt.text),
            0
        );
        instructions.add(instr);
    };


/**
  BRANCH
*/

beq_stmt : 'beq' rs=REG (',')? rt=REG (',')? l=IDENTIFIER 
    {
        Beq instr = new Beq(
            Register.registerToInteger($rs.text),
            Register.registerToInteger($rt.text),
            $l.text,
            0
        );  
        instructions.add(instr); 
        usedLabels.put($l.text, $l.getLine());
    };

bne_stmt : 'bne' rs=REG (',')? rt=REG (',')? l=IDENTIFIER 
    {
        Bne instr = new Bne(
            Register.registerToInteger($rs.text),
            Register.registerToInteger($rt.text),
            $l.text,
            0
        );  
        instructions.add(instr); 
        usedLabels.put($l.text, $l.getLine());
    };

blt_stmt : 'blt' rs=REG (',')? rt=REG (',')? l=IDENTIFIER 
    {
        // Pseudo instruction
        Slt instr1 = new Slt(
            1, 
            Register.registerToInteger($rs.text),
            Register.registerToInteger($rt.text),
            0
        );  
        instructions.add(instr1); 
        Bne instr2 = new Bne(
            1,
            0,
            $l.text,
            0
        );  
        instructions.add(instr2); 
        usedLabels.put($l.text, $l.getLine());
    };

bgt_stmt : 'bgt' rs=REG (',')? rt=REG (',')? l=IDENTIFIER 
    {
        // Pseudo instruction
        Slt instr1 = new Slt(
            1, 
            Register.registerToInteger($rt.text),
            Register.registerToInteger($rs.text),
            0
        );  
        instructions.add(instr1); 
        Bne instr2 = new Bne(
            1,
            0,
            $l.text,
            0
        );  
        instructions.add(instr2); 
        usedLabels.put($l.text, $l.getLine());
    };

ble_stmt : 'ble' rs=REG (',')? rt=REG (',')? l=IDENTIFIER 
    {
        // Pseudo instruction
        Slt instr1 = new Slt(
            1, 
            Register.registerToInteger($rt.text),
            Register.registerToInteger($rs.text),
            0
        );  
        instructions.add(instr1); 
        Beq instr2 = new Beq(
            1,
            0,
            $l.text,
            0
        );  
        instructions.add(instr2); 
        usedLabels.put($l.text, $l.getLine());
    };

bge_stmt : 'bge' rs=REG (',')? rt=REG (',')? l=IDENTIFIER 
    {
        // Pseudo instruction
        Slt instr1 = new Slt(
            1, 
            Register.registerToInteger($rs.text),
            Register.registerToInteger($rt.text),
            0
        );  
        instructions.add(instr1); 
        Beq instr2 = new Beq(
            1,
            0,
            $l.text,
            0
        );  
        instructions.add(instr2); 
        usedLabels.put($l.text, $l.getLine());
    };


/** 
  JUMP
*/

j_stmt : 'j' l=IDENTIFIER
    {
        J instr = new J(
            $l.text,
            0
        );
        instructions.add(instr);
        usedLabels.put($l.text, $l.getLine());
    };


jal_stmt : 'jal' l=IDENTIFIER
    {
        Jal instr = new Jal(
            $l.text,
            0
        );
        instructions.add(instr);
        usedLabels.put($l.text, $l.getLine());
    };

jr_stmt : 'jr' rs=REG 
    {
        Jr instr = new Jr(
            Register.registerToInteger($rs.text),
            0
        );
        instructions.add(instr);
    };

jalr_stmt : 'jalr' rs=REG 
    {
        Jalr instr = new Jalr(
            Register.registerToInteger($rs.text),
            0
        );
        instructions.add(instr);
    };

// Pending instruction.
exit_stmt : 'exit';


/**
  LOAD MEMORY
*/

move_stmt : 'move' rd=REG (',')? rs=REG 
    {
        // Pseudo instruction
        Add instr = new Add(
            Register.registerToInteger($rd.text),
            Register.registerToInteger($rs.text),
            0, // Zero register
            0
        );
        instructions.add(instr);
    };

lb_stmt : 'lb' rt=REG (',')? a=addr 
    {
        Lb instr = new Lb(
            Register.registerToInteger($rt.text),
            $a.reg,
            $a.offset,
            0
        );
        instructions.add(instr);
    };

lbu_stmt : 'lbu' rt=REG (',')? a=addr 
    {
        Lbu instr = new Lbu(
            Register.registerToInteger($rt.text),
            $a.reg,
            $a.offset,
            0
        );
        instructions.add(instr);
    };

lh_stmt : 'lh' rt=REG (',')? a=addr 
    {
        Lh instr = new Lh(
            Register.registerToInteger($rt.text),
            $a.reg,
            $a.offset,
            0
        );
        instructions.add(instr);
    };

lhu_stmt : 'lhu' rt=REG (',')? a=addr 
    {
        Lhu instr = new Lhu(
            Register.registerToInteger($rt.text),
            $a.reg,
            $a.offset,
            0
        );
        instructions.add(instr);
    };

lui_stmt : 'lui' rt=REG (',')? i=integer 
    {
        // Pseudo instruction
        Addiu instr = new Addiu(
            Register.registerToInteger($rt.text),
            0, // Zero register
            Integer.valueOf($i.text),
            0
        );
        instructions.add(instr);
    };

lw_stmt : 'lw' rt=REG (',')? a=addr 
    {
        Lw instr = new Lw(
            Register.registerToInteger($rt.text),
            $a.reg,
            $a.offset,
            0
        );
        instructions.add(instr);
    };

li_stmt : 'li' rt=REG (',')? i=integer 
    {
        // Pseudo instruction
        Addi instr = new Addi(
            Register.registerToInteger($rt.text),
            0, // Zero register
            Integer.valueOf($i.text),
            0
        );
        instructions.add(instr);
    };

la_stmt : 'la' rt=REG (',')? l=IDENTIFIER 
    {
        if (dataVarsMap.get($l.text) == null) {
            System.out.println("Error in line " + $l.getLine() + ", label \"" + $l.text + "\" does not exist.");
            System.exit(0);                
        }

        // Pseudo instruction
        Addiu instr1 = new Addiu(
            1,
            0, // Zero register
            0,
            0
        );
        instructions.add(instr1);

        Ori instr2 = new Ori(
            Register.registerToInteger($rt.text),
            1,
            memory.loadAddress($l.text),
            0
        );
        instructions.add(instr2);
    };

/**
  STORE MEMORY
*/

sb_stmt : 'sb' rt=REG (',')? a=addr 
    {
        Sb instr = new Sb(
            Register.registerToInteger($rt.text),
            $a.reg,
            $a.offset,
            0
        );
        instructions.add(instr);
    };

sw_stmt : 'sw' rt=REG (',')? a=addr 
    {
        Sw instr = new Sw(
            Register.registerToInteger($rt.text),
            $a.reg,
            $a.offset,
            0
        );
        instructions.add(instr);
    };

sh_stmt : 'sh' rt=REG (',')? a=addr 
    {
        Sh instr = new Sh(
            Register.registerToInteger($rt.text),
            $a.reg,
            $a.offset,
            0
        );
        instructions.add(instr);
    };


/**
  COMMON TOKENS
*/ 

addr  returns [Integer offset, Integer reg]  : 
    i=integer '(' r=REG ')' 
        {
            $offset = Integer.valueOf($i.text);            
            $reg = Register.registerToInteger($r.text);
        }
    | '(' r=REG ')'
        {
            $offset = 0;            
            $reg = Register.registerToInteger($r.text);
        }
    ;

quoted_str returns [String str] : 
    '"' a=.*? '"' 
        {
            $str = $a.text;
        }
    ;

null_terminated_str returns [String str] :
    '"' a=.*? '\\0"'
        {
            $str = $a.text;
        }
    ;

integer : 
    NEGINTEGER
    | POSINTEGER
    ;

REG :  '$' TREG
    |  '$' VREG
    |  '$' SREG
    |  '$' AREG
    |  '$' KREG
    |  '$ra'
    |  '$fp'
    |  '$sp'
    |  '$gp'
    |  '$zero'
    |  '$at'
    ;

TREG  :  't' [0-9] ;

AREG  :  'a' [0-3] ;

VREG  :  'v' [0-1] ;

SREG  :  's' [0-7] ;

KREG  :  'k' [0-1] ;

IDENTIFIER  :  
    '.' (LETTER|'_'|'.') (LETTER|DIGIT|'_'|'.')*
    |   LETTER (LETTER|DIGIT|'_'|'.')*
    ;

NEGINTEGER : 
    '-' DIGIT+ 
    ;

POSINTEGER : 
    '+' DIGIT+ 
    | DIGIT+
    ;

fragment LETTER  : [a-zA-Z] ;

fragment DIGIT:  '0'..'9' ;

COMMENT : 
    '#' .*? '\r'? ('\n' | EOF) -> skip
    ;

// Match both UNIX and Windows newlines
NL      :   '\r'? '\n' -> skip;

WS      :   [ \t]+ -> skip ;
