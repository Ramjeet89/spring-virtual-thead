package com.virtual.threads.service;

import com.virtual.threads.constant.CustomerConstant;
import com.virtual.threads.entity.Customer;
import com.virtual.threads.repository.CustomerRepository;
import com.virtual.threads.util.CsvReportUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Service
@Slf4j
@RequiredArgsConstructor
public class VirtualThreadReportService {

    private final CustomerRepository repository;

    private final Executor virtualThreadExecutor;

    public void generateReportForRegion(String region) {
        virtualThreadExecutor.execute(() -> {
            log.info(CustomerConstant.VIRTUAL_THREAD, region, Thread.currentThread());
            List<Customer> customers = repository.findByRegion(region);
            try {
                CsvReportUtil.writeCustomersToCsv("virtual_" + region, customers);//2
            } catch (Exception e) {
                System.out.println(CustomerConstant.VIRTUAL_THREAD_ERROR + region);
            }
        });

    }
}