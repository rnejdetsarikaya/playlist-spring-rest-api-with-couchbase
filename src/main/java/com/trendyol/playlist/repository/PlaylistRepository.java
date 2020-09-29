package com.trendyol.playlist.repository;

import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.Collection;
import com.couchbase.client.java.kv.GetResult;
import com.couchbase.client.java.query.QueryResult;
import com.trendyol.playlist.domain.Playlist;
import com.trendyol.playlist.domain.Track;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PlaylistRepository {

    private final Cluster couchbaseCluster;
    private final Collection playlistCollection;

    public PlaylistRepository(Cluster couchbaseCluster, Collection playlistCollection) {
        this.couchbaseCluster = couchbaseCluster;
        this.playlistCollection = playlistCollection;
    }

    public Playlist findById(String playlistId){
        GetResult getResult = playlistCollection.get(playlistId);
        Playlist playlist = getResult.contentAs(Playlist.class);
        return playlist;
    }
    public List<Playlist> findAllByUserId(String userId){
        String statement = String.format("Select id,name,description,followersCount,trackCount,userId,tracks from playlist where userId='%s'",userId);
        QueryResult query = couchbaseCluster.query(statement);
        return query.rowsAs(Playlist.class);
    }
    public void addTrack(String playlistId, Track track){
        GetResult getResult = playlistCollection.get(playlistId);
        Playlist playlist = getResult.contentAs(Playlist.class);
        playlist.getTracks().add(track);
        playlistCollection.upsert(playlistId,playlist);
    }
    public void removeTrack(String playlistId,Track track){
        GetResult getResult = playlistCollection.get(playlistId);
        Playlist playlist = getResult.contentAs(Playlist.class);
        Track track1 = playlist.getTracks().stream()
                .filter(t -> t.getName().equals(track.getName())).findFirst().get();
        playlist.getTracks().remove(track1);
        playlistCollection.upsert(playlistId,playlist);
    }
    public void create(Playlist playlist){
        playlistCollection.insert(playlist.getId(),playlist);
    }
    public void delete(String playlistId){
        playlistCollection.remove(playlistId);
    }
}
