
package com.amex.scheduler.helper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.amex.scheduler.exception.SchedulerException;
import com.amex.scheduler.model.ScheduleDetails;

public class ScheduleHelper {

    public List<ScheduleDetails> scheduleDetails(String path) throws SchedulerException {
        try {
            if (!Files.exists(Paths.get(path))) {
                throw new SchedulerException("File not found at : " + path);
            }

            return Files.lines(Paths.get(path))
                    .map(String::trim)
                    .filter(line -> !line.isEmpty() && !line.startsWith("#"))
                    .map(this::scheduleDetail)
                    .filter(detail -> detail != null)
                    .collect(Collectors.toList());
        } catch (IOException ioException) {
            throw new SchedulerException("Exception reading the file : " + path, ioException);
        }
    }

    private ScheduleDetails scheduleDetail(String line) {
        try {
            if (line.startsWith("*/")) {
                int interval = Integer.parseInt(line.substring(2, line.indexOf(' ')));
                String command = line.substring(line.indexOf(' ') + 1);
                return new ScheduleDetails(ScheduleDetails.Type.Recurring, null, interval, command);
            } else {
                String[] schedule = line.split(" ", 6);
                int minute = Integer.parseInt(schedule[0]);
                int hour = Integer.parseInt(schedule[1]);
                int day = Integer.parseInt(schedule[2]);
                int month = Integer.parseInt(schedule[3]);
                int year = Integer.parseInt(schedule[4]);
                String command = schedule[5];
                LocalDateTime localDateTime = LocalDateTime.of(year, month, day, hour, minute);
                return new ScheduleDetails(ScheduleDetails.Type.Once, localDateTime, -1, command);
            }
        } catch (Exception e) {
            System.err.println("Skipping invalid line: " + line);
            return null;
        }
    }
}
