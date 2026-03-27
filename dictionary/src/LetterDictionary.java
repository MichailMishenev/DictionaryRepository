public class LetterDictionary extends FileDictionary
{
    public boolean isValidKey(String key)
    {
        if (key == null)
        {
            return false;
        }
        if (key.length() != 4)
        {
            return false;
        }
        for (int i = 0; i < key.length(); i++)
        {
            char c = key.charAt(i);
            if (!((c >='a' && c<='z') || (c>='A' && c<='Z')))
            {
                return false;
            }
        }
        return true;
    }
}