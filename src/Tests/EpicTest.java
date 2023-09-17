package Tests;

import manager.Managers;
import manager.TaskManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tasks.Epic;
import tasks.Status;
import tasks.Subtask;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EpicTest {

    private static TaskManager manager;
    private static Epic epic;

    @BeforeEach
    public void BeforeEach() throws IOException {
        manager = Managers.getDefault();
        epic = new Epic("title", "content");
        manager.createNewEpic(epic);
    }

    @Test
    void shouldBeNewIfListOfSubtasksIsEmpty() {
        Status result = epic.getStatus();
        assertEquals(Status.NEW, result);
    }

    @Test
    void shouldBeNewIfAllSubtasksAreNew() throws IOException {
        Subtask subtask1 = new Subtask("subtask1Title", "subtask1Content", epic.getId());
        manager.createNewSubtask(subtask1);
        Subtask subtask2 = new Subtask("subtask2Title","subtask2Content", epic.getId());
        manager.createNewSubtask(subtask2);
        Status result = epic.getStatus();
        assertEquals(Status.NEW, result);
    }

    @Test
    void shouldBeDoneWhenAllSubtasksDone() throws IOException {
        Subtask subtask1 = new Subtask("subtask1Title", "subtask1Content", epic.getId());
        manager.createNewSubtask(subtask1);
        Subtask subtask2 = new Subtask("subtask2Title","subtask2Content", epic.getId());
        manager.createNewSubtask(subtask2);
        manager.updateSubtask(subtask1.getId(), Status.DONE);
        manager.updateSubtask(subtask2.getId(), Status.DONE);
        Status result = epic.getStatus();
        assertEquals(Status.DONE, result);
    }

    @Test
    void shouldBeInProgressIfSubtasksAreNewOrDone() throws IOException {
        Subtask subtask1 = new Subtask("subtask1Title", "subtask1Content", epic.getId());
        manager.createNewSubtask(subtask1);
        Subtask subtask2 = new Subtask("subtask2Title","subtask2Content", epic.getId());
        manager.createNewSubtask(subtask2);
        manager.updateSubtask(subtask1.getId(), Status.DONE);
        Status result = epic.getStatus();
        assertEquals(Status.IN_PROGRESS, result);
    }

    @Test
    void shouldBeInProgressWhenAllSubtasksAreInProgress() throws IOException {
        Subtask subtask1 = new Subtask("subtask1Title", "subtask1Content", epic.getId());
        manager.createNewSubtask(subtask1);
        Subtask subtask2 = new Subtask("subtask2Title","subtask2Content", epic.getId());
        manager.createNewSubtask(subtask2);
        manager.updateSubtask(subtask1.getId(), Status.IN_PROGRESS);
        manager.updateSubtask(subtask2.getId(), Status.IN_PROGRESS);
        Status result = epic.getStatus();
        assertEquals(Status.IN_PROGRESS, result);
    }
}