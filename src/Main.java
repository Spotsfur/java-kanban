import Manager.TaskManager;
import Tasks.Epic;
import Tasks.SubTask;
import Tasks.Task;

public class Main {

    public static void main(String[] args) {
        TaskManager manager = new TaskManager();

        //В соответствии с ТЗ, создаём две задачи, два эпика и три подзадачи
        Task task1 = new Task("Тестовая задача","Её описание");
        Task task2 = new Task("Ещё одна задача","Ещё что-то");
        Epic epic1 = new Epic("Первый эпик", "Его описание");
        Epic epic2 = new Epic("Второй эпик", "Другое описание");
        SubTask subTask1 = new SubTask("Название подзадачи","Какое-то описание", 3);
        SubTask subTask2 = new SubTask("Название другой подзадачи","Какое-то описание", 4);
        SubTask subTask3 = new SubTask("Название очередной подзадачи","Какое-то описание", 4);

        //Передаём в Tasks.Task Задачи, Эпики, Подзадачи
        manager.putTask(task1);
        manager.putTask(task2);

        manager.putEpic(epic1);
        manager.putEpic(epic2);

        manager.putSubTask(subTask1);
        manager.putSubTask(subTask2);
        manager.putSubTask(subTask3);

        //Печатаем всё
        //System.out.println(manager.getTasks());
        //System.out.println(manager.getEpics());
        //System.out.println(manager.getSubTasks());

        manager.updateSubTask(subTask1, 5);
        manager.updateSubTask(subTask2, 6);
        System.out.println(manager.getSubTasks());
        System.out.println(manager.getEpics());

        //Создаём ещё один объект для обновления задачи
        //Tasks.Task task3 = new Tasks.Task("Обновлённая задача","Новое описание");
        //System.out.println(manager.updateTask(task3, 2, Tasks.Status.IN_PROGRESS));
        //System.out.println(manager.getTasks());

        //Удаляем задачу
        //System.out.println(manager.removeTask(1));
        //System.out.println(manager.removeTask(3));
        //System.out.println(manager.getTasks());
    }
}