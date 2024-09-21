package org.example.leetcode;

public class JumpGame {

    public boolean canJump(int[] nums) {

        int maxIndex = 0;

        for (int i = 0; i < nums.length; i++) {
            if (i <= maxIndex) {
                maxIndex = Math.max(maxIndex, i + nums[i]);
            }
        }

        return maxIndex >= nums.length - 1;
    }
}
