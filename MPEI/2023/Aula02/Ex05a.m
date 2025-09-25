N = 1e7;
p_paridade = 0.5;
lancamentos = 2;
num_faces = 6;
p_serX = 1/6;

rolls = randi(num_faces, lancamentos, N);

%P(A) -  Soma de 2 valores é 9

soma = sum(rolls);
igual_nove = soma == 9;

p_a = sum(igual_nove)/N

%P(B) -  o 2º valor é par

soma_par = 0;
roll2 = rolls(2 : N+1);  %2 lancamento
for i = 1 : N
    if rem(roll2(i), 2) == 0
        soma_par = soma_par + 1;
    end
end
p_b = soma_par/N


%P(C) -  pelo menos um dos valores é igual a 5

igC = rolls == 5;
pmU = sum(igC) >= 1;

p_c = sum(pmU)/N

%P(D) -  nenhum dos valores é igual a 1
difZ = rolls ~= 1;
igual_um = sum(difZ) >= 2;

p_d = sum(igual_um)/N
%% B
%pa = 0.1111
%pb = 0.5
%p(ab)= 2/36 = 0.5555
%p(ab) = pa * pb Independentes

%% c
%pc = 0.3055
%pb = 0.6943
%p(ab)= 10/36 = 0.2778
%p(cd) != pc * pd  Não Independentes
