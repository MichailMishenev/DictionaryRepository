import java.io.*;
import java.util.*;

public abstract class FileDictionary implements Dictionary
{
    HashMap<String, String> map = new HashMap<>();
    List<String> allLines = new ArrayList<>();
    String filePath;
    public void load(String path)
    {
        filePath = path;
        map.clear();
        allLines.clear();
        try (BufferedReader r = new BufferedReader(new FileReader(path)))
        {
            String s;
            while ((s = r.readLine()) != null)
            {
                String[] p = s.split(";");
                if (p.length == 2)
                {
                    String key = p[0];
                    String val = p[1];
                    if (isValidKey(key))
                    {
                        map.put(key, val);
                        allLines.add(s);
                    }
                    else
                    {
                        allLines.add(s);
                    }
                }
                else
                {
                    allLines.add(s);
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
            for (String line : allLines)
            {
                w.write(line);
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
            String newLine = key + ";" + val;
            allLines.add(newLine);
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
            for (int i = 0; i < allLines.size(); i++)
            {
                String line = allLines.get(i);
                String[] p = line.split(";");
                if (p.length == 2 && p[0].equals(key))
                {
                    allLines.remove(i);
                    break;
                }
            }
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