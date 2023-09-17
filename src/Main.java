import manager.FileBackedTaskManager;
import tasks.*;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {


        FileBackedTaskManager f = new FileBackedTaskManager("/dev/java-sprint7-hw/resources/history.csv");
        Task task;
        Epic epic;
        Subtask subtask;


        //Тестирование
        // 1. создайте две задачи, эпик с тремя подзадачами и эпик без подзадач
        task = new Task("Почитать книгу", "Гарри Поттер");
        f.createNewTask(task);
        task = new Task("Посмотреть фильм", "Звёздные воины");
        f.createNewTask(task);

        epic = new Epic("Сходить в магазин", "Сделать покупки");
        f.createNewEpic(epic);
        subtask = new Subtask("Купить помидоры", "3 кг", 3);
        f.createNewSubtask(subtask);
        subtask = new Subtask("Купить яблоки", "2 кг", 3);
        f.createNewSubtask(subtask);
        subtask = new Subtask("Купить огурцы", "2 кг", 3);
        f.createNewSubtask(subtask);

        epic = new Epic("Сходить в культурные места", "Театры");
        f.createNewEpic(epic);

        f.getTaskFromId(2);
        f.getTaskFromId(1);
        f.getTaskFromId(2);
        System.out.println(f.getHistory());

    }
}
