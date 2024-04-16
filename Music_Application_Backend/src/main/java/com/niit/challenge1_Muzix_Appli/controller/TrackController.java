package com.niit.challenge1_Muzix_Appli.controller;

import com.niit.challenge1_Muzix_Appli.domain.MusicTrack;
import com.niit.challenge1_Muzix_Appli.exception.TrackAlreadyExistsException;
import com.niit.challenge1_Muzix_Appli.exception.TrackNotFoundException;
import com.niit.challenge1_Muzix_Appli.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class TrackController
{

    private TrackService trackService;
    private ResponseEntity responseEntity;

    @Autowired
    public TrackController(TrackService trackService) {
        this.trackService = trackService;
    }

    @PostMapping("/musicTrack")
    public ResponseEntity saveTrack(@RequestBody MusicTrack musicTrack) throws TrackAlreadyExistsException
    {
        try
        {
            trackService.saveTrackDetail(musicTrack);
            responseEntity = new ResponseEntity(musicTrack, HttpStatus.CREATED);
        }
        catch (TrackAlreadyExistsException e)
        {
            throw new TrackAlreadyExistsException();
        }
        catch (Exception e)
        {
            responseEntity = new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @DeleteMapping("/musicTrack/{trackId}")
    public ResponseEntity deleteTrack(@PathVariable int trackId) throws TrackNotFoundException
    {
        try
        {
            trackService.deleteTrack(trackId);
            responseEntity = new ResponseEntity("Successfully Deleted", HttpStatus.OK);
        }
        catch (TrackNotFoundException e)
        {
            throw new TrackNotFoundException();
        }
        catch (Exception e)
        {
            responseEntity = new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @GetMapping("/musicTracks")
    public ResponseEntity getAllMusicTracks()
    {
        try
        {
            List<MusicTrack> getAllTracks = trackService.getALlTracks();
            responseEntity = new ResponseEntity(getAllTracks, HttpStatus.FOUND);
        }
        catch (Exception e)
        {
            responseEntity = new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @GetMapping("/musicTracks/ratings/{trackRating}")
    public ResponseEntity getTrackRatings(@PathVariable int trackRating) throws TrackNotFoundException
    {
        try
        {
            responseEntity = new ResponseEntity(trackService.getAllTrackRatingsGt4(trackRating), HttpStatus.FOUND);
        }
        catch (TrackNotFoundException e)
        {
            throw new TrackNotFoundException();
        }
        catch (Exception e)
        {
            responseEntity = new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @GetMapping("musicTracks/artists/{artistName}")
    public ResponseEntity GetTrackByArtistName(@PathVariable String artistName)
    {
        try
        {
            responseEntity = new ResponseEntity(trackService.getAllTrackWithArtistName(artistName), HttpStatus.FOUND);
        }
        catch (Exception e)
        {
            responseEntity = new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }


}
