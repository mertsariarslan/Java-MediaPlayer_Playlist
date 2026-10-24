/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MediaPlayer_Playlist;

/**
 *
 * @author merts
 */
public abstract class MediaItem implements Playable {
    private final String title;
    private final int durationSeconds;
    private PlaybackStatus status;

    protected MediaItem(String title, int durationSeconds) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Title can not be null or empty!");
        }
        if (durationSeconds < 1) {
            throw new IllegalArgumentException("Duration must be at least 1 second!");
        }
        this.title = title;
        this.durationSeconds = durationSeconds;
        this.status = PlaybackStatus.STOPPED;
    }
    
    @Override
    public boolean play() {
        if (this.status == PlaybackStatus.STOPPED) {
            this.status = PlaybackStatus.PLAYING;
            return true;
        }
        return false;
    }
    
    @Override
    public boolean stop() {
        if (this.status == PlaybackStatus.PLAYING) {
            this.status = PlaybackStatus.STOPPED;
            return true;
        }
        return false;
    }
    
    @Override
    public boolean isPlaying(){
        if(this.status==PlaybackStatus.PLAYING){
            return true;
        }
        return false;
    }
    
    public String getTitle() {
        return title;
    }

    public int getDurationSeconds() {
        return durationSeconds;
    }

    public PlaybackStatus getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "[" + getMediaType() + "] " + title + " (" + durationSeconds + "seconds) - Status: " + status;
    }
    
    public abstract String getMediaType();
    public abstract String toFileString();

}
