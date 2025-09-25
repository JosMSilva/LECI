%% Alinea A

%    A     B     C
%A  1/3   1/4    0
%B  1/3  11/20  1/2
%C  1/3   1/5   1/2

T = [1/3, 1/4, 0; 1/3, 11/20, 1/2 ; 1/3, 1/5, 1/2]

% É estocastica

%% Alinea B

%alunosA = 2 * (alunosB + alunosC)        %alunosA = 4*alunosB
%alunosA + alunosB + alunosC = 90   % alunosA + 2 * alunosB = 90 
%3/2 *alunosA = 90 % 3alunosA = 180
%alunosB = alunosC

%Alunos A = 60
%Alunos B = 15
%Alunos C = 15

v = [60; 15; 15]%/90

%% Alinea C

probC = T^29 * v

%% Alinea D

v = [90/3; 90/3; 90/3];

probD = T^29 * v