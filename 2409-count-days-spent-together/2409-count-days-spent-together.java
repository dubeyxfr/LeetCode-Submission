class Solution {
    // Number of days in each month (non-leap year)
    int[] daysInMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    public int countDaysTogether(String arriveAlice, String leaveAlice, String arriveBob, String leaveBob) {
        int aliceStart = getDayOfYear(arriveAlice);
        int aliceEnd = getDayOfYear(leaveAlice);
        int bobStart = getDayOfYear(arriveBob);
        int bobEnd = getDayOfYear(leaveBob);
        
        // Find the latest arrival and earliest departure
        int overlapStart = Math.max(aliceStart, bobStart);
        int overlapEnd = Math.min(aliceEnd, bobEnd);
        
        // If start is after end, there is no overlap
        if (overlapStart > overlapEnd) {
            return 0;
        }
        
        // Calculate total days (inclusive)
        return overlapEnd - overlapStart + 1;
    }
    
    private int getDayOfYear(String date) {
        // Parse month and day from "MM-DD" format
        int month = Integer.parseInt(date.substring(0, 2));
        int day = Integer.parseInt(date.substring(3, 5));
        
        int totalDays = 0;
        // Add days from all previous months
        for (int i = 0; i < month - 1; i++) {
            totalDays += daysInMonth[i];
        }
        
        // Add current day
        totalDays += day;
        
        return totalDays;
    }
}