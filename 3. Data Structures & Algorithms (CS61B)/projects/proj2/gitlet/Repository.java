package gitlet;

import java.io.File;
import java.util.LinkedList;

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
     * TODO: add instance variables here.
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
    public static final File COMMITS_DIR = join(GITLET_DIR, "commits");
    public static LinkedList<String> commits;

    public static void writeCommit(Commit commitToWrite) {
        File filePath = join(COMMITS_DIR, commitToWrite.commitHash);
        Repository.commits.addLast(commitToWrite.commitHash);
        writeObject(filePath, commitToWrite);
    }

    public static void init() {
        boolean newGitletFolder = GITLET_DIR.mkdir();
        if (!newGitletFolder) {
            System.out.println("A Gitlet version-control system already exists in the current directory.");
            System.exit(0);
        }
        STAGING_DIR.mkdir();
        STAGING_ADDITION_DR.mkdir();
        STAGING_REMOVE_DR.mkdir();
        COMMITS_DIR.mkdir();
        Repository.commits = new LinkedList<>();
        Commit commitZero = new Commit("initial commit");
        Repository.writeCommit(commitZero);
    }
}
