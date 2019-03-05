package com.lxisoft.elasticsearch.repository;

import static org.elasticsearch.index.query.QueryBuilders.matchAllQuery;

import java.util.List;

import org.elasticsearch.common.unit.Fuzziness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Repository;

import com.lxisoft.elasticsearch.model.Complaint;
import com.lxisoft.elasticsearch.model.User;

@Repository
public class UserDAO {
	
 	@Value("${elasticsearch.index.name.two}")
    private String indexName;

    @Value("${elasticsearch.type.name.two}")
    private String typeName;

    @Autowired
    private ElasticsearchTemplate esTemplate;
    
    
    //GET ALL USERS
    public List<User> getAllUsers() {
        SearchQuery getAllQuery = new NativeSearchQueryBuilder()
                .withQuery(matchAllQuery())
                .build();
        
        return esTemplate.queryForList(getAllQuery, User.class);
    }
    
    //SEARCH USERS BASED ON TEXT PHRASE
    public List<User> searchUsers(String searchTerm) {
	    SearchQuery searchQuery = new NativeSearchQueryBuilder()
	    		  .withQuery(
	    			org.elasticsearch.index.query.QueryBuilders
	    				.multiMatchQuery(searchTerm, "first_name", "last_name", "email")
	    				.fuzziness(Fuzziness.TWO))
	    		  .build();
	    
        return esTemplate.queryForList(searchQuery, User.class);
    }
    
    


}
