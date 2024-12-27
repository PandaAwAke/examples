//给定一个由 整数 组成的 非空 数组所表示的非负整数，在该数的基础上加一。 
//
// 最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。 
//
// 你可以假设除了整数 0 之外，这个整数不会以零开头。 
//
// 
//
// 示例 1： 
//
// 
//输入：digits = [1,2,3]
//输出：[1,2,4]
//解释：输入数组表示数字 123。
// 
//
// 示例 2： 
//
// 
//输入：digits = [4,3,2,1]
//输出：[4,3,2,2]
//解释：输入数组表示数字 4321。
// 
//
// 示例 3： 
//
// 
//输入：digits = [9]
//输出：[1,0]
//解释：输入数组表示数字 9。
//加 1 得到了 9 + 1 = 10。
//因此，结果应该是 [1,0]。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= digits.length <= 100 
// 0 <= digits[i] <= 9 
// 
//
// Related Topics 数组 数学 👍 1452 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[] plusOne(int[] digits) {
        digits[digits.length - 1]++;
        for (int i = digits.length - 1; i > 0; i--) {
            digits[i - 1] += digits[i] / 10;
            digits[i] %= 10;
        }
        if (digits[0] > 9) {
            int[] res = new int[digits.length + 1];
            res[0] = 1;
            res[1] = digits[0] % 10;
            for (int i = 2; i <= digits.length; i++) {
                res[i] = digits[i - 1];
            }
            return res;
        } else {
            return digits;
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
