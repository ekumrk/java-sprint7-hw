package Tests;

import manager.FileBackedTaskManager;
import org.junit.jupiter.api.BeforeEach;

import java.io.IOException;

public class FileBackedTaskManagerTest extends TaskManagerTest<FileBackedTaskManager>{

    @BeforeEach
    void init() {
        super.init();
        try {
            manager = new FileBackedTaskManager("./resources/test.csv");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
