package com.niit.challenge1_Muzix_Appli.service;

import com.niit.challenge1_Muzix_Appli.domain.MusicTrack;
import com.niit.challenge1_Muzix_Appli.exception.TrackAlreadyExistsException;
import com.niit.challenge1_Muzix_Appli.exception.TrackNotFoundException;
import com.niit.challenge1_Muzix_Appli.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrackServiceImpl implements TrackService
{

    private TrackRepository trackRepository;

    @Autowired
    public TrackServiceImpl(TrackRepository trackRepository) {
        this.trackRepository = trackRepository;
    }

    @Override
    public MusicTrack saveTrackDetail(MusicTrack musicTrack) throws TrackAlreadyExistsException {
        if (trackRepository.findById(musicTrack.getTrackId()).isPresent()) {
            throw new TrackAlreadyExistsException();
        }
        return trackRepository.save(musicTrack);
    }

    @Override
    public boolean deleteTrack(int trackId) throws TrackNotFoundException {
        boolean flag = false;
        if (trackRepository.findById(trackId).isEmpty()) {
            throw new TrackNotFoundException();
        } else {
            trackRepository.deleteById(trackId);
            flag = true;
        }
        return flag;
    }

    @Override
    public List<MusicTrack> getALlTracks() throws Exception {
        return trackRepository.findAll();
    }

    @Override
    public List<MusicTrack> getAllTrackRatingsGt4(int trackRating) throws TrackNotFoundException {
        if (trackRepository.findByTrackRatingGtThan4(trackRating).isEmpty()) {
            throw new TrackNotFoundException();
        }
        return trackRepository.findByTrackRatingGtThan4(trackRating);
    }

    @Override
    public List<MusicTrack> getAllTrackWithArtistName(String artistName) throws Exception {
        if (trackRepository.findByTrackArtistName(artistName).isEmpty()) {
            throw new Exception();
        }
        return trackRepository.findByTrackArtistName(artistName);
    }


}
