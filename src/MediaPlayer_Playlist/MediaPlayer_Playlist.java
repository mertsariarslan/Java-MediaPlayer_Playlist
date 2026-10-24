/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package MediaPlayer_Playlist;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
/**
 *
 * @author merts
 */
public class MediaPlayer_Playlist {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner input= new Scanner(System.in);
        ArrayList<Playlist> allPlaylists = new ArrayList<>();
        Playlist currentPlaylist = null;
        boolean run=true;
        
        while (run==true){
            if (currentPlaylist == null) {
                System.out.println("Status: No Active Playlist");
                System.out.println("1. Create New Playlist");
                if (!allPlaylists.isEmpty()) System.out.println("2. Select Playlist");
                System.out.println("0. Exit");
            }else{
                System.out.println("\n=== MAIN MENU ===");
                System.out.println("Active Playlist Index: " + (currentPlaylist != null ? allPlaylists.indexOf(currentPlaylist) : "None"));
                System.out.println("1. Create New Playlist");
                System.out.println("2. Select Playlist");
                System.out.println("3. Add Song");
                System.out.println("4. Add Podcast Episode");
                System.out.println("5. Print Active Playlist");
                System.out.println("6. Save to File");
                System.out.println("7. Load from File");
                System.out.println("0. Exit");
                System.out.print("Select an option: ");
            }
            int prefer = input.nextInt();
            input.nextLine();
            
            switch(prefer){
                case 1:
                    System.out.print("Enter Playlist Name: ");
                    String name = input.nextLine();
                    allPlaylists.add(new Playlist(name));
                    System.out.println("Playlist created.");
                    break;
                
                case 2:
                    System.out.println("Total playlists: " + allPlaylists.size());
                    System.out.print("Enter playlist index (0 to " + (allPlaylists.size() - 1) + "): ");
                    int i = input.nextInt();
                    if (i >= 0 && i < allPlaylists.size()) {
                        currentPlaylist = allPlaylists.get(i);
                        System.out.println("Playlist selected.");
                    } else {
                        System.out.println("Invalid index.");
                    }
                    break;
                
                case 3:
                    try {
                        System.out.print("Enter song title: ");
                        String songTitle = input.nextLine();
                        System.out.print("Enter duration in seconds: ");
                        int songDuration = Integer.parseInt(input.nextLine());
                        System.out.print("Enter artist: ");
                        String artist = input.nextLine();
                        System.out.print("Enter album (press Enter to skip): ");
                        String album = input.nextLine();

                        Song newSong;
                        if (album.trim().isEmpty()) {
                            newSong = new Song(songTitle, songDuration, artist);
                        } else {
                            newSong = new Song(songTitle, songDuration, artist, album);
                        }

                        if (currentPlaylist.addItem(newSong)) {
                            System.out.println("Song added successfully.");
                        } else {
                            System.out.println("Failed: An item with this title already exists.");
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error: " + e.getMessage());
                    } catch (Exception e) {
                        System.out.println("Invalid input format.");
                    }
                    break;

                case 4:
                    try {
                        System.out.print("Enter podcast title: ");
                        String podTitle = input.nextLine();
                        System.out.print("Enter duration in seconds: ");
                        int podDuration = Integer.parseInt(input.nextLine());
                        System.out.print("Enter podcast name: ");
                        String podName = input.nextLine();
                        System.out.print("Enter episode number: ");
                        int episodeNum = Integer.parseInt(input.nextLine());

                        PodcastEpisode newPodcast = new PodcastEpisode(podTitle, podDuration, podName, episodeNum);

                        if (currentPlaylist.addItem(newPodcast)) {
                            System.out.println("Podcast episode added successfully.");
                        } else {
                            System.out.println("Failed: An item with this title already exists.");
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error: " + e.getMessage());
                    } catch (Exception e) {
                        System.out.println("Invalid input format.");
                    }
                    break;
                    
                case 5:
                    System.out.println("\n--- Current Playlist ---");
                    currentPlaylist.printPlaylist();
                    break;
                
                    case 6:
                    if (currentPlaylist == null) break;
                    try {
                        System.out.print("Enter file name to save (e.g., data.txt): ");
                        String saveName = input.nextLine();
                        currentPlaylist.writeToFile(saveName);
                        System.out.println("Playlist saved successfully to " + saveName);
                    } catch (IOException e) {
                        System.out.println("Error saving file: " + e.getMessage());
                    }
                    break;

                case 7:
                    if (currentPlaylist == null) break;
                    try {
                        System.out.print("Enter file name to load (e.g., data.txt): ");
                        String loadName = input.nextLine();
                        int loaded = currentPlaylist.populateFromFile(loadName);
                        System.out.println("Successfully loaded " + loaded + " items from " + loadName);
                    } catch (IOException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                    
                case 0: 
                    run = false;
                    break;
                default:
                    System.out.println("Invalid option.");
            }
            
        
        }
        input.close();
    }
 }
    

