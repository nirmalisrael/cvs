package com.algoriant.cvs.controller;

import com.algoriant.cvs.dto.CandidateDTO;
import com.algoriant.cvs.entity.Candidate;
import com.algoriant.cvs.service.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/cvs", produces = {MediaType.APPLICATION_JSON_VALUE})
@PreAuthorize("hasAuthority('admin')")
public class CandidateController {

    @Autowired
    CandidateService candidateService;

    @PostMapping(value = "/createCandidate")
    public ResponseEntity<CandidateDTO> createCandidate(@RequestBody CandidateDTO candidateDTO) {
        try {
            return new ResponseEntity<>(candidateService.createCandidate(candidateDTO), HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @PostMapping(value = "/createCandidates")
    public ResponseEntity<List<CandidateDTO>> createCandidates(@RequestBody List<CandidateDTO> candidateDTOS) {
        try {
            return new ResponseEntity<>(candidateService.createCandidates(candidateDTOS), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(Collections.emptyList(), HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @DeleteMapping(value = "/removeCandidate")
    public ResponseEntity<Map<String, String>> removeCandidate(@RequestParam String candidateId) {
        Map<String, String> response = new HashMap<>();
        try {
            String deleteResponse = candidateService.removeCandidate(candidateId);
            if (deleteResponse == null)
                response.put("message", candidateId + " NOT FOUND");
            else
                response.put("message", deleteResponse);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
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
    @PreAuthorize("hasAnyAuthority('admin','user')")
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
