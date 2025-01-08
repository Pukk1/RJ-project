package com.iver.rjproject.records;

public record Processor(
        int coreNumber,
        int speed
) {
    @Override
    public int coreNumber() {
        return coreNumber;
    }
}
