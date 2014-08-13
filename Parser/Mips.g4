

grammar Mips;

prog : text ;

text  :	'.text' label_list
		  ;

label_list  : (label)* ;

label   : IDENTIFIER ':' stmt_list
		    ;

stmt_list	: (stmt)* ;

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
      ;

/**
  ARITHMETIC 
*/

add_stmt  : 'add' REG REG REG ;

addi_stmt : 'addi' REG REG INTEGER ;

addiu_stmt : 'addiu' REG REG INTEGER ;

addu_stmt : 'addu' REG REG REG ;

sub_stmt  : 'sub' REG REG REG ;

subu_stmt : 'subu' REG REG REG ;

mult_stmt  : 'mult' REG REG REG ;

multu_stmt : 'multu' REG REG REG ;

div_stmt  : 'div' REG REG REG ;

divu_stmt : 'divu' REG REG REG ;



/**
  LOGICAL 
*/

and_stmt  : 'and' REG REG REG ;

andi_stmt : 'andi' REG REG INTEGER ;

nor_stmt  : 'nor' REG REG REG ;

or_stmt : 'or' REG REG REG ;

ori_stmt : 'ori' REG REG INTEGER ;

xor_stmt : 'xor' REG REG REG ;

xori_stmt : 'xori' REG REG INTEGER ;

/**
  BIT SHIFTING
*/

sll_stmt : 'sll' REG REG REG ;

srl_stmt : 'srl' REG REG REG ;

sra_stmt : 'sra' REG REG REG ;

sllb_stmt : 'sllb' REG REG REG ;

srlb_stmt : 'srlb' REG REG REG ;

srab_stmt : 'srab' REG REG REG ;

slt_stmt : 'slt' REG REG REG ;

slti_stmt : 'slti' REG REG INTEGER ;

sltiu_stmt : 'sltiu' REG REG INTEGER ;

sltu_stmt : 'sltu' REG REG REG ;

/**
  BRANCH
*/

beq_stmt : 'beq' REG REG IDENTIFIER ;

bne_stmt : 'bne' REG REG IDENTIFIER ;

blt_stmt : 'blt' REG REG IDENTIFIER ;

bgt_stmt : 'bgt' REG REG IDENTIFIER ;

ble_stmt : 'ble' REG REG IDENTIFIER ;

bge_stmt : 'bge' REG REG IDENTIFIER ;

/** 
  JUMP
*/

j_stmt : 'j' IDENTIFIER ;

jal_stmt : 'jal' IDENTIFIER ;

jr_stmt : 'jr' REG ;

jalr_stmt : 'jalr' REG ;

/**
  LOAD MEMORY
*/

move_stmt : 'move' REG REG ;

lb_stmt : 'lb' REG ADDR ;

lbu_stmt : 'lbu' REG ADDR ;

lh_stmt : 'lh' REG ADDR ;

lhu_stmt : 'lhu' REG ADDR ;

lui_stmt : 'lui' REG INTEGER ;

lw_stmt : 'lw' REG ADDR ;

li_stmt : 'li' REG INTEGER ;

la_stmt : 'la' REG ADDR ;

/**
  STORE MEMORY
*/

sb_stmt : 'sb' REG ADDR ;

sw_stmt : 'sw' REG ADDR ;

sh_stmt : 'sh' REG ADDR ;



ADDR : (INTEGER)? '(' REG ')' ;

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


IDENTIFIER  :  '.' (LETTER|'_'|'.') (LETTER|DIGIT|'_'|'.')*
            |   LETTER (LETTER|DIGIT|'_'|'.')*
            ;

INTEGER  : [-]? DIGIT+ [Ll]? ;

fragment LETTER  : [a-zA-Z] ;

fragment DIGIT:  '0'..'9' ;

COMMENT :   '#' .*? '\r'? '\n' -> type(NL) ;

// Match both UNIX and Windows newlines
NL      :   '\r'? '\n' -> skip;

WS      :   [ \t]+ -> skip ;






