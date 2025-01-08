package com.iver.rjproject.service;

import com.iver.rjproject.model.CPU;
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
    private final Flowable<CPU> cpuFlowable;
    private final CpuGenerator cpuGenerator;

    public ChineseMakerService(CpuGenerator cpuGenerator) {
        this.cpuGenerator = cpuGenerator;
        this.executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
        this.scheduler = Schedulers.from(executor);
        cpuFlowable = Flowable.fromStream(Stream.generate(cpuGenerator::generate));
    }
}
