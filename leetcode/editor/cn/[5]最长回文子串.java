//给你一个字符串 s，找到 s 中最长的 回文 子串。 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "babad"
//输出："bab"
//解释："aba" 同样是符合题意的答案。
// 
//
// 示例 2： 
//
// 
//输入：s = "cbbd"
//输出："bb"
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 1000 
// s 仅由数字和英文字母组成 
// 
//
// Related Topics 双指针 字符串 动态规划 👍 7467 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String longestPalindrome(String s) {
        int n = s.length();
        if (n == 0) {
            return "";
        }
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }

        // s[i..j]: 记 dp[i][j] 为包含 s[i] 和 s[j] 的答案
        // - s[i] == s[j]: Max(0, 2 + dp[i+1][j-1])
        // 计算方法：i 从大到小，j 从小到大
        int maxLength = 0;
        int ans_i = 0, ans_j = 0;
        for (int i = n - 2; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    if (j == i + 1) {
                        dp[i][j] = 2;
                    } else if (dp[i + 1][j - 1] > 0) {
                        dp[i][j] = dp[i + 1][j - 1] + 2;
                    }

                    if (dp[i][j] > maxLength) {
                        maxLength = dp[i][j];
                        ans_i = i;
                        ans_j = j;
                    }
                }
            }
        }
        return s.substring(ans_i, ans_j + 1);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
