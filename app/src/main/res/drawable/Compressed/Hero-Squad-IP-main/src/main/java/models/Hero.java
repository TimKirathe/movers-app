package models;

import java.util.ArrayList;

public class Hero {
    private String mName;
    private int mAge;
    private int id;
    private String mSpecialPower;
    private String mWeakness;
    private boolean mPublished;
    private String mSquadMembership;

    public static ArrayList<Hero> mInstances = new ArrayList<Hero>();

    public Hero(String name, int age, String specialPower, String weakness, String squadMembership) {
        this.mName = name;
        this.mAge = age;
        this.mSpecialPower = specialPower;
        this.mWeakness = weakness;
        this.mPublished = false;
        this.mSquadMembership = squadMembership;

        mInstances.add(this);
        this.id = mInstances.size();
    }

    public void setSquadMembership(String membership){
        this.mSquadMembership = membership;
    }

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public int getAge() {
        return mAge;
    }

    public void setAge(int mAge) {
        this.mAge = mAge;
    }

    public String getSpecialPower() {
        return mSpecialPower;
    }

    public void setSpecialPower(String mSpecialPower) {
        this.mSpecialPower = mSpecialPower;
    }

    public String getWeakness() {
        return mWeakness;
    }

    public void setWeakness(String mWeakness) {
        this.mWeakness = mWeakness;
    }

    public static ArrayList<Hero> getAll() {
        return mInstances;
    }

    public boolean getPublished(){
        return this.mPublished;
    }

    public static void clearAllHeroes(){
        mInstances.clear();
    }

    public int getId(){
        return this.id;
    }

    public static Hero findById(int id){
        return mInstances.get(id - 1);
    }

    public void deleteHero(){
        mInstances.remove(id - 1);
    }
}
