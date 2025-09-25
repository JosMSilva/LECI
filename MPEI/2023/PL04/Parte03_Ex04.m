t = tic;
K = 100;

[Set,Nu,users] = make_set('u.data');
threshold = 0.4;
assinaturas = inf(Nu,K);
for n = 1:Nu
    conjunto = Set{n}; 
    for i = 1:length(conjunto)
        chave = num2str(conjunto(i));
        h_out = muxDJB31MA(chave, 127,K);
        assinaturas(n,:) = min(h_out,assinaturas(n,:));
    end
end 
for n1 = 1:Nu
    for n2= n1+1:Nu                 
        J(n1,n2) = sum(assinaturas(n1,:) ~= assinaturas(n2,:))/K;
    end
end
J
toc(t)

SimilarUsers = pares(J, Nu, users, threshold)

%% TESTE DE FUNCTION
    
% for K = [50, 100, 200]
%     fprintf("\nFor (K = %d): ", K)
%     [Set,Nu,users] = make_set('u.data');
%     M = minHash(Set, Nu, K);
%     threshold = 0.4;
%     SimilarUsers = pares(J, Nu, users, threshold)
% end