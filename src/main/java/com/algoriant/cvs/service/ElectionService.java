package com.algoriant.cvs.service;

import com.algoriant.cvs.entity.Election;

import java.util.List;

public interface ElectionService {

    Election createElection(Election election);

    String removeElection(String electionName);

    Election getElectionById(String electionName);

    List<Election> getAllElections();
}
