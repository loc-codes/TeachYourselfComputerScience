package gitlet;


import java.io.File;
import java.util.Map;
import java.util.HashMap;
import java.util.Date;
import static gitlet.Utils.*;

/** Represents a gitlet repository.
 *
 *  @author Lachlan Young
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
    public static final File STAGING_AREA = join(GITLET_DIR, "staging");
    public static final File COMMITS_DIR = join(GITLET_DIR, "commits");
    public static final File BLOBS_DIR = join(GITLET_DIR, "blobs");
    public static final File REFS_DIR = join(GITLET_DIR, "refs");
    public static final File HEADS_DIR = join(REFS_DIR, "heads");

    /** The directory for the master branch reference. */
    public static final File MASTER_BRANCH = join(HEADS_DIR,  "master");
    public static final File HEAD_FILE = join(GITLET_DIR, "HEAD");




    public static void init() {
        if (GITLET_DIR.exists()) {
            System.out.println("A Gitlet version-control system already exists in the current directory.");
            return; // Early return if the system already exists
        }

        // Create the initial structure for the Gitlet repository
        createInitialDirectoryStructure();

        // Create the initial commit with no files and the message "initial commit"
        Commit initialCommit = createInitialCommit();

        // Save the initial commit to the commit directory
        saveInitialCommit(initialCommit);

        // Update the HEAD and master branch to point to the initial commit
        updateReferences(initialCommit);
    }

    /**
     * Creates the initial directory structure for a Gitlet repository.
     */
    private static void createInitialDirectoryStructure() {
        GITLET_DIR.mkdir();
        STAGING_AREA.mkdir();
        BLOBS_DIR.mkdir();
        COMMITS_DIR.mkdir();
        join(GITLET_DIR, "refs").mkdir(); // Directory for branch references
        join(GITLET_DIR, "refs", "heads").mkdir(); // Directory for actual branches
    }

    /**
     * Creates the initial commit.
     * @return The initial commit object.
     */
    private static Commit createInitialCommit() {
        // Using the Unix Epoch as the timestamp for the initial commit
        return new Commit("initial commit");
    }

    /**
     * Saves the initial commit to the commit directory.
     * @param initialCommit The initial commit object to save.
     */
    private static void saveInitialCommit(Commit initialCommit) {
        File commitFile = join(COMMITS_DIR, initialCommit.getSha1Hash());
        writeObject(commitFile, initialCommit);
    }

    /**
     * Updates the HEAD and master references to point to the initial commit.
     * @param initialCommit The initial commit object.
     */
    private static void updateReferences(Commit initialCommit) {
        writeContents(HEAD_FILE, "refs/heads/master"); // this writes a string with the directory address
        File masterFile = MASTER_BRANCH;
        writeContents(masterFile, initialCommit.getSha1Hash());
    }

    public static void add(String fileName) {
        File file = new File(CWD, fileName);
        if (!file.exists()) {
            System.out.println("File does not exist.");
            return;
        }

        // Compute the file's SHA-1 hash
        String fileHash = sha1(readContents(file));

        // Retrieve the current commit and its blobs
        Commit currentCommit = getCurrentCommit();
        currentCommit.dump();

        // Check if the current version of the file is identical to the version in the current commit
        if (currentCommit.getBlobHash(fileName) != null && currentCommit.getBlobHash(fileName).equals(fileHash)) {
            // File has not changed, remove it from staging area if it's there
            unstageFile(fileName);
            return;
        }

        // Stage the file for addition
        stageFile(file);
    }

    /**
     * Gets the current commit.
     * @return The current commit object.
     */
    private static Commit getCurrentCommit() {
        String currentBranch = readContentsAsString(HEAD_FILE);
        String currentCommitHash = readContentsAsString(join(GITLET_DIR, currentBranch));
        File currentCommitFile = join(COMMITS_DIR, currentCommitHash);
        return readObject(currentCommitFile, Commit.class);
    }

    /**
     * Stages the given file.
     * @param file The file to stage.
     */
    private static void stageFile(File file) {
        File stagedFile = join(STAGING_AREA, file.getName());
        writeContents(stagedFile, readContents(file));
    }

    /**
     * Unstages the file with the given name.
     * @param fileName The name of the file to unstage.
     */
    private static void unstageFile(String fileName) {
        File stagedFile = join(STAGING_AREA, fileName);
        if (stagedFile.exists()) {
            stagedFile.delete();
        }
    }

    public static void commit(String message) {
        if (message == null || message.isEmpty()) {
            System.out.println("Please enter a commit message.");
            return;
        }

        File[] stagedFiles = STAGING_AREA.listFiles();
        if (stagedFiles == null || stagedFiles.length == 0) {
            System.out.println("No changes added to the commit.");
            return;
        }

        Commit currentCommit = getCurrentCommit();
        Map<String, String> updatedBlobs = new HashMap<>(currentCommit.getBlobs()); // Clone current blobs

        // Update blobs with staged files
        for (File file : stagedFiles) {
            String fileName = file.getName();
            String fileHash = sha1(readContents(file));
            updatedBlobs.put(fileName, fileHash);
            // Copy the file to the blobs directory
            File blobFile = join(BLOBS_DIR, fileHash);
            writeContents(blobFile, readContents(file));
            // Clear staging area
            file.delete();
        }

        // Create a new commit with the updated blobs
        Commit newCommit = new Commit(message, new Date(), currentCommit.getSha1Hash(), updatedBlobs);
        String commitHash = newCommit.getSha1Hash();
        File commitFile = join(COMMITS_DIR, commitHash);
        writeObject(commitFile, newCommit);

        // Update the HEAD to point to the new commit
        updateReferences(newCommit);
    }


}
