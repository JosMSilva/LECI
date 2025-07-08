.data

str: .asciiz "11Arquitetura de Computadores I"

.text
.globl main

main:
	addi $sp,$sp,-4
	sw $ra,0($sp)
	la $a0,str
	jal atoi
	move $a0,$v0
	li $v0,1
	syscall
	li $v0,0
	lw $ra,0($sp)
	addi $sp,$sp,4
	jr $ra
	

atoi:
	li $v0,0
while:
	lb $t1,0($a0)
	blt $t1,'0',endwhile
 	bgt $t1,'9',endwhile
 	
 	sub $t0,$t1,'0'
 	addiu $a0,$a0,1
 	mulu $v0,$v0,10
 	addu $v0,$v0,$t0
 	j while
endwhile:
	jr $ra
	

	
