package tasks;


public class Subtask extends Task {

    protected int epicId;

    public Subtask(String title, String content, int epicId) {
        super(title, content);
        this.epicId = epicId;
    }

    public int getEpicId() {

        return epicId;
    }

    @Override
    public String toString() {
        return String.format("%s,%s,%s,%s,%s,%s", id, TaskTypes.SUBTASK, title, status, content, epicId);
    }

}
