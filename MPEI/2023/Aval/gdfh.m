
movieData = readcell('movies.csv','Delimiter',',');
totalGenres = movieData(:, 3:end);
clear movieData;
totalGenres = reshape(totalGenres,1,numel(totalGenres));
temp = 1;
ti = {};

for i = 1:length(totalGenres) -1 
    if ismissing(totalGenres{i}) ~= 1
        ti{temp} = totalGenres{i};
        temp = temp +1;
    end
end

clear i; 
clear totalGenres; 
clear temp;

unique(ti)