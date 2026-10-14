class Solution {
    public String maxSumOfSquares(int num, int sum) {
        // Base case: if sum is too large or too small
        if (sum > 9 * num || sum < 1) {
            return "";
        }
        
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < num; i++) {
            int remainingPositions = num - i - 1;
            
            // Try to put the largest possible digit
            for (int d = 9; d >= 0; d--) {
                // First digit cannot be 0
                if (i == 0 && d == 0) continue;
                
                int remainingSum = sum - d;
                
                // Check if remaining sum can be formed by remaining positions
                if (remainingSum >= 0 && remainingSum <= 9 * remainingPositions) {
                    sb.append(d);
                    sum = remainingSum;
                    break;
                }
            }
        }
        
        return sb.toString();
    }
}