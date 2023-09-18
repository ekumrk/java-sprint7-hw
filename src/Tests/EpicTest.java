package Tests;

import manager.Managers;
import manager.TaskManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tasks.Epic;
import tasks.Status;
import tasks.Subtask;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EpicTest {

    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm dd.MM.yyyy");
    public static final ZoneId zone = ZoneId.of("Europe/Simferopol");

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
        Subtask subtask1 = new Subtask("subtask1Title", "subtask1Content", epic.getId(),
                ZonedDateTime.of(LocalDateTime.parse("12:15 03.01.2023", DATE_TIME_FORMATTER), zone), 30);
        manager.createNewSubtask(subtask1);
        Subtask subtask2 = new Subtask("subtask2Title","subtask2Content", epic.getId(),
                ZonedDateTime.of(LocalDateTime.parse("13:00 03.01.2023", DATE_TIME_FORMATTER), zone), 30);
        manager.createNewSubtask(subtask2);
        Status result = epic.getStatus();
        assertEquals(Status.NEW, result);
    }

    @Test
    void shouldBeDoneWhenAllSubtasksDone() throws IOException {
        Subtask subtask1 = new Subtask("subtask1Title", "subtask1Content", epic.getId(),
                ZonedDateTime.of(LocalDateTime.parse("12:15 03.01.2023", DATE_TIME_FORMATTER), zone), 30);
        manager.createNewSubtask(subtask1);
        Subtask subtask2 = new Subtask("subtask2Title","subtask2Content", epic.getId(),
                ZonedDateTime.of(LocalDateTime.parse("13:00 03.01.2023", DATE_TIME_FORMATTER), zone), 30);
        manager.createNewSubtask(subtask2);
        manager.updateSubtask(subtask1.getId(), Status.DONE);
        manager.updateSubtask(subtask2.getId(), Status.DONE);
        Status result = epic.getStatus();
        assertEquals(Status.DONE, result);
    }

    @Test
    void shouldBeInProgressIfSubtasksAreNewOrDone() throws IOException {
        Subtask subtask1 = new Subtask("subtask1Title", "subtask1Content", epic.getId(),
                ZonedDateTime.of(LocalDateTime.parse("12:15 03.01.2023", DATE_TIME_FORMATTER), zone), 30);
        manager.createNewSubtask(subtask1);
        Subtask subtask2 = new Subtask("subtask2Title","subtask2Content", epic.getId(),
                ZonedDateTime.of(LocalDateTime.parse("13:00 03.01.2023", DATE_TIME_FORMATTER), zone), 30);
        manager.createNewSubtask(subtask2);
        manager.updateSubtask(subtask1.getId(), Status.DONE);
        Status result = epic.getStatus();
        assertEquals(Status.IN_PROGRESS, result);
    }

    @Test
    void shouldBeInProgressWhenAllSubtasksAreInProgress() throws IOException {
        Subtask subtask1 = new Subtask("subtask1Title", "subtask1Content", epic.getId(),
                ZonedDateTime.of(LocalDateTime.parse("12:15 03.01.2023", DATE_TIME_FORMATTER), zone), 30);
        manager.createNewSubtask(subtask1);
        Subtask subtask2 = new Subtask("subtask2Title","subtask2Content", epic.getId(),
                ZonedDateTime.of(LocalDateTime.parse("13:00 03.01.2023", DATE_TIME_FORMATTER), zone), 30);
        manager.createNewSubtask(subtask2);
        manager.updateSubtask(subtask1.getId(), Status.IN_PROGRESS);
        manager.updateSubtask(subtask2.getId(), Status.IN_PROGRESS);
        Status result = epic.getStatus();
        assertEquals(Status.IN_PROGRESS, result);
    }
}