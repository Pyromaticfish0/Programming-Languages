:- use_module(lambda).


% THe list of moves avaliable to the soultion when starting at the first
% peg position being empty, each function looks for the replacement peg
% surrounded by two lists, and gives a member of the remaining list and
% the move to be made.
list_of_moves(Head,2,Tail):-
    member([Head,Tail], [[1,4], [4,1]]).
list_of_moves(Head,3,Tail):-
    member([Head,Tail], [[1,6], [6,1]]).
list_of_moves(Head,4,Tail):-
    member([Head,Tail], [[2,7], [7,2]]).
list_of_moves(Head,5,Tail):-
    member([Head,Tail], [[2,9], [9,2]]).
list_of_moves(Head,5,Tail):-
    member([Head,Tail], [[3,8], [8,3]]).
list_of_moves(Head,6,Tail):-
    member([Head,Tail], [[3,10], [10,3]]).
list_of_moves(Head,5,Tail):-
    member([Head,Tail], [[4,6], [6,4]]).
list_of_moves(Head,7,Tail):-
    member([Head,Tail], [[4,11], [11,4]]).
list_of_moves(Head,8,Tail):-
    member([Head,Tail], [[4,13], [13,4]]).
list_of_moves(Head,8,Tail):-
    member([Head,Tail], [[5,12], [12,5]]).
list_of_moves(Head,9,Tail):-
    member([Head,Tail], [[5,14], [14,5]]).
list_of_moves(Head,9,Tail):-
    member([Head,Tail], [[6,13], [13,6]]).
list_of_moves(Head,10,Tail):-
    member([Head,Tail], [[6,15], [15,6]]).
list_of_moves(Head,8,Tail):-
    member([Head,Tail], [[9,7], [7,9]]).
list_of_moves(Head,9,Tail):-
    member([Head,Tail], [[10,8], [8,10]]).
list_of_moves(Head,12,Tail):-
    member([Head,Tail], [[11,13], [13,11]]).
list_of_moves(Head,13,Tail):-
    member([Head,Tail], [[12,14], [14,12]]).
list_of_moves(Head,14,Tail):-
    member([Head,Tail], [[15,13], [13,15]]).

%call function to start the game. call (start.)
start:-
    start(X),
    show(X).

%Computational section of the lists for the game
% start will give the empty peg position, starting list of pegs,
% solution list, and variable for move set
start(X):-
    game([1], [2,3,4,5,6,7,8,9,10,11,12,13,14,15], [], X).

%game will actaully alter lists
game(_, [_], Sol, X):-
    reverse(Sol, X).

% open is the free peg, taken is all taken peg slots, sol is soultion
% list, and x is list of moves
game(Open, Taken, Sol, X):-
    select(Head, Taken, Temp),
    select(Replace, Temp, Temp1),
    select(Tail, Open, Final),

    %return recursive game function with new free peg position, old free peg to taken peg, updated sol list, and list of moves.
    list_of_moves(Head, Replace, Tail),
    game([Head, Replace | Final], [Tail | Temp1], [move(Head,Replace,Tail) | Sol], X).

% Output the solution for the free peg starting in the first position
show(Sol):-
    show(Sol, [1]).

% run through all avalible pegs and print a 1 for all pegs, and 0 for
% all empty positions
% show base case for when list has been run through
show([], Free):-
    numlist(1,15, Lst),
    maplist(\X^N^(member(X, Free) -> N = 0; N = 1),
	Lst,
	[N1,N2,N3,N4,N5,N6,N7,N8,N9,N10,N11,N12,N13,N14,N15]),
    format('    ~w        ~n', [N1]),
    format('   ~w ~w      ~n', [N2,N3]),
    format('  ~w ~w ~w    ~n', [N4,N5,N6]),
    format(' ~w ~w ~w ~w  ~n', [N7,N8,N9,N10]),
    format('~w ~w ~w ~w ~w~n~n', [N11,N12,N13,N14,N15]),
    writeln(solution_found).

% show recursive step for running through list and selecting a new free
% position given at each iteration.
show([move(Start, Middle, End) | Tail], Free):-
    numlist(1,15, Lst),
    maplist(\X^N^(member(X, Free) -> N = 0; N = 1),
	Lst,
	[N1,N2,N3,N4,N5,N6,N7,N8,N9,N10,N11,N12,N13,N14,N15]),
    format('    ~w        ~n', [N1]),
    format('   ~w ~w      ~n', [N2,N3]),
    format('  ~w ~w ~w    ~n', [N4,N5,N6]),
    format(' ~w ~w ~w ~w  ~n', [N7,N8,N9,N10]),
    format('~w ~w ~w ~w ~w~n~n', [N11,N12,N13,N14,N15]),
    select(End, Free, F1),
    show(Tail,  [Start, Middle | F1]).
















