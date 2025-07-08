.data

str: .asciiz "Arquitetura de Computadores I"

.text
.globl main

main:
	addi $sp,$sp,-4
	sw $ra,0($sp)
	la $a0,str
	jal strlen
	move $a0,$v0
	li $v0,1
	syscall
	lw $ra,0($sp)
	addi $sp,$sp,4
	jr $ra
	

strlen:
	li $t1,0
while:	lb $t0,0($a0)
	addiu $a0,$a0,1
 	beq $t0,'\0',endwhile
 	addi $t1,$t1,1
 	j while
endwhile:
	or $v0,$0,$t1
	jr $ra
	

	
