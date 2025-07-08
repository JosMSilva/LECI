.data

.eqv print_float,2
.eqv read_int,5

.text
.globl main

main:
	mtc1 $0,$f0
	cvt.s.w $f0,$f0
	li $t1,0
	
do:
	li $v0,read_int
	syscall
	move $t1,$v0
	mtc1 $t1,$f1
	cvt.s.w $f1,$f1
	li $t2,0x40260000
	mtc1 $t2,$f2
	cvt.s.w $f2,$f2
	mul.s $f0,$f1,$f2
	li $v0,print_float
	mov.s $f12,$f0
	syscall
while:	bne $t1,0,do

	jr $ra
