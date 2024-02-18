package gitlet;
import java.io.File;
import gitlet.Utils.*;

import static gitlet.Repository.CWD;
import static gitlet.Utils.*;

public class Blob implements RepositoryObject{
    private String fileName;
    private byte[] fileContents;
    public String sha1Hash;

    // Create a new blob
    public Blob(String filename) {
        this.fileName = filename;
        File filePath = join(CWD, filename);

        //If the file does not exist, print the error message   and exit without changing anything.
        try {
            this.fileContents = readContents(filePath);
        }
        catch (IllegalArgumentException excp) {
            System.out.println("File does note exist");
            System.exit(0);
        }
        this.sha1Hash = sha1(this.fileName, this.fileContents);
    }

}
