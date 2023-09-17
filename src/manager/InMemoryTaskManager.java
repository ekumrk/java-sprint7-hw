package manager;
import tasks.*;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class InMemoryTaskManager implements TaskManager {

    private int nextId = 1;

    protected HistoryManager historyManager = Managers.getDefaultHistory();

    public InMemoryTaskManager() {
    }


    @Override
    public List<Task> getHistory() {
        return historyManager.getHistory();
        }

    @Override
    public int createNewTask(Task task) throws IOException {
        task.setId(nextId);
        nextId++;
        tasks.put(task.getId(), task);
        return (task.getId());
    }

    @Override
    public void updateTask(int id, Status status) {
        Task task = tasks.get(id);
        task.setStatus(status);
        tasks.put(id,task);
    }

    @Override
    public List<Task> getTaskList() {
        List<Task> taskArrayList = new ArrayList<>(tasks.values());
        return taskArrayList;
    }

    @Override
    public void deleteAllTasks() {

        tasks.clear();
    }

    @Override
    public Task getTaskFromId(int id) throws IOException {
        Task task = tasks.get(id);
        if (task != null) {
            historyManager.addToHistoryTask(task);
        }
        return tasks.get(id);
    }

    @Override
    public void deleteTaskFromId(int id) {
        historyManager.remove(id);
        tasks.remove(id);
    }

    @Override
    public int createNewEpic(Epic epic) throws IOException {
        epic.setId(nextId);
        nextId++;
        epics.put(epic.getId(), epic);
        return (epic.getId());
    }

    @Override
    public ArrayList<Epic> getEpicList() {
        ArrayList<Epic> epicArrayList = new ArrayList<>(epics.values());
        return epicArrayList;
    }

    @Override
    public Epic getEpicFromId(int id) throws IOException {
        Task task = (Task) epics.get(id);
        if (task != null) {
            historyManager.addToHistoryTask(task);
        }
        return epics.get(id);
    }

    @Override
    public ArrayList<Subtask> getSubtasksFromEpicId (int id) {
        ArrayList<Subtask> subtasksFromEpicId = new ArrayList<>();

        Epic epic = epics.get(id);
        for (Integer subtId : epic.getSubtasksId()) {
            Subtask subtask = subtasks.get(subtId);
            subtasksFromEpicId.add(subtask);
        }
        return subtasksFromEpicId;
        }

    @Override
    public void deleteAllEpics() {
        epics.clear();
        subtasks.clear();
    }

    @Override
    public void deleteEpicFromId(int id) {
        historyManager.remove(id);
        Epic epic = epics.remove(id);
        for (Integer subtaskId : epic.getSubtasksId()) {
            historyManager.remove(subtaskId);
            subtasks.remove(subtaskId);
        }
    }

    @Override
    public Integer createNewSubtask(Subtask subtask) throws IOException {
        subtask.setId(nextId);
        nextId++;
        subtasks.put(subtask.getId(), subtask);

        Epic epic = epics.get(subtask.getEpicId());
        if (epic == null) {
            return null;
        }
        epic.getSubtasksId().add(subtask.getId());
        updateEpicStatus(epic);
        return (subtask.getId());
    }


    @Override
    public ArrayList<Subtask> getSubtaskList() {
        ArrayList<Subtask> subtaskArrayList = new ArrayList<>(subtasks.values());
        return subtaskArrayList;
    }

    @Override
    public void deleteAllSubtasks() {
        for (Epic epic : epics.values()) {
            epic.getSubtasksId().clear();
            updateEpicStatus(epic);
        }
        subtasks.clear();
    }

    @Override
    public void updateEpicStatus(Epic epic) {
        ArrayList<Status> epicStatuses = new ArrayList<>();

        for (Integer subTaskId : epic.getSubtasksId()) {
            Subtask subTs = subtasks.get(subTaskId);
            if (subTs.getStatus().equals(Status.NEW)) {
                epicStatuses.add(Status.NEW);
            } else if (subTs.getStatus().equals(Status.IN_PROGRESS)) {
                epicStatuses.add(Status.IN_PROGRESS);
            } else if (subTs.getStatus().equals(Status.DONE)) {
                epicStatuses.add(Status.DONE);
            }
        }
        int counter = 0;
        if ((epicStatuses.contains(Status.IN_PROGRESS)) || (epicStatuses.contains(Status.NEW)
                && epicStatuses.contains(Status.IN_PROGRESS))
                || (epicStatuses.contains(Status.DONE) && epicStatuses.contains(Status.NEW))) {
            epic.setStatus(Status.IN_PROGRESS);
        }
            for (Status epicStatus : epicStatuses) {
                if (epicStatus.equals(Status.DONE)) {
                    ++counter;
                }
            }
        if (counter == epicStatuses.size()) {
                epic.setStatus(Status.DONE);
        }
    }

    @Override
    public Subtask getSubtaskFromId(int id) throws IOException {
        Task task = subtasks.get(id);
        if (task !=null) {
            historyManager.addToHistoryTask(task);
        }
        return subtasks.get(id);
    }

    @Override
    public void deleteSubtaskFromId(int id) {
        for (Epic epic : epics.values()) {
            if (epic.getSubtasksId().contains(id)) {
                epic.getSubtasksId().remove(id);
                updateEpicStatus(epic);
            }
            historyManager.remove(id);
            subtasks.remove(id);
        }
    }

    @Override
    public void updateSubtask (int subtId, Status updated) {
        Subtask subtask = subtasks.get(subtId);
        subtask.setStatus(updated);
        subtasks.put(subtask.getId(), subtask);

        int epId = subtask.getEpicId();
        Epic epic = epics.get(epId);
        updateEpicStatus(epic);
    }
}


