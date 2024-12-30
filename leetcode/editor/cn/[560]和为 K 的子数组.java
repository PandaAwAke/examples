//给你一个整数数组 nums 和一个整数 k ，请你统计并返回 该数组中和为 k 的子数组的个数 。 
//
// 子数组是数组中元素的连续非空序列。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,1,1], k = 2
//输出：2
// 
//
// 示例 2： 
//
// 
//输入：nums = [1,2,3], k = 3
//输出：2
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 2 * 10⁴ 
// -1000 <= nums[i] <= 1000 
// -10⁷ <= k <= 10⁷ 
// 
//
// Related Topics 数组 哈希表 前缀和 👍 2567 👎 0


import com.sun.source.tree.Tree;

import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int subarraySum(int[] nums, int k) {
        int[] sums = new int[nums.length];
        int sum = 0;

        HashMap<Integer, TreeSet<Integer>> sumAndIndices = new HashMap<>();
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            sums[i] = sum;

            // 1. 从头到这里正好和为 k
            if (sum == k) {
                ans++;
            }

            // 2. 某个左端点满足条件，找它
            int targetSum = sum - k;
            if (sumAndIndices.containsKey(targetSum)) {
                TreeSet<Integer> indices = sumAndIndices.get(targetSum);
                ans += indices.size();
            }

            sumAndIndices.putIfAbsent(sum, new TreeSet<>());
            sumAndIndices.get(sum).add(i);
        }

        return ans;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
