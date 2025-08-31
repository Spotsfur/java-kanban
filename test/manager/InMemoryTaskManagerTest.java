package manager;

import org.junit.jupiter.api.Test;
import tasks.Epic;
import tasks.SubTask;
import tasks.Task;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryTaskManagerTest {

    //Две Задачи с одним ИД - одинаковые
    @Test
    void tasksWithTheSameIdIsTheSame() {
        TaskManager manager = Managers.getDefault();
        Task task1 = new Task("Тестовая задача","Её описание");
        Task task2 = new Task("Ещё одна задача","Ещё что-то");
        manager.putTask(task1);
        task1 = manager.getTask(1);
        manager.updateTask(task2,1);
        task2 = manager.getTask(1);

        assertEquals(task1, task2, "Задачи не совпадают");
    }

    //Два Эпика с одним ИД - одинаковые
    @Test
    void epicsWithTheSameIdIsTheSame() {
        TaskManager manager = Managers.getDefault();
        Epic epic1 = new Epic("Первый эпик", "Его описание");
        Epic epic2 = new Epic("Второй эпик", "Другое описание");
        manager.putEpic(epic1);
        manager.putEpic(epic2);
        epic1 = manager.getEpic(1);
        epic2 = manager.getEpic(2);
        epic1.setId(5);
        epic2.setId(5);

        assertEquals(epic1, epic2, "Эпики не совпадают");
    }

    //Две Подзадачи с одним ИД - одинаковые
    @Test
    void subtasksWithTheSameIdIsTheSame() {
        TaskManager manager = Managers.getDefault();
        Epic epic1 = new Epic("Первый эпик", "Его описание");
        SubTask subTask1 = new SubTask("Название подзадачи","Какое-то описание", 1);
        SubTask subTask2 = new SubTask("Название другой подзадачи","Какое-то описание", 1);
        manager.putEpic(epic1);
        manager.putSubTask(subTask1);
        manager.putSubTask(subTask2);
        subTask1 = manager.getSubTask(2);
        subTask2 = manager.getSubTask(3);
        subTask1.setId(5);
        subTask2.setId(5);

        assertEquals(subTask1, subTask2, "Подзадачи не совпадают");
    }

    /*Поле epicId у SubTask приватное, не имеет сеттера и может быть проинициализировано только в конструкторе.
    * Я не могу придумать кейс, при котором subTask сам себе родитель*/

    //Эпик не может добавить сам себя в Подзадачи
    @Test
    void epicCantHaveSelfIdInSubtasks() {
        TaskManager manager = Managers.getDefault();
        Epic epic1 = new Epic("Первый эпик", "Его описание");
        manager.putEpic(epic1);
        ArrayList<Integer> newSubTasks = new ArrayList<>();
        newSubTasks.add(1);
        epic1.setSubtasksIds(newSubTasks);
        assertFalse(epic1.getSubTasksIds().contains(1));
    }

    //ТаскМенеджер работает с разными задачами и получает их по ИД
    @Test
    void taskManagerCanAddAndFindDifferendTasks() {
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

        int requestId1 = 1;
        int requestId2 = 2;
        int requestId3 = 3;

        String taskName = manager.getTask(requestId1).getName();
        String epicName = manager.getEpic(requestId2).getName();
        String subTaskName = manager.getSubTask(requestId3).getName();

        assertTrue((taskName.equals("Одын") && (epicName.equals("Еники")) && (subTaskName.equals("Чё-то"))));
    }

    //Поля задачи после добавления в Менеджер не меняются
    @Test
    void tasksFieldDoNotChanged() {
        TaskManager manager = Managers.getDefault();
        Task task1 = new Task("Имя задачи", "Описание задачи");
        manager.putTask(task1);
        Task task2 = manager.getTask(1);
        assertTrue((task1.getName().equals(task2.getName())) && (task1.getDescription().equals(task2.getDescription())));
    }

    //В HistoryManager хранится предыдущая версия задачи
    @Test
    void historyManagerHaveCorrectInformation() {
        TaskManager manager = Managers.getDefault();
        Task task1 = new Task("Задача","Описание");
        manager.putTask(task1);
        manager.getTask(1);
        Task task2 = manager.getHistory().getFirst();
        assertTrue((task1.getName().equals(task2.getName())) && (task1.getDescription().equals(task2.getDescription())));
    }
}