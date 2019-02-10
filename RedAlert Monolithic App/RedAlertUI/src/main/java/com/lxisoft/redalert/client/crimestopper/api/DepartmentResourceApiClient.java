package com.lxisoft.redalert.client.crimestopper.api;

import org.springframework.cloud.openfeign.FeignClient;

import com.lxisoft.redalert.client.red_alert.ClientConfiguration;


@FeignClient(name="${crimestopper.name:crimestopper}", url="${crimestopper.url:localhost:8084/crimestopper}", configuration = ClientConfiguration.class)
public interface DepartmentResourceApiClient extends DepartmentResourceApi {
}