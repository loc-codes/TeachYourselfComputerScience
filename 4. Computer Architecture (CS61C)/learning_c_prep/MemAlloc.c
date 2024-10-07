#include <stdio.h>
#include <stdlib.h>

// malloc() function allocates size bytes and returns a pointer to the allocated memory
// memory is not initialised. If size is 0, then malloc returns either NULL/unique pointer value
// pointer can later be successfully passed to free()

// free() function frees the memory space pointer to by ptr
// which must have been returned by a previous call to malloc(), calloc(), realloc

void example1() {
    printf("== Malloc example 1 ==\n");

    printf("Request memory\n");
    void *ptr = malloc(1024);

    printf("Free memory\n");
    free(ptr);
}

void example1a() {
    printf("== Malloc example 1a ==\n");

    printf("Request memory\n");
    char *ptr = (char *) malloc(1024);

    if(ptr!=NULL) {

        ptr[0] = 'A';
        ptr[1] = 0;
        printf("buffer: %s\n", ptr);

        printf("Free memory\n");
        free(ptr);
    }
}


void example1b() {
    printf("== Malloc example 1b ==\n");

    printf("Request memory\n");
    char *ptr = (char *) malloc(64);

    if(ptr!=NULL) {
        int i;
        for (i = 0; i < 32; i++) {
            ptr[i] = 'A';
        }

        ptr[i] = 0;
        printf("buffer: %s\n", ptr);

        printf("Free memory\n");
        free(ptr);
    }
}


void example2() {
    printf("== Malloc example 2 ==\n");

    printf("Request memory\n");
    void *ptr = malloc(1024);

    printf("Free memory\n");
    free(ptr);

    printf("Free again! uh oh\n");
    free(ptr);
}


char *fill_buffer(char *s) {
    sprintf(s, "This is a string\n");
    free(s);
    return s;
}

void example3() {
    printf("== Malloc example 3 ==\n");

    printf("Request memory\n");
    void *ptr = malloc(8192);

    printf("fill_buffer returned: %s", fill_buffer(ptr));
}

void example4() {
    printf("== Malloc example 4 ==\n");

    printf("Request memory\n");
    char *ptr = (char *) malloc(64);

    if(ptr!=NULL) {
        int i;
        for (i = 0; i < 64; i++) {
            ptr[i] = 'A';
        }

        ptr[i] = 0; // Valgrind picks this up as error, but compiler & runtime doesn't
        // Above is the 65th byte!! This memory doesn't belong to us
        printf("buffer: %s\n", ptr);

        printf("Free memory\n");
        free(ptr);
    }
}


int main() {
    example4();
}