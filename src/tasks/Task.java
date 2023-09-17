package tasks;

import java.util.Objects;

public class Task {
    protected int id;
    protected String title;
    protected String content;
    protected Status status = Status.NEW;

    public Task (String title, String content) {
        this.title = title;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {

        this.id = id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {

        this.status = status;
    }

    @Override
    public String toString() {
        return String.format("%s,%s,%s,%s,%s", id, TaskTypes.TASK, title, status, content);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id == task.id && Objects.equals(title, task.title) && Objects.equals(content, task.content) && status == task.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, content, status);
    }
}
