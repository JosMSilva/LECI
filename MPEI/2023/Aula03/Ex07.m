%% Alinea A
lamb = 15;
k = 0;

probA = ((lamb.^k)/factorial(k))*exp(-lamb)

%% Alinea B
lamb = 15;
cont = 0;

for k = 0 : 10
    cont = cont + ((lamb.^k)/factorial(k))*exp(-lamb);
end

probB = 1-cont