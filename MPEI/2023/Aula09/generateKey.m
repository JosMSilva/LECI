function out = generateKey(imin, imax, N, chars, prob)

if (nargin == 4)
    prob = ones(size(chars))/length(chars);
end

cumprob = cumsum(prob);
out = cell(1,N);

for i = 1: N
    keySize = randi([imin, imax]);
    aux = zeros(1,keySize);

    for j = 1: keySize
        U = rand();
        k = 1 + sum(U > cumprob);
        aux(j) = k;
    
    end
    out{i} = chars(aux);
end

out = unique(out);
lout = length(out);

while lout < N
    keySize = randi([imin, imax]);
    aux = zeros(1,keySize);

    for j = 1: keySize
        U = rand();
        k = 1 + sum(U > cumprob);
        aux(j) = k;
    
    end
    key= chars(aux);
    if ~ismember(key, out)
        lout = lout+1;
        out{lout} = key;
    end


end