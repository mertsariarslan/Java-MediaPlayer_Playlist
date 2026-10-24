/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MediaPlayer_Playlist;

/**
 *
 * @author merts
 */
public class PodcastEpisode extends MediaItem {
    private final int episodeNumber;
    private final String podcastName;

    public PodcastEpisode(String title, int durationSeconds, String podcastName ,int episodeNumber){
        super(title, durationSeconds);
        if (podcastName == null || podcastName.trim().isEmpty()) {
            throw new IllegalArgumentException("Podcast name cannot be null or blank!");
        }
        if (episodeNumber < 1) {
            throw new IllegalArgumentException("Episode number must be at least 1!");
        }
        
        this.podcastName = podcastName;
        this.episodeNumber = episodeNumber;
    }

    public int getEpisodeNumber() {
        return episodeNumber;
    }

    public String getPodcastName() {
        return podcastName;
    }
    
    public String getMediaType(){
        return "PODCAST EPİSODE";
    }

    @Override
    public String toString() {
        return super.toString()+ ", EpisodeNumber=" + episodeNumber + ", podcastName=" + podcastName;
    }
    
    @Override
    public String toFileString(){
        return "PODCAST|" + getTitle() + "|" + getDurationSeconds() + "|" + podcastName + "|" + episodeNumber;
    } 
    
}
