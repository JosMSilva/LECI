.data

.eqv a1, 0 
.eqv g, 16
.eqv a2, 24
.eqv v, 36
.eqv f, 40
.align 2
str1: .asciiz "St1"
doub: .double 3.141592653589
s2:
	.space 10
	.align 3
	.space 8
	.align 2
	.space 12
	.space 1
	.align 3
	.space 8

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
	la $t0,s2
	la $t1,st1
	sw $t1,a1($t0)
	la $t1,doub
	l.d $f0,0($t1)
	s.d $f0,g($t0)
	li $t1, 291
	sw $t1,a2($t0)
	addi $t1,$,4
	li 
	jr $ra
	
	
	
