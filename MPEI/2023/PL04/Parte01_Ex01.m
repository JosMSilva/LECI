%% Alinea A
tic
    N = 1e5; 
    imin = 6; 
    imax = 20;
    vec = ['A':'Z','a':'z'];
    keysA = key_gen(N,imin,imax,vec);
toc
    save 'keysA.mat' 'keysA'

%% Alinea B

tic
    N = 1e5;
    imin = 6; 
    imax = 20;
    vec = ['a':'z'];
    prob = zeros(1,length(vec));  
    file = readlines("prob_pt.txt");

    for k = 1:length(prob)
        prob(k) = file(k);
    end

    keysB = key_gen(N,imin,imax,vec,prob);
toc
    save 'keysB.mat' 'keysB'
