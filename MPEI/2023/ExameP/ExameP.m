T = [0 0 0.9 0 0
     1 0 0 0 0
     0 0.9 0 0 0
     0 0.1 0 1 0
     0 0 0.1 0 1];

prob1_1 = ([1; 0; 0; 0; 0] .* T^30)
prob1_1 = prob1_1(1);


%% EX2

load file.mat

size = length(persons)
andre = 0;
for n = 1: size-1
    if (persons{n, 1} == "André")
        andre = andre + 1;
    end
end 
andre


%% EX3

cars = table2cell(cars3)
string(cars)

for n = 1: 4

    tableString = join(cars{n,1})
    
    
        % Converte String para um Set de Words
        words = unique(strsplit(tableString, ' '));
    
        % Cria MinHash para a String
        signature = inf(1, 4);
        for i = 1:length(words)
            word = words{i};
            h_out = hf24(word, 4);
            signature(1, n) = min(h_out, signature(1, n));
        end

end

