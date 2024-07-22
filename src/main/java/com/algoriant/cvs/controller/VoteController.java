package com.algoriant.cvs.controller;

import com.algoriant.cvs.dto.VoteDTO;
import com.algoriant.cvs.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/cvs", produces = {MediaType.APPLICATION_JSON_VALUE})
public class VoteController {

    @Autowired
    VoteService voteService;

    @PostMapping(value = "/createVote")
    @PreAuthorize("hasAuthority('user')")
    public ResponseEntity<VoteDTO> createVote(@RequestBody VoteDTO voteDTO) {
        try {
            return new ResponseEntity<>(voteService.createVote(voteDTO), HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @DeleteMapping(value = "/removeAllVotes")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<Map<String, String>> removeAllVotes() {
        Map<String, String> response = new HashMap<>();
        try {
            response.put("message", voteService.removeAllVotes());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
        }
    }
}
