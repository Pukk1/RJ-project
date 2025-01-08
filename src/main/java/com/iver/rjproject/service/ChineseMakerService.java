package com.iver.rjproject.service;

import com.iver.rjproject.model.DomainModel;
import com.iver.rjproject.records.Processor;
import com.iver.rjproject.service.impl.ProcessorGenerator;
import com.iver.rjproject.util.CustomSubscriber;
import io.reactivex.rxjava3.core.BackpressureStrategy;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.schedulers.Schedulers;
import org.springframework.stereotype.Service;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.Stream;

@Service
public class ChineseMakerService implements MakerService {

    private final ThreadPoolExecutor cpuExecutor;
    private final Scheduler cpuScheduler;
    private final Flowable<Processor> processorFlowable;
    private final Generator<Processor> processorGenerator;
//    private final Flowable<MemoryTab> memoryTabFlowable;
    private final DomainModel domainModel;
    private final CustomSubscriber customSubscriber;

    public ChineseMakerService(Generator<Processor> processorGenerator, DomainModel domainModel, CustomSubscriber customSubscriber) {
        this.processorGenerator = processorGenerator;
        this.customSubscriber = customSubscriber;
        this.domainModel = domainModel;

        this.cpuExecutor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
        this.cpuScheduler = Schedulers.from(cpuExecutor);

        processorFlowable = Observable.fromStream(Stream.generate(processorGenerator::generate)).toFlowable(BackpressureStrategy.BUFFER);
        processorFlowable
                .observeOn(cpuScheduler)
                .subscribe(customSubscriber);
    }
}
