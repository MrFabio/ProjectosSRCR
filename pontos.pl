% -----------------------------------
% flags

% set_prolog_flag(single_var_warnings, off).

% -----------------------------------
% define as posições dos vários pontos de interesse
% posicao(nome, x, y)

posicao(p1, 0, 0).
posicao(p2, 2, 2).
posicao(p3, 4, 6).
posicao(p4, 5, 7).
posicao(p5, 6, 8).
posicao(p6, 7, 9).
posicao(p7, 8, 0).

% -----------------------------------
% define serviços nos pontos de interesse
% servico(nome_loja, nome_servico).

servico(p1, McDonalds).
servico(p2, Combustivel).
servico(p3, Lavandaria).

servico(p7, McDonalds).
servico(p12, BananaCorp).
% -----------------------------------
% define ligações entre pontos
% arco( Ponto1, Ponto2 )

arco(p1,p7).

% -----------------------------------
% predicado distancias entre as lojas
% distancia(lojaA, lojaB, Resultado).

distancia(A,B,R) :-
	posicao(A,X1,Y1),
	posicao(B,X2,Y2),
	distancia(X1,Y1,X2,Y2,R).

% -----------------------------------
% predicado distancias entre pontos

distancia(X1, Y1, X2, Y2, R) :-
	R is sqrt( exp(X1 - X2,2) + exp(Y1 - Y2,2) ).

% -----------------------------------
% predicado ponto de interesse próximo
% pontoProximo( X, Y, distanciaMax, Ponto ).

pontoProximo( X, Y, 0, P ) :-
	posicao(P, X, Y).
pontoProximo( X, Y, M, P ) :-
	posicao(P, A, B),
	distancia( X, Y, A, B, N ),
	N =< M.

% -----------------------------------
% predicado pontos numa regiao
% orla( X, Y, distanciaMax, ListaPontos )

% é preciso a cena de obter todas as soluções do pontoProximo

% -----------------------------------
% predicado ponto de interesse mais proximo
% pontoMaisProximo( X, Y, P )

% pontoMaisProximo( X, Y, P ) :-
% mais próximo que quê?
% usar uma lista de todas as distancias? é preciso ver o dijkstra que o 12 encontrou.


