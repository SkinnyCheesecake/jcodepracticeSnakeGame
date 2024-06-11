#include<stdio.h>

int main(){

    int op, var1, var2, var3, temp;

    printf("Ingrese un numero para comenzar la suma\n>> ");
    scanf("%d", &var1);
    printf("Mas: ");
    scanf("%d", &var2);

    printf("Desea introducir algun otro numero?\n[1]Si\t\t[2]No\n>> ");
    scanf("%d", &op);
    var3 = var1 + var2;
        if(op == 1){
            do{
                printf("Mas: ");
                scanf("%d", &temp);
                var3 += temp;
            printf("Desea ingresar otro numero?\n[1]Si\t\t[2]No\n>>  ");
            scanf("%d", &op);
            }while(op == 1);
            printf("El resultado es: %d", var3);
        }else{
            printf("El resultado es: %d", var3);
        }

    return 0;
}
