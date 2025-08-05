//package collection;
//
//import java.util.ArrayList;
//import java.util.Random;
//
//public class MusicPlayer {
//	ArrayList <Songs> so;
//	//run time adding
//	MusicPlayer(){
//		so=new ArrayList <Songs>();
//	}
//	void addSongs(Songs s) {
//		so.add(s);
//	}
//	void removeSongs(Songs s) {
//		so.remove(s);
//	}
//	//return type as array list
//	ArrayList<Songs> getAllsongs() {
//		return so;
//	}
//	void playRandomSong() {
//		Random r=new Random();
//		int index=r.nextInt(so.size());
//		Songs s=so.get(index);
//		System.out.println(s.getAuthorName()+" "+s.getSongName());
//	}
//
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		MusicPlayer ob=new MusicPlayer();
//		Songs s1=new Songs("tere bina","arjitsingh");
//		Songs s2=new Songs("pushpa","DSP");
//		Songs s3=new Songs("Devera","anirudh");
//		Songs s4=new Songs("Power House of coolie","anirudh");
//		 // Add songs to the player
//        ob.addSongs(s1);
//        ob.addSongs(s2);
//        ob.addSongs(s3);
//        ob.addSongs(s4);
//		for(Songs x:ob.getAllsongs()) {
//			System.out.println(x.getAuthorName()+" "+x.getSongName());
//			
//		}
//		System.out.println("random selecetion");
//		ob.playRandomSong();
//		
//
//	}
//
//}
package collection;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class MusicPlayer {
    ArrayList<Songs> so;

    MusicPlayer() {
        so = new ArrayList<>();
    }

    void addSongs(Songs s) {
        so.add(s);
    }

    void removeSongs(String songName) {
        boolean removed = false;
        for (int i = 0; i < so.size(); i++) {
            if (so.get(i).getSongName().equalsIgnoreCase(songName)) {
                so.remove(i);
                removed = true;
                System.out.println("Song removed successfully.");
                break;
            }
        }
        if (!removed) {
            System.out.println("Song not found.");
        }
    }

    ArrayList<Songs> getAllsongs() {
        return so;
    }

    void playRandomSong() {
        if (so.isEmpty()) {
            System.out.println("No songs to play.");
            return;
        }
        Random r = new Random();
        int index = r.nextInt(so.size());
        Songs s = so.get(index);
        System.out.println("Now Playing: " + s.getAuthorName() + " - " + s.getSongName());
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        MusicPlayer ob = new MusicPlayer();
        int choice;

        do {
            System.out.println("\n🎵 Music Player Menu 🎵");
            System.out.println("1. Add Song");
            System.out.println("2. Remove Song");
            System.out.println("3. Display All Songs");
            System.out.println("4. Play Random Song");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Song Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Author Name: ");
                    String author = sc.nextLine();
                    ob.addSongs(new Songs(name, author));
                    System.out.println("Song added successfully.");
                    break;

                case 2:
                    System.out.print("Enter Song Name to Remove: ");
                    String removeName = sc.nextLine();
                    ob.removeSongs(removeName);
                    break;

                case 3:
                    System.out.println("🎶 All Songs in Playlist:");
                    for (Songs s : ob.getAllsongs()) {
                        System.out.println(s.getAuthorName() + " - " + s.getSongName());
                    }
                    break;

                case 4:
                    ob.playRandomSong();
                    break;

                case 5:
                    System.out.println("Exiting Music Player. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }

        } while (choice != 5);

        sc.close();
    }
}

