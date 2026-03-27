public class NumberDictionary extends FileDictionary
{
    public boolean isValidKey(String key)
    {
        if (key==null)
        {
            return false;
        }
        if (key.length()!= 5)
        {
            return false;
        }
        for (int i = 0; i<key.length(); i++)
        {
            char c =key.charAt(i);
            if (!(c >='0' && c<='9'))
            {
                return false;
            }
        }
        return true;
    }

}