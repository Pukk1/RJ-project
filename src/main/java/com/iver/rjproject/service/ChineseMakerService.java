package com.iver.rjproject.service;

import com.iver.rjproject.model.DomainModel;
import com.iver.rjproject.records.Computer;
import com.iver.rjproject.util.CustomSubscriber;
import io.reactivex.rxjava3.core.BackpressureStrategy;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.schedulers.Schedulers;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.Stream;

@Service
public class ChineseMakerService implements MakerService {

    private final ThreadPoolExecutor executor;
    private final Scheduler scheduler;
    private final Flowable<Computer> flowable;
    private final CustomSubscriber customSubscriber;

    public ChineseMakerService(Generator<Computer> generator, CustomSubscriber customSubscriber, DomainModel domainModel) {
        this.executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(domainModel.getMakersCount());
        this.scheduler = Schedulers.from(executor);

        flowable = Flowable.generate(emitter -> {
            Computer computer = generator.generate();
            emitter.onNext(computer);
        });
//        flowable = Observable.fromStream(Stream.generate(generator::generate)).toFlowable(BackpressureStrategy.BUFFER);

        this.customSubscriber = customSubscriber;
//        executor.setCorePoolSize(domainModel.getMakersCount());
//        executor.setMaximumPoolSize(domainModel.getMakersCount());
    }

    @EventListener(classes = { ContextRefreshedEvent.class})
    public void handleMultipleEvents() {
        flowable
                .forEach(it -> Flowable
                        .just(it)
                        .subscribeOn(scheduler)
                        .doOnNext(el -> System.out.println(Thread.currentThread().getName()))
                        .subscribe(customSubscriber)
                );
    }

    public void setPoolSize(int size) {
        if (executor != null) {
            executor.setMaximumPoolSize(size);
            executor.setCorePoolSize(size);
        } else {
            System.err.println("Пул потоков не инициализирован!");
        }
    }
}
