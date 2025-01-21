
class Solution {

    public int xorAllNums(int[] nums1, int[] nums2) {
        int result = 0;

        // If nums2.length is odd, include all nums1 elements
        if (nums2.length % 2 == 1) {
            for (int num : nums1) {
                result ^= num;
            }
        }

        // If nums1.length is odd, include all nums2 elements
        if (nums1.length % 2 == 1) {
            for (int num : nums2) {
                result ^= num;
            }
        }

        return result;
    }
}
