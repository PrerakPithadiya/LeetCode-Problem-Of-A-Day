class Solution {
    public int countGoodTriplets(int[] arr, int a, int b, int c) {
        int count = 0;
        int n = arr.length;
        boolean checkC = (a + b) > c;
        int[][] diff = new int[n][n];
        
        // Precompute absolute differences between all pairs
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                diff[i][j] = Math.abs(arr[i] - arr[j]);
            }
        }
        
        for (int i = 0; i < n; ++i) {
            for (int k = i + 2; k < n; ++k) {
                if (checkC && diff[i][k] > c) {
                    continue;
                }
                for (int j = i + 1; j < k; ++j) {
                    if (diff[i][j] <= a && diff[j][k] <= b) {
                        count++;
                    }
                }
            }
        }
        
        return count;
    }
}
