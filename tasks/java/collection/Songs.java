package collection;

public class Songs {
    private String songName;
    private String authorName;

    // Constructor
    public Songs(String songName, String authorName) {
        this.songName = songName;
        this.authorName = authorName;
    }

    // Getter for songName
    public String getSongName() {
        return songName;
    }

    // Setter for songName
    public void setSongName(String songName) {
        this.songName = songName;
    }

    // Getter for authorName
    public String getAuthorName() {
        return authorName;
    }

    // Setter for authorName
    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }
}
