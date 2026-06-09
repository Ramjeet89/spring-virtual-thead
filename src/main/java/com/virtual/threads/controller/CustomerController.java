package com.virtual.threads.controller;

import com.virtual.threads.constant.CustomerConstant;
import com.virtual.threads.entity.Customer;
import com.virtual.threads.service.CustomerService;
import com.virtual.threads.service.PlatformReportService;
import com.virtual.threads.service.ReportService;
import com.virtual.threads.service.VirtualThreadReportService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/report")
public class CustomerController {

    private final ReportService reportService;

    private final PlatformReportService platformReportService;

    private final VirtualThreadReportService virtualThreadReportService;

    private final CustomerService service;

    @GetMapping("/ping")
    public String ping() {
        return "PONG!!";
    }

    @PostMapping("/create")
    public ResponseEntity<Customer> createCustomerDetail(
            @Valid @RequestBody Customer customer) {
        Customer savedCustomer = service.createCustomerDetail(customer);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedCustomer);
    }

    @PostMapping("/{region}")
    public ResponseEntity<String> generateReport(@PathVariable String region) {
        reportService.generateReportForRegion(region);
        return ResponseEntity.ok(CustomerConstant.REPORT_STARTED_FOR_REGION + region);
    }

    @PostMapping("/platform/{region}")
    public ResponseEntity<String> generatePlatformReport(@PathVariable String region) {
        platformReportService.generateReportForRegion(region);
        return ResponseEntity.ok(CustomerConstant.REPORT_STARTED_FOR_REGION + region);
    }

    @PostMapping("/virtual/{region}")
    public ResponseEntity<String> generateVirtualReport(@PathVariable String region) {
        virtualThreadReportService.generateReportForRegion(region);
        return ResponseEntity.ok(CustomerConstant.REPORT_STARTED_FOR_REGION + region);
    }
}
