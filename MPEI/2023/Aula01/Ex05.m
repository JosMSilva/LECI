%% Exercicio 5

N = 1e5;
p = 0.5;
k = 6;
n = 15;

%A)
results = zeros(1,n+1);
for i = 0:n
    results(i+1) = calcP(p,n,i,N)
end
calcP(p,n,k,N)
calcP(p,20,k,N)
calcP(p,40,k,N)
calcP(p,100,k,N)

%B)

stem(0:n,results)