/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MediaPlayer_Playlist;

/**
 *
 * @author merts
 */
public class MediaItemNotFoundException extends Exception {
    public MediaItemNotFoundException(String title) {
        super("Media item with title '" + title + "' was not found.");
    }
    
}
