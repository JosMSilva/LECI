function Jaccard = calc_Jaccard(numUsers, userFilm)
    Jaccard = zeros(numUsers);
    h= waitbar(0,'Calculating');
    for n1= 1:numUsers
        waitbar(n1/numUsers,h);
        for n2= n1+1:numUsers
            I = intersect(userFilm{n1},userFilm{n2});
            U = union(userFilm{n1}, userFilm{n2});
            Jaccard(n1, n2) = 1 - length(I)/length(U);
        
        end
    end
    delete (h)
end