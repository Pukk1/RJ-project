package com.iver.rjproject.records;


import enums.OperationSystemName;

import java.time.LocalDate;
import java.util.List;

public record Computer(
        String macAddress,
        int storageSize,
        LocalDate firstStartTime,
        OperationSystemName operationSystemName,
        List<MemoryTab> memoryTabs,
        Processor processor
) {
}
