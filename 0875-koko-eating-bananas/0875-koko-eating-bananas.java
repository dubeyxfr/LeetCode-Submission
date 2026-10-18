class Solution {
    public int minEatingSpeed(int[] piles, int h) {
        int low = 1;
        int high = 0;
        
        for (int pile : piles) {
            high = Math.max(high, pile);
        }
        
        int ans = high;
        
       
        while (low <= high) {
            int mid = low + (high - low) / 2;
            
            if (canFinish(piles, h, mid)) {
                ans = mid;      
                high = mid - 1;  
            } else {
                low = mid + 1;  
            }
        }
        
        return ans;
    }
    
    // Helper function to check if Koko can finish at a specific speed
    private boolean canFinish(int[] piles, int h, int speed) {
        long totalHours = 0; // Use long to prevent integer overflow
        
        for (int pile : piles) {
            // Math.ceil(pile / speed) in integer arithmetic
            totalHours += (pile + speed - 1) / speed;
            
            // Optimization: If totalHours already exceeds h, we can stop early
            if (totalHours > h) {
                return false;
            }
        }
        
        return totalHours <= h;
    }
}