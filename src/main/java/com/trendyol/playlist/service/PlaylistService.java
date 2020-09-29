package com.trendyol.playlist.service;

import com.trendyol.playlist.domain.Playlist;
import com.trendyol.playlist.domain.Track;
import com.trendyol.playlist.repository.PlaylistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaylistService {

    @Autowired
    private final PlaylistRepository playlistRepository;

    public PlaylistService(PlaylistRepository playlistRepository) {
        this.playlistRepository = playlistRepository;
    }

    public Playlist findById(String playlistId){
        return playlistRepository.findById(playlistId);
    }
    public List<Playlist> findAllByUserId(String userId){
        return playlistRepository.findAllByUserId(userId);
    }
    public void addTrack(String playlistId, Track track){
        playlistRepository.addTrack(playlistId,track);
    }
    public void removeTrack(String playlistId,Track track){
        playlistRepository.removeTrack(playlistId,track);
    }
    public void create(Playlist playlist){
        playlistRepository.create(playlist);
    }
    public void delete(String playlistId){
        playlistRepository.delete(playlistId);
    }
}
