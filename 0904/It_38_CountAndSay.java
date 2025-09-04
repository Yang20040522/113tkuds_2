public class It_38_CountAndSay {

    public String countAndSay(int n) {
        if (n == 1) return "1";

        String result = "1";
        for (int i = 2; i <= n; i++) {
            result = encode(result);
        }
        return result;
    }

    private String encode(String s) {
        StringBuilder sb = new StringBuilder();
        int count = 1;
        for (int i = 1; i <= s.length(); i++) {
            if (i < s.length() && s.charAt(i) == s.charAt(i - 1)) {
                count++;
            } else {
                sb.append(count).append(s.charAt(i - 1));
                count = 1;
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        It_38_CountAndSay solver = new It_38_CountAndSay();
        System.out.println(solver.countAndSay(1)); // "1"
        System.out.println(solver.countAndSay(2)); // "11"
        System.out.println(solver.countAndSay(3)); // "21"
        System.out.println(solver.countAndSay(4)); // "1211"
        System.out.println(solver.countAndSay(5)); // "111221"
    }
}
