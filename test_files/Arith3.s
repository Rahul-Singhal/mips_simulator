.data
number: .word -1
.text

main:

li $t1, 1
li $t2, 2
li $t3, 3
xor $t4, $t3, $t1
bne $t4, $t2, main
xori $t4, $t2, 2
bne $t4, $zero, main
slt $t4, $t3, $t1
bne $t4, $zero, main
sgt $t4, $t3, $t1
beq $t4, $t1, main

# should go around in an infinite loop of 11 instructions 
# xor xori slt sgt