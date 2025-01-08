package com.iver.rjproject.service.impl;


import com.iver.rjproject.records.MemoryTab;
import com.iver.rjproject.service.Generator;
import com.iver.rjproject.service.MultipleGenerator;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

@Service
public class MemoryTabGenerator implements Generator<MemoryTab>, MultipleGenerator<MemoryTab> {

    private final Random random;

    public MemoryTabGenerator(Random random) {
        this.random = random;
    }

    @Override
    public MemoryTab generate() {
        return generateMemoryTab();
    }

    private MemoryTab generateMemoryTab() {
        return new MemoryTab(
                generateSize(),
                generateSpeed()
        );
    }

    private int generateSize() {
        int[] sizes = {1, 2, 4, 8, 16, 32, 64};
        return 1024 * sizes[random.nextInt(sizes.length)];
    }

    private int generateSpeed() {
        return 1000 * random.nextInt(1, 8);
    }


    @Override
    public List<MemoryTab> generate(int size) {
        var memTabs = new ArrayList<MemoryTab>();

        IntStream.range(0, size).forEach(it -> {
            memTabs.add(generateMemoryTab());
        });

        return memTabs;
    }
}
