.data
number: .word 0
.text

main:
li $t1, 2
la $t2, number
sh $t1, ($t2)
lw $t3, ($t2)
beq $t1, $t3, main