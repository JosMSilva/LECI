    histogram(H(4,:),100);

    Hnorm = H(4,:)/T;
    fprintf('Momento 2: Teórico = %.5f Medido %.5f\n',1/3,mean(Hnorm.^2));
    fprintf('Momento 5: Teórico = %.5f Medido %.5f\n',1/6,mean(Hnorm.^5));    
    fprintf('Momento 10: Teórico = %.5f Medido %.5f\n',1/11,mean(Hnorm.^10));
    fprintf('Max no de atribuições %d\n',max(P(4,:)));
    fprintf('Execution time = %.5f\n',t(4))