.data

.text
.globl main

main: 
	li $t0,0x12345678
	sll $t2,$t0,1 
	srl $t3,$t0,1 
	sra $t4,$t0,1
	
	#c
	
	li $t1,15 #bin
	srl $t5,$t1,1
	xor $t5,$t5,$t1
	
	#d
	
	or $t6,$0,$t5
	srl $t7,$t6,4
	xor $t6,$t6,$t7
	srl $t7,$t6,2
	xor $t6,$t6,$t7
	srl $t7,$t6,1
	xor $t6,$t6,$t7
	
	
	jr $ra