package algorithm.string;

import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class lc387_First_Unique_Character_in_a_String {
    static int firstUniqChar(String s) {
        Map<Character, Integer> map = new HashMap<>();
        char[] chars = s.toCharArray();
        int length = chars.length;
        for (char aChar : chars) {
            int i = map.getOrDefault(aChar, 0) + 1;
            map.put(aChar, i);
        }
        for (int i = 0; i < length; i++) {
            if (map.get(chars[i]) == 1){
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {

    }
}
