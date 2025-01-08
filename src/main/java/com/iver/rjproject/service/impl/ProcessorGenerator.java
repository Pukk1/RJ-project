package com.iver.rjproject.service.impl;


import com.iver.rjproject.records.Processor;
import com.iver.rjproject.service.Generator;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class ProcessorGenerator implements Generator<Processor> {
    private final Random random;
    private final boolean withDelay;

    public ProcessorGenerator(Random random, boolean withDelay) {
        this.random = random;
        this.withDelay = withDelay;
    }

    @Override
    public Processor generate() {
        return generateProcessor();
    }

    private Processor generateProcessor() {
        return new Processor(
                generateCoreNumbers(),
                generateSpeed(),
                withDelay
        );
    }

    private int generateCoreNumbers() {
        int[] coreNumbers = {1, 2, 4, 8, 16, 32};
        return coreNumbers[random.nextInt(coreNumbers.length)];
    }

    private int generateSpeed() {
        return random.nextInt(1000, 7000);
    }
}
