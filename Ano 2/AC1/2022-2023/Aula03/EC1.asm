.data

	str1: .asciiz "Insira um numero: "
	str2: .asciiz "\nValor em Gray: "
	str3: .asciiz "\nValor em Binario:"
	.eqv print_string,4
	.eqv read_int,5
	.eqv print_int16,34
.text
.globl main

main:
	li $t0,0 #gray
	li $t1,0 #bin
	li $t2,0 #mask
	
	la $a0,str1
	li $v0,print_string
	syscall
	li $v0,read_int
	syscall
	or $t0,$0,$v0

	srl $t2,$t0,1
	or $t1,$t2,$0
	
while:
	beqz $t2,endw
	or $t1,$t1,$t2
	srl $t2,$t2,1
	j while
	
	
endw:
	la $a0,str2
	li $v0,print_string
	syscall
	or $a0,$0,$t0
	li $v0,print_int16
	syscall
	la $a0,str3
	li $v0,print_string
	syscall
	or $a0,$0,$t1
	li $v0,print_int16
	syscall
	jr $ra
