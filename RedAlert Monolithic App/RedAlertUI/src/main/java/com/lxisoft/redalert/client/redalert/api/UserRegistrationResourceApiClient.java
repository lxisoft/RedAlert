package com.lxisoft.redalert.client.redalert.api;

import org.springframework.cloud.openfeign.FeignClient;
import com.lxisoft.redalert.client.redalert.ClientConfiguration;

@FeignClient(name="${redalert.name:redalert}", url="${redalert.url:localhost:8082/RedAlert}", configuration = ClientConfiguration.class)
public interface UserRegistrationResourceApiClient extends UserRegistrationResourceApi {
}