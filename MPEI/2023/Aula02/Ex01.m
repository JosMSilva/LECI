N = 1e7;
p = 0.5;
k = 1;
filhos = 2;
possivel = rand(filhos,N) > p;

%% Alinea A
fav = sum(possivel) >= k;
probA = sum(fav)/N

%% Alinea B

% 0.75 Igual

%% Alinea C

dfilhos = sum(possivel) == 2;
ufilhos = sum(possivel) >= 1;

probC = sum(dfilhos)/sum(ufilhos)

%% Alinea D

possivel(1,:) = ones(1,N);
fav = sum(possivel) == 2;

probD = sum(fav)/N

%% Alinea E

filhos = 5;
possivel = rand(filhos, N) > p;
ufilhos = sum(possivel) >= 1;
dfilhos = sum(possivel) == 2;

probE = sum(dfilhos)/sum(ufilhos)

%% Alinea F

filhos = 5;
possivel = rand(filhos, N) > p;
ufilhos = sum(possivel) >= 1;
tfilhos = sum(possivel) >= 2;

probE = sum(tfilhos)/sum(ufilhos)



