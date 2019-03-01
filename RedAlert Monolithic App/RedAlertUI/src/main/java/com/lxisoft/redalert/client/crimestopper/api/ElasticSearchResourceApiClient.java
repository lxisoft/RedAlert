package com.lxisoft.redalert.client.crimestopper.api;

import org.springframework.cloud.openfeign.FeignClient;

import com.lxisoft.redalert.client.red_alert.ClientConfiguration;


@FeignClient(name="${elasticsearch.name:elasticsearch}", url="${elasticsearch.url:localhost:8102}", configuration = ClientConfiguration.class)
public interface ElasticSearchResourceApiClient extends ElasticSearchResourceApi {
}
