%% Exercicio 3

N = 1e5;
p = 0.5;
k = 6;
n = 15;

lanc = rand(n,N) > p;
suss = sum(lanc) == k;
probS = sum(suss)/N