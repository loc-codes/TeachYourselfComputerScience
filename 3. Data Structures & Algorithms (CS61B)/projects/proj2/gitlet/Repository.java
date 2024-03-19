package gitlet;


import java.io.File;
import java.util.*;

import static gitlet.Utils.*;

/** Represents a gitlet repository.
 *
 *  @author Lachlan Young
 */
public class Repository {
    /**
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


    /** Utils **/
    private static void updateReferences(Commit commit) {
        writeContents(HEAD_FILE, "refs/heads/master"); // this writes a string with the directory address
        writeContents(MASTER_BRANCH, commit.getSha1Hash());
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

    /** Gets the commit with a given hash */
    private static Commit getCommit(String commitHash) {
        if (commitHash == null) {
            return null;
        }
        File commitPath = join(COMMITS_DIR, commitHash);
        boolean commitPathExists = commitPath.exists();
        if (commitPathExists) {
            return readObject(commitPath, Commit.class);
        }
        return null;
    }

    /** Gets the head commit */
    private static Commit getCurrentCommit() {
        String currentBranch = readContentsAsString(HEAD_FILE);
        String currentCommitHash = readContentsAsString(join(GITLET_DIR, currentBranch));
        return getCommit(currentCommitHash);
    }

    private static String formatDate(Date date) {
        Formatter f = new Formatter();
        f.format("Date: %tc", date);
        return f.toString();
    }

    private static String formatParents(List<String> parents) {
        if (parents.size() > 1) {
            String firstParent = parents.get(0).substring(0,6);
            String secondParent = parents.get(1).substring(0,6);
            return "Merge: " + firstParent + " " + secondParent + "\n";
        }
        return "";
    }

    private static String buildCommitMessage(Commit commit) {
        StringBuilder commitMessage = new StringBuilder();
        List<String> parents = commit.getParentHashs();
        String id = commit.getSha1Hash();
        Date date = commit.getCommitDate();
        String message = commit.getMessage();

        commitMessage.append(formatParents(parents));
        commitMessage.append("===\ncommit ").append(id).append("\n");;
        commitMessage.append(formatDate(date)).append("\n");
        commitMessage.append(message).append("\n\n");
        return String.valueOf(commitMessage);
    }

    /** MAIN COMMANDS **/
    public static void init() {
        if (GITLET_DIR.exists()) {
            System.out.println("A Gitlet version-control system already exists in the current directory.");
            return;
        }

        // Create the initial structure for the Gitlet repository
        GITLET_DIR.mkdir();
        STAGING_AREA.mkdir();
        BLOBS_DIR.mkdir();
        COMMITS_DIR.mkdir();
        join(GITLET_DIR, "refs").mkdir(); // Directory for branch references
        join(GITLET_DIR, "refs", "heads").mkdir(); // Directory for actual branches

        // Create the initial commit with no files and the message "initial commit"
        Commit initialCommit = new Commit("initial commit");

        // Save the initial commit to the commit directory
        File commitFile = join(COMMITS_DIR, initialCommit.getSha1Hash());
        writeObject(commitFile, initialCommit);

        // Update the HEAD and master branch to point to the initial commit
        updateReferences(initialCommit);
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

        // Check if the current version of the file is identical to the version in the current commit
        if (currentCommit.getBlobHash(fileName) != null && currentCommit.getBlobHash(fileName).equals(fileHash)) {
            // File has not changed, remove it from staging area if it's there
            unstageFile(fileName);
            return;
        }

        // Stage the file for addition
        stageFile(file);
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

        boolean hasStagedAdditions = false;
        boolean hasStagedRemovals = false;
        // Update blobs with staged files
        for (File file : stagedFiles) {
            String fileName = file.getName();
            String fileHash = sha1(readContents(file));
            if (fileName.startsWith("remove_")) {
                // If the file is marked for removal, remove it from the updated blobs
                String originalFileName = fileName.substring("remove_".length());
                updatedBlobs.remove(originalFileName);
                hasStagedRemovals = true;
            }
            else {
                hasStagedAdditions = true;
                updatedBlobs.put(fileName, fileHash);
                File blobFile = join(BLOBS_DIR, fileHash);
                writeContents(blobFile, readContents(file));
            }
            file.delete();
        }

        if (!hasStagedAdditions && !hasStagedRemovals) {
            System.out.println("No changes added to the commit.");
            return;
        }

        // Create a new commit with the updated blobs
        Commit newCommit = new Commit(message, new Date(), currentCommit.getSha1Hash(), updatedBlobs);
        String commitHash = newCommit.getSha1Hash();
        File commitFile = join(COMMITS_DIR, commitHash);
        writeObject(commitFile, newCommit);

        // Update the HEAD to point to the new commit
        updateReferences(newCommit);
    }

    public static void rm(String fileName) {
        File fileToRemove = join(STAGING_AREA, fileName);
        Commit currentCommit = getCurrentCommit();
        boolean isStaged = fileToRemove.exists();
        boolean isTracked = currentCommit.getBlobs().containsKey(fileName);

        if (!isStaged && !isTracked) {
            System.out.println("No reason to remove the file.");
            return;
        }

        if (isStaged) {
            // If the file is currently staged, unstage it.
            unstageFile(fileName);
        }


    }

    public static void log() {
        StringBuilder logMessage = new StringBuilder();
        Commit currentCommit = getCurrentCommit();
        while (currentCommit != null) {
            String commitLog = buildCommitMessage(currentCommit);
            logMessage.append(commitLog);
            String firstParent = currentCommit.getFirstParent();
            currentCommit = getCommit(firstParent);
        }
        System.out.println(logMessage);
    }

    public static void globalLog() {
        StringBuilder logMessage = new StringBuilder();
        List<String> fileNames = plainFilenamesIn(COMMITS_DIR);
        for (String filename: fileNames) {
            Commit commit = getCommit(filename);
            String commitLog = buildCommitMessage(commit);
            logMessage.append(commitLog);
        }
        System.out.println(logMessage);
    }

    public static void find(String searchMessage) {
        StringBuilder findOutput = new StringBuilder();
        List<String> fileNames = plainFilenamesIn(COMMITS_DIR);
        for (String filename: fileNames) {
            Commit commit = getCommit(filename);
            String commitMessage = commit.getMessage();
            if (commitMessage.equals(searchMessage)) {
                findOutput.append(commit.getSha1Hash()).append("\n");
            }
        }
        if (findOutput.length() > 0) {
            System.out.println(findOutput);
            return ;
        }
        System.out.println("Found no commit with that message.");
    }
}
