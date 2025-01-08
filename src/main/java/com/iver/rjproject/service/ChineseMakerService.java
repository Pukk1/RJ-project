package com.iver.rjproject.service;

import com.iver.rjproject.model.DomainModel;
import com.iver.rjproject.records.MemoryTab;
import com.iver.rjproject.records.Processor;
import com.iver.rjproject.service.impl.ProcessorGenerator;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.schedulers.Schedulers;
import org.springframework.stereotype.Service;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.Stream;

@Service
public class ChineseMakerService implements MakerService {

    private final ThreadPoolExecutor executor;
    private final Scheduler scheduler;
    private final Generator<Processor> processorGenerator;
    private final Flowable<Processor> cpuFlowable;
    private final Flowable<MemoryTab> memoryTabFlowable;
    private final DomainModel domainModel;

    public ChineseMakerService(ProcessorGenerator processorGenerator, DomainModel domainModel) {
        this.processorGenerator = processorGenerator;
        this.executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
        this.scheduler = Schedulers.from(executor);
        this.domainModel = domainModel;
        cpuFlowable = Flowable.fromStream(Stream.generate(processorGenerator::generate));
    }
}
