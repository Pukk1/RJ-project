package com.iver.rjproject.service.impl;


import com.iver.rjproject.records.Processor;
import com.iver.rjproject.service.Generator;
import com.iver.rjproject.service.MultipleGenerator;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

@Service
public class ProcessorGenerator implements Generator<Processor>, MultipleGenerator<Processor> {
    private final Random random;

    public ProcessorGenerator(Random random) {
        this.random = random;
    }

    @Override
    public Processor generate() {
        return generateProcessor();
    }

    private Processor generateProcessor() {
        return new Processor(
                generateCoreNumbers(),
                generateSpeed()
        );
    }

    private int generateCoreNumbers() {
        int[] coreNumbers = {1, 2, 4, 8, 16, 32};
        return coreNumbers[random.nextInt(coreNumbers.length)];
    }

    private int generateSpeed() {
        return random.nextInt(1000, 7000);
    }

    @Override
    public List<Processor> generate(int size) {
        var processors = new ArrayList<Processor>();

        IntStream.range(0, size).forEach(it -> {
            processors.add(generateProcessor());
        });

        return processors;
    }
}
