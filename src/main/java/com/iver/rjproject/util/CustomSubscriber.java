package com.iver.rjproject.util;

import com.iver.rjproject.model.DomainModel;
import com.iver.rjproject.records.Computer;
import com.iver.rjproject.records.Processor;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.FlowableSubscriber;
import lombok.Getter;
import org.reactivestreams.Subscription;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class CustomSubscriber implements FlowableSubscriber<Computer> {

    private final long BUFFER_SIZE = 10L;
    @Getter
    private Map<Integer, Long> results;
    private Subscription subscription;
    private final DomainModel domainModel;

    public CustomSubscriber(DomainModel domainModel) {
        this.domainModel = domainModel;
    }

    @Override
    public void onSubscribe(@NonNull Subscription s) {
        this.subscription = s;
        results = new ConcurrentHashMap<>();
        subscription.request(BUFFER_SIZE);
    }

    @Override
    public void onNext(Computer computer) {
        domainModel.setComputersCount(domainModel.getComputersCount() + 1);
        var coreNumber = computer.processor().coreNumber();
        var valueByCoreNumber = results.getOrDefault(coreNumber, 0L);
        valueByCoreNumber++;
        results.put(coreNumber, valueByCoreNumber);
        subscription.request(1L);
    }

    @Override
    public void onError(Throwable throwable) {
        throwable.printStackTrace();
    }

    @Override
    public void onComplete() {

    }

}
