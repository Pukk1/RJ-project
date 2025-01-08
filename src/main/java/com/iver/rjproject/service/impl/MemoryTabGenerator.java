package com.iver.rjproject.service.impl;


import com.iver.rjproject.records.MemoryTab;
import com.iver.rjproject.service.Generator;

import java.util.Random;

public class MemoryTabGenerator implements Generator<MemoryTab> {

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
}
