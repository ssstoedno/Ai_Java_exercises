IDS(iterative deepening search)

input

N //number between 5 and 10 for matrix NxN

k //number blocked cells (randomly chosen)

root1 root2 //row and col of start cell

goal1 goal2 //row and col of end cell


example input: 

7 

7 

0 0

5 4

1 1 0 1 1 1 1 

1 0 0 1 1 1 1 

1 1 1 1 1 1 1 

1 1 1 0 1 1 1 

1 1 1 0 1 1 1 

1 1 0 0 1 1 1 

1 1 1 1 1 1 1 

output:

* 1 0 1 1 1 1 

* 0 0 1 1 1 1 

* * * * * 1 1 

1 1 1 0 * 1 1 

1 1 1 0 * 1 1 

1 1 0 0 * 1 1 

1 1 1 1 1 1 1
