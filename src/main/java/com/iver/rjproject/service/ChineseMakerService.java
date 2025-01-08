package com.iver.rjproject.service;

import com.iver.rjproject.model.DomainModel;
import com.iver.rjproject.records.Computer;
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

    private final ThreadPoolExecutor executor;
    private final Scheduler scheduler;
    private final Flowable<Computer> flowable;
    private final Generator<Computer> generator;
    private final DomainModel domainModel;
    private final CustomSubscriber customSubscriber;

    public ChineseMakerService(Generator<Computer> generator, DomainModel domainModel, CustomSubscriber customSubscriber) {
        this.generator = generator;
        this.customSubscriber = customSubscriber;
        this.domainModel = domainModel;

        this.executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
        this.scheduler = Schedulers.from(executor);

        flowable = Observable.fromStream(Stream.generate(generator::generate)).toFlowable(BackpressureStrategy.BUFFER);
        flowable
                .observeOn(scheduler)
                .subscribe(customSubscriber);
    }

    public void setPoolSize(int size) {
        if (executor != null) {
            executor.setCorePoolSize(size);
            executor.setMaximumPoolSize(size);
        } else {
            System.err.println("Пул потоков не инициализирован!");
        }
    }
}
