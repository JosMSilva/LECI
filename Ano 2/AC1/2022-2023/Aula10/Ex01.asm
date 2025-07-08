.data

.eqv id_number, 0 
.eqv first_name, 4
.eqv last_name, 22
.eqv grade, 40
.align 2

stg: 	.word 72343
	.asciiz "Napoleao"
	.space 9
	.asciiz "Bonaparte"
	.space 5
	.float 5.1

str1: .asciiz "\nN.Mec: "
str2: .asciiz "\nNome: "
str3: .asciiz "\nNota: "
str4: .asciiz ","
str5: .asciiz "Primeiro nome: "

.text
.globl main

main:
	la $t0,stg		# //$t0 = &stg
	li $v0,4
	la $a0,str1
	syscall			#print_string(str1)
	li $v0,5
	syscall			#N_Mec = read_int()
	sw $v0,id_number($t0)
	li $v0,4
	la $a0,str5
	syscall
	li $v0,8
	addiu $a0,$t0,first_name
	li $a1,17
	syscall
	li $v0,4
	la $a0,str1
	syscall			#print_string(str1)
	li $v0,1
	lw $a0,id_number($t0)
	syscall			#print_int10(N_Mec)
	li $v0,4
	la $a0,str2
	syscall			#print_string(str2)
	li $v0,4
	addiu $a0,$t0,last_name
	syscall			#print_string(last_name)
	li $v0,4
	la $a0,str4
	syscall			#print_string(str4)
	li $v0,4
	addiu $a0,$t0,first_name
	syscall			#print_string(first_name)
	li $v0,4
	la $a0,str3
	syscall			#print_string(str3)
	li $v0,2
	l.s $f12,grade($t0)
	syscall
	
	li $v0,0
	
	jr $ra
