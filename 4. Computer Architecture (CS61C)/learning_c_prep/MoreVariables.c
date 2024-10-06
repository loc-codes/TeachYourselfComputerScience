#include <stdio.h>
#include <stdbool.h>

int main() {
    char a = 'C';
    char b[] = "Bro";

    float c = 3.14159265358979323846264338327950;  // 4 bytes, accurate to 6-7 digits
    double d = 3.14159265358979323846264338327950; // 8 bytes, accurate to 15-16 digits

    printf("Float = %f\n", c);
    printf("Double = %lf\n", d);

    bool e = true; // 1 byte 

    char f = 100; // 1 byte, can store any number between (-128 to +127)
                  // Can print as Ascii char or int

    printf("ASCII = %c\n", f);
    printf("Char Int = %d\n", f);

    unsigned char g = 255; // Can store 0 to +255
    signed char gg = 127; // Don't need to explicitly call out "signed" everytime

    short int j = 32768; // 2 Bytes 
    int i = 12412412412; // 4 Bytes (-2bil to +2bil)
    long int k = 1232112412; // Same as int i, int's are longs
    long long int l = 123421421412421; // 8 Bytes, -9quintrillion to +9quintrillion
}