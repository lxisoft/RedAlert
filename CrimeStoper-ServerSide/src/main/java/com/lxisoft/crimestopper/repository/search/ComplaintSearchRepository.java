package com.lxisoft.crimestopper.repository.search;

import com.lxisoft.crimestopper.domain.Complaint;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the Complaint entity.
 */
public interface ComplaintSearchRepository extends ElasticsearchRepository<Complaint, Long> {
}
