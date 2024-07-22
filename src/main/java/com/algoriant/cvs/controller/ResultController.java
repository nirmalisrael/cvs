package com.algoriant.cvs.controller;

import com.algoriant.cvs.dto.VoteDTO;
import com.algoriant.cvs.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/cvs", produces = {MediaType.APPLICATION_JSON_VALUE})
@PreAuthorize("hasAuthority('admin')")
public class ResultController {

    @Autowired
    ResultService resultService;

    @GetMapping(value = "/getVotesByElectionNameAndCandidateId")
    public ResponseEntity<List<VoteDTO>> getVotesByElectionNameAndCandidateId(@RequestParam String electionName,
                                                                              @RequestParam String candidateId) {
        try {
            return new ResponseEntity<>(resultService.getVotesByElectionNameAndCandidateId(electionName, candidateId),
                    HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping(value = "/getAllVotes")
    public ResponseEntity<List<VoteDTO>> getAllVotes() {
        try {
            return new ResponseEntity<>(resultService.getAllVotes(), HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }
}
