.data
number: .word 0
.text

main:
la $t2, number
li $t1, -1
sb $t1, ($t2)
lb $t3, ($t2)
beq $t1, $t3, main




# tested : sb, sh, sw (negative numbers)

# problems : 
