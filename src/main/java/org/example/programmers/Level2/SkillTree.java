package org.example.programmers.Level2;

class SkillTree {
    public int solution(String skill, String[] skill_trees) {
        int count = 0;
        for (String skillTree : skill_trees) {
            String after = changeSkill(skill, skillTree);
            if (isRightSkillTree(skill, after)) count++;
        }
        return count;
    }

    public String changeSkill(String origin, String skillTree) {
        return skillTree.replaceAll("[^" + origin + "]", "");
    }

    public boolean isRightSkillTree(String origin, String user) {
        for (int i = 0; i < user.length(); i++)
            if (origin.charAt(i) != user.charAt(i)) return false;
        return true;
    }
}