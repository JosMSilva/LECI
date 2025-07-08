.data
	str1: .asciiz "Introduza um numero: "
	str2: .asciiz "\nO valor binario e: "
	.eqv print_string,4
	.eqv read_int,5
	.eqv print_char,11
.text
.globl main

main:
	li $t0,0 # $t0 – value
	li $t1,0 # $t1 – bit
	li $t2,0 # $t2 - i
	li $t3,0 # rem B)
	la $a0,str1
	li $v0,print_string
	syscall
	
	li $v0,read_int
	syscall
	or $t0,$0,$v0
	
	la $a0,str2
	li $v0,print_string
	syscall
	
for:
	bge $t2,32,endfor
	andi $t1,$t0,0x80000000
	srl $t1,$t1,31
	
	bnez $t4,space #D
	bne $t1,1,endif #D
	
space:
	rem $t3,$t2,4 #B)
	bnez $t3,if1 #B)
	li $a0,' ' #B)
	li $v0,print_char #B)
	syscall #B)
	
if1:	
	addi $a0,$t1,'0'
	li $v0,print_char
	syscall
	li $t4,1 #D
endif:
	sll $t0,$t0,1
	addi $t2,$t2,1
	j for
endfor:
	jr $ra
	
	
