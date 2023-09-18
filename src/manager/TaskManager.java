package manager;

import tasks.Epic;
import tasks.Status;
import tasks.Subtask;
import tasks.Task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface TaskManager {


    Map<Integer, Task> tasks = new HashMap<>();
    Map<Integer, Subtask> subtasks = new HashMap<>();
    Map<Integer, Epic> epics = new HashMap<>();

    public int createNewTask(Task task) throws IOException;

    public void updateTask(int id, Status status);

    public List<Task> getTaskList();

    public void deleteAllTasks();

    public Task getTaskFromId(int id) throws IOException;

    public void deleteTaskFromId(int id);

    public int createNewEpic(Epic epic) throws IOException;

    public ArrayList<Epic> getEpicList();

    public Epic getEpicFromId(int id) throws IOException;

    public ArrayList<Subtask> getSubtasksFromEpicId (int id);

    public void deleteAllEpics();

    public void deleteEpicFromId(int id);

    public Integer createNewSubtask(Subtask subtask) throws IOException;


    public ArrayList<Subtask> getSubtaskList();

    public void deleteAllSubtasks();

    public void updateEpicStatus(Epic epic);

    public Subtask getSubtaskFromId(int id) throws IOException;
    public void deleteSubtaskFromId(int id);

    public void updateSubtask (int subtId, Status updated);

    public List<Task> getHistory();
}
