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
public class PlatformReportService {

    private final CustomerRepository repository;

    Executor executor = Executors.newFixedThreadPool(5);

    public void generateReportForRegion(String region) {
        executor.execute(() -> {
            log.info(CustomerConstant.PLATFORM_THREAD, region, Thread.currentThread());
            List<Customer> customers = repository.findByRegion(region);//1
            try {
                CsvReportUtil.writeCustomersToCsv("platform_" + region, customers);//2
            } catch (Exception e) {
                System.out.println(CustomerConstant.PLATFORM_THREAD_ERROR + region);
            }
        });

    }
}