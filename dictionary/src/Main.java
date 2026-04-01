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
        System.out.println("1. Буквенный словарь");
        System.out.println("2. Численный словарь");
        System.out.print("Выбор: ");
        int vibor = scan.nextInt();
        scan.nextLine();
        if (vibor == 1)
        {
            dictType = "букв";
            dict = new LetterDictionary();
        }
        else if (vibor == 2)
        {
            dictType = "цифр";
            dict = new NumberDictionary();
        }
        else
        {
            System.out.println("Неверный выбор. Завершение программы");
            scan.close();
            return;
        }
        System.out.print("Введите путь к файлу словаря: ");
        dictPath = scan.nextLine();
        dict.load(dictPath);
        while (true)
        {
            System.out.println("\nВыберите действие со словарем " + dictType + ":");
            System.out.println("1. Добавить запись");
            System.out.println("2. Удалить запись");
            System.out.println("3. Найти запись");
            System.out.println("4. Показать все");
            System.out.println("5. Изменить путь к файлу");
            System.out.println("6. Выход");
            System.out.print("Выбор: ");
            vibor = scan.nextInt();
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
}