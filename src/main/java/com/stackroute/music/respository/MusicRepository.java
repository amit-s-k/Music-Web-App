package com.stackroute.music.respository;

import com.stackroute.music.model.Music;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MusicRepository extends JpaRepository<Music, Integer> {
    @Query(value = "select * from music m where m.track_name = :track_name", nativeQuery = true)
    Music findByTrackComments(@Param("track_name") String trackName);
}
