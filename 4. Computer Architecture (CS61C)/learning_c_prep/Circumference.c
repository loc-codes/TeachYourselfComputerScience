#include <stdio.h>

int main() {

    const double PI = 3.14159;
    double radius;
    double circumference;

    printf("\nEnter radius of a circle: ");
    scanf("%lf", &radius);
    //printf("\nThe radius is: %lf", radius);

    circumference = 2 * PI * radius;

    printf("\nCircumference: %.6lf\n", circumference);

    return 0;
}