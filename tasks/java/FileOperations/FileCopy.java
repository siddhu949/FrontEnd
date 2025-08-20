import java.io.*;  // import BufferedReader, FileReader, etc.

public class FileCopy {
    public static void main(String[] args) {
        String inputFile = "C:\\Users\\adity\\Downloads\\virat.txt";
        String outputFile = "C:\\Users\\adity\\Downloads\\output.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(inputFile));
             BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile))) {

            String line;
            while ((line = br.readLine()) != null) {
                bw.write(line);
                bw.newLine();  // to keep lines properly
            }

            System.out.println("File copied successfully!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
//BufferReader br = new BufferReader(new FileReader(inputFile));
// BufferWriter bw =new BufferWriter(new FILEwwRITER(OutputFile));
//sTRING LINE;
//while(line =br.readline()!=null){
//bw.write(line);
//bw.newlinw();
