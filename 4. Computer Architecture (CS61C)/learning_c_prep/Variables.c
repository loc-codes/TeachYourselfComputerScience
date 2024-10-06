#include <stdio.h>

// variable = Allocated space in memory to store a value
//             Need to declare what type of data we are storing

int main(){

    int x; // Declaration
    x = 123; // Initalization
    int y = 321; // Both in 1 line

    int age = 21;
    float gpa = 2.05;
    char grade = 'C'; // store's single character
    // No string data type, as no objects in C
    char name[] = "Bro"; // array of characters

    printf("Hello %s\n", name);
    printf("You are %d years old\n", age);
    printf("Your average grade is %c\n", grade);
    printf("Your gpa is %f\n",gpa);
    return 0;
}