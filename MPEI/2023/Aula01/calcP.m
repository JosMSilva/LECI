function[prob] = calcP(p,n,k,N)

lan = rand(n,N) > p;
suss = sum(lan) == k;
prob = sum(suss)/N;

end