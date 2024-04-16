package com.niit.challenge1_Muzix_Appli.service;

import com.niit.challenge1_Muzix_Appli.domain.MusicTrack;
import com.niit.challenge1_Muzix_Appli.exception.TrackAlreadyExistsException;
import com.niit.challenge1_Muzix_Appli.exception.TrackNotFoundException;

import java.util.List;

public interface TrackService
{

    MusicTrack saveTrackDetail(MusicTrack musicTrack) throws TrackAlreadyExistsException;
    boolean deleteTrack(int trackId) throws TrackNotFoundException;
    List<MusicTrack> getALlTracks() throws Exception;
    List<MusicTrack> getAllTrackRatingsGt4(int trackRating) throws TrackNotFoundException;
    List<MusicTrack> getAllTrackWithArtistName(String artistName) throws Exception;


}
