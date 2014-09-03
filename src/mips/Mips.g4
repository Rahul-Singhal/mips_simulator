grammar Mips;

options {
  language = Java;
}

@header {
  package mips;
  import java.util.*;
}

@members {
    public ArrayList<Instruction> instructions = new ArrayList<Instruction>();
    public HashMap<String, Integer> labelMap = new HashMap<String, Integer>();
    public HashMap<String, Integer> usedLabels = new HashMap<String, Integer>();
    public Integer instructionIndex = 0;
}

prog  : (text)? data
      | (data)? text
      ;

/** 
  DATA SECTION
*/

data  : '.data' var_list ;

var_list  : (var_decl)* ; 

var_decl  : IDENTIFIER ':' var_init ; 

var_init  : ascii_var
          | asciiz_var
          | word_var 
          | space_var 
          | byte_var
          ;

ascii_var  : '.ascii' STRING ;

asciiz_var  : '.asciiz' STRING ;

word_var  : '.word' int_list 
            {
              System.out.println("Number List");
              for (Integer val : $int_list.vec) {
                System.out.print(val + "  ");
              }
              System.out.println();
            }
          | '.word' ;

int_list  returns [Vector<Integer> vec]  : 
  a=INTEGER 
    {
      Integer num = Integer.valueOf($a.text);
      $vec = new Vector<Integer>();      
      $vec.addElement(num);
    }
  | list=int_list ',' a=INTEGER
    {
      Integer num = Integer.valueOf($a.text);
      $list.vec.addElement(num);
      $vec = $list.vec; 
    }
  ;

space_var  : '.space' INTEGER ;

byte_var  : '.byte' byte_list
            {
              System.out.println("Character List");
              for (Character val : $byte_list.vec) {
                System.out.print(val + "  ");
              }
              System.out.println();
            }
          | '.byte'
          ;

byte_list  returns [Vector<Character> vec]  : 
  '\'' a=. '\''
    {
      $vec = new Vector<Character>();
      $vec.addElement($a.text.charAt(0));
    } 
  | list=byte_list ',' '\'' a=. '\''
    {
      $list.vec.addElement($a.text.charAt(0));
      $vec = $list.vec; 
    }
  ;


/**
  TEXT SECTION
*/

text  : '.text' label_list ;

label_list  : (label)* ;

label  : l=IDENTIFIER ':' stmt_list 
    {
        if (instructions.size() > instructionIndex) {
            labelMap.put($l.text,instructionIndex);
            instructionIndex = instructions.size();
        }
        else if (instructions.size() == instructionIndex) {
            labelMap.put($l.text,-1);
        }
    };

stmt_list  : (stmt)* ;

stmt  : add_stmt
      | addi_stmt
      | addiu_stmt
      | addu_stmt
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
      | srl_stmt
      | sra_stmt
      | sllb_stmt
      | srlb_stmt
      | srab_stmt
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
    

addi_stmt : 'addi' rd=REG (',')? rs=REG (',')? i=INTEGER 
    {
        Addi instr = new Addi(
            Register.registerToInteger($rd.text),
            Register.registerToInteger($rs.text),
            Integer.valueOf($i.text),
            0
        );
        instructions.add(instr);
    };


addiu_stmt : 'addiu' rd=REG (',')? rs=REG (',')? i=INTEGER 
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

andi_stmt : 'andi' 'addiu' rd=REG (',')? rs=REG (',')? i=INTEGER 
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

ori_stmt : 'ori' 'addiu' rd=REG (',')? rs=REG (',')? i=INTEGER 
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

xori_stmt : 'xori' 'addiu' rd=REG (',')? rs=REG (',')? i=INTEGER 
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

sll_stmt : 'sll' rd=REG (',')? rs=REG (',')? rt=REG  
    {
        Sll instr = new Sll(
            Register.registerToInteger($rd.text),
            Register.registerToInteger($rs.text),
            Register.registerToInteger($rt.text),
            0
        );
        instructions.add(instr);
    };

srl_stmt : 'srl' rd=REG (',')? rs=REG (',')? rt=REG  
    {
        Srl instr = new Srl(
            Register.registerToInteger($rd.text),
            Register.registerToInteger($rs.text),
            Register.registerToInteger($rt.text),
            0
        );
        instructions.add(instr);
    };

sra_stmt : 'sra' rd=REG (',')? rs=REG (',')? rt=REG  
    {
        Sra instr = new Sra(
            Register.registerToInteger($rd.text),
            Register.registerToInteger($rs.text),
            Register.registerToInteger($rt.text),
            0
        );
        instructions.add(instr);
    };

/*** Pending instructions ***/

sllb_stmt : 'sllb' rd=REG (',')? rs=REG (',')? rt=REG  ;

srlb_stmt : 'srlb' rd=REG (',')? rs=REG (',')? rt=REG  ;

srab_stmt : 'srab' rd=REG (',')? rs=REG (',')? rt=REG  ;

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

slti_stmt : 'slti' 'addiu' rd=REG (',')? rs=REG (',')? i=INTEGER 
    {
        Slti instr = new Slti(
            Register.registerToInteger($rd.text),
            Register.registerToInteger($rs.text),
            Integer.valueOf($i.text),
            0
        );
        instructions.add(instr);
    };

sltiu_stmt : 'sltiu' 'addiu' rd=REG (',')? rs=REG (',')? i=INTEGER 
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
    };

jalr_stmt : 'jalr' rs=REG 
    {
        Jalr instr = new Jalr(
            Register.registerToInteger($rs.text),
            0
        );
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

lui_stmt : 'lui' rt=REG (',')? i=INTEGER 
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

li_stmt : 'li' rt=REG (',')? i=INTEGER 
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

// Pending Instruction
la_stmt : 'la' REG (',')? IDENTIFIER ;


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
  i=INTEGER '(' r=REG ')' 
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

REG  :  '$' TREG
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

STRING : '"' .*? '"' ;

IDENTIFIER  :  '.' (LETTER|'_'|'.') (LETTER|DIGIT|'_'|'.')*
            |   LETTER (LETTER|DIGIT|'_'|'.')*
            ;

INTEGER  : [-]? DIGIT+ [Ll]? ;

fragment LETTER  : [a-zA-Z] ;

fragment DIGIT:  '0'..'9' ;

COMMENT :   '#' .*? '\r'? '\n' -> skip ;

// Match both UNIX and Windows newlines
NL      :   '\r'? '\n' -> skip;

WS      :   [ \t]+ -> skip ;
