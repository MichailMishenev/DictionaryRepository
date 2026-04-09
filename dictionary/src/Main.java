import java.util.Scanner;
import java.io.File;

public class Main
{
    static String dictPath;
    static String dictType;
    static Dictionary dict;
    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);
        while (true)
        {
            if (!selectDictionary(scan))
            {
                System.out.println("Завершение работы программы");
                break;
            }
            workWithDictionary(scan);
        }
        scan.close();
    }
    private static boolean selectDictionary(Scanner scan)
    {
        while (true)
        {
            System.out.println("\nВыберите словарь для работы:");
            System.out.println("1. Буквенный словарь");
            System.out.println("2. Численный словарь");
            System.out.println("3. Выход");
            System.out.print("Выбор: ");
            String vibor = scan.nextLine();
            if (vibor.equals("1"))
            {
                dictType = "букв";
                dict = new LetterDictionary();
                if (selectPath(scan))
                {
                    return true;
                }
            }
            else if (vibor.equals("2"))
            {
                dictType = "цифр";
                dict = new NumberDictionary();
                if (selectPath(scan))
                {
                    return true;
                }
            }
            else if (vibor.equals("3"))
            {
                return false;
            }
            else
            {
                System.out.println("Неверный выбор");
            }
        }
    }
    private static void workWithDictionary(Scanner scan)
    {
        while (true)
        {
            System.out.println("\nВыберите действие со словарем " + dictType + ":");
            System.out.println("1. Добавить запись");
            System.out.println("2. Удалить запись");
            System.out.println("3. Найти запись");
            System.out.println("4. Показать все");
            System.out.println("5. Изменить путь к файлу");
            System.out.println("6. Выход в главное меню");
            System.out.print("Выбор: ");
            String vibor = scan.nextLine();
            if (vibor.equals("1"))
            {
                System.out.print("Введите ключ: ");
                String key = scan.nextLine();
                System.out.print("Введите значение: ");
                String value = scan.nextLine();
                dict.add(key, value);
            }
            else if (vibor.equals("2"))
            {
                System.out.print("Введите ключ для удаления: ");
                String key = scan.nextLine();
                dict.remove(key);
            }
            else if (vibor.equals("3"))
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
            else if (vibor.equals("4"))
            {
                dict.showAll();
            }
            else if (vibor.equals("5"))
            {
                System.out.println("\nТекущий путь: " + dictPath);
                System.out.print("Введите новый путь: ");
                String input = scan.nextLine();
                if (!input.trim().isEmpty())
                {
                    File f = new File(input);
                    if (f.exists())
                    {
                        dictPath = input;
                        dict.load(dictPath);
                        System.out.println("Путь изменен, словарь загружен");
                    }
                    else
                    {
                        System.out.println("Ошибка! Файл не найден. Путь не изменен");
                    }
                }
            }
            else if (vibor.equals("6"))
            {
                System.out.println("Возврат в главное меню");
                break;
            }
            else
            {
                System.out.println("Неверный выбор");
            }
        }
    }
    private static boolean selectPath(Scanner scan)
    {
        while (true)
        {
            System.out.print("Введите путь к файлу словаря: ");
            dictPath = scan.nextLine();
            File f = new File(dictPath);
            if (!f.exists())
            {
                System.out.println("Ошибка! Файл не существует");
                System.out.print("Выберите действие: 1 - повторить путь, 2 - выбрать другой словарь: ");
                String input = scan.nextLine();
                if (input.equals("2"))
                {
                    return false;
                }
                continue;
            }
            dict.load(dictPath);
            System.out.println("Словарь загружен");
            return true;
        }
    }
}