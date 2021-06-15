/*
Matchsticks to Square
You are given an integer array matchsticks where matchsticks[i] is the length of the ith matchstick. You want to use all the matchsticks to make one square. You should not break any stick, but you can link them up, and each matchstick must be used exactly one time.

Return true if you can make this square and false otherwise.

 

Example 1:


Input: matchsticks = [1,1,2,2,2]
Output: true
Explanation: You can form a square with length 2, one side of the square came two sticks with length 1.
Example 2:

Input: matchsticks = [3,3,3,3,4]
Output: false
Explanation: You cannot find a way to form a square with all the matchsticks.
 

Constraints:

1 <= matchsticks.length <= 15
0 <= matchsticks[i] <= 109
*/
class Solution {
    
    public boolean makesquare(int[] matchsticks) {
               
        if (matchsticks == null || matchsticks.length < 4) {
            return false;
        }       
        
        int sum = Arrays.stream(matchsticks).sum();        
        
        if (sum % 4 != 0) {
            return false;
        }
        int side1 = 0, side2 = 0, side3 = 0, side4 = 0, currIndex = 0;        
        int oneSideLen = sum / 4;
        
        return dfs (side1, side2, side3, side4, matchsticks, currIndex, oneSideLen);
        
    }
    
    
    // DFS function to return if len of 4 sides are equal and sum up to total_sum/4 when arr ends.
    
    public boolean dfs (int s1, int s2, int s3, int s4, int arr[], int k, int len) {
             
        if (s1 > len || s2 > len || s3 > len || s4 > len) return false;
       
        if (k == arr.length) return s1 == s2 && s2 == s3 && s3 == s4; 
        
        return (dfs (s1 + arr[k], s2         , s3         , s4         , arr, k + 1, len) ||
                dfs (s1         , s2 + arr[k], s3         , s4         , arr, k + 1, len) ||
                dfs (s1         , s2         , s3 + arr[k], s4         , arr, k + 1, len) ||
                dfs (s1         , s2         , s3         , s4 + arr[k], arr, k + 1, len));
    }
}