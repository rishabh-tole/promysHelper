#include <assert.h>
#include <inttypes.h>
#include <stdbool.h>
#include <stddef.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>

bool nextPerm(int *numbers, int length) {
    int kay = -1;
    for (int k = length - 2; k >= 0; k--) {
        if (numbers[k] < numbers[k + 1]) {
            kay = k;
            break;
        }
    }
    if (kay == -1) {
        return false;
    }

    int ell = -1;
    for (int l = length - 1; l > kay; l--) {
        if (numbers[l] > numbers[kay]) {
            ell = l;
            break;
        }
    }
    int temp = numbers[kay];
    numbers[kay] = numbers[ell];
    numbers[ell] = temp;

    for (int i = kay + 1; i <= (length + kay) / 2; i++) {
        temp = numbers[i];
        numbers[i] = numbers[length - i + kay];
        numbers[length - i + kay] = temp;
    }
    return true;
}

int cmp(const void *a, const void *b) {
    int first = *(int *)a;
    int second = *(int *)b;
    return first - second;
}

bool validateGrid(int *grid, size_t n){
   int *rows = malloc(sizeof(int) * n);
   int *cols = malloc(sizeof(int) * n);
   int rowProd = 1;
   int colProd = 1;
   for (size_t i = 0; i < n; i++) {
       for (size_t j = 0; j < n; j++) {
           rowProd *= grid[i * n + j];
           colProd *= grid[j * n + i];
       }
       rows[i] = rowProd;
       cols[i] = colProd;
       rowProd = 1;
       colProd = 1;
   }
   qsort(rows, n, sizeof(int), cmp);
   qsort(cols, n, sizeof(int), cmp);
   for (int i = 0; i < n; i++) {
       if (rows[i] != cols[i]) {
           return false;
       }
   }
   return true;
}

int main(int argc, char *argv[]) {
    int n = 6;
    int numRemaining = 12;
    int *grid = calloc(sizeof(int), n * n);
    int *indices = calloc(sizeof(int), numRemaining);
    int *remaining = calloc(sizeof(int), numRemaining);
    grid[0] = 19;
    grid[1] = 15;
    grid[2] = 20;
    grid[3] = 5;
    grid[6] = 10;
    grid[7] = 23;
    grid[8] = 17;
    grid[9] = 12;
    grid[12] = 30;
    grid[13] = 34;
    grid[14] = 29;
    grid[15] = 13;
    grid[18] = 25;
    grid[19] = 1;
    grid[20] = 26;
    grid[21] = 31;
    grid[22] = 33;
    grid[23] = 28;
    grid[27] = 22;
    grid[28] = 11;
    grid[29] = 21;
    grid[33] = 35;
    grid[34] = 14;
    grid[35] = 7;
    indices[0] = 4;
    indices[1] = 5;
    indices[2] = 10;
    indices[3] = 11;
    indices[4] = 16;
    indices[5] = 17;
    indices[6] = 24;
    indices[7] = 25;
    indices[8] = 26;
    indices[9] = 30;
    indices[10] = 31;
    indices[11] = 32;
    remaining[0] = 2;
    remaining[1] = 3;
    remaining[2] = 4;
    remaining[3] = 6;
    remaining[4] = 8;
    remaining[5] = 9;
    remaining[6] = 16;
    remaining[7] = 18;
    remaining[8] = 24;
    remaining[9] = 27;
    remaining[10] = 32;
    remaining[11] = 36;

    while (true) {
        for (int i = 0; i < numRemaining; i++) {
            grid[indices[i]] = remaining[i];
        }
        if (validateGrid(grid, n)) {
            for (int i = 0; i < n * n; i++) {
                printf("%d,", grid[i]);
            }
            printf("\n");
            fflush(stdout);
            break;
        }
        if(!nextPerm(remaining, numRemaining)) {
            printf("no solutions\n");
            fflush(stdout);
            break;
        }
    }
    return 0;
}
