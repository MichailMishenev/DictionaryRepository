public class DictionaryService
{

    LetterDictionary dict1;
    NumberDictionary dict2;
    public DictionaryService()
    {
        dict1 = new LetterDictionary();
        dict2 = new NumberDictionary();
    }
    public void loadAll(String path1, String path2)
    {
        dict1.load(path1);
        dict2.load(path2);
    }
    public void saveAll(String path1, String path2)
    {
        dict1.save(path1);
        dict2.save(path2);
    }
    public LetterDictionary getDict1()
    {
        return dict1;
    }
    public NumberDictionary getDict2()
    {
        return dict2;
    }
}