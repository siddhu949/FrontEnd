package batch1029;

import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;

public class LargeFileChunkViewer {
    // Safe default: 64 MB window
    private static final long DEFAULT_WINDOW = 64L * 1024 * 1024;

    public static void main(String[] args) throws Exception {
        // Change path; tweak startOffset/windowBytes to “scroll” through the file.
        String filePath = "C:\\Users\\adity\\Downloads\\bigfile.txt";

        long startOffset = 0;                   // where to start (bytes)
        long windowBytes = DEFAULT_WINDOW;      // how many bytes to show

        viewChunk(filePath, startOffset, windowBytes);
    }

    /**
     * Maps a window [startOffset, startOffset+windowBytes) and prints it.
     * Does NOT load the entire file; OS pages only the needed data.
     */
    public static void viewChunk(String path, long startOffset, long windowBytes) throws Exception {
        try (RandomAccessFile raf = new RandomAccessFile(path, "r");
             FileChannel ch = raf.getChannel()) {

            long fileSize = ch.size();
            if (startOffset < 0) startOffset = 0;
            if (startOffset >= fileSize) {
                System.out.println("(start offset beyond EOF)");
                return;
            }

            long size = Math.min(windowBytes, fileSize - startOffset);
            MappedByteBuffer map = ch.map(FileChannel.MapMode.READ_ONLY, startOffset, size);

            // Convert bytes to text (assumes UTF-8; adjust if your file uses another encoding)
            byte[] bytes = new byte[(int) size];
            map.get(bytes);
            String text = new String(bytes, StandardCharsets.UTF_8);

            // Optional: clip to end of a line for neatness
            int lastNewline = text.lastIndexOf('\n');
            if (lastNewline > 0 && lastNewline < text.length() - 1) {
                text = text.substring(0, lastNewline + 1);
            }

            System.out.println(text);
            System.out.printf("%n-- Shown %,d bytes from offset %,d (file size: %,d bytes) --%n",
                    size, startOffset, fileSize);
        }
    }
}
// c:\Users\adity\eclipse-workspace\ServletsProgram
