package com.iver.rjproject.service;

import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.schedulers.Schedulers;
import org.springframework.stereotype.Service;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class ChineseMakerService implements MakerService {

    private final ThreadPoolExecutor executor;

    public ChineseMakerService() {
        this.executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();

        Scheduler scheduler = Schedulers.from(executor);

    }
}
