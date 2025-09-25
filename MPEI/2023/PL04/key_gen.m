function keys = key_gen(N,imin,imax,vec,prob)

    if (nargin == 4)
        prob = ones(size(vec))/length(vec);
    end
    
    keys = cell(1,N);
    cumprob = cumsum(prob);
    for i = 1:N
        num = randi([imin,imax]);
        key = zeros (1,num); % vector da chave
        for j = 1:num
            U = rand();
            k = 1 + sum(U > cumprob);
            key(j) = k;
        end
        keys{i} = vec(key);
    end
    keys = unique(keys);
    lenKeys = length(keys);

    %Gerar Chaves Unicas

    while lenKeys < N
        num = randi([imin,imax]); 
        aux = zeros (1,num);
        for j = 1:num
            U = rand();
            k = 1 + sum(U > cumprob); 
            aux(j) = k;
        end
        key = vec(aux);
        if ~ismember(key,keys)
            lenKeys = lenKeys + 1;
            keys{lenKeys} = key;
        end
    end
end
