//给你两个二进制字符串 a 和 b ，以二进制字符串的形式返回它们的和。 
//
// 
//
// 示例 1： 
//
// 
//输入:a = "11", b = "1"
//输出："100" 
//
// 示例 2： 
//
// 
//输入：a = "1010", b = "1011"
//输出："10101" 
//
// 
//
// 提示： 
//
// 
// 1 <= a.length, b.length <= 10⁴ 
// a 和 b 仅由字符 '0' 或 '1' 组成 
// 字符串如果不是 "0" ，就不含前导零 
// 
//
// Related Topics 位运算 数学 字符串 模拟 👍 1260 👎 0


import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String addBinary(String a, String b) {
        int i = a.length() - 1;
        int j = b.length() - 1;
        List<Character> reversedSum = new ArrayList<>();
        while (i >= 0 || j >= 0) {
            if (i < 0) {
                reversedSum.add(b.charAt(j));
                j--;
            } else if (j < 0) {
                reversedSum.add(a.charAt(i));
                i--;
            } else {
                char sum = (char) ('0' + a.charAt(i) - '0' + b.charAt(j) - '0');
                reversedSum.add(sum);
                i--;
                j--;
            }
        }

        for (i = 0; i < reversedSum.size() - 1; i++) {
            if (reversedSum.get(i) > '1') {
                reversedSum.set(i, (char) (reversedSum.get(i) - 2));
                reversedSum.set(i + 1, (char) (reversedSum.get(i + 1) + 1));
            }
        }

        // i == reversedSum.size() - 1
        if (reversedSum.get(i) > '1') {
            reversedSum.set(i, (char) (reversedSum.get(i) - 2));
            reversedSum.add('1');
        }

        Collections.reverse(reversedSum);
        char[] chars = new char[reversedSum.size()];
        for (i = 0; i < reversedSum.size(); i++) {
            chars[i] = reversedSum.get(i);
        }
        return new String(chars);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
