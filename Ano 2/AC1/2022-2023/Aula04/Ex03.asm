.data
	array: .word 7692,20,5,234
	.eqv print_int10,1
	.eqv SIZE,4
.text
.globl main

main:
	li $t5,0 # soma
	li $t0,0 # $t0 – soma
	la $t1,array # $t1 = p
	li $t2,SIZE
	sub $t2,$t2,1
	sll $t2,$t2,2
	addu $t3,$t2,$t1 # pultimo
	
while:
	bgtu $t1,$t3,endwhile

	lw $t4,0($t1)
	add $t5,$t5,$t4
	addi $t1,$t1,4
	
	j while
endwhile:	
	or $a0,$0,$t5
	li $v0,print_int10
	syscall
	jr $ra
	
	
