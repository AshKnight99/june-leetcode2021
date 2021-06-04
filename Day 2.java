/*
Interleaving String
Given strings s1, s2, and s3, find whether s3 is formed by an interleaving of s1 and s2.

An interleaving of two strings s and t is a configuration where they are divided into non-empty substrings such that:

s = s1 + s2 + ... + sn
t = t1 + t2 + ... + tm
|n - m| <= 1
The interleaving is s1 + t1 + s2 + t2 + s3 + t3 + ... or t1 + s1 + t2 + s2 + t3 + s3 + ...
Note: a + b is the concatenation of strings a and b.

 

Example 1:


Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
Output: true
Example 2:

Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
Output: false
Example 3:

Input: s1 = "", s2 = "", s3 = ""
Output: true
 

Constraints:

0 <= s1.length, s2.length <= 100
0 <= s3.length <= 200
s1, s2, and s3 consist of lowercase English letters.
 

Follow up: Could you solve it using only O(s2.length) additional memory space?
*/
class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        int x = s1.length();
        int y = s2.length();
        int z = s3.length();
        if(x+y!=z) return false;
		
        boolean dp[][] = new boolean[x+1][y+1];
		// dp array will be initialized with false
		
        for(int i = 0; i <= x; i++){
            for(int j = 0; j <= y; j++){
                if(i == 0 && j == 0)
                    dp[i][j] = true;
                else if(i == 0)
                    dp[i][j] = dp[i][j - 1] && s2.charAt(j - 1)==s3.charAt(j - 1);
                else if(j == 0)
                    dp[i][j] = dp[i - 1][j] && s1.charAt(i - 1)==s3.charAt(i - 1);
                else
                    dp[i][j] = (dp[i][j - 1] && s2.charAt(j - 1)==s3.charAt(i + j - 1)) || (dp[i - 1][j] && s1.charAt(i - 1)==s3.charAt(i + j - 1));
            }
        }
        return dp[x][y];
    }
}