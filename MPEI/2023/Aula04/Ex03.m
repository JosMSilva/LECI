%% Alinea A

T = rand(20,20);
s = sum(T);

for k = 1 :20
    T(:, k) = T(:, k)/s(k);
end
%% Alinea B

v = zeros(20, 1);
v(1) = 1;

probB2 = (T^2 * v);
probB2 = probB2(20)*100
probB5 = (T^5 * v);
probB5 = probB5(20)*100
probB10 = (T^10 * v);
probB10 = probB10(20)*100
probB100 = (T^100 * v);
probB100 = probB100(20)*100