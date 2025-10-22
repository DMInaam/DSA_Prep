import java.util.HashMap;

public class ransomNoteMy {
    public boolean canConstruct(String r, String m) {
        HashMap<Character, Integer> letter = new HashMap<Character,Integer>();
        for(int i=0;i<m.length();i++)
        {
            char c = m.charAt(i);
            letter.put(c,letter.getOrDefault(c,0)+1);
        }
        for(int i=0;i<r.length();i++){
            char c = r.charAt(i);
            int count = letter.getOrDefault(c,0);
            if(count == 0) return false;
            letter.put(c,count-1);
        }
        return true;
    }
}
