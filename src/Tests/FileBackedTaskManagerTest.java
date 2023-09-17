package Tests;

import manager.FileBackedTaskManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class FileBackedTaskManagerTest extends TaskManagerTest<FileBackedTaskManager>{

    @BeforeEach
    void init() {
        super.init();
        try {
            manager = new FileBackedTaskManager("/dev/java-sprint7-hw/resources/test.csv");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @AfterEach
    void afterEach() throws IOException {
        Files.delete(manager.getHistoryFile());
    }

    @Test
    void correctSaveToFileIfNormalBehaviour() throws IOException {
        manager.createNewTask(task);
        assertTrue(Files.size(manager.getHistoryFile()) != 0);
    }

    @Test
    void correctReadFromFile() throws IOException {
        manager.createNewTask(task2);
        manager.tasks.clear();
        manager.readListFromFile();
        assertFalse(manager.tasks.isEmpty());
    }

    @Override
    public void ifCorrectGettingHistory() {
        if (Files.exists(manager.getHistoryFile())) {
            super.ifCorrectGettingHistory();
        }
    }

    @Override
    void deleteTaskFromId() {
        if (Files.exists(manager.getHistoryFile())) {
            super.deleteTaskFromId();
        }
    }

    @Override
    void getEpicListIfEpicsAreNotCreated() {
        if (Files.exists(manager.getHistoryFile())) {
            super.getEpicListIfEpicsAreNotCreated();
        }
    }

    @Override
    public void deleteAllEpicsInAnyCase() {
        if (Files.exists(manager.getHistoryFile())) {
            super.deleteAllEpicsInAnyCase();
        }
    }

    @Override
    public void getSubtasksFromEpicIdIfEpicsEmpty() {
        if (Files.exists(manager.getHistoryFile())) {
            super.getSubtasksFromEpicIdIfEpicsEmpty();
        }
    }

    @Override
    public void deleteAllTasksIfTaskListIsNotEmpty() {
        if (Files.exists(manager.getHistoryFile())) {
            super.deleteAllTasksIfTaskListIsNotEmpty();
        }
    }

    @Override
    void getTaskListIfStandardBehaviour() {
        if (Files.exists(manager.getHistoryFile())) {
            super.getTaskListIfStandardBehaviour();
        }
    }

    @Override
    void getSubtaskIfEpicsAreNotCreated() {
        if (Files.exists(manager.getHistoryFile())) {
            super.getSubtaskIfEpicsAreNotCreated();
        }
    }

    @Override
    public void deleteAllSubtasksIfSubtaskListIsNotEmpty() {
        if (Files.exists(manager.getHistoryFile())) {
            super.deleteAllSubtasksIfSubtaskListIsNotEmpty();
        }
    }

    @Override
    void getSubtaskListIfStandardBehaviour() {
        if (Files.exists(manager.getHistoryFile())) {
            super.getSubtaskListIfStandardBehaviour();
        }
    }

    @Override
    void getEpicListIfStandardBehaviour() {
        if (Files.exists(manager.getHistoryFile())) {
            super.getEpicListIfStandardBehaviour();
        }
    }

    @Override
    void deleteEpicFromIdInAnyCase() {
        if (Files.exists(manager.getHistoryFile())) {
            super.deleteEpicFromIdInAnyCase();
        }
    }

    @Override
    void deleteSubtaskFromIdInAnyCase() {
        if (Files.exists(manager.getHistoryFile())) {
            super.deleteSubtaskFromIdInAnyCase();
        }
    }

    @Override
    void getTaskListIfTasksAreNotCreated() {
        if (Files.exists(manager.getHistoryFile())) {
            super.getTaskListIfTasksAreNotCreated();
        }
    }
}
