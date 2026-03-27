import java.io.*;
import java.util.*;

public abstract class FileDictionary implements Dictionary
{
    HashMap<String, String>map=new HashMap<>();
    public void load(String path)
    {
        map.clear();
        try (BufferedReader r=new BufferedReader(new FileReader(path)))
        {
            String s;
            while ((s=r.readLine()) != null)
            {
                String[] p = s.split(";");
                if (p.length==2)
                {
                    map.put(p[0], p[1]);
                }
            }
        }
        catch (Exception e)
        {
            System.out.println("Ошибка загрузки: "+e.getMessage());
        }
    }
    public void save(String path)
    {
        try (BufferedWriter w = new BufferedWriter(new FileWriter(path)))
        {
            for (String k : map.keySet())
            {
                w.write(k +";"+map.get(k));
                w.newLine();
            }
        }
        catch (Exception e)
        {
            System.out.println("Ошибка сохранения: "+e.getMessage());
        }
    }
    public void add(String key, String val)
    {
        if (isValidKey(key))
        {
            map.put(key, val);
        }
        else
        {
            System.out.println("Неверный формат ключа");
        }
    }
    public void remove(String key)
    {
        map.remove(key);
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
            System.out.println(k+" -> "+map.get(k));
        }
    }
    public abstract boolean isValidKey(String key);
}