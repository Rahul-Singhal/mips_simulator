.data
number:	.word 1
.text
main:
li $t1, 1
li $t2, 2
li $t3, 2
li $t4, 4
mult $t2, $t1
move $t6, hi
add $t6, $t2, $t2
beq $t6, $t4, main
