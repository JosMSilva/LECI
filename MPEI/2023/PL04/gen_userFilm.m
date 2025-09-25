function film_list = gen_userFilm(numUsers, globalList, users)
    film_list = cell(numUsers,1);

    % Para cada utilizador
    for n = 1:numUsers
        % Obtem os filmes de cada um
        ind = find(globalList(:,1) == users(n)); 
        film_list{n} = [film_list{n} globalList(ind,2)];
    end
end