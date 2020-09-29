package com.trendyol.playlist.controller;

import com.trendyol.playlist.domain.Playlist;
import com.trendyol.playlist.domain.Track;
import com.trendyol.playlist.service.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/playlist")
public class PlaylistController {

    @Autowired
    public PlaylistService playlistService;

    @GetMapping("/{playlistId}")
    public Playlist findById(@PathVariable String playlistId){
        return playlistService.findById(playlistId);
    }

    @GetMapping("/user/{userId}")
    public List<Playlist> findAll(@PathVariable String userId){
        return playlistService.findAllByUserId(userId);
    }
    @PostMapping("/user/{userId}")
    public ResponseEntity create(@RequestBody Playlist playlist, @PathVariable String userId){
        playlist.setUserId(userId);
        playlistService.create(playlist);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{playlistId}/track")
    public ResponseEntity addTrack(@RequestBody Track track,@PathVariable String playlistId){
        playlistService.addTrack(playlistId,track);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("/{playlistId}/track")
    public ResponseEntity removeTrack(@RequestBody Track track,@PathVariable String playlistId){
        playlistService.removeTrack(playlistId,track);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("/{playlistId}")
    public ResponseEntity delete(@PathVariable String playlistId){
        playlistService.delete(playlistId);
        return ResponseEntity.ok().build();
    }

}
