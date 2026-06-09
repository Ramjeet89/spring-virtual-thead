package com.virtual.threads.service;


import com.virtual.threads.constant.CustomerConstant;
import com.virtual.threads.entity.Customer;
import com.virtual.threads.repository.CustomerRepository;
import com.virtual.threads.util.CsvReportUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReportService {

    private final CustomerRepository repository;

    /*
        300
        Tomcat default thread 200
        200 request processing
        100 request waiting in queue
     */
    public void generateReportForRegion(String region) {
        log.info(CustomerConstant.SIMPLE_THREAD, region, Thread.currentThread());
        List<Customer> customers = repository.findByRegion(region);
        try {
            CsvReportUtil.writeCustomersToCsv("simple_" + region, customers);
        } catch (Exception e) {
            System.out.println(CustomerConstant.SIMPLE_THREAD_ERROR + region);
        }
    }
}