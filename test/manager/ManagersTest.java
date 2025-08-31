package manager;

import org.junit.jupiter.api.Test;
import tasks.Epic;
import tasks.SubTask;
import tasks.Task;

import static org.junit.jupiter.api.Assertions.*;

class ManagersTest {

    //Создание ТаскМенеджера работоспособно
    @Test
    void managersCreateInMemoryTaskManagerCorrectly() {
        TaskManager manager = Managers.getDefault();
        assertNotNull(manager, "manager не должен быть null");
        //Создаём набор данных
        Task task = new Task("Одын", "Дуа");
        Epic epic = new Epic("Еники","Беники");
        SubTask subTask = new SubTask("Чё-то","Куда-то",2);
        //Суём данные
        manager.putTask(task);
        manager.putEpic(epic);
        manager.putSubTask(subTask);

        String taskName = manager.getTask(1).getName();
        String epicDescription = manager.getEpic(2).getDescription();
        int subTaskId = manager.getSubTask(3).getId();

        assertTrue((taskName.equals("Одын") && (epicDescription.equals("Беники")) && (subTaskId == 3)));
    }

    //Создание ХисториМенеджера работоспособно
    @Test
    void managersCreateInMemoryHistoryManagerCorrectly() {
        HistoryManager historyManager = Managers.getDefaultHistory();
        Task task = new Task("Задача", "Описание");
        historyManager.addTask(task);
        assertTrue(historyManager.getHistory().size() == 1, "Там должна быть история");
    }
}