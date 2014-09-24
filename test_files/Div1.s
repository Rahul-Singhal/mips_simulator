.data
number:	.word 1
.text
main:
li $t1, 1
li $t2, 2
li $t3, 2
li $t4, 4
div $t5, $t2, $t1
div $t6, $t3, $t1
div $t7, $t5, $t6
beq $t7, $t1, main