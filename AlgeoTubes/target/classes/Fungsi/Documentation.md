int determinangauss.

Param:
- float with 2D array, matrix
- the size of matrix n * n , n

The code describe as this

~ Step 1 ~
` X as in what iterations the loop in {0,1,2...n} `

1. If there's an element go in the x row, continue to step 2
2. Find the x row that has Element thats not 0 in the x iterations
3. If found, swap that row with the founded row.
4. If not found return 0, because det will be 0 if main diagonal has 0 as the element

~ Step 2 ~
5. Search under the leading element, if there's a number that's not 0 under the col of x, and row of x.
6. If there's a number, substract that number with ratio of first element if the founded rows divided by the first element of the x rows.
7. Iterate and substract all the columns with the corresponding x rows Element multiplied by the ratio in (5.); 
8. Repeat step 1 and step 2 untill x Times

~ Step3 ~
9. Calculate the determinant by multiplie 1 with all the element of main diagonal.