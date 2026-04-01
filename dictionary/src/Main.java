import java.util.Scanner;
import java.io.File;

public class Main
{
    static String letterPath;
    static String numberPath;
    public static void main(String[] args)
    {
        DictionaryService serv = new DictionaryService();
        Scanner scan = new Scanner(System.in);
        System.out.print("Введите путь к файлу буквенного словаря: ");
        letterPath = scan.nextLine();
        System.out.print("Введите путь к файлу числового словаря: ");
        numberPath = scan.nextLine();
        serv.loadAll(letterPath, numberPath);
        while (true)
        {
            System.out.println("\nВыберите действие:");
            System.out.println("1. Вывести словари");
            System.out.println("2. Взаимодействие со словарем букв");
            System.out.println("3. Взаимодействие со словарем цифр");
            System.out.println("4. Изменить путь к буквенному словарю");
            System.out.println("5. Изменить путь к числовому словарю");
            System.out.println("6. Выход");
            System.out.print("Выбор: ");
            int vibor = scan.nextInt();
            scan.nextLine();
            if (vibor == 1)
            {
                System.out.println("\nСловарь букв:");
                serv.getDict1().showAll();
                System.out.println("\nСловарь цифр:");
                serv.getDict2().showAll();
            }
            else if (vibor == 2)
            {
                workWithDict(scan, serv.getDict1(), "букв");
            }
            else if (vibor == 3)
            {
                workWithDict(scan, serv.getDict2(), "цифр");
            }
            else if (vibor == 4)
            {
                System.out.println("\nТекущий путь: " + letterPath);
                System.out.print("Введите новый путь для буквенного словаря: ");
                String input = scan.nextLine();
                if (!input.trim().isEmpty())
                {
                    File f = new File(input);
                    if (f.exists())
                    {
                        letterPath = input;
                        serv.getDict1().load(letterPath);
                        System.out.println("Путь изменен, словарь загружен");
                    }
                    else
                    {
                        System.out.println("Ошибка! Файл не найден. Путь не изменен");
                    }
                }
            }
            else if (vibor == 5)
            {
                System.out.println("\nТекущий путь: " + numberPath);
                System.out.print("Введите новый путь для числового словаря: ");
                String input = scan.nextLine();
                if (!input.trim().isEmpty())
                {
                    File f = new File(input);
                    if (f.exists())
                    {
                        numberPath = input;
                        serv.getDict2().load(numberPath);
                        System.out.println("Путь изменен, словарь загружен");
                    }
                    else
                    {
                        System.out.println("Ошибка! Файл не найден. Путь не изменен");
                    }
                }
            }
            else if (vibor == 6)
            {
                System.out.println("Завершение работы программы");
                break;
            }
            else
            {
                System.out.println("Неверный выбор");
            }
        }
        scan.close();
    }
    private static void workWithDict(Scanner scan, Dictionary dict, String name)
    {
        while (true)
        {
            System.out.println("\nВыберите действие со словарем " + name + ":");
            System.out.println("1. Добавить запись");
            System.out.println("2. Удалить запись");
            System.out.println("3. Найти запись");
            System.out.println("4. Показать все");
            System.out.println("5. Назад");
            System.out.print("Выбор: ");
            int vibor = scan.nextInt();
            scan.nextLine();
            if (vibor == 1)
            {
                System.out.print("Введите ключ: ");
                String key = scan.nextLine();
                System.out.print("Введите значение: ");
                String value = scan.nextLine();
                dict.add(key, value);
            }
            else if (vibor == 2)
            {
                System.out.print("Введите ключ для удаления: ");
                String key = scan.nextLine();
                dict.remove(key);
            }
            else if (vibor == 3)
            {
                System.out.print("Введите ключ для поиска: ");
                String key = scan.nextLine();
                String result = dict.find(key);
                if (result != null)
                {
                    System.out.println("Найдено: " + key + " -> " + result);
                }
                else
                {
                    System.out.println("Не найдено");
                }
            }
            else if (vibor == 4)
            {
                dict.showAll();
            }
            else if (vibor == 5)
            {
                break;
            }
            else
            {
                System.out.println("Неверный выбор");
            }
        }
    }
}