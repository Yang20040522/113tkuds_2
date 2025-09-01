import java.util.HashMap;

public class lt_03_LongestSubstringWithoutRepeatingCharacters {
    public int lengthOfLongestSubstring(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        int maxLength = 0;
        int left = 0;

        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            if (map.containsKey(c)) {
                left = Math.max(left, map.get(c) + 1);
            }
            map.put(c, right);
            maxLength = Math.max(maxLength, right - left + 1);
        }
        return maxLength;
    }

    public static void main(String[] args) {
        lt_03_LongestSubstringWithoutRepeatingCharacters solution = new lt_03_LongestSubstringWithoutRepeatingCharacters();

        String test1 = "abcabcbb";
        String test2 = "bbbbb";
        String test3 = "pwwkew";

        System.out.println("Test 1 result: " + solution.lengthOfLongestSubstring(test1)); // 預期 3
        System.out.println("Test 2 result: " + solution.lengthOfLongestSubstring(test2)); // 預期 1
        System.out.println("Test 3 result: " + solution.lengthOfLongestSubstring(test3)); // 預期 3
    }
}
