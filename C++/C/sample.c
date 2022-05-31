#include <stdio.h>
// sample1:固定長配列のポインタ渡し1
void sample1(int array[10]) {
    int i;

    for(i = 0; i < 10; i++){
        array[i] = i + 1;
    }
}

// sample2:固定長配列のポインタ渡し2
void sample2(int array[10]) {
    int i, j, num;

    for(i = 0; i < 10; i++){
        num = 1;
        for(j = 0; j < i; j++){
            num *= 2;
        }
        array[i] = num;
    }
    printf("2:max:%d\n", num);
}

// sample3:固定長配列のポインタ渡し3
void sample3(int array[10]) {
    int i, j = 1, num = 1;

    for(i = 0; i < 10; i++){
        array[i] = num;
        num *= 2;
    }
    printf("3:max:%d\n", num);
}

// main:固定長配列のポインタ受け取り
int main() {
    // 受け取り配列の準備
    int array[3][10], i, j;

    // 初期化した配列の受け取り
    sample1(array[0]);
    sample2(array[1]);
    sample3(array[2]);

    // 配列の個数を取得
    int array_num = sizeof(array) / sizeof(*array);
    int array_size = sizeof(*array) /sizeof(int);

    // 配列の中身の表示
    for(i = 0; i < array_num; i++) {
        printf("// ----- sample%d\n", i + 1);
        for(j = 0; j < array_size; j++) {
            printf("[%d]:%d\n", j, array[i][j]);
        }
        printf("\n");
    }
    printf("this array = [%d][%d]", array_num, array_size);
}