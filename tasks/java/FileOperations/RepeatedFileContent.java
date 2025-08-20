import java.io.*;

public class RepeatedFileContent {
    public static void main(String[] args) {
        String inputFile = "C:\\Users\\adity\\Downloads\\virat.txt";   // source file
        String outputFile = "C:\\Users\\adity\\Downloads\\output1.txt"; // destination file
        int repeatCount = 3;              // how many times to repeat

        StringBuilder sb = new StringBuilder();

        // Step 1: Read file into StringBuilder
        try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append(System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Step 2: Write repeated content
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile))) {
            for (int i = 0; i < repeatCount; i++) {
                bw.write(sb.toString()); // repeat entire file content
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("File content copied and repeated " + repeatCount + " times into " + outputFile);
    }
}
//string builder 
//buffer reader first to read line separator
//buffer writer for the write sb.append