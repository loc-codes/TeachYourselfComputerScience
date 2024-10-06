#include <stdio.h>

void hello(char[], int); // Function Prototype

int main() {
    char name[] = "Bro";
    int age = 21;
    hello(name, age);

    return 0;
}

void hello(char name[], int age) {
    printf("Hello %s\n", name);
    printf("You are %d years old\n", age);
}