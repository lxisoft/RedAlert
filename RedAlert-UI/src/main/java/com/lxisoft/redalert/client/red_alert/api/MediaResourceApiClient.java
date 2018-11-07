package com.lxisoft.redalert.client.red_alert.api;

import org.springframework.cloud.openfeign.FeignClient;
import com.lxisoft.redalert.client.red_alert.ClientConfiguration;

@FeignClient(name="${RedAlert.name:RedAlert}", url="${RedAlert.url:localhost:8082/RedAlert}", configuration = ClientConfiguration.class)
public interface MediaResourceApiClient extends MediaResourceApi {
}