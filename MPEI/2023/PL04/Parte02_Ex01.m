%% EXERCÍCIO 1


    fid = fopen('wordlist-preao-20201103.txt','r');
    dicionario = textscan(fid,'%s');
    dicionario = dicionario{1};
    fclose(fid);

    n = 8000;   % comprimento do Bloom Filter
    k = 3;      % nº de hash functions
    m = 1000;   % nº de elementos inseridos

    BF = iniciar(n); % iniciar Bloom Filter de comprimento n
    for i = 1:m
         BF = inserir(BF,dicionario{i},k);
    end
%% EXERCÍCIO 2

    fn = 0;
    for i = 1:m
        out = pertencer(BF,dicionario{i},k);
        if ~out 
            fn = fn + 1;
        end
    end
    
    fprintf("Ex. 4.2.2: Percentagem de Falsos Negativos: %.2f %%\n", fn/100)

%% EXERCÍCIO 3
 
    fp = 0;
    for i = m+1:m+10000
        out = pertencer(BF,dicionario{i},k);
        if out 
            fp = fp + 1;
        end
    end
    
    percent = fp/100;
    fprintf("Ex. 4.2.3: Percentagem de Falsos Positivos: %.2f %%\n", percent)

%% EXERCÍCIO 4

    Pfp = (1-exp((-k*m)/n))^k;
    Pfp = Pfp*100;

    fprintf("Ex. 4.2.4: Percentagem de Falsos Positivos Teóricos: %.2f %%\n", Pfp)

%% EXERCÍCIO 5

    n = 8000;           % comprimento do Bloom Filter
    m = 1000;           % nº de elementos inseridos
    res = zeros(1,7);   % nº de elementos inseridos
    idx = 1;
    
    for k = 4:10
        BF = iniciar(n); % iniciar Bloom Filter de comprimento n
        for i = 1:m
             BF = inserir(BF,dicionario{i},k);
        end
        fp = 0;
        for i = m+1:m+10000
            out = pertencer(BF,dicionario{i},k);
            if out 
                fp = fp + 1;
            end
        end
        res(idx) = fp;
        idx = idx + 1;
    end
        k = 4:10;
        plot(k,res/100)
        title("K Ótimo")
        xlabel("Valores de K")
        ylabel("Percentagens de Falsos Positivos")

        kOtimo = round((n*log(2))/m);
        fprintf("Ex. 4.2.5: kOtimo Teórico: %d \n", kOtimo)
