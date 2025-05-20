
package com.amex.scheduler.model;

import java.time.LocalDateTime;

public class ScheduleDetails {

    public enum Type {
        Once, Recurring
    }

    private final Type type;
    private final LocalDateTime scheduleTimeForOnce;
    private final int intervalMinForRecurring;
    private final String command;

    public ScheduleDetails(Type type, LocalDateTime scheduleTimeForOnce, int intervalMinForRecurring, String command) {
        this.type = type;
        this.scheduleTimeForOnce = scheduleTimeForOnce;
        this.intervalMinForRecurring = intervalMinForRecurring;
        this.command = command;
    }

    public Type getType() {
        return type;
    }

    public LocalDateTime getScheduleTimeForOnce() {
        return scheduleTimeForOnce;
    }

    public int getIntervalMinForRecurring() {
        return intervalMinForRecurring;
    }

    public String getCommand() {
        return command;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", type, command);
    }
}
