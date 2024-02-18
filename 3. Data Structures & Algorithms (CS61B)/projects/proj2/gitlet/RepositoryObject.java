package gitlet;

import java.io.Serializable;

public interface RepositoryObject extends Serializable {
    public String sha1hash = null;
    public default String sha1hash() {
        return this.sha1hash;
    }
}
