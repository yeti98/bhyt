package com.devculi.bhyt.controllers;

import com.devculi.bhyt.services.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

@RestController
@RequestMapping("/bills")
public class BillController {
    @Autowired
    BillService billService;

    @GetMapping("/analyzer")
    public List analyzeBill(@RequestParam long start, @RequestParam long end) {
        LocalDateTime startDateTime = Instant.ofEpochMilli(start).atZone(ZoneId.systemDefault()).toLocalDateTime();
        LocalDateTime endDateTime = Instant.ofEpochMilli(end).atZone(ZoneId.systemDefault()).toLocalDateTime();
        System.out.println(startDateTime + "\n" + endDateTime);
        return billService.buildStatisticalMap(startDateTime, endDateTime);
    }

    @GetMapping("/analyzer/annual")
    public List analyzeBillByYear(@RequestParam(name = "range", defaultValue = "5") Integer range) {
        LocalDateTime endDateTime = LocalDateTime.now();
        System.out.println(endDateTime);
        LocalDateTime startDateTime = endDateTime.minusYears(range).with(TemporalAdjusters.firstDayOfYear());
        System.out.println(startDateTime + "\n" + endDateTime);
        return billService.buildStatisticalForYears(startDateTime, endDateTime);
    }
}
