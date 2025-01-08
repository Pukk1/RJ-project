package com.iver.rjproject.service.impl;

import com.iver.rjproject.records.Computer;
import com.iver.rjproject.records.MemoryTab;
import com.iver.rjproject.records.Processor;
import com.iver.rjproject.service.Generator;
import enums.OperationSystemName;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

@Service
public class ComputerGenerator implements Generator<Computer> {

    private final Generator<MemoryTab> memoryTabGenerator;
    private final Generator<Processor> processorGenerator;
    private final Random random;

    public ComputerGenerator(Generator<MemoryTab> memoryTabGenerator, Generator<Processor> processorGenerator, Random random) {
        this.memoryTabGenerator = memoryTabGenerator;
        this.processorGenerator = processorGenerator;
        this.random = random;
    }

    @Override
    public Computer generate() {
        return generateComputer();
    }

    private Computer generateComputer() {
        return new Computer(
                randomMACAddress(),
                randomStorageSize(),
                randomFirstStartTime(),
                randomOperationSystemName(),
                generateMemoryTabs(),
                generateProcessor()
        );
    }

    private String randomMACAddress() {
        Random rand = new Random();
        byte[] macAddr = new byte[6];
        rand.nextBytes(macAddr);

        macAddr[0] = (byte) (macAddr[0] & (byte) 254);

        StringBuilder sb = new StringBuilder(18);
        for (byte b : macAddr) {

            if (!sb.isEmpty())
                sb.append(":");

            sb.append(String.format("%02x", b));
        }


        return sb.toString();
    }

    private int randomStorageSize() {
        int[] sizes = {128, 256, 512, 1024, 2048};
        return sizes[random.nextInt(sizes.length)];
    }

    private LocalDate randomFirstStartTime() {
        var daysAgo = random.nextInt(365 * 5);
        return LocalDate.now().minusDays(daysAgo);
    }

    private OperationSystemName randomOperationSystemName() {
        var randomOperationSystemIndex = random.nextInt(OperationSystemName.values().length);
        return OperationSystemName.values()[randomOperationSystemIndex];
    }

    private List<MemoryTab> generateMemoryTabs() {
        var memoryTabsSize = random.nextInt(1, 4);
        return memoryTabGenerator.generate(memoryTabsSize);
    }

    private Processor generateProcessor() {
        return processorGenerator.generate(1).get(0);
    }
}
