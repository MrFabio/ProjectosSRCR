% -----------------------------------
% flags

% set_prolog_flag(single_var_warnings, off).

% -----------------------------------
% define as posições dos vários pontos de interesse
% posicao(nome, x, y)
nodo(p1).
nodo(p2).

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

% -----------------------------------
% define ligações entre pontos
% arco( Ponto1, Ponto2 )

arco(p1,p7).
arco(p7,p3).
arco(p7,p4).
arco(p4,p5).
arco(p1,p4).
arco(p4,p1).

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

%findall(A,arco(O,A),[X|Y])

caminho(O,D,_,0):-O==D.
caminho(O,D,_,V):-arco(O,D), distancia(O,D,V).
caminho(O,D,[H|T],A+V):- distancia(O,H,X), findall(L,arco(H,L),Bag),Bag\==[],caminho(H,D,Bag,Y),A is (X+Y).%; caminho(O,D,T,V).
caminho(O,D,[],1000000).

caminhos(O,D,Bag):-findall(X,arco(O,X),L),findall(V,caminho(O,D,L,V),Bag).

contidoNaArea(CX1,CY1,CX2,CY2,Bag):- findall(N,(posicao(N,X,Y),X>=CX1,X=<CX2,Y>=CY1,Y=<CY2),Bag).

acessiveis(O,D):-arco(O,D),!.
acessiveis(O,D):-arco(O,X),acessiveis(X,D).

caminhosPossiveis(O,D,L):-travessia(O,D,[O],L).


%ESTA RESOLVE!
caminhos2(A,B,Lc):-setof(Cam,(caminhosPossiveis(A,B,Cam),member(A,Cam),member(B,Cam)),Lc),!.
caminhos2(_,_,[]).

custoCaminho([X],0).
custoCaminho([H,Y|T],C):-distancia(H,Y,X),custoCaminho([Y|T],Z), C is X+Z.

travessia(O,D,Vis,[D|Vis]):- arco(O,D).
travessia(O,D,Vis,L):- arco(O,X),X\==D,\+ member(X,Vis),travessia(X,B,[X|Vis],L). 

melhorCaminho(O,D,M):- caminhos2(O,D,Lc), transformaCamsEmVals(Lc,Vals), minLista(Vals,M).

transformaCamsEmVals([],[]).
transformaCamsEmVals([A|As],[H|T]):-custoCaminho(A,H),transformaCamsEmVals(As,T).

reverse([],Z,Z).
reverse([H|T],Z,Acc) :- reverse(T,Z,[H|Acc]).

min(X,Y,X):- X<Y.
min(X,Y,Y):- X>=Y. 

minLista([H],H).
minLista([H|T],X):- minLista(T,M), X is min(H,M).

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

