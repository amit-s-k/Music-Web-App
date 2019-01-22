package com.stackroute.music.controller;

import com.stackroute.music.model.Music;
import com.stackroute.music.service.BasicMusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "/api/v2/")
public class MusicController {
    private BasicMusicService basicMusicService;

    @Autowired
    public MusicController(BasicMusicService basicMusicService) {
        this.basicMusicService = basicMusicService;
    }

    @GetMapping(value = "/findalltracks")
    public ResponseEntity<List<Music>> getAllTracks() {
        return new ResponseEntity<>(basicMusicService.getAllTracks(), HttpStatus.OK);

    }

    @PostMapping(value = "/addtrack")
    public ResponseEntity<String> addNewTrack(@RequestBody Music music) {
        ResponseEntity<String> responseEntity;
        try {
            basicMusicService.addOrUpdateTrack(music);
            responseEntity = new ResponseEntity<>("Track Added Sucessfully", HttpStatus.OK);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<>("Error Occured While Adding Track", HttpStatus.BAD_REQUEST);
        }
        return responseEntity;
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<String> deleteTrackById(@PathVariable int id) {
        ResponseEntity<String> responseEntity;
        try {
            basicMusicService.deleteTrackById(id);
            responseEntity = new ResponseEntity<>("Track Deleted Sucessfully", HttpStatus.OK);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<>("Error Occured While Deleting Track", HttpStatus.BAD_REQUEST);
        }
        return responseEntity;
    }

    @PutMapping(value = "/update")
    public ResponseEntity<String> updateTrack(@RequestBody Music music) {
        ResponseEntity<String> responseEntity;
        try {
            basicMusicService.addOrUpdateTrack(music);
            responseEntity = new ResponseEntity<>("Track Updated Sucessfully", HttpStatus.OK);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<>("Error Occured While Updating Track", HttpStatus.BAD_REQUEST);
        }
        return responseEntity;

    }

    @GetMapping(value = "/find/{id}")
    public ResponseEntity<Music> findTrackById(@PathVariable int id) {
        ResponseEntity<Music> responseEntity;
        Music music = null;
        try {
            Optional<Music> trackById = basicMusicService.findTrackById(id);
            if (trackById.isPresent()) {
                music = trackById.get();
            }
            responseEntity = new ResponseEntity<>(music, HttpStatus.OK);

        } catch (Exception e) {
            responseEntity = new ResponseEntity<>(music, HttpStatus.BAD_REQUEST);
        }
        return responseEntity;
    }

    @GetMapping(value = "/findbyname/{trackname}")
    public ResponseEntity<Music> findByTrackName(@PathVariable String trackname) {
        ResponseEntity<Music> responseEntity;
        try {
            responseEntity = new ResponseEntity<>(basicMusicService.findByTrackName(trackname), HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return responseEntity;
    }
}
