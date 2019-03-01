package com.lxisoft.elasticsearch.repository;

import static org.elasticsearch.index.query.QueryBuilders.matchAllQuery;

import java.util.List;

import org.elasticsearch.common.unit.Fuzziness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Repository;

import com.lxisoft.elasticsearch.model.Complaint;

@Repository
public class ComplaintDAO {

	
	 	@Value("${elasticsearch.index.name}")
	    private String indexName;

	    @Value("${elasticsearch.complaint.type}")
	    private String typeName;

	    @Autowired
	    private ElasticsearchTemplate esTemplate;
	    
	    //GET ALL COMPLAINTS
	    public List<Complaint> getAllComplaints() {
	        SearchQuery getAllQuery = new NativeSearchQueryBuilder()
	                .withQuery(matchAllQuery()).build();
	        return esTemplate.queryForList(getAllQuery, Complaint.class);
	    }
	    
	    //SEARCH SIMILAR COMPLAINTS WITH RESPECT TO COMPLAINT.SUBJECT
	    public List<Complaint> searchComplaintsBySubject(String searchTerm) {
		    SearchQuery searchQuery = new NativeSearchQueryBuilder()
		    		  .withQuery(
		    			org.elasticsearch.index.query.QueryBuilders
		    				.matchQuery("subject", searchTerm)
		    				.fuzziness(Fuzziness.TWO)
		    		  ).build();
	        return esTemplate.queryForList(searchQuery, Complaint.class);
	    }
	    
	    //SEARCH SIMILAR COMPLAINTS WITH RESPECT TO COMPLAINT.DESCRIPTION
	    public List<Complaint> searchComplaintsByDescription(String searchTerm) {
		    SearchQuery searchQuery = new NativeSearchQueryBuilder()
		    		  .withQuery(
		    			org.elasticsearch.index.query.QueryBuilders
		    				.matchQuery("description", searchTerm)
		    				.fuzziness(Fuzziness.TWO)
		    		  ).build();
	        return esTemplate.queryForList(searchQuery, Complaint.class);
	    }
	    

	
}
