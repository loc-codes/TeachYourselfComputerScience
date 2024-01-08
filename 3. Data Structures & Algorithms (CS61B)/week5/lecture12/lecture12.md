# Lecture 12 - Git and CMD
## Decimal, Binary and Hexadecimal
- Decimal number system is 0-9
  - Numbers larger than 9 are represented as a sequence
- Binary is 0-1
  - Numbers larger than 1 are represented as a sequence
- Hexadecimal number system is 0-9a-f (Base 16)
  - Used in computer science in lieu of decimal

## Command Line Compilation
- javac Hello.java -> Hello.class
- java Hello

- IntelliJ hides this two step process from us

## public static void main(String[] args)
- Public: public method
- Static: cannot be used by instances of the class
- void: returns nothing
- main - name of function
- (String[] args): allows for command line arguments

## Git: A command line tool
- The git tool we've been using is a command line program
- Written in C
- Unlike Java, C code is typically compiled into binary which doesn't require an interpreter
  - hence we type git status not something like "java git status"
- Relies on ideas we haven't covered yet
  - Maps
  - Hashing
  - File I/O
  - Graphs

## Basic Git Functionality
## Why Version Control
- Software development is an iterative process = maintain multiple copies is useful

## How it works
- Every time you commit changes to a file, it stores a copy of the entire repo in a secret folder on your computer called .git
- Various tricks to avoid redundancy (if 3 commits, but 1 file is unchanged, only store one copy of that file)

## Approach 1: Store multiple copies of everything
- Every time you commit changes to a file, it stores a copy of the entire project in the .git folder as a new commit
- Each commit stored in a subdirectory with copies of every file
  - Easy to implement
    - Commit simply creates a new subdirectory then copies all added files to the subdirectory
    - Checkout simply deletes everything in current folder and copies the commit from a subdirectory
  - But it's very inefficient - we store multiple files with the same or very similar content

## Approach 2: Store only files that change
- See slide - pointers go to the same memory box

## Approach 3: Approach 2 but with Version Data Structure
- Better approach: Rather than wlak through commits from the beginning, explicitly store a list of a list of commits, where each commit tells us the filename and version number for the files in that commit
- eg: you could represent {Hello.java: 2, Friend.java: 4, Egg.java: 3}
- Where Hello.java was last updated in v2, Friend in v4 and egg in v3
- Another advantage: we don't need to store redundant files

## Avoiding Redundancy with "Hashing"
## Approach 5: Use a Hash as the Version number
- Use git-SHA1 hash of a file as its version number
  - each file's text content is hashed in git's hashing algo
  - eg: 66ccd...
- First git computes the hash
- Git creates a folder called .git/objects/66
  - 66 is the first two characters of the hash
- Git stores the contents in a file called ccd...
  - File is stored in a compressed format to save space

## Serializble and Storing Data Structures
- Every commit in git stores (from git log)
  - An author
  - A data
  - A commit message
  - Visit of all files and their versions
  - The parents commit id
- The commit id is git hash of the commit

## Representing a Commit in Java
public class Commit implements Serializable {
  public String author;
  public String Date;
  public String commitMessage;
  public String parentId;
  ...
}

- Java has a built-in feature called Serializable that lets you store arbitrary objects
- Easy ot use, just make class IMPLEMENT Serializable
  - No methods to implement

## Branching and Merging
- A common feature in vcs is the ability to create branches