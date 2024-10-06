#include <stdio.h>

void printAge(int *age) {

    printf("You are %d years old\n", *age);
}



int main() {
    // pointer = a variable like regerence that holds a memory address
    //          some tasks are easier with pointers
    //          * indirection operator (value at address)

    int age = 21;
    int *pAge = &age;

    printf("address of age: %p\n", &age);
    printf("value of pAge: %p\n", pAge);

    printf("size of age: %d bytes\n", sizeof(age));
    printf("size of pAge: %d bytes\n", sizeof(pAge));

    printf("value of age: %d\n", age);
    printf("value at stored address: %d\n", *pAge);

    printf("address of pointer: %p\n", &pAge);

    printAge(pAge);
}