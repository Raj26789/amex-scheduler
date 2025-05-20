
package com.amex.scheduler;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.amex.scheduler.exception.SchedulerException;
import com.amex.scheduler.executer.ScheduleExecutor;
import com.amex.scheduler.helper.ScheduleHelper;
import com.amex.scheduler.model.ScheduleDetails;

public class AmexScheduler {

    private static final String outputFile = "sample-output.txt";

    public static void main(String[] args) {

        String commandFile = System.getProperty("os.name").toLowerCase().contains("win")
                ? "C:/tmp/commands.txt"
                : "/tmp/commands.txt";

        ScheduleHelper scheduleHelper = new ScheduleHelper();
        ScheduleExecutor scheduleExecutor = new ScheduleExecutor();

        try {
            List<ScheduleDetails> scheduleDetails = scheduleHelper.scheduleDetails(commandFile);
            Map<String, LocalDateTime> lastExecuted = new HashMap<>();

            try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(outputFile, true))) {
                while (true) {
                    LocalDateTime localDateTime = LocalDateTime.now().withSecond(0).withNano(0);
                    System.out.println("Checking at: " + localDateTime);

                    for (ScheduleDetails schDetails : scheduleDetails) {
                        boolean run = false;
                        switch (schDetails.getType()) {
                            case Once:
                                run = localDateTime.equals(schDetails.getScheduleTimeForOnce());
                                break;
                            case Recurring:
                                LocalDateTime lastTime = lastExecuted.getOrDefault(
                                    schDetails.getCommand(), localDateTime.minusMinutes(schDetails.getIntervalMinForRecurring()));
                                run = lastTime.plusMinutes(schDetails.getIntervalMinForRecurring()).equals(localDateTime);
                                break;
                        }
                        if (run) {
                            System.out.println("Executing: " + schDetails.getCommand());
                            scheduleExecutor.execute(schDetails.getCommand(), bufferedWriter);
                            if (schDetails.getType() == ScheduleDetails.Type.Recurring) {
                                lastExecuted.put(schDetails.getCommand(), localDateTime);
                            }
                        }
                    }
                    Thread.sleep(60_000);
                }
            }
        } catch (SchedulerException e) {
            System.err.println("Failed to start scheduler: " + e.getMessage());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Scheduler interrupted");
        } catch (IOException e) {
            System.err.println("Error writing to output file: " + e.getMessage());
        }
    }
}
