package manager;

import java.io.IOException;

public class Managers {

    public static HistoryManager getDefaultHistory() {
        return new InMemoryHistoryManager();
    }
    public static TaskManager getDefault() throws IOException {
        return new InMemoryTaskManager();
    }
}
