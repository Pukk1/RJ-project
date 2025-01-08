package com.iver.rjproject.model;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "config")
@Data
@Component
public class DomainModel {
    private Integer delay;
    private Integer makersCount;
    private volatile Integer computersCount;
}
