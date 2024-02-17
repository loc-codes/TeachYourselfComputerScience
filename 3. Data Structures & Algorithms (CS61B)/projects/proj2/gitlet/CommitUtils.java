package gitlet;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.ZonedDateTime;
import java.util.Locale;

public class CommitUtils {
    public static String createInitialCommitTimestape() {
        // Create an Instant object representing the epoch (00:00:00 UTC on 1 January 1970)
        Instant epoch = Instant.EPOCH;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss 'UTC, 'EEEE, d MMMM uuuu", Locale.ENGLISH)
                .withZone(ZoneId.of("UTC"));
        return formatter.format(epoch);
    }

    public static String createCommitTimestamp() {
        ZonedDateTime now = ZonedDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss 'UTC, 'EEEE, d MMMM uuuu", Locale.ENGLISH)
                .withZone(ZoneId.systemDefault());
        return formatter.format(now);
    }
}
