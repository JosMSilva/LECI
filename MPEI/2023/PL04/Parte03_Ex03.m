%% Parte 3
clear all;
%% Exercicio 2 e 3
udata=load('u.data');

% Fica apenas com as duas primeiras colunas
u = udata(1:end,1:2); 
clear udata;

% Lista de utilizadores
users = unique(u(:,1)); % IDs
Nu = length(users);

% Constroi a lista de filmes para cada utilizador

Set = gen_userFilm(Nu,u,users); % Usa celulas

% Calcula a distancia de Jaccard entre todos os pares pela definicao.
t = tic;
J = calc_Jaccard(Nu, Set);
toc(t)

%Limiar de Decisão
threshold = 0.4;

% Array para guardar pares similares
SimilarUsers= get_Similar(Nu,threshold,J,users)