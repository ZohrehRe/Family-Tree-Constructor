import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: zohre
 * Date: 1/13/15
 * Time: 12:01 PM
 * To change this template use File | Settings | File Templates.
 */
public class Sys {
    AVLTree<Integer,User> avlID;
    AVLTree<String,User> avlName;
    HashMap<Integer,User> bDays;
    int all, men, wemen;

    public Sys() throws Exception {
        avlID  = new AVLTree<Integer, User>();
        avlName  = new AVLTree<String, User>();
        bDays  =  new HashMap<Integer, User>();
    }

    public void addUser(String name, int id, int birthDay, int fatherID, int motherID, Gender gender){
        User u = new User(name, id, birthDay, fatherID, motherID, gender);
        try {
            avlID.insert(id , u);
            avlName.insert(name , u);

            bDays.put(u.getBirthDay(),u);
            if(fatherID != -1) {
                if (findById(fatherID) != null)
                    findById(fatherID).element().getValue().children.add(u);
                else {
                    User f = new User(null, fatherID, -1, -1, -1, Gender.male);
                    f.children.add(u);
                    avlID.insert(fatherID, f);
                }
            }
            if(motherID != -1) {
                if (findById(motherID) != null)
                    findById(motherID).element().getValue().children.add(u);
                else {
                    User m = new User(null, motherID, -1, -1, -1, Gender.female);
                    m.children.add(u);
                    avlID.insert(motherID, m);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        all++;
        if (u.gender == Gender.male)
            men++;
        else
            wemen++;
    }

    public BTPosition<Entry<String, User>> findByName(String name){
        return inOrderName(avlName.root, name);
    }

    public BTPosition<Entry<Integer,User>> findById(int id)  {
            return inOrder(avlID.root , id);
    }

    public void removeById(int id) throws Exception{
        User u = findById(id).element().getValue();
        u.setDead(true);
        if (u.gender == Gender.male)
            men--;
        else
            wemen--;
    }

    public BTPosition<Entry<Integer,User>> inOrder(BTPosition<Entry<Integer,User>> root, int id) {
        try {
            //System.out.print(avlID.hasLeft(root));
            if( root.element().getKey() == id)
                return root;
            if (avlID.hasLeft(root)  && root.element().getKey()>id)
                return inOrder(root.getLeft(), id);
            if (avlID.hasRight(root) && root.element().getKey()<id )
                return inOrder(root.getRight(), id);
            if(!avlID.hasLeft(root) && avlID.hasRight(root) && root.element().getKey() != id) {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        return null;
    }

    public BTPosition<Entry<String,User>> inOrderName(BTPosition<Entry<String,User>> root, String name) {
        try {
            if (!root.getLeft().equals(null)  && root.element().getKey().compareTo(name) > 0)
                return inOrderName(root.getLeft(), name);
            if( root.element().getKey().compareTo(name) == 0)
                return root;
            if (!root.getRight().equals(null) && root.element().getKey().compareTo(name)<0 )
                return inOrderName(root.getRight(), name);
            if(root.getLeft().equals(null) && root.getRight().equals(null) && root.element().getKey().compareTo(name) != 0)
                return null;
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        return null;
    }

    public void printAll(BTPosition<Entry<String,User>> root){
        try {

            if (root.getLeft()!= null)
                printAll(root.getLeft());

            if (!root.element().getValue().isDead()) {
            System.out.print(root.element().getKey()+"\n");
            System.out.print(root.element().getValue().getId()+"\n");
            }

            if (root.getRight()!= null )
                printAll(root.getRight());

        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

    }

    public void findbybirthrange(int t1 , int t2){

        for(Map.Entry<Integer, User> entry : bDays.entrySet()) {
            if( entry.getKey() <= t2 && entry.getKey()>= t1)
                System.out.print(entry.getValue().getName());
        }
    }

    public void findrel1(int id){
        try {
            User me = findById(id).element().getValue();
            try {
                System.out.print("Father:" + "\n");
                System.out.print(findById(me.getFatherID()).element().getValue().getName() + "\n");
            }catch(NullPointerException e){
                System.out.print("No father");
            }
            try {
                System.out.print("Mother:" + "\n");
                System.out.print(findById(me.getMotherID()).element().getValue().getName() + "\n");
            }catch(NullPointerException e){
                System.out.print("No mother");
            }
            System.out.print("Brothers:"+"\n");
            try {
                for (int i = 0; i < findById(me.getFatherID()).element().getValue().children.size(); i++)
                    if (findById(me.getFatherID()).element().getValue().children.get(i).getGender() == Gender.male && me.getId() != findById(me.getFatherID()).element().getValue().children.get(i).getId())
                        System.out.print(findById(me.getFatherID()).element().getValue().children.get(i).getName() + "\n");
            }catch(NullPointerException e){
                System.out.print("No brother");
            }
            System.out.print("Sisters:"+"\n");
            try{
            for (int i = 0 ; i < findById(me.getFatherID()).element().getValue().children.size() ; i++)
                if (findById(me.getFatherID()).element().getValue().children.get(i).getGender()==Gender.female && me.getId()!= findById(me.getFatherID()).element().getValue().children.get(i).getId() )
                    System.out.print(findById(me.getFatherID()).element().getValue().children.get(i).getName()+ "\n");
            }catch(NullPointerException e){
                System.out.print("No sister");
            }
            System.out.print("Children:"+"\n");
            try{
            for (int i = 0 ; i < me.children.size() ; i++)
                    System.out.print(me.children.get(i).getName()+"\n");
            }catch(NullPointerException e){
                System.out.print("No child");
            }
        } catch (Exception e) {
//            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

    }

    public void findrel2(int id){
        try {
            User me = findById(id).element().getValue();
            User father = findById(me.getFatherID()).element().getValue();
            User mother = findById(me.getMotherID()).element().getValue();

            System.out.print("Grandfathers:\n");
            if (findById(father.getFatherID()).element().getValue() != null)
                System.out.print(findById(father.getFatherID()).element().getValue().getName()+"\n");
            if (findById(mother.getFatherID()).element().getValue() != null)
                System.out.print(findById(mother.getFatherID()).element().getValue().getName()+"\n");

            System.out.print("Grandmothers:\n");
            if (findById(father.getMotherID()).element().getValue() != null)
                System.out.print(findById(father.getMotherID()).element().getValue().getName()+"\n");
            if (findById(mother.getMotherID()).element().getValue() != null)
                System.out.print(findById(mother.getMotherID()).element().getValue().getName()+"\n");

            System.out.print("Grandchildren:\n");
            for (int i = 0 ; i< me.children.size() ; i++){
                for (int j = 0 ; j < me.children.get(i).children.size() ; j++){
                    System.out.print(me.children.get(i).children.get(j).getName()+"\n");
                }
            }

            System.out.print("Uncles:\n");
            for (int i = 0 ; i < findById(father.getFatherID()).element().getValue().children.size() ; i++)
                if (findById(father.getFatherID()).element().getValue().children.get(i).getGender()==Gender.male && father.getId()!= findById(father.getFatherID()).element().getValue().children.get(i).getId())
                    System.out.print(findById(father.getFatherID()).element().getValue().children.get(i).getName()+"\n");
            for (int i = 0 ; i < findById(mother.getFatherID()).element().getValue().children.size() ; i++)
                if (findById(mother.getFatherID()).element().getValue().children.get(i).getGender()==Gender.male && mother.getId()!= findById(mother.getFatherID()).element().getValue().children.get(i).getId())
                    System.out.print(findById(mother.getFatherID()).element().getValue().children.get(i).getName()+"\n");

            System.out.print("Aunts:\n");
            for (int i = 0 ; i < findById(father.getFatherID()).element().getValue().children.size() ; i++)
                if (findById(father.getFatherID()).element().getValue().children.get(i).getGender()==Gender.female && father.getId()!= findById(father.getFatherID()).element().getValue().children.get(i).getId())
                    System.out.print(findById(father.getFatherID()).element().getValue().children.get(i).getName()+"\n");
            for (int i = 0 ; i < findById(mother.getFatherID()).element().getValue().children.size() ; i++)
                if (findById(mother.getFatherID()).element().getValue().children.get(i).getGender()==Gender.female && mother.getId()!= findById(mother.getFatherID()).element().getValue().children.get(i).getId())
                    System.out.print(findById(mother.getFatherID()).element().getValue().children.get(i).getName()+"\n");

            System.out.print("Cousins:\n");
            for (int i = 0 ; i < findById(father.getFatherID()).element().getValue().children.size() ; i++){
                if(findById(father.getFatherID()).element().getValue().children.get(i).getId() != father.getId())
                    for (int j = 0 ; j < findById(father.getFatherID()).element().getValue().children.get(i).children.size() ; j++){
                        System.out.print(findById(father.getFatherID()).element().getValue().children.get(i).children.get(j).getName()+"\n");
                    }
            }
            for (int i = 0 ; i < findById(mother.getFatherID()).element().getValue().children.size() ; i++){
                if(findById(mother.getFatherID()).element().getValue().children.get(i).getId() != mother.getId())
                    for (int j = 0 ; j < findById(mother.getFatherID()).element().getValue().children.get(i).children.size() ; j++){
                        System.out.print(findById(mother.getFatherID()).element().getValue().children.get(i).children.get(j).getName()+"\n");
                    }
            }

        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

    }
}
