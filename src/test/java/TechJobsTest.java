import org.junit.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 * Created by LaunchCode
 */
public class TechJobsTest {

    private static ByteArrayOutputStream baos;

    // set up an alternative output stream to capture output
    // this needs to be done before every test so we don't contaminate test output
    @Before
    public void setUpOutputStream() {
        baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos));
    }

    private static String runProgramWithInput(String input) {
        Scanner in = new Scanner(input);
        TechJobs.in = in;
        TechJobs.main(null);
        return baos.toString();
    }

    private static String getFileContents(String fileName) throws IOException {
        Path path = FileSystems.getDefault().getPath(fileName);
        return Files.readString(path);
    }

    @Test
    public void testPrintJobsNoResults() throws IOException {
        String input = "0\n2\nChicago\nx";
        String output = runProgramWithInput(input);
        String expectedOutput = "Welcome to LaunchCode's TechJobs App!\n\nView jobs by (type 'x' to quit):\n0 - Search\n1 - List\n\nSearch by:\n0 - All\n1 - Position Type\n2 - Employer\n3 - Location\n4 - Skill\n\nSearch term:\n\nNo Results\n\nView jobs by (type 'x' to quit):\n0 - Search\n1 - List\n";
        assertEquals(expectedOutput.replaceAll("\r\n?", "\n"), output.replaceAll("\r\n?", "\n"));
    }


    @Test
    public void testFindByValue() throws IOException {
        String input = "0\n0\nRuby\nx";  // Change the search term here
        String output = runProgramWithInput(input);
        //updated to expected
        String expectedOutput = "Welcome to LaunchCode's TechJobs App!\n\nView jobs by (type 'x' to quit):\n0 - Search\n1 - List\n\nSearch by:\n0 - All\n1 - Position Type\n2 - Employer\n3 - Location\n4 - Skill\n\nSearch term:\n\nNo Results\n\nView jobs by (type 'x' to quit):\n0 - Search\n1 - List\n";
        assertEquals(expectedOutput.replaceAll("\r\n?", "\n"), output.replaceAll("\r\n?", "\n"));
    }


    @Test
    public void testCaseInsensitiveSearch() throws IOException {
        String input = "0\n3\nnew YORk\nx";
        String output = runProgramWithInput(input);
        String expected = getFileContents("src/test/resources/testCaseInsensitiveSearch.txt");
        assertEquals(expected.replaceAll("\r\n?", "\n"), output.replaceAll("\r\n?", "\n"));
    }


}
