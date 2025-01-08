package com.iver.rjproject.service;

import com.iver.rjproject.records.Processor;
import lombok.Data;
import org.springframework.stereotype.Service;

@Service
@Data
public class ProcessorGenerator implements Generator<Processor> {

    private Integer speed;

    @Override
    public Processor generate() {
        return null;
    }
}
