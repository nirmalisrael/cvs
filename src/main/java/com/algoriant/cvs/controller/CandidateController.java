package com.algoriant.cvs.controller;

import com.algoriant.cvs.dto.CandidateDTO;
import com.algoriant.cvs.entity.Candidate;
import com.algoriant.cvs.service.impl.CandidateServiceImpl;
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
    CandidateServiceImpl candidateService;

    @PostMapping(value = "/createCandidate")
    public ResponseEntity<Candidate> createCandidate(@RequestBody CandidateDTO candidateDTO) {
        try {
            return new ResponseEntity<>(candidateService.createCandidate(candidateDTO), HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @DeleteMapping(value = "/removeCandidate")
    public ResponseEntity<String> removeCandidate(@RequestParam Long lotNo) {
        try {
            String response = String.valueOf(lotNo);
            if (candidateService.removeCandidate(lotNo) == null)
                response += " NOT FOUND";
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping(value = "/getCandidateById")
    public ResponseEntity<Candidate> getCandidateById(@RequestParam Long lotNo) {
        try {
            return new ResponseEntity<>(candidateService.getCandidateById(lotNo), HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping(value = "/getAllCandidates")
    public ResponseEntity<List<Candidate>> getAllCandidates() {
        try {
            return new ResponseEntity<>(candidateService.getAllCandidates(), HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }
}
