/**
 * // This is MountainArray's API interface.
 * // You should not implement it, or speculate about its implementation
 * interface MountainArray {
 *     public int get(int index) {}
 *     public int length() {}
 * }
 */

class Solution {
    public int findInMountainArray(int target, MountainArray mountainArr) {
        int peak = peakIndexInMountainArray(mountainArr);
        
        // 1. Search in the ascending part (left side)
        int firstTry = orderAgnosticBS(mountainArr, target, 0, peak);
        if (firstTry != -1) {
            return firstTry;
        }
        
        // 2. If not found, search in the descending part (right side)
        return orderAgnosticBS(mountainArr, target, peak + 1, mountainArr.length() - 1);
    }

    // Finds the peak index of the mountain array
    static int peakIndexInMountainArray(MountainArray arr) {
        int start = 0;
        int end = arr.length() - 1;

        while (start < end) {
            int mid = start + (end - start) / 2;
            if (arr.get(mid) > arr.get(mid + 1)) {
                // You are in the descending part of the array
                // This may be the answer, but look at the left side
                end = mid;
            } else {
                // You are in the ascending part of the array
                // Because we know that mid+1 element > mid element
                start = mid + 1;
            }
        }
        // In the end, start == end and pointing to the largest number because of the two checks above
        return start; 
    }

    // Binary search that works for both ascending and descending sorted arrays
    static int orderAgnosticBS(MountainArray arr, int target, int start, int end) {
        // Determine if the current sub-array is ascending or descending
        boolean isAsc = arr.get(start) < arr.get(end);

        while (start <= end) {
            int mid = start + (end - start) / 2;
            int midVal = arr.get(mid);

            if (midVal == target) {
                return mid;
            }

            if (isAsc) {
                if (target < midVal) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            } else {
                // Descending order logic
                if (target > midVal) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            }
        }
        return -1;
    }
}