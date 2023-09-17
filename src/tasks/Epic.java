package tasks;

import java.util.ArrayList;

public class Epic extends Task {

    protected ArrayList<Integer> subtasksId = new ArrayList<>();

    public Epic(String title, String content) {
        super(title, content);
    }

    public ArrayList<Integer> getSubtasksId() {
        return subtasksId;
    }


    @Override
    public String toString() {
        return String.format("%s,%s,%s,%s,%s", id, TaskTypes.EPIC, title, status, content);
    }

}

