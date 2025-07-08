.data
	str1: .asciiz "Introduza uma frase:\n"
	str: .space 21
	.eqv print_string,4
	.eqv read_string,8
	.eqv print_int10,1
	.eqv SIZE,20
.text
.globl main

main:
	li $t0,0 # $t0 – num
	li $t1,0 # $t1 – i
	la $t2,str # $t2 - *p
	la $a0,str1
	li $v0,print_string
	syscall
	
	li $v0,read_string
	or $a0,$0,$t2
	li $a1,SIZE
	syscall
	
while:
	add $t3,$t2,$t1
	lb $t4,0($t3)
	beq $t4,'\0',endwhile
if:
	blt $t4,'0',endif
	bgt $t4,'9',endif
	addi $t0,$t0,1
endif:
	addi $t1,$t1,1
	j while
endwhile:	
	or $a0,$0,$t0
	li $v0,print_int10
	syscall
	jr $ra
	
	
