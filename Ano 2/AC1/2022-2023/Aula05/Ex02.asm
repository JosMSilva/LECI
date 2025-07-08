.data

str1:	.asciiz ";"
	.align 2
lista: .word 8,-4,3,5,124,-15,87,9,27,15
	.eqv SIZE,10
	.eqv print_string,4
	.eqv print_int10,1
	

.text
.globl main

	#i = $t0
main:
	la $t0,lista
	li $t1,SIZE
	sll $t1,$t1,2
	addu $t1,$t0,$t1
	
while: 
	bgeu $t0,$t1,endwhile
	
	li $v0,print_int10
	lw $a0,0($t0)
	syscall
	
	
	la $a0,str1
	li $v0,print_string
	syscall
	
	addi $t0,$t0,4
	
	j while


endwhile:

	jr $ra
