package com.algoriant.cvs.controller;

import com.algoriant.cvs.entity.Election;
import com.algoriant.cvs.service.impl.ElectionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/cvs", produces = {MediaType.APPLICATION_JSON_VALUE})
public class ElectionController {

    @Autowired
    ElectionServiceImpl electionService;

    @PostMapping(value = "/createElection")
    public ResponseEntity<Election> createElection(@RequestBody Election election) {
        try {
            return new ResponseEntity<>(electionService.createElection(election), HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @DeleteMapping(value = "/removeElection")
    public ResponseEntity<String> removeElection(@RequestParam String electionName) {
        try {
            if (electionService.removeElection(electionName) == null)
                electionName += " election NOT FOUND";
            return new ResponseEntity<>(electionName, HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping(value = "/getElectionById")
    public ResponseEntity<Election> getElectionById(String electionName) {
        try {
            return new ResponseEntity<>(electionService.getElectionById(electionName), HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping(value = "/getAllElections")
    public ResponseEntity<List<Election>> getAllElections() {
        try {
            return new ResponseEntity<>(electionService.getAllElections(), HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }
}
