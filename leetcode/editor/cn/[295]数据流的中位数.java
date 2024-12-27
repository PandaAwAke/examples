//中位数是有序整数列表中的中间值。如果列表的大小是偶数，则没有中间值，中位数是两个中间值的平均值。 
//
// 
// 例如 arr = [2,3,4] 的中位数是 3 。 
// 例如 arr = [2,3] 的中位数是 (2 + 3) / 2 = 2.5 。 
// 
//
// 实现 MedianFinder 类: 
//
// 
// MedianFinder() 初始化 MedianFinder 对象。 
// void addNum(int num) 将数据流中的整数 num 添加到数据结构中。 
// double findMedian() 返回到目前为止所有元素的中位数。与实际答案相差 10⁻⁵ 以内的答案将被接受。 
// 
//
// 示例 1： 
//
// 
//输入
//["MedianFinder", "addNum", "addNum", "findMedian", "addNum", "findMedian"]
//[[], [1], [2], [], [3], []]
//输出
//[null, null, null, 1.5, null, 2.0]
//
//解释
//MedianFinder medianFinder = new MedianFinder();
//medianFinder.addNum(1);    // arr = [1]
//medianFinder.addNum(2);    // arr = [1, 2]
//medianFinder.findMedian(); // 返回 1.5 ((1 + 2) / 2)
//medianFinder.addNum(3);    // arr[1, 2, 3]
//medianFinder.findMedian(); // return 2.0 
//
// 提示: 
//
// 
// -10⁵ <= num <= 10⁵ 
// 在调用 findMedian 之前，数据结构中至少有一个元素 
// 最多 5 * 10⁴ 次调用 addNum 和 findMedian 
// 
//
// Related Topics 设计 双指针 数据流 排序 堆（优先队列） 👍 1066 👎 0


import java.util.PriorityQueue;

//leetcode submit region begin(Prohibit modification and deletion)
class MedianFinder {

    PriorityQueue<Integer> lowerQueue = new PriorityQueue<>((a, b) -> b - a);
    PriorityQueue<Integer> upperQueue = new PriorityQueue<>((a, b) -> a - b);
    int size = 0;

    public MedianFinder() {

    }
    
    public void addNum(int num) {
        if (size == 0) {
            lowerQueue.add(num);
        } else {
            double median = findMedian();
            if (size % 2 == 0) {
                if (num <= median) {
                    lowerQueue.offer(num);
                } else {
                    upperQueue.offer(num);
                    Integer upperMin = upperQueue.poll();
                    lowerQueue.offer(upperMin);
                }
            } else {
                if (num <= median) {
                    lowerQueue.offer(num);
                    Integer lowerMax = lowerQueue.poll();
                    upperQueue.offer(lowerMax);
                } else {
                    upperQueue.offer(num);
                }
            }
        }
        size++;
    }
    
    public double findMedian() {
        if (size % 2 == 0) {
            Integer lowerMax = lowerQueue.peek();
            Integer upperMin = upperQueue.peek();
            return (double) (lowerMax + upperMin) / 2.0;
        } else {
            return lowerQueue.peek();
        }
    }

}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */
//leetcode submit region end(Prohibit modification and deletion)
