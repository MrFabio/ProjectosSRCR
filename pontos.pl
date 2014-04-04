% -----------------------------------
% flags

% set_prolog_flag(single_var_warnings, off).

% -----------------------------------
% define as posições dos vários pontos de interesse
% posicao(nome, x, y)
nodo(p1).
nodo(p2).




% -----------------------------------
% -----------------------------------
% -----------------------------------
% -----------------------------------
% -----------------------------------
%braga
posicao( p1, -7000, +3000 ).
%guimaraes
posicao( p2, 9000, 5000 ).
%porto
posicao( p3, 5000, 7000 ).
%lisboa
posicao( p4, 1000, 33000 ).
%faro
posicao( p5, 11000, 52000).
%guarda
posicao( p6, 16000, 13000).
%fatima
posicao( p7, 4000, 24000 ).
%viseu
posicao( p8, 11000, 12000 ).
%coimbra
posicao( p9, 7000, 18000 ).
%aveiro
posicao( p10, 11000, 6000 ).
%evora
posicao( p11, 11000, 35000).
%braganca
posicao( p12, 5000, 13000 ).
%ourem
posicao( p13, 5000, 24000 ).

%braga
propriedade( p1, empresa, 'banana Corp' ).
propriedade( p1, servico, turismo ).
propriedade( p1, shopping, bragaparque ).
propriedade( p1, farmacia, almiro ).
propriedade( p1, restauracao, mcDonalds ).
propriedade( p1, cinema, goldCenter ).
propriedade( p1, aerodromo, palmeira ).
propriedade( p1, museu, biscainhos ).
propriedade( p1, hospital, lavadaria ).
propriedade( p1, clinica, sta_Tecla ).
propriedade( p1, monumento, bom_Jesus ).
propriedade( p1, estadio, axa ).
propriedade( p1, transportes, tub ).
propriedade( p1, universidade, universidade_do_minho ).


%guimaraes
propriedade( p2, universidade, minho_azurem ).
propriedade( p2, monumento, castelo ).
propriedade( p2, estacao, cp ).
propriedade( p2, empresa, mcDonalds ).
propriedade( p2, hospital,sra_da_Oliveira ).
propriedade( p2, hotel, hotel_Guimaraes ).
propriedade( p2, estadio, d_Afonso_Henriques ).
propriedade( p2, empresa, fraterna ).


%porto
propriedade( p3, estadio, dragao ).
propriedade( p3, monumento, clerigos ).
propriedade( p3, shopping, norte_Shopping ).
propriedade( p3, lazer, via_Norte ).
propriedade( p3, aeroporto, sa_Carneiro ).
propriedade( p3, restauracao, papaki ).
propriedade( p3, turismo, rota_dos_vinhos ).
propriedade( p3, pastelaria, petulia).
propriedade( p3, empresa, sonae ).
propriedade( p3, transportes, carris ).


%lisboa
propriedade( p4, estadio, nacional ).
propriedade( p4, catedral, sta_Maria).
propriedade( p4, monumento, padrao_Descobrimentos).
propriedade( p4, pavilhao, meoArena ).
propriedade( p4, aeroporto, internacional ).
propriedade( p4, restauracao, pizzaHut ).
propriedade( p4, transportes, transtejo ).
propriedade( p4, empresa, google_pt ).
propriedade( p4, universidade, nova_de_Lisboa ).


%faro
propriedade( p5, aeroporto, aeroporto_Faro ).
propriedade( p5, lazer, aquashow).
propriedade( p5, marina, marina_de_Vilamoura).
propriedade( p5, estacao, ferroviaria_de_faro ).
propriedade( p5, monumento, ruinas_de_milreu ).
propriedade( p5, igreja, carmo ).
propriedade( p5, shopping, forum_Algarve ).
propriedade( p5, praia, falesia ).


%guarda
propriedade( p6, lazer, serraDaEstrela).
propriedade( p6, universidade, covilha).
propriedade( p6, hotel, estrelaHotel).

%fatima
propriedade( p7, santuario, santuario_de_Fatima ).
propriedade( p7, grutas, grutas_de_mira_d_aire ).


%viseu
propriedade( p8, monumento, se_de_viseu ).
propriedade( p8, shopping, palacio_do_gelo ).
propriedade( p8, hospital, sao_Teotonio ).
propriedade( p8, farmacia, tomas_Ribeiro ).
propriedade( p8, pastelaria, croassaint_de_ouro ).
propriedade( p8, desporto, fontelo ).
propriedade( p8, politecnico, politecnico_de_Viseu ).


%coimbra
propriedade( p9, universidade, universidade_de_Coimbra ).
propriedade( p9, monumento, quinta_das_lagrimas ).
propriedade( p9, estadio, cidade_de_Coimbra ).
propriedade( p9, lazer, portugal_dos_pequenitos ).
propriedade( p9, convento, santa_clara_a_nova ).
propriedade( p9, palacio , bussaco ).


%aveiro
propriedade( p10, universidade, universidade_de_Aveiro ).
propriedade( p10, lazer, ria_de_Aveiro ).
propriedade( p10, estadio, municipal_de_Aveiro ).
propriedade( p10, pastelaria, ria_pao ).
propriedade( p10, igreja, nossa_sra_Nazare ).
propriedade( p10, monumento , forte_da_Barra ).
propriedade( p10, shopping , forum_Aveiro ).
propriedade( p10, praia , praia_da_Barra ).


%evora
propriedade( p11, universidade, universidade_de_Evora ).
propriedade( p11, catedral, se_catedral ).
propriedade( p11, museu, museu_de_Evora ).
propriedade( p11, capela, capela_dos_ossos ).
propriedade( p11, monumento, templo_romano ).


%braganca
propriedade( p12, aerodromo, aerodromo_Braganca ).
propriedade( p12, monumento, castelo_de_Braganca ).
propriedade( p12, politecnico, politecnico_de_Braganca ).


%ourem
propriedade( p13, escola, profissional_de_Ourem ).
propriedade( p13, monumento, castelo_de_Ourem ).

% -----------------------------------
% -----------------------------------
% -----------------------------------
% -----------------------------------
% -----------------------------------
% -----------------------------------
% -----------------------------------










% -----------------------------------
% define ligações entre pontos
% arco( Ponto1, Ponto2 )

arco(p1,p7). arco(p7,p1).
arco(p7,p3). arco(p3,p7).
arco(p7,p4). arco(p4,p7).
arco(p4,p5). arco(p5,p4).
arco(p1,p4). arco(p4,p1).

% ciclo infinito
% arco(X,Y) :- arco(Y,X).

% -----------------------------------
% predicado distancias entre s pontos
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

% encontrar todos os arcos

todosArcos(Bag):-findall((X,Y),arco(X,Y),Bag).
 
 
coordenadasPontos((X,Y),X1,Y1,X2,Y2):- posicao(X,X1,Y1),posicao(Y,X2,Y2).
 
todosPontosArcos([],[]).
todosPontosArcos([H|T],[(X1,Y1,X2,Y2)|P]):- coordenadasPontos(H,X1,Y1,X2,Y2),todosPontosArcos(T,P).
 
predicadoTotalArcos(L):-todosArcos(Bag),todosPontosArcos(Bag,L).

% encontrar todos os pontos do melhor caminho

melhorPercursoPontos(O,D,C2):-
	caminhos2(O,D,Lc),
	transformaCamsEmVals(Lc,Vals),
	minLista(Vals,Min),
	obtemCaminhoPeloValor(Min, Vals, Lc, C),
	reverse(C,C2,[]).

% valor_alvo, listaValores, listaCaminhos, caminho_resultado
obtemCaminhoPeloValor(Vt,[Vt|Lv],[Ct|Lc],Ct).
obtemCaminhoPeloValor(Vt,[Va|Lv],[Ca|Lc],Ct) :-
	obtemCaminhoPeloValor(Vt,Lv,Lc,Ct).

% encontrar todos os custos do melhor caminho

melhorPercursoDistancias(O,D,V2) :-
	melhorPercursoPontos(O,D,C),
	custosCaminho(C,V),
	reverse(V,V2,[]).

custosCaminho([X],[]).
custosCaminho([O,D|Resto],[Resultado|Next]):-
	distancia(O,D,Resultado),
	custosCaminho([D|Resto],Next).
