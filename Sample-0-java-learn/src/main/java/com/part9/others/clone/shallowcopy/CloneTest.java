package com.part9.others.clone.shallowcopy;

public class CloneTest implements Cloneable {

    private int age;

    private Experience experience;

    public CloneTest() {
        this.age = 10;
        this.experience = new Experience();
    }

    public int getAge() {
        return age;
    }

    public CloneTest setAge(int age) {
        this.age = age;
        return this;
    }

    public Experience getExperience() {
        return experience;
    }

    public void setExperience(String skill) {
        this.experience.setExperience(skill);
    }

    public void show() {
        System.out.println(experience.toString());
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return (CloneTest) super.clone();
    }
}
