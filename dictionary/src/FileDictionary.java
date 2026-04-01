import java.io.*;
import java.util.*;

public abstract class FileDictionary implements Dictionary
{
    HashMap<String, String> map = new HashMap<>();
    String filePath;
    public void load(String path)
    {
        filePath = path;
        map.clear();
        try (BufferedReader r = new BufferedReader(new FileReader(path)))
        {
            String s;
            while ((s = r.readLine()) != null)
            {
                String[] p = s.split(";");
                if (p.length == 2)
                {
                    map.put(p[0], p[1]);
                }
            }
        }
        catch (Exception e)
        {
            System.out.println("Ошибка загрузки: " + e.getMessage());
        }
    }
    void save()
    {
        try (BufferedWriter w = new BufferedWriter(new FileWriter(filePath)))
        {
            for (String k : map.keySet())
            {
                w.write(k + ";" + map.get(k));
                w.newLine();
            }
        }
        catch (Exception e)
        {
            System.out.println("Ошибка сохранения: " + e.getMessage());
        }
    }
    public void add(String key, String val)
    {
        if (isValidKey(key))
        {
            map.put(key, val);
            save();
            System.out.println("Добавлено и сохранено");
        }
        else
        {
            System.out.println("Неверный формат ключа");
        }
    }
    public void remove(String key)
    {
        if (map.containsKey(key))
        {
            map.remove(key);
            save();
            System.out.println("Удалено и сохранено");
        }
        else
        {
            System.out.println("Запись не найдена");
        }
    }
    public String find(String key)
    {
        return map.get(key);
    }
    public void showAll()
    {
        if (map.isEmpty())
        {
            System.out.println("Словарь пуст");
            return;
        }
        for (String k : map.keySet())
        {
            System.out.println(k + " -> " + map.get(k));
        }
    }
    public abstract boolean isValidKey(String key);
}