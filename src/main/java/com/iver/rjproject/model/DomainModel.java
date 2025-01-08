package com.iver.rjproject.model;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "config")
@Data
@Component
public class DomainModel {
    private int cpuGenerationSpeed;
    private int pcGenerationSpeed;
    private int makersCount;
}
