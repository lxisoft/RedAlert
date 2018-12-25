package com.lxisoft.crimestopper.repository.search;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;

/**
 * Configure a Mock version of ComplaintSearchRepository to test the
 * application without starting Elasticsearch.
 */
@Configuration
public class ComplaintSearchRepositoryMockConfiguration {

    @MockBean
    private ComplaintSearchRepository mockComplaintSearchRepository;

}
