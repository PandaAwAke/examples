//给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。 
//
// 子序列 是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子
//序列。 
//
// 示例 1： 
//
// 
//输入：nums = [10,9,2,5,3,7,101,18]
//输出：4
//解释：最长递增子序列是 [2,3,7,101]，因此长度为 4 。
// 
//
// 示例 2： 
//
// 
//输入：nums = [0,1,0,3,2,3]
//输出：4
// 
//
// 示例 3： 
//
// 
//输入：nums = [7,7,7,7,7,7,7]
//输出：1
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 2500 
// -10⁴ <= nums[i] <= 10⁴ 
// 
//
// 
//
// 进阶： 
//
// 
// 你能将算法的时间复杂度降低到 O(n log(n)) 吗? 
// 
//
// Related Topics 数组 二分查找 动态规划 👍 3818 👎 0


import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int lengthOfLIS(int[] nums) {
//        // 定义 dp[i] 为包含 nums[i] 的最长严格递增子序列
//        // dp[i] = max(dp[j] + 1) (for j in [0, i-1] if nums[j] < nums[i])
//        // 时间复杂度 O(n^2)
//        int[] dp = new int[nums.length];
//        Arrays.fill(dp, 1);
//
//        for (int i = 1; i < nums.length; i++) {
//            for (int j = 0; j < i; j++) {
//                if (nums[i] > nums[j]) {
//                    dp[i] = Math.max(dp[i], dp[j] + 1);
//                }
//            }
//        }
//
//        return Arrays.stream(dp).max().orElse(0);

        // 贪心法：同长度序列下，末尾最小的最好
        // 记录 d[i] 为长度为 i 的递增子序列中的末尾最大值（它是单调递增的，可以二分查找）
        // 遍历 nums[i]：
        // - 如果 nums[i] > d[len] 则更新 len++
        // - 否则尝试让 d[i] 变小：在 d[1..len] 找到那个可以接续序列的位置 j（即 d[i-1]<nums[j]<d[i]）
        int len = 1, n = nums.length;
        if (n == 0) {
            return 0;
        }
        int[] d = new int[n + 1];
        d[len] = nums[0];
        for (int i = 1; i < n; ++i) {
            if (nums[i] > d[len]) {
                d[++len] = nums[i];
            } else {
                int l = 1, r = len, pos = 0; // 如果找不到说明所有的数都比 nums[i] 大，此时要更新 d[1]，所以这里将 pos 设为 0
                while (l <= r) {
                    int mid = (l + r) >> 1;
                    if (d[mid] < nums[i]) {
                        pos = mid;
                        l = mid + 1;
                    } else {
                        r = mid - 1;
                    }
                }
                d[pos + 1] = nums[i];
            }
        }
        return len;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
