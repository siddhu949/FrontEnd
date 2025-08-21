package batch1029;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ChunkFileCopySimple {
    private static final int CHUNK_SIZE = 1024 * 1024; // 1MB per chunk
    private static final int QUEUE_CAPACITY = 5; // allow 5 chunks in memory

    public static void main(String[] args) throws Exception {
        String inputFile = "C:\\Users\\adity\\Downloads\\bigfile.txt";
        String outputFile = "C:\\Users\\adity\\Downloads\\bigfile_copy.txt";

        // Queue to share data between Reader and Writer
        BlockingQueue<byte[]> queue = new ArrayBlockingQueue<>(QUEUE_CAPACITY);

        // Reader Thread -> Reads chunks from file and puts in queue
        Thread reader = new Thread(() -> {
            try (FileInputStream fis = new FileInputStream(inputFile)) {
                byte[] buffer = new byte[CHUNK_SIZE];
                int bytesRead;

                while ((bytesRead = fis.read(buffer)) != -1) {
                    // Copy only the part that was read (avoid garbage data in last chunk)
                    byte[] actualData = new byte[bytesRead];
                    System.arraycopy(buffer, 0, actualData, 0, bytesRead);

                    queue.put(actualData); // add to queue
                }

                // Special signal to tell writer "file is finished"
                queue.put(new byte[0]);

            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        // Writer Thread -> Takes chunks from queue and writes to output file
        Thread writer = new Thread(() -> {
            try (FileOutputStream fos = new FileOutputStream(outputFile)) {
                while (true) {
                    byte[] chunk = queue.take();

                    // If chunk is empty → End of File
                    if (chunk.length == 0) break;

                    fos.write(chunk); // write chunk to output
                }

                System.out.println("✅ File copied successfully with Reader & Writer threads!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        // Start both threads
        reader.start();
        writer.start();

        // Wait until both finish
        reader.join();
        writer.join();
    }
}
