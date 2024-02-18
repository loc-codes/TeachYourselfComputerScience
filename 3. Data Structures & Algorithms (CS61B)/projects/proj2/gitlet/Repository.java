package gitlet;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import static gitlet.Utils.*;

// TODO: any imports you need here

/** Represents a gitlet repository.
 *  TODO: It's a good idea to give a description here of what else this Class
 *  does at a high level.
 *
 *  @author TODO
 */
public class Repository {
    /**
     *
     *
     * List all instance variables of the Repository class here with a useful
     * comment above them describing what that variable represents and how that
     * variable is used. We've provided two examples for you.
     */

    /** The current working directory. */
    public static final File CWD = new File(System.getProperty("user.dir"));
    /** The .gitlet directory. */
    public static final File GITLET_DIR = join(CWD, ".gitlet");
    public static final File STAGING_DIR = join(GITLET_DIR, "staging");
    public static final File STAGING_ADDITION_DR = join(STAGING_DIR, "add");
    public static final File STAGING_REMOVE_DR = join(STAGING_DIR, "remove");
    public static final File BLOBS_DIR = join(GITLET_DIR, "blobs");
    public static final File COMMITS_DIR = join(GITLET_DIR, "commits");
    private static final File HEADPATH = join(GITLET_DIR, "HEAD");
    public static String HEAD;


    public static void stageFile() {

    }

    public static void init() {
        boolean newGitletFolder = GITLET_DIR.mkdir();
        if (!newGitletFolder) {
            System.out.println("A Gitlet version-control system already exists in the current directory.");
            System.exit(0);
        }
        Repository.makeGitletSubdirectories();
        Repository.writeRepopositoryObject(COMMITS_DIR, Commit("initial commit"));
    }

    private static void makeGitletSubdirectories() {
        STAGING_DIR.mkdir();
        STAGING_ADDITION_DR.mkdir();
        STAGING_REMOVE_DR.mkdir();
        BLOBS_DIR.mkdir();
        COMMITS_DIR.mkdir();
    }

    public static void add(String filename) {

        //Adds a copy of the file as it currently exists to the staging area (see the description of the command). For this reason, adding a file is
        // also called staging the file for addition. Staging an already-staged file overwrites the previous entry in the staging area with the new contents. The staging area should be somewhere in   . If the current working version of the file is identical to the version in the current commit, do not stage it to be added, and remove it from the staging area if it is already there (as can happen when a file is changed, added, and then changed back to it’s original version). The file will no longer be staged for removal (see   ), if it was at the time of the command.
        // Create blob at time of git add
        // Read blob of file

        Blob blobToAdd = new Blob(filename);
        boolean blobInHead = Repository.checkForBlobInHead(blobToAdd);
        if (!blobInHead) {
            // Adds a copy of the file as it currently exists to the staging area.
            Repository.writeRepopositoryObject(BLOBS_DIR, blobToAdd);
        }
    }

    private static void writeRepopositoryObject(File directory, RepositoryObject objectToWrite) {
        File commitPath = join(directory, objectToWrite.sha1hash());
        Repository.updateHead(objectToWrite.sha1hash());
        writeObject(commitPath, objectToWrite);
    }

    private static boolean checkForBlobInHead(Blob blobToAdd) {
        // Check if Blob is in the current working version of the file is identical to the version in the current commit
        String blobToAddHash = blobToAdd.sha1Hash;
        String headHash = readContentsAsString(HEADPATH);
        File headCommitPath = join(COMMITS_DIR, headHash);
        Commit headCommit = readObject(headCommitPath, Commit.class);
        if (headCommit.blobHashs != null) {
            // TO FIX: Make this a tree search, as spec requires: lg N runtime, for N the number of files in the commit.
            for (String headBlobHash: headCommit.blobHashs) {
                    if (blobToAddHash.equals(headBlobHash)) {
                        return true;
                    }
            }
        }
         return false;
    }

    private static void updateHead(String headHash) {
        writeObject(Repository.HEADPATH, headHash);
    }
}
