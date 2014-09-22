.data
number: .word -1
.text

main:
la $t2, number
lh $t3, 2($t2) #lbu $t3, ($t2)
li $t1, -1
beq $t1, $t3, main




# tested : lh, lhu (negative numbers)

# problems : ( sign extension not happening )
