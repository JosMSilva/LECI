.data

	str1: .asciiz "Uma string qualquer\n"
	str2: .asciiz "AC1 - P\n"
	str3: .asciiz "Insira 2 numeros\n"
	str4: .asciiz "A soma dos 2 numeros e: "
	.eqv print_string,4
	.eqv read_int,5
	.eqv print_int10,1
.text
.globl main

main:
	la $a0,str1
	ori $v0,$0,print_string
	syscall
	
	#d
	
	la $a0,str3
	li $v0,print_string
	syscall
	li $v0,read_int
	syscall
	or $t0,$0,$v0
	li $v0,read_int
	syscall
	or $t1,$0,$v0
	add $t2,$t1,$t0
	la $a0,str4
	li $v0,print_string
	syscall
	or $a0,$0,$t2
	li $v0,print_int10
	syscall
	jr $ra