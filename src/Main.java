import manager.Managers;
import manager.TaskManager;
import tasks.Epic;
import tasks.SubTask;
import tasks.Task;

public class Main {

    public static void main(String[] args) {
        TaskManager manager = Managers.getDefault();

        //В соответствии с ТЗ, создаём две задачи, два эпика и три подзадачи
        //Передаём в Tasks.task Задачи, Эпики, Подзадачи
        manager.putTask(new Task("Тестовая задача","Её описание"));
        manager.putTask(new Task("Ещё одна задача","Ещё что-то"));
        manager.putEpic(new Epic("Первый эпик", "Его описание"));
        manager.putEpic(new Epic("Второй эпик", "Другое описание"));
        manager.putSubTask(new SubTask("Название подзадачи","Какое-то описание", 3));
        manager.putSubTask(new SubTask("Название другой подзадачи","Какое-то описание", 3));
        manager.putSubTask(new SubTask("Название очередной подзадачи","Какое-то описание", 3));

        //Запрашиваем несколько разных задач в разном порядке и выводим историю
        manager.getSubTask(7);
        System.out.println(manager.getHistory());
        manager.getTask(1);
        System.out.println(manager.getHistory());
        manager.getTask(2);
        System.out.println(manager.getHistory());
        manager.getSubTask(6);
        System.out.println(manager.getHistory());
        manager.getEpic(3);
        System.out.println(manager.getHistory());
        manager.getSubTask(5);
        System.out.println(manager.getHistory());
        manager.getEpic(3);
        System.out.println(manager.getHistory());
        manager.getSubTask(7);
        System.out.println(manager.getHistory());
        manager.getTask(2);
        System.out.println(manager.getHistory());
        manager.getEpic(4);
        System.out.println(manager.getHistory()); //Здесь должно быть 1 6 5 3 7 2 4

        //Удаляем задачу, смотрим историю
        manager.removeTask(1);
        System.out.println(manager.getHistory());

        //Удаляем эпик, смотрим историю
        manager.removeEpic(3);
        System.out.println(manager.getHistory());
    }
}