.data

str1:	.asciiz "\nIntroduza um numero: "
	.align 2
lista: .space 40
	.eqv SIZE,10
	.eqv print_string,4
	.eqv read_int,5
	

.text
.globl main

	#i = $t0
main:
	li $t0,0
	
while: 
	bge $t0,SIZE,endwhile
	
	la $a0,str1
	li $v0,print_string
	syscall
	li $v0,read_int
	syscall
	
	la $t1,lista
	sll $t2,$t0,2
	addu $t2,$t2,$t1
	sw $v0,0($t2)
	addi $t0,$t0,1
	
	j while


endwhile:

	li $t3,0
	li $t0,0
for:	
	bge $t0,SIZE,endfor
	
	la $t1,lista
	sll $t2,$t0,2
	addu $t1,$t1,$t2
	
	lw $t4,0($t2)
	lw $t5,4($t2)
	
if:	blt $t5,$t4,endif

	or $t6,$0,$t4
	

endif:
	
endfor:

	jr $ra
