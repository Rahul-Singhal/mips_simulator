

grammar Mips;


text  :	label_list
		  ;

label_list  :  
			      | label_list label
			      ;

label   : IDENTIFIER ':' stmt_list
		    ;

stmt_list	:
				  | stmt_list stmt
          ;

stmt  : add_stmt
      | addi_stmt
      | addiu_stmt
      | addu_stmt
      ;

/**
  ARITHMETIC 
*/

add_stmt  :  'add' REG REG REG ;

addi_stmt : 'addi' REG REG INTEGER ;

addiu_stmt : 'addiu' REG REG INTEGER ;

addu_stmt : 'addu' REG REG REG ;

sub_stmt  :  'sub' REG REG REG ;

subu_stmt : 'subu' REG REG REG ;

/**
  LOGICAL 
*/

and_stmt  :  'and' REG REG REG ;

andi_stmt : 'andi' REG REG INTEGER ;

nor_stmt  :  'nor' REG REG REG ;

or_stmt : 'or' REG REG REG ;

ori_stmt : 'ori' REG REG INTEGER ;

xor_stmt : 'xor' REG REG REG ;

xori_stmt : 'xori' REG REG INTEGER ;

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

INTEGER  :  DIGIT+ [Ll]? ;

fragment LETTER  : [a-zA-Z] ;

fragment DIGIT:  '0'..'9' ;

COMMENT :   '##' .*? '\r'? '\n' -> type(NL) ;

// Match both UNIX and Windows newlines
NL      :   '\r'? '\n' ;

WS      :   [ \t]+ -> skip ;






