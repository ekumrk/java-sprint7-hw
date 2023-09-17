package Tests;

import manager.InMemoryTaskManager;
import org.junit.jupiter.api.BeforeEach;

class InMemoryTaskManagerTest extends TaskManagerTest<InMemoryTaskManager> {

    @BeforeEach
    void init() {
        super.init();
        manager = new InMemoryTaskManager();
    }
}
