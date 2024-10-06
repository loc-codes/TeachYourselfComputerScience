#include <stdio.h>

int main() {
    // memory = an array of bytes within RAM
    // memory block =  a single unit (byte) within memory
    // memory address = the address of where a memory block is located

    char a;
    short b[3];

    printf("%d bytes\n", sizeof(a));
    printf("%d bytes\n", sizeof(b));
    //printf("%d bytes\n", sizeof(c));

    printf("%p\n", &a);
    printf("%p\n", &b);
    //printf("%p\n", &c);

    return 0;
}