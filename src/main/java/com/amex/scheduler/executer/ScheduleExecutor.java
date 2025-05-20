
package com.amex.scheduler.executer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

import com.amex.scheduler.helper.OSHelper;

public class ScheduleExecutor {

    public void execute(String command, BufferedWriter bufferedWriter) throws IOException {
        if (command == null || command.trim().isEmpty()) {
            bufferedWriter.write("Invalid command: empty or null\n");
            return;
        }

        ProcessBuilder processBuilder = OSHelper.isWindows()
                ? new ProcessBuilder("cmd.exe", "/c", command)
                : new ProcessBuilder("/bin/sh", "-c", command);

        processBuilder.redirectErrorStream(true);

        try {
            bufferedWriter.write(String.format("[%s] Starting execution: %s%n", LocalDateTime.now(), command));

            Process process = processBuilder.start();
            try (BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(process.getInputStream()))) {
                String output = bufferedReader.lines().collect(Collectors.joining("\n"));
                bufferedWriter.write(output + "\n");
                bufferedWriter.write("----------------------------------------------------------\n");
            }
            int exitCode = process.waitFor();
            if (exitCode != 0) {
                bufferedWriter.write("Command exited with code: " + exitCode + "\n");
            }
        } catch (IOException | InterruptedException e) {
            bufferedWriter.write("Command execution failed: " + e.getMessage() + "\n");
            Thread.currentThread().interrupt(); 
        } finally {
            bufferedWriter.flush();
        }
    }
}
