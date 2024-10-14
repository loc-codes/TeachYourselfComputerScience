#include <ctype.h>
#include <string.h>
#include "utils.h" 

// A function to convert the entire string to lowercase and return it
void convertToLowercase(const char* src, char* dest) {
    for (int i = 0; src[i]; i++) {
        dest[i] = tolower(src[i]);
    }
    dest[strlen(src)] = '\0'; // Ensure null-termination
}

void convertToTitleCase(const char* src, char* dest) {
    if (strlen(src) > 0) {
        dest[0] = src[0];
        for (int i = 1; src[i]; i++) {
            dest[i] = tolower(src[i]);
        }
    }
    dest[strlen(src)] = '\0'; // Ensure null-termination
}
