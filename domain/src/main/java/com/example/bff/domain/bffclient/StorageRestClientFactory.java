package com.example.bff.domain.bffclient;

import com.example.zoostorestorage.restExport.ZooStorageRestClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
@RequiredArgsConstructor
public class StorageRestClientFactory {
        @Bean
        public ZooStorageRestClient getRestExportClient() {
            final ObjectMapper objectMapper = new ObjectMapper();
            return Feign.builder()
                    .encoder(new JacksonEncoder(objectMapper))
                    .decoder(new JacksonDecoder(objectMapper))
                    .target( ZooStorageRestClient.class, "http://localhost:8081");
        }

    }


