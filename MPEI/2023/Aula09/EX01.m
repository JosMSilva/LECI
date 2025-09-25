%% Alinea A
imin = 6;
imax = 20;
N = 1e5;
letters = ['A' : 'Z', 'a' : 'z'];
prob = 0;

aux = generateKey(imin, imax, N, letters)
le = length(unique(aux))

%% Alinea B

letters = ['a' : 'z'];
load prob_pt.txt

aux = generateKey(imin, imax, N, letters, prob_pt)
le = length(unique(aux))
