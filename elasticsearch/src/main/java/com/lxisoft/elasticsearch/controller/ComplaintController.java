package com.lxisoft.elasticsearch.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.lxisoft.elasticsearch.model.Complaint;
import com.lxisoft.elasticsearch.repository.ComplaintDAO;
import com.lxisoft.elasticsearch.util.PaginationUtil;


@RestController
@RequestMapping("/api/elasticsearch")
public class ComplaintController {
	
    @Autowired
    private ComplaintDAO complaintDAO;
    
    
    
    //GET ALL COMPLAINTS FROM ELASTICSEARCH
    @GetMapping("/complaints")
    public ResponseEntity<List<Complaint>> getAllComplaints(
    		Pageable pageable, 
    		@RequestParam(required = false, defaultValue = "false") boolean eagerload) {	
    	
    	List<Complaint> complaints = complaintDAO.getAllComplaints();							//LIST
        Page<Complaint> page = new PageImpl<Complaint>(complaints);								//PAGE
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(						//HTTP HEADER
        		page, 
        		String.format("/api/elasticsearch/complaints?eagerload=%b", 
        				eagerload));
        
        return ResponseEntity.ok().headers(headers).body((page.getContent()));					//RETURN RESPONSE ENTITY
    }
    
    
    
    //SEARCH COMPLAINTS BY COMPLAINTS.SUBJECT
    @RequestMapping(value = "/complaints/searchBySubject", method = RequestMethod.POST)
    public ResponseEntity<List<Complaint>> searchComplaintsBySubject(
    		@RequestBody String searchTerm, 
    		Pageable pageable, 
    		@RequestParam(required = false, defaultValue = "false") boolean eagerload) {
        
    	List<Complaint> complaints = complaintDAO.searchComplaintsBySubject(searchTerm);		//LIST
        Page<Complaint> page = new PageImpl<Complaint>(complaints);								//PAGE
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(						//HTTP HEADER
        		page, 
        		String.format("/api/elasticsearch/complaints/searchBySubject?eagerload=%b", 
        				eagerload));
        	
        return ResponseEntity.ok().headers(headers).body((page.getContent()));					//RETURN RESPONSE ENTITY
    }
    
    
    
    //SEARCH COMPLAINTS BY COMPLAINTS.DESCRIPTION
    @RequestMapping(value = "/complaints/searchByDescription", method = RequestMethod.POST)
    public ResponseEntity<List<Complaint>> searchComplaintsByDescription(
    		@RequestBody String searchTerm, 
			Pageable pageable, 
			@RequestParam(required = false, defaultValue = "false") boolean eagerload) {
    	
    	List<Complaint> complaints = complaintDAO.searchComplaintsByDescription(searchTerm);	//LIST
        Page<Complaint> page = new PageImpl<Complaint>(complaints);								//PAGE
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(						//HTTP HEADER
        		page, 
        		String.format("/api/elasticsearch/complaints/searchByDescription?eagerload=%b", 
        				eagerload));
        
        return ResponseEntity.ok().headers(headers).body((page.getContent()));					//RETURN RESPONSE ENTITY
    }

}

