// 題目：Substring with Concatenation of All Words
// VS Code 可直接編譯執行的完整版本（含 main 測試）

import java.util.*;

public class It_30_SubstringWithConcat {

    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> ans = new ArrayList<>();
        if (s == null || words == null || words.length == 0) return ans;

        int n = s.length();
        int wLen = words[0].length();
        int wCnt = words.length;
        int totLen = wLen * wCnt;
        if (n < totLen) return ans;

        Map<String, Integer> need = new HashMap<>();
        for (String w : words) need.put(w, need.getOrDefault(w, 0) + 1);

        for (int offset = 0; offset < wLen; offset++) {
            int left = offset;
            int matched = 0;
            Map<String, Integer> window = new HashMap<>();

            for (int right = offset; right + wLen <= n; right += wLen) {
                String w = s.substring(right, right + wLen);

                if (need.containsKey(w)) {
                    window.put(w, window.getOrDefault(w, 0) + 1);
                    matched++;

                    while (window.get(w) > need.get(w)) {
                        String drop = s.substring(left, left + wLen);
                        window.put(drop, window.get(drop) - 1);
                        left += wLen;
                        matched--;
                    }

                    if (matched == wCnt) {
                        ans.add(left);
                        String drop = s.substring(left, left + wLen);
                        window.put(drop, window.get(drop) - 1);
                        left += wLen;
                        matched--;
                    }
                } else {
                    window.clear();
                    matched = 0;
                    left = right + wLen;
                }
            }
        }
        return ans;
    }

    // 測試
    public static void main(String[] args) {
        It_30_SubstringWithConcat solver = new It_30_SubstringWithConcat();

        String s1 = "barfoothefoobarman";
        String[] w1 = {"foo","bar"};
        System.out.println("Case1: " + solver.findSubstring(s1, w1)); // [0, 9]

        String s2 = "wordgoodgoodgoodbestword";
        String[] w2 = {"word","good","best","word"};
        System.out.println("Case2: " + solver.findSubstring(s2, w2)); // []

        String s3 = "barfoofoobarthefoobarman";
        String[] w3 = {"bar","foo","the"};
        System.out.println("Case3: " + solver.findSubstring(s3, w3)); // [6,9,12]

        // 其他測試：重複單字
        String s4 = "aaaabaaab";
        String[] w4 = {"aa","aa","ab"};
        System.out.println("Case4: " + solver.findSubstring(s4, w4));
    }
}

/*
解題思路：
1. 每個單字長度固定為 wLen，總長 totLen = wLen * words.length。
2. 用 need 統計 words 的目標詞頻，然後針對偏移 0..wLen-1 做滑動視窗：
   - 視窗右端每次吸收一個長度為 wLen 的片段。
   - 若片段在 need 中，加入 window；若某詞超量，從左端（同樣按 wLen）丟詞直至不超量。
   - 當視窗內單字數 matched == words.length，記錄左界為答案，然後從左再丟一個詞繼續。
   - 若遇到不在 need 的片段，視窗清空並重置左界。
3. 此法確保每個片段僅被加入/移除常數次，總體為線性掃描。
時間複雜度 O(n)，空間 O(U)（U 為不同單字數）。
*/
