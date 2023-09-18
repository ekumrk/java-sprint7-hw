package tasks;

import java.time.ZonedDateTime;
import java.util.ArrayList;

public class Epic extends Task {

    private final ArrayList<Integer> subtasksId = new ArrayList<>();


    private ZonedDateTime endTime;

    public Epic(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public ArrayList<Integer> getSubtasksId() {
        return subtasksId;
    }

    @Override
    public ZonedDateTime getEndTime() {
        return endTime;
    }

    public void setStartTime(ZonedDateTime startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(ZonedDateTime endTime) {
        this.endTime = endTime;
    }

    public String startTimeToString() {
        try {
            return startTime.format(DATE_TIME_FORMATTER);
        } catch (NullPointerException e) {
            return null;
    }
    }

    public String endTimeToString() {
        try {
            return endTime.format(DATE_TIME_FORMATTER);
        } catch (NullPointerException e) {
            return null;
        }
    }

    @Override
    public String toString() {
        return String.format("%s,%s,%s,%s,%s,%s,%s,%s", id, TaskTypes.EPIC, title, status, content,
                startTimeToString(), duration, endTimeToString());
    }

}

