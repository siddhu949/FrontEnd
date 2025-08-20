package batch1029;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DisplaySource {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String filePath ="C:\\Users\\adity\\Downloads\\Frontend\\tasks\\java\\FileOperations\\FileCopy.java";
		String outputFile ="C:\\Users\\adity\\Downloads\\virat.txt";
		try(BufferedReader br =new BufferedReader(new FileReader(filePath));
			BufferedWriter bw =new BufferedWriter(new FileWriter(outputFile))) {
			String content =new String(Files.readAllBytes(Paths.get(filePath)));
			System.out.println(content);
			String line;
			while((line=br.readLine())!=null) {
				bw.write(line);
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}

	}

}
