//给定两个字符串 s 和 p，找到 s 中所有 p 的 异位词 的子串，返回这些子串的起始索引。不考虑答案输出的顺序。 
//
// 
//
// 示例 1: 
//
// 
//输入: s = "cbaebabacd", p = "abc"
//输出: [0,6]
//解释:
//起始索引等于 0 的子串是 "cba", 它是 "abc" 的异位词。
//起始索引等于 6 的子串是 "bac", 它是 "abc" 的异位词。
// 
//
// 示例 2: 
//
// 
//输入: s = "abab", p = "ab"
//输出: [0,1,2]
//解释:
//起始索引等于 0 的子串是 "ab", 它是 "ab" 的异位词。
//起始索引等于 1 的子串是 "ba", 它是 "ab" 的异位词。
//起始索引等于 2 的子串是 "ab", 它是 "ab" 的异位词。
// 
//
// 
//
// 提示: 
//
// 
// 1 <= s.length, p.length <= 3 * 10⁴ 
// s 和 p 仅包含小写字母 
// 
//
// Related Topics 哈希表 字符串 滑动窗口 👍 1558 👎 0


import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        int windowLength = p.length();
        if (p.length() > s.length()) {
            return new ArrayList<>();
        }

        Map<Character, Integer> charCount = new TreeMap<>();
        p.chars().forEach(c -> {
            charCount.putIfAbsent((char) c, 0);
            charCount.put((char) c, charCount.get((char) c) + 1);
        });

        for (int i = 0; i < p.length(); i++) {
            char c = s.charAt(i);
            charCount.putIfAbsent(c, 0);
            charCount.put(c, charCount.get(c) - 1);
        }

        int l = 0;
        int r = l + windowLength;

        ArrayList<Integer> ans = new ArrayList<>();
        while (r <= s.length()) {
            if (charCount.values().stream().noneMatch(count -> count != 0)) {
                ans.add(l);
            }
            char c = s.charAt(l);
            charCount.put(c, charCount.get(c) + 1);

            if (r < s.length()) {
                c = s.charAt(r);
                charCount.putIfAbsent(c, 0);
                charCount.put(c, charCount.get(c) - 1);
            }

            l++;
            r++;
        }

        return ans;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
