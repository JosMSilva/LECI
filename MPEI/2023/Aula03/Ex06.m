%% Alinea A
p = 1/1000;
n = 8000;
m = 7

Binomial = nchoosek(n,m)*p.^m*(1-p).^(n-m)

%% Alinea B
lamb = p*n;
k = 7;

Poisson = ((lamb.^k)/factorial(k))*exp(-lamb)


