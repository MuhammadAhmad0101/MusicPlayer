import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import javax.sound.sampled.*;
import java.io.IOException;

public class MusicPlayer {
        ArrayList<Song> Allsong = new ArrayList<Song>();

        private String defaultDirectory = "C:/Users/ahmad/Desktop/MusicPlayer/audio/";

        public void AddSong() {
                Scanner input = new Scanner(System.in);
                System.out.print("Enter the Title : ");
                String title = input.nextLine();
                System.out.print("Enter the path : ");
                String path = input.nextLine();
                path = defaultDirectory + path;
                File file = new File(path);
                if (file.exists()) {
                        Song addSong = new Song(title, path);
                        Allsong.add(addSong);
                        System.out.println("Song Added : " + title);
                } else {
                        System.out.println("Invalid Path");
                }

        }

        public void playMusic(Song song) {
                File audioFile = new File(song.getPath());
                try {

                        AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
                        Clip clip = AudioSystem.getClip();
                        clip.open(audioStream);
                        clip.start();
                        System.out.println("Now Playing...." + song.getTitle());
                        System.out.println("Please 'Enter' if you want to stop Music Playing ");
                        Scanner input = new Scanner(System.in);
                        input.nextLine();
                        clip.stop();
                        clip.close();
                } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
                        System.out.println("Error Playing The Audio...");
                }
        }

        public void MusicMenu() {
                if (Allsong.isEmpty())
                        System.out.println("No Song was Added");

                else {
                        System.out.println("-----All Songs------");
                        for (int i = 0; i < Allsong.size(); i++) {
                                System.out.println((i + 1) + ". " + Allsong.get(i).getTitle());
                        }
                        System.out.print("Choose a Song by its number : ");
                        Scanner input = new Scanner(System.in);
                        int choose = input.nextInt();
                        if (choose > 0 && choose <= Allsong.size()) {
                                playMusic(Allsong.get(choose - 1));
                        } else {
                                System.out.println("Invalid Music Selection Try Again");
                                MusicMenu();
                        }

                }
        }

        public void start() {
                boolean keepRunning = true;
                while (keepRunning) {
                        System.out.println("1. Enter a Song ");
                        System.out.println("2. Run a Music Player ");
                        System.out.println("3. Exit ");
                        System.out.print("Choose an Option: ");
                        Scanner input = new Scanner(System.in);
                        int option = input.nextInt();
                        switch (option) {
                                case 1:
                                        AddSong();
                                        break;
                                case 2:
                                        MusicMenu();
                                        break;
                                case 3:
                                        System.out.println("Existing.....");
                                        keepRunning = false;
                                        break;

                                default:
                                        System.out.println("Invalid Selection Try....");
                        }
                }

        }
}
