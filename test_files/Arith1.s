.data
number: .word -1
.text

main:

li $t1, 1
li $t2, 2
li $t3, 3
add $t4, $t2, $t1
bne $t4, $t3, main
addi $t4, $t1, 2
bne $t4, $t3, main
sub $t4, $t3, $t1
beq $t4, $t2, main

# should go around in an infinite loop of 9 instructions 
# Add Addi Sub