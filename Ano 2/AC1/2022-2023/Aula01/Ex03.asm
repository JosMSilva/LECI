.data

.text
.globl main

main:
	ori $v0,$0,5
	syscall
	
	or $t0,$0,$v0 # $t0 = x
	ori $t2,$0,8 # $t2 = 8
	add $t1,$t0,$t0 # x + x = 2 * x
	sub $t1,$t1,$t2 # y = 2 * x + 8
	
	or $a0,$0,$t1
	ori $v0,$0,1
	syscall
	
	#b
	
	ori $v0,$0,34
	syscall
	
	#c
	
	ori $v0,$0,36
	syscall
	jr $ra