import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: zohre
 * Date: 1/12/15
 * Time: 2:01 PM
 * To change this template use File | Settings | File Templates.
 */
enum Gender {
    male, female
}
public class User {
    String name;
    int id;
    int birthDay;
    int fatherID;
    int motherID;
    Gender gender;
    boolean isDead;
    ArrayList<User> children;

    public User(String name, int id, int birthDay, int fatherID, int motherID, Gender gender) {
        this.name = name;
        this.id = id;
        this.birthDay = birthDay;
        this.fatherID = fatherID;
        this.motherID = motherID;
        this.gender = gender;
        this.isDead = false;
        children = new ArrayList<User>();
    }

    public boolean isDead() {
        return isDead;
    }

    public void setDead(boolean dead) {
        isDead = dead;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(int birthDay) {
        this.birthDay = birthDay;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public int getMotherID() {
        return motherID;
    }

    public void setMotherID(int motherID) {
        this.motherID = motherID;
    }

    public int getFatherID() {
        return fatherID;
    }

    public void setFatherID(int fatherID) {
        this.fatherID = fatherID;
    }
}
