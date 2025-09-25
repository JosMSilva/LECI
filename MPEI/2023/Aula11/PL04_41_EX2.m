clear; 
clc;
%{
################################
UNIVERSIDADE DE AVEIRO [2023/24]
UC: MPEI
ALUNO: JOÃO PEDRO NUNES VIEIRA
NºMEC.: 50458
CURSO: LECI
################################

[ PL03 - Algoritmos Probabilísticos ]
[4.1 Funções de dispersão ]

%}
%% EXERCÍCIO 2
%{
    Considere a função Matlab string2hash() que implementa duas funções de 
    dispersão diferentes. Considere ainda 2 funções Matlab fornecidas que 
    são adaptações para Matlab das funções de dispersão hashstring()
    e DJB31MA()

    Utilizando separadamente cada uma destas quatro funções de dispersão, 
    simule a inserção das chaves criadas no exercício 1a) em 3 Chaining 
    Hash Tables, uma de tamanho (5x10^5) outra de tamanho (10^6)
    e a terceira de tamanho (2×10^6). 
    
    Para cada uma das simulações (4 funções de dispersão × 3 tamanhos):
    
    a) Guarde um vetor com os hashcodes obtidos
    b) Registe o numero de atribuições a cada uma das posições de cada Hash Table.
    c) Calcule o numero de colisões (em cada Hash Table e para cada função de dispersão). 
    d) O tempo de execução da simulação.
%}
%%  a,b,c,d)
% main()

load keysA.mat;
T = 5e5;
N = length(keysA);

H = zeros(4,N);  % guarda os hashcodes
P = zeros(4,T);  % nº de atribuições de cada hashcode
col = [0 0 0 0]; % nº de colisões
t = [0 0 0 0];   % guarda tempos de execução
%% Hash: string2hash() ---> djb2
start1 = tic;
    for i = 1:N
        hash = string2hash(keysA{i}); % DEFAULT: djb2
        hash = mod(hash,T) + 1;
        H(1,i) = hash;
        P(1,hash) = P(hash) + 1;
    end
    for i = 1:T
        if P(1,i) >= 2
            col(1) = col(1) + P(1,i) - 1;
        end
    end
t(1) = toc(start1);
%% Hash: string2hash() ---> sdbm
start2 = tic;
    for i = 1:N
        hash = string2hash(keysA{i},'sdbm');
        hash = mod(hash,T) + 1;
        H(2,i) = hash;
        P(2,hash) = P(hash) + 1;
    end
    for i = 1:T
        if P(2,i) >= 2
            col(2) = col(2) + P(2,i) - 1;
        end
    end
t(2) = toc(start2);
%% Hash: hashstring()
start3 = tic;
    for i = 1:N
        code = hashstring(keysA{i}, T) + 1 ;
        H(2,i) = code;
        P(2,code) = P(code) + 1;
    end
    for i = 1:T
        if P(3,i) >= 2
            col(3) = col(3) + P(3,i) - 1;
        end
    end
t(3) = toc(start3);
%% Hash: DJB31MA 
start4 = tic;
for i = 1:N
    h = DJB31MA(keysA{i}, 127); % 127 é (2^32 - 1)
    h = mod(h,T) + 1; % calcula resto de divisão de h por T dando valor entre 0 e T-1(+1) (ie, entre 0 e T)
    H(4,i) = h;
    P(4,h) = P(h) + 1;
end
for i = 1:T
    if P(4,i) >= 2
        col(4) = col(4) + P(4,i)-1;
    end
end
t(4) = toc(start4);

 
