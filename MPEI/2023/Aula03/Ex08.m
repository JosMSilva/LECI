lamb = 2; % lambda = 0.02 == 2 erros a cada 100 pag

count = 0;
for k = 0:1
    count = count + ((lamb.^k)/factorial(k))*exp(-lamb)
end

probA = count