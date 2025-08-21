package batch1029;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class ChuckFileCopy{
    public static void main(String[] args) {
        String inputFile = "C:\\Users\\adity\\Downloads\\bigfile.txt";
        String outputFile = "C:\\Users\\adity\\Downloads\\bigfile_copy.txt";

        try (FileInputStream fis = new FileInputStream(inputFile);
             FileChannel readChannel = fis.getChannel();
             FileOutputStream fos = new FileOutputStream(outputFile);
             FileChannel writeChannel = fos.getChannel()) {

            int chunkSize = 1024 * 1024 * 10; // 10MB chunks
            ByteBuffer buffer = ByteBuffer.allocate(chunkSize);

            while (readChannel.read(buffer) != -1) {
                buffer.flip();  // switch to read mode

                // instantly write what was read
                while (buffer.hasRemaining()) {
                    writeChannel.write(buffer);
                }

                buffer.clear(); // ready for next chunk
            }

            System.out.println("File copied in chunks successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
//here we use the inputfile reader and the output stream writer
//and takes the chuck byte buffer
//nio new input and output
//new channel
//byteBuffer mapped bytebuffer