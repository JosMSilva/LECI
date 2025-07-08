.data

.eqv MAX_STUDENTS,4

mg: .float -20.0
sum: .float 0.0
grade: .float 0.0


st1: .asciiz "\n N.Mec: "
st2: .asciiz "\n Primeiro Nome: "
st3: .asciiz "\n Ultimo Nome: "
st4: .asciiz "\n Nota: "

.align 2
std_array: .space 176


.text
.globl main

main:	
	addi $sp,$sp,-4
	sw $ra,0($sp)
	la $a0,std_array
	li $a1,MAX_STUDENTS
	jal read_data
	
	
	lw $ra,0($sp)
	addi $sp,$sp,4
	li $v0,0
	jr $ra
	
read_data:
	li $t0,0
	move $t1,$a0
	move $t2,$a1
rd_w1:	bge $t0,$t2,rd_ew1
	mul $t1,$t0,44
	
	li $v0,4
	la $a0,st1
	syscall
	
	li $v0,5
	syscall
	sw $v0,0($t1)
	
	li $v0,4
	la $a0,st2
	syscall
	
	li $v0,4
	la $a0,st3
	syscall
	
	li $v0,4
	la $a0,st4
	syscall
	
	addi $t0,$t0,1
	j rd_w1
rd_ew1:	
	jr $ra

	
max:
	l.s $f2, mg
	l.s $f4, sum
	mul $t2, $a1, 44
	addu $t1,$a0,$t2
	move $t0,$a0
m_w1:
	bgeu $t0,$t1,m_ew1
	l.s $f6,40($t0)
	add.s $f4,$f4,$f6
m_if:
	c.le.s $f6,$f2
	bc1t m_eif
	mov.s $f2,$f6
	move $v0,$t0
m_eif:
	addiu $t0,$t0,44
	j m_w1
m_ew1:
	mtc1 $a1,$f8
	cvt.s.w $f8,$f8
	div.s $f8,$f4,$f8
	s.s $f8, 0($a2)
	jr $ra