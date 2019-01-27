package com.lxisoft.redalert.client.crimestopper.api;

import org.springframework.cloud.openfeign.FeignClient;
import com.lxisoft.redalert.client.crimestopper.ClientConfiguration;

@FeignClient(name="${crimestopper.name:crimestopper}", url="${crimestopper.url:localhost:8084/crimestopper}", configuration = ClientConfiguration.class)
public interface ReplyResourceApiClient extends ReplyResourceApi {
}