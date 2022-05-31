#include <stdio.h>
// sample1:固定長配列のポインタ渡し1
void sample1(int array[2][10]) {
    int i;

    for(i = 0; i < 10; i++){
        array[0][i] = i + 1;
    }
}

// sample1:固定長配列のポインタ渡し2
void sample2(int array[2][10]) {
    int i;

    for(i = 0; i < 10; i++){
        array[1][i] = 1;
    }
}

// preview:固定長配列のポインタ受け取り
int main() {
    // 初期化した配列の受け取り
    int array[2][10];
    sample1(array);
    sample2(array);
    int i;
    int j;

    for(i = 0; i < 2; i++) {
        printf("\n// ----- sample%d\n", i + 1);
        for(j = 0; j < 10; j++) {
            printf("[%d]:%d\n", j, array[i][j]);
        }
    }
}