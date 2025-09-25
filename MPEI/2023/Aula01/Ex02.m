%% Exemplo 1

exp = rand(3,10000);
lan = exp > 0.5;
res = sum(lan);
sus = res == 2;
prob = sum(sus)/10000

%% Exemplo 2

N = 1e5;
p = 0.5;
k = 2;
n = 3;

lanc = rand(n,N) > p;
suss = sum(lanc) == k;
probS = sum(suss)/N