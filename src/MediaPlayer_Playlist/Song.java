/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MediaPlayer_Playlist;

/**
 *
 * @author merts
 */
public class Song extends MediaItem{
    private final String artist;
    private final String album;

    public Song(String title, int durationSeconds, String artist) {
        super(title, durationSeconds);
        this.artist = artist;
        this.album="Unknown Album";
    }

    
    public Song(String title, int durationSeconds, String artist, String album) {
        super(title, durationSeconds);
        if (artist == null || artist.trim().isEmpty() || album == null || album.trim().isEmpty()) {
            throw new IllegalArgumentException("Artist or album cannot be null or blank!");
        }
        this.artist = artist;
        this.album = album;
    }

    public String getArtist() {
        return artist;
    }

    public String getAlbum() {
        return album;
    }
    
    @Override
    public String getMediaType(){
        return "SONG";
    }

    @Override
    public String toString() {
        return super.toString() + ", Artist=" + artist + ", Album=" + album;
    }

    @Override
    public String toFileString() {
        return "SONG|" + getTitle() + "|" + getDurationSeconds() + "|" + artist + "|" + album;
    }
    
}
