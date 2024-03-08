package com.algoriant.cvs.controller;

import com.algoriant.cvs.dto.ElectionResponse;
import com.algoriant.cvs.service.ElectionService;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/cvs", produces = {MediaType.APPLICATION_JSON_VALUE})
@PreAuthorize("hasAuthority('admin')")
public class ElectionController {

    @Autowired
    ElectionService electionService;

    @PostMapping(value = "/createElection")
    public ResponseEntity<ElectionResponse> createElection(@RequestParam String electionName,
                                                           @RequestParam String electionDateTime,
                                                           @RequestParam int durationHours) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime dateTime = LocalDateTime.parse(electionDateTime, formatter);
            return new ResponseEntity<>(electionService.createElection(electionName,
                    dateTime, durationHours), HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @DeleteMapping(value = "/removeElection")
    public ResponseEntity<Map<String, String>> removeElection(@RequestParam String electionName) {
        Map<String, String> response = new HashMap<>();
        try {
            String deleteResponse = electionService.removeElection(electionName);
            if (deleteResponse == null)
                response.put("message", electionName + " NOT FOUND");
            else
                response.put("message", deleteResponse);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping(value = "/getElectionById")
    public ResponseEntity<ElectionResponse> getElectionById(String electionName) {
        try {
            return new ResponseEntity<>(electionService.getElectionById(electionName), HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping(value = "/getAllElections")
    public ResponseEntity<List<ElectionResponse>> getAllElections() {
        try {
            return new ResponseEntity<>(electionService.getAllElections(), HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }

    @PutMapping("/modifyElection")
    public ResponseEntity<ElectionResponse> modifyElection(@RequestParam String electionName,
                                                          @RequestParam String startTime,
                                                          @RequestParam int durationHours) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime dateTime = LocalDateTime.parse(startTime, formatter);
            return new ResponseEntity<>(electionService.startElection(electionName, dateTime, durationHours), HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }
    }
}
