package com.algoriant.cvs.controller;

import com.algoriant.cvs.dto.CandidateDTO;
import com.algoriant.cvs.entity.Candidate;
import com.algoriant.cvs.service.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/cvs", produces = {MediaType.APPLICATION_JSON_VALUE})
public class CandidateController {

    @Autowired
    CandidateService candidateService;

    @PostMapping(value = "/createCandidate")
    public ResponseEntity<Candidate> createCandidate(@RequestBody CandidateDTO candidateDTO) {
        try {
            return new ResponseEntity<>(candidateService.createCandidate(candidateDTO), HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @DeleteMapping(value = "/removeCandidate")
    public ResponseEntity<String> removeCandidate(@RequestParam String candidateId) {
        try {
            String response = String.valueOf(candidateId);
            if (candidateService.removeCandidate(candidateId) == null)
                response += " NOT FOUND";
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping(value = "/getCandidateById")
    public ResponseEntity<CandidateDTO> getCandidateById(@RequestParam String candidateId) {
        try {
            return new ResponseEntity<>(candidateService.getCandidateById(candidateId), HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping(value = "/getCandidatesByElectionName")
    public ResponseEntity<List<CandidateDTO>> getCandidatesByElectionName(@RequestParam String electionName) {
        try {
            return new ResponseEntity<>(candidateService.getCandidatesByElectionName(electionName), HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping(value = "/getAllCandidates")
    public ResponseEntity<List<CandidateDTO>> getAllCandidates() {
        try {
            return new ResponseEntity<>(candidateService.getAllCandidates(), HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }
}
