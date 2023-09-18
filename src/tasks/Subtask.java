package tasks;


import java.time.ZonedDateTime;

public class Subtask extends Task {

    protected int epicId;

    public Subtask(String title, String content, int epicId, ZonedDateTime startTime, int duration) {
        super(title, content, startTime, duration);
        this.epicId = epicId;
    }

    public int getEpicId() {
        return epicId;
    }

    @Override
    public String toString() {
        return String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s", id, TaskTypes.SUBTASK, title, status, content,
                startTime.format(DATE_TIME_FORMATTER), duration, getEndTime().format(DATE_TIME_FORMATTER), epicId);
    }

}
