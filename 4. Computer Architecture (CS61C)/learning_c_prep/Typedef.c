#include <stdio.h>
#include <string.h>

typedef char user[25];

typedef struct {
    char name[25];
    char password[12];
    int id;
} User;

int main() {
    User user1 = {"Bro", "password123", 123456789};
    user user2 = "Brah";
    return 0;
}

