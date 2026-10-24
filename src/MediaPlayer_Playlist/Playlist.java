/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MediaPlayer_Playlist;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
/**
 *
 * @author merts
 */
public class Playlist {
    private final String name;
    private final ArrayList<MediaItem> items;

    public Playlist(String name) {
        if (name == null || name.trim().isEmpty()){
            throw new IllegalArgumentException("Name cannot null or blank");
        }
        this.name = name;
        this.items = new ArrayList<>();
    }
    
    public int getSize(){
    return items.size();
    }
    
    public boolean addItem(MediaItem item){
        if (item == null) {
            return false;
        }
        if (findItemByTitle(item.getTitle()) != null) {
            return false;
        }
        return items.add(item);
    }
    
    public MediaItem findItemByTitle(String title) {
        if (title == null) {
            return null;
        }
        for (MediaItem item : items) {
            if (item.getTitle().equalsIgnoreCase(title.trim())) {
                return item;
            }
        }
        return null;
    }
    
    public boolean removeItemByTitle(String title){
        MediaItem item = findItemByTitle(title);
        if(item==null){
            return false;
        }
        items.remove(item);
        return true;
    }
    
    public boolean playItem(String title) throws MediaItemNotFoundException{
        MediaItem item= findItemByTitle(title);
        if(item==null){
            throw new MediaItemNotFoundException(title);
        }
        return item.play();
    }
    
    public ArrayList<MediaItem> getItemsLongerThan(int sec){
        ArrayList<MediaItem> longers= new ArrayList<>();
        for(MediaItem item:items){
            if(item.getDurationSeconds()>sec){
                longers.add(item);
            }
        }
        return longers;
    }
    
    public int getTotalDuration(){
        int total=0;
        for(MediaItem item:items){
            total+= item.getDurationSeconds();
        }
        return total;
    }
    
    public void printPlaylist(){
        for(int i=0;i<items.size();i++){
            System.out.println((i+1)+".) "+items.get(i).toString());
        }
    }
    
    
    public void writeToFile(String fileName) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            for (MediaItem item : items) {
                writer.println(item.toFileString());
            }
        }
    }
    
    public int populateFromFile(String fileName) throws IOException {
        items.clear();
        int loadedCount = 0;
    
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }
            
                String[] parts = line.split("\\|", -1); 
                String type = parts[0];
                String title = parts[1];
                int durationSeconds = Integer.parseInt(parts[2]);
            
                if (type.equals("SONG")) {
                    String artist = parts[3];
                    String album = parts[4];
                    items.add(new Song(title, durationSeconds, artist, album));
                    loadedCount++;
                } else if (type.equals("PODCAST")) {
                    String podcastName = parts[3];
                    int episodeNumber = Integer.parseInt(parts[4]);
                    items.add(new PodcastEpisode(title, durationSeconds, podcastName, episodeNumber));
                    loadedCount++;
                }
            }
        }
        return loadedCount;
    }
}
