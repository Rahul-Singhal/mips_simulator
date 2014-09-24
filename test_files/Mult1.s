.data
number:	.word 1
.text
main:
li $t1, 1
li $t2, 2
li $t3, 2
li $t4, 4
mult $t5, $t2, $t1
mult $t6, $t1, $t3
mult $t7, $t5, $t6
beq $t7, $t4, main