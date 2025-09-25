function Similar = get_Similar(numUsers, threshold, Jaccard, users)
    Similar = zeros(1,3);
    k = 1;

    for n1= 1:numUsers
        for n2= n1+1:numUsers
            if Jaccard(n1, n2) < threshold
                Similar(k,:)= [users(n1) users(n2) Jaccard(n1,n2)];
                k= k+1;
            end
        end
    end

    Similar = sortrows(Similar,3);
end