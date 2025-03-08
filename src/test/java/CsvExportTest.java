import org.example.Main;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.assertTrue;

public class CsvExportTest {
    private static final String FILENAME = "test_results.csv";

    @Test
    public void testCsvFileCreation() throws IOException {
        Main.exportToCsv(FILENAME, -1, 1, 0.5, 1e-6);
        assertTrue(Files.exists(Paths.get(FILENAME)));
    }

    @Test
    public void testCsvContent() throws IOException {
        Main.exportToCsv(FILENAME, 0.5, 0.5, 0.1, 1e-6);
        String content = Files.readString(Paths.get(FILENAME));
        assertTrue(content.contains("0.5,"));
    }
}
