%% Alinea A

T = [0.7, 0.8; 0.3, 0.2];                         %   P       F
v = [1; 0];                                      % P 0.7    0.8
pA = T*T*v                                       % F 0.3    0.2


%% Alinea B

v = [0; 1];
pB = T*T*v

%% Alinea C

v = [1; 0];
pC = T^29 * v

%% Alinea D

ini = [0.85; 0.15];
aulas(1) = ini(2);

for k = 1 : 29

    aulak = T^k * ini;
    aulas(k+1) = aulak(2);

end

x = (1 : 30);
plot(x, aulas)