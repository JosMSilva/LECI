.data

.eqv a1, 0 
.eqv i, 16
.eqv a2, 20
.eqv g, 40
.align 2

s1:
	.asciiz "Str_1"
	.space 8
	.align 2
	.word 2021
	.asciiz "Str_2"
	.space 11
	.align 3
	.double 2.718281828459045

.text
.globl main

main:
	addiu $sp,$sp,-4
	sw $ra,0($sp)
	jal f1
	mov.d $f12,$f0
	li $v0,3
	syscall 
	lw $ra,0($sp)
	addi $sp,$sp,4
	li $v0,0
	jr $ra
			
	
f1:
	la $t0,s1
	l.d $f0,g($t0)
	jr $ra
	
	
	
