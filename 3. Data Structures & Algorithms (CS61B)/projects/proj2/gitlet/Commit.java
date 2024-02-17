package gitlet;
import static gitlet.Utils.*;
import java.io.File;
import static gitlet.CommitUtils.*;

// TODO: any imports you need here

import java.io.Serializable;
import java.util.Date; // TODO: You'll likely use this in this class

/** Represents a gitlet commit object.
 *  TODO: It's a good idea to give a description here of what else this Class
 *  does at a high level.
 *
 *  @author TODO
 */
public class Commit implements Serializable, Dumpable {
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
    public String commitHash;
    private File files;

    public Commit(String message) {
        this.message = message;
        this.timestamp = createInitialCommitTimestape();
        this.commitHash = sha1(this.message, this.timestamp);
    }

    public Commit(String message, File files) {
        this.message = message;
        this.timestamp = createInitialCommitTimestape();
        this.files = files;
        this.commitHash = sha1(this.message, this.timestamp, this.files);
    }

    @Override
    public void dump() {
        System.out.println("commit " + commitHash);
        System.out.println("Date: " + timestamp);
        System.out.println(message);
    }
}