% -----------------------------------
% flags

%set_prolog_flag(single_var_warnings, off).


% -----------------------------------
% define as posições das várias lojas
% posicao(nome, x, y)

posicao(loja1, 0, 0).
posicao(loja2, 2, 2).
posicao(loja3, 4, 6).
posicao(loja4, 5, 7).
posicao(loja5, 6, 8).
posicao(loja6, 7, 9).
posicao(loja7, 8, 0).

% -----------------------------------
% distancias entre as lojas
% distancia(lojaA, lojaB, Resultado)

distancia(A,B,R) :-
	posicao(A,X1,Y1),
	posicao(B,X2,Y2),
	R is sqrt( exp(X1 - X2,2) + exp(Y1 - Y2,2) ).
