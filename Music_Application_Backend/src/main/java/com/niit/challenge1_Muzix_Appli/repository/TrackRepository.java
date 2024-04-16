package com.niit.challenge1_Muzix_Appli.repository;

import com.niit.challenge1_Muzix_Appli.domain.MusicTrack;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface TrackRepository extends MongoRepository<MusicTrack, Integer>
{

    @Query("{'trackRating' : {$gt : ?0}}")
    List<MusicTrack> findByTrackRatingGtThan4(int trackRating);

    @Query("{'trackArtist.artistName' : ?0}")
    List<MusicTrack> findByTrackArtistName(String artistName);


}
