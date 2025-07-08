.data

	str1: .asciiz "Insira um numeros\n"
	str2: .asciiz "A soma dos positivos e: "
	str3: .asciiz "Valor Ignorado\n"
	.eqv print_string,4
	.eqv read_int,5
	.eqv print_int10,1
.text
.globl main

main:
	li $t0,0 #soma
	li $t1,0 #value
	li $t2,0 #i
for:
	bge $t2,5,endfor
	la $a0,str1
	li $v0,print_string
	syscall
	li $v0,read_int
	syscall
	or $t1,$0,$v0
if:
	blt $t1,0,else
	add $t0,$t0,$t1
	addi $t2,$t2,1
	j for
else:
	la $a0,str3
	li $v0,print_string
	j for
	
	
endfor:
	la $a0,str2
	li $v0,print_string
	syscall
	or $a0,$0,$t0
	li $v0,print_int10
	syscall
	jr $ra
