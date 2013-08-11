package com.staleylabs.resteasy.service.impl;

import com.staleylabs.resteasy.service.SystemService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.lang.management.ManagementFactory;
import java.util.HashMap;
import java.util.Map;

/**
 * Implementation of {@link SystemService}
 *
 * @author Sean M. Staley
 * @version 1.0 (8/10/13)
 */
@Service
public class SystemServiceImpl implements SystemService {

    private static final Logger LOG = Logger.getLogger(SystemServiceImpl.class.getName());

    private static final int MB = 1024*1024;

    @Override
    public Map<String, String> getSystemInformation() {
        Map<String, String> results = new HashMap<>();

        results.put("Total Processors", String.valueOf(Runtime.getRuntime().availableProcessors()));

        results.put("Used Memory", String.valueOf(
                (Runtime.getRuntime().maxMemory() - Runtime.getRuntime().freeMemory()) / MB) + " MB");
        results.put("Free Memory", String.valueOf(Runtime.getRuntime().freeMemory() / MB) + " MB");
        results.put("Max Memory", String.valueOf(Runtime.getRuntime().maxMemory() / MB) + " MB");

        return results;
    }

    @Override
    public String getUpTimeInformation() {
        long applicationUpTime = ManagementFactory.getRuntimeMXBean().getUptime();

        int seconds = (int) (applicationUpTime / 1000) % 60 ;
        int minutes = (int) ((applicationUpTime / (1000*60)) % 60);
        int hours   = (int) ((applicationUpTime / (1000*60*60)) % 24);
        int days = (int) ((applicationUpTime / (1000*60*60*24)));

        return String.format("%3d Days, %2d Hours, %2d Minutes, and %2d Seconds", days, hours, minutes, seconds);
    }
}
