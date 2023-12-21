package com.algoriant.cvs.controller;

import com.algoriant.cvs.dto.VoteDTO;
import com.algoriant.cvs.entity.Vote;
import com.algoriant.cvs.service.impl.VoteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/cvs", produces = {MediaType.APPLICATION_JSON_VALUE})
public class VoteController {

    @Autowired
    VoteServiceImpl voteService;

    @PostMapping(value = "/createVote")
    public ResponseEntity<VoteDTO> createVote(@RequestBody VoteDTO voteDTO) {
        try {
            return new ResponseEntity<>(voteService.createVote(voteDTO), HttpStatus.OK);
        } catch (Exception exception) {
            exception.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @DeleteMapping(value = "/removeAllVotes")
    public ResponseEntity<String> removeAllVotes() {
        try {
            return new ResponseEntity<>(voteService.removeAllVotes(), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }
}
