//给定三个字符串 s1、s2、s3，请你帮忙验证 s3 是否是由 s1 和 s2 交错 组成的。 
//
// 两个字符串 s 和 t 交错 的定义与过程如下，其中每个字符串都会被分割成若干 非空 子字符串： 
//
// 
// s = s1 + s2 + ... + sn 
// t = t1 + t2 + ... + tm 
// |n - m| <= 1 
// 交错 是 s1 + t1 + s2 + t2 + s3 + t3 + ... 或者 t1 + s1 + t2 + s2 + t3 + s3 + ... 
// 
//
// 注意：a + b 意味着字符串 a 和 b 连接。 
//
// 
//
// 示例 1： 
// 
// 
//输入：s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
//输出：true
// 
//
// 示例 2： 
//
// 
//输入：s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
//输出：false
// 
//
// 示例 3： 
//
// 
//输入：s1 = "", s2 = "", s3 = ""
//输出：true
// 
//
// 
//
// 提示： 
//
// 
// 0 <= s1.length, s2.length <= 100 
// 0 <= s3.length <= 200 
// s1、s2、和 s3 都由小写英文字母组成 
// 
//
// 
//
// 进阶：您能否仅使用 O(s2.length) 额外的内存空间来解决它? 
//
// Related Topics 字符串 动态规划 👍 1060 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        // dp[i][j][k] 代表三个字符串的下标 (从 1 开始，0 用于边界)
        // 1. i < s1.length() && s1[i-1] == s3[k-1]: dp[i,j,k] = dp[i-1,j,k-1]
        // 2. j < s2.length() && s2[j-1] == s3[k-1]: dp[i,j,k] = dp[i,j-1,k-1]
        // 3. else: dp[i,j,k] = 0
        // 4. Init: dp[0,0,0] = 1
        // 5. 可以优化掉 k: k = i + j，dp[i,j] = dp[i-1,j] / dp[i,j-1]
        // 6. 还能进一步优化：上式实际上可以用滚动数组取代二维数组，可以去掉一个维度

        int l1 = s1.length();
        int l2 = s2.length();
        int l3 = s3.length();

        if (l3 != l1 + l2) {
            return false;
        }
        if (l3 == 0) {
            return true;
        }

//        int[][] dp = new int[l1 + 1][l2 + 1];
//
//        for (int i = 0; i <= l1; i++) {
//            for (int j = 0; j <= l2; j++) {
//                if (i == 0 && j == 0) {
//                    dp[i][j] = 1;
//                    continue;
//                }
//                int k = i + j;
//                if (i > 0 && s1.charAt(i - 1) == s3.charAt(k - 1)) {
//                    dp[i][j] |= dp[i - 1][j];
//                }
//                if (j > 0 && s2.charAt(j - 1) == s3.charAt(k - 1)) {
//                    dp[i][j] |= dp[i][j - 1];
//                }
//            }
//        }
//
//        return dp[l1][l2] == 1;

        int[] dp = new int[l2 + 1];

        for (int i = 0; i <= l1; i++) {
            for (int j = 0; j <= l2; j++) {
                if (i == 0 && j == 0) {
                    dp[0] = 1;
                    continue;
                }
                int k = i + j;
                if (i > 0) {
                    dp[j] = dp[j] & (s1.charAt(i - 1) == s3.charAt(k - 1) ? 1 : 0);
                }
                if (j > 0 && s2.charAt(j - 1) == s3.charAt(k - 1)) {
                    dp[j] |= dp[j - 1];
                }
            }
        }

        return dp[l2] == 1;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
