.data
number: .word -1
.text

main:

li $t1, 1
li $t2, 2
li $t3, 3
and $t4, $t2, $t1
bne $t4, $zero, main
andi $t4, $t2, 2
bne $t4, $t2, main
or $t4, $t3, $t1
bne $t4, $t3, main
ori $t4, $t1, 3
beq $t4, $t3, main

# should go around in an infinite loop of 11 instructions 
# And andi or or1