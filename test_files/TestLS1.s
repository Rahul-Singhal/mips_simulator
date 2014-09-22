.data
number: .word -1
.text

main:
la $t2, number
lbu $t1, ($t2)
li $t3, 255
beq $t1, $t3, main


# tested : lbu

# problems : unsigned not following unsigned arithmetic
