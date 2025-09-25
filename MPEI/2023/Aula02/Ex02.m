n = 20;
N = 1e6;
m = 100; 

%% Alinea A

possiveis = randi(m,n,N);
dif = 0;
for k = 1:N
    if(length(unique(possiveis(:,k))) == 20)
        dif = dif + 1;
    end
end

probA = dif/N

%% Alinea B

possiveis = randi(m,n,N);
dif = 0;
for k = 1:N
    if(length(unique(possiveis(:,k))) < 20)
        dif = dif + 1;
    end
end

probB = dif/N