#include<stdio.h>

int menu(int x, int y);
int suma(int c, int d);
int resta(int c, int d);
int multiplicacion(int c, int d);
float division(int c, int d);
float modulo(int c, int d);
int restart();

int main(){

int a, b;
printf("Para comenzar el programa primero ingrese un numero\n>> ");
scanf("%d", &a);
printf("Ahora ingrese un segundo valor\n>> ");
scanf("%d", &b);
    menu(a, b);

}

int menu(int x, int y){

char o, b, w, e;
int s = 1, fin, op;

printf("Los valores ingresados fueron %d y %d\n\n", x, y);

do{
printf("Con los valores ingresados, que operacion deseas realizar?\n");
printf("[1]Suma\t[2]Resta\t[3]Multiplicacion\t[4]Division\t[5]Modulo\n>> ");

    scanf("%s", &o);
    scanf("%c", &b);

    switch(o){
        case '1':
            printf("El resultado es: %d\n", suma(x, y));
        break;

        case '2':
            printf("El resultado es: %d\n", resta(x, y));
        break;

        case '3':
            printf("El resultado es: %d\n", multiplicacion(x, y));
        break;

        case '4':
            printf("El resultado es: %.2f\n", division(x, y));
        break;

        case '5':
            printf("El resultado es: %.2f\n", modulo(x, y));
        break;
        }

        printf("\n--Deseas realizar otra operacion con *estos numeros*?--\n[1]Si\tCualquier tecla para No\n>> ");
        scanf("%d", &s);
        printf("\n");
    }while(s == 1);

printf("Deseas introducir nuevos valores o salir del programa?\n[1]Nuevos\tSalir\n>> ");
scanf("%d", &fin);
    if(fin == 1){
        restart();
    }
    else{
        printf("\nHasta luego!\n");
    }

return 0;
}

int suma(int c, int d){
    return c + d;
}

int resta(int c, int d){
    return c - d;
}

int multiplicacion(int c, int d){
    return c * d;
}

float division(int c, int d){
    float resultado;
    resultado = (float)c / d;
    return resultado;
}

float modulo(int c, int d){
    return c % d;
}

int restart(){
    int c, d;
    printf("Que valores vas a ingresar?\n>> ");
        scanf("%d", &c);
        printf("y\n>>");
        scanf("%d", &d);
            menu(c, d);
}
