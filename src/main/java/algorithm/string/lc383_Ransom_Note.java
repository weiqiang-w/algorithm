package algorithm.string;

/**
 *
 */
public class lc383_Ransom_Note {
    static boolean canConstruct(String ransomNote, String magazine) {
        if (ransomNote.length() > magazine.length()) {
            return false;
        }
        int[] ints = new int[26];
        for (char c : magazine.toCharArray()) {
            ints[c - 'a']++;
        }
        for (char c : ransomNote.toCharArray()) {
            ints[c - 'a']--;
            if (ints[c - 'a'] < 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(canConstruct("fihjjjjei", "hjibagacbhadfaefdjaeaebgi"));
    }
}