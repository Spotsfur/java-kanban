import manager.InMemoryTaskManager;
import manager.Managers;
import manager.TaskManager;
import tasks.Epic;
import tasks.SubTask;
import tasks.Task;

public class Main {

    public static void main(String[] args) {
        TaskManager manager = Managers.getDefault();

        //В соответствии с ТЗ, создаём две задачи, два эпика и три подзадачи
        //Task task1 = new Task("Тестовая задача","Её описание");
        //Task task2 = new Task("Ещё одна задача","Ещё что-то");
        Epic epic1 = new Epic("Первый эпик", "Его описание");
        //Epic epic2 = new Epic("Второй эпик", "Другое описание");
        SubTask subTask1 = new SubTask("Название подзадачи","Какое-то описание", 1);
        SubTask subTask2 = new SubTask("Название другой подзадачи","Какое-то описание", 1);
        //SubTask subTask3 = new SubTask("Название очередной подзадачи","Какое-то описание", 4);

        //Передаём в Tasks.task Задачи, Эпики, Подзадачи
        //manager.putTask(task1);
        //manager.putTask(task2);

        manager.putEpic(epic1);
        //manager.putEpic(epic2);

        manager.putSubTask(subTask1);
        manager.putSubTask(subTask2);
        //manager.putSubTask(subTask3);

        //Цвет
        String ANSI_GREEN = "\u001B[32m";
        String ANSI_RED = "\u001B[31m";
        String ANSI_RESET = "\u001B[0m"; // Сброс цвета

        //Тест на задачи с ИД 0
        /*Task innerTask1 = manager.getTask(1);
        Task innerTask2 = manager.getTask(2);
        innerTask1.setId(0);
        innerTask2.setId(0);
        if (innerTask1.equals(innerTask2)) {
            System.out.println(ANSI_GREEN + "Задачи равны" + ANSI_RESET);
        } else {
            System.out.println(ANSI_RED + "Задачи НЕ равны" + ANSI_RESET);
        }*/

        //Тест на задачи с ИД 0

        /*Epic innerEpic = manager.getEpic(3);
        Epic innerEpic1 = manager.getEpic(4);
        innerEpic.setId(0);
        innerEpic1.setId(0);
        if (innerEpic.equals(innerEpic1)) {
            System.out.println(ANSI_GREEN + "Эпики равны" + ANSI_RESET);
        } else {
            System.out.println(ANSI_RED + "Эпики НЕ равны" + ANSI_RESET);
        }*/

        /*SubTask innerSubTask = manager.getSubTask(5);
        SubTask innerSubTask1 = manager.getSubTask(6);
        innerSubTask.setId(0);
        innerSubTask1.setId(0);
        if (innerSubTask.equals(innerSubTask1)) {
            System.out.println(ANSI_GREEN + "Сабтаски равны" + ANSI_RESET);
        } else {
            System.out.println(ANSI_RED + "Сабтаски НЕ равны" + ANSI_RESET);
        }*/

        //manager.getTask(1).setId(0);
        //manager.getTask(2).setId(0);
        //System.out.println(manager.getTasks());

        //Печатаем всё
        //System.out.println(manager.getTasks());
        System.out.println(manager.getEpics());
        System.out.println(manager.getSubTasks());

        //System.out.println("Тест");
        //System.out.println(manager.getTask(2));
        //System.out.println(manager.getEpic(4));
        //System.out.println(manager.getSubTask(5));
        //System.out.println(manager.getHistory());
        //System.out.println("Тест");

        //manager.updateSubTask(subTask1, 5);
        //manager.updateSubTask(subTask2, 6);
        //System.out.println(manager.getSubTasks());
        //System.out.println(manager.getEpics());

        //manager.clearSubTasks();
        //System.out.println(manager.getEpics());

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