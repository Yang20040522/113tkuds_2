import java.util.*;

public class AdvancedStringRecursion {
    public static void main(String[] args) {
        String test = "abc";
        String repeated = "aabcc";
        String haystack = "hellorecursion";
        String needle = "recur";

        System.out.println("🔁 所有排列組合（" + test + "）：");
        generatePermutations("", test);

        System.out.println("\n🔍 字串匹配：" + needle + " in " + haystack + " → index = " + stringMatch(haystack, needle, 0));

        System.out.println("\n🧹 移除重複字元（" + repeated + "） → " + removeDuplicates(repeated, 0, new HashSet<>()));

        System.out.println("\n🔎 所有子字串組合（" + test + "）：");
        generateSubstrings(test, 0, "");
    }

    // ✅ 1. 遞迴產生所有排列組合
    public static void generatePermutations(String prefix, String remaining) {
        if (remaining.length() == 0) {
            System.out.println(prefix);
            return;
        }

        for (int i = 0; i < remaining.length(); i++) {
            String next = prefix + remaining.charAt(i);
            String rest = remaining.substring(0, i) + remaining.substring(i + 1);
            generatePermutations(next, rest);
        }
    }

    // ✅ 2. 遞迴字串匹配（回傳首次匹配 index，無則 -1）
    public static int stringMatch(String text, String pattern, int index) {
        if (index + pattern.length() > text.length()) return -1;
        if (text.substring(index, index + pattern.length()).equals(pattern)) return index;
        return stringMatch(text, pattern, index + 1);
    }

    // ✅ 3. 遞迴移除字串中重複字符（只保留第一次出現）
    public static String removeDuplicates(String s, int index, Set<Character> seen) {
        if (index == s.length()) return "";
        char c = s.charAt(index);
        if (seen.contains(c)) {
            return removeDuplicates(s, index + 1, seen);
        } else {
            seen.add(c);
            return c + removeDuplicates(s, index + 1, seen);
        }
    }

    // ✅ 4. 遞迴產生所有子字串組合（不重複）
    public static void generateSubstrings(String s, int index, String current) {
        if (index == s.length()) {
            if (!current.isEmpty()) System.out.println(current);
            return;
        }

        // 包含當前字元
        generateSubstrings(s, index + 1, current + s.charAt(index));

        // 不包含當前字元
        generateSubstrings(s, index + 1, current);
    }
}
