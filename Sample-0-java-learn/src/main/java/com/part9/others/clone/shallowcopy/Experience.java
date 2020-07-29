package com.part9.others.clone.shallowcopy;

public class Experience {
    private String skill;

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public void setExperience(String skill) {
        this.skill = skill;
    }

    @Override
    public String toString() {
        return "Experience{" +
                "skill='" + skill + '\'' +
                '}';
    }
}
