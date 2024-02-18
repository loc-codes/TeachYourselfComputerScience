package gitlet;
import static gitlet.Utils.*;
import java.io.File;
import static gitlet.CommitUtils.*;

// TODO: any imports you need here

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date; // TODO: You'll likely use this in this class
import java.util.HashMap;
import java.util.List;

/** Represents a gitlet commit object.
 *  TODO: It's a good idea to give a description here of what else this Class
 *  does at a high level.
 *
 *  @author TODO
 */
public class Commit implements Dumpable, RepositoryObject {
    /**
     * TODO: add instance variables here.
     *
     * List all instance variables of the Commit class here with a useful
     * comment above them describing what that variable represents and how that
     * variable is used. We've provided one example for `message`.
     */

    /** The message of this Commit. */
    private String message;
    private String timestamp;
    public String sha1Hash;
    public List<String> blobHashs;
    public HashMap<String, File> blobMap;
    private String parentId;

//    Having our metadata consist only of a timestamp and log message.
//    A commit, therefore, will consist of:
//    a log message,
//    timestamp,
//    a mapping of file names to blob references,
//    a parent reference,
//    and (for merges) a second parent reference.


    // Constructor for initial commit
    public Commit(String message) {
        this.message = message;
        this.timestamp = createInitialCommitTimestape();
        this.sha1Hash = sha1(this.message, this.timestamp);
        this.parentId = null;
    }

    // Constructor for all other commits
    public Commit(String message, ArrayList<String> blobs, String parentId) {
        this.message = message;
        this.timestamp = createInitialCommitTimestape();
        this.parentId = parentId;
        this.blobHashs = blobs;
        this.blobMap = createBlobMap();
        this.sha1Hash = sha1(this.message, this.timestamp, this.blobHashs);
    }

    private HashMap<String, File> createBlobMap() {
        return null;
    }

    @Override
    public void dump() {
        System.out.println("commit " + sha1Hash);
        System.out.println("Date: " + timestamp);
        System.out.println(message);
    }
}