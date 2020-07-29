package com.part9.others.clone.deepcopy;

public class Experience implements Cloneable{
    private String skill;

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public void setExperience(String skill) {
        this.skill = skill;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return "Experience{" +
                "skill='" + skill + '\'' +
                '}';
    }
}
