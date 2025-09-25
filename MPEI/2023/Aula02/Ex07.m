Perro = 0.01*20/100 + 0.05*30/100 + 0.001*50/100;
p_Carlos_erro = 0.001*50/100/Perro;
p_Bruno_erro = 0.05*30/100/Perro;
p_Andre_erro = 0.01*20/100/Perro;

N = 1e6;
experiencias = [rand(20,N)<.01; rand(30,N)<0.05; rand(50,N)<0.001];
prog_com_prob = 0;
prog_cp_Carlos = 0;
prog_cp_Andre = 0;
prog_cp_Bruno = 0;

for i = 1:N
    escolha_chefe = randi(100);
    if experiencias(escolha_chefe,i)==1
        prog_com_prob = prog_com_prob + 1;
        if escolha_chefe > 50
            prog_cp_Carlos = prog_cp_Carlos + 1;
        %Alinea B
        elseif escolha_chefe <= 20
            prog_cp_Andre = prog_cp_Andre + 1;
        else
            prog_cp_Bruno = prog_cp_Bruno + 1;
        end
    end
end

probCarlos = prog_cp_Carlos/prog_com_prob
% Alinea B
probAnde =prog_cp_Andre/prog_com_prob
probBruno = prog_cp_Bruno/prog_com_prob

