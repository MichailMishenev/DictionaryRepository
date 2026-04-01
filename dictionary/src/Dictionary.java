public interface Dictionary
{
    void load(String path);
    void add(String key, String value);
    void remove(String key);
    String find(String key);
    void showAll();
    boolean isValidKey(String key);
}