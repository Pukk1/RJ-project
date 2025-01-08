package com.iver.rjproject.records;

public record Processor(
        int coreNumber,
        int speed,
        boolean withDelay
) {
    @Override
    public int coreNumber() {
        if (withDelay) {
            try {
                Thread.sleep(0,1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        return coreNumber;
    }
}
