package com.lxisoft.redalert.client.crime_stopper.api;

import org.springframework.cloud.openfeign.FeignClient;
import com.lxisoft.redalert.client.crime_stopper.ClientConfiguration;

@FeignClient(name="${CrimeStopper.name:CrimeStopper}", url="${CrimeStopper.url:localhost:8084/CrimeStopper}", configuration = ClientConfiguration.class)
public interface LocationResourceApiClient extends LocationResourceApi {
}