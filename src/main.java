import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * User: zohre
 * Date: 1/12/15
 * Time: 5:31 PM
 * To change this template use File | Settings | File Templates.
 */
public class main {
    public static void main(String[] args) throws Exception {
        System.out.println("Choose from operations: exit/ add/ rename/ findbyid/ findbyname/ delete/ percentFemale/ percentMale/ list/ findbybirthrange, findrel1, findrel2");
        Scanner scanner = new Scanner(System.in);
        Sys sys = new Sys();
        String st = scanner.next();
        while (!st.equals("exit")){
            if (st.equals("add")){
                System.out.println("name?");
                scanner.nextLine();
                String name= scanner.nextLine();
                System.out.println("id?");
                int id=scanner.nextInt();
                System.out.println("bDay?");
                int birthDay=scanner.nextInt();
                System.out.println("gender? female? male?");
                String g=scanner.next();
                Gender gender;
                if (g.equals("male"))
                    gender= Gender.male;
                else
                    gender= Gender.female;
                System.out.println("father id? (-1 if not exists)");
                int fatherId=scanner.nextInt();
                System.out.println("mother id? (-1 if not exists)");
                int motherId=scanner.nextInt();
                sys.addUser(name, id, birthDay,fatherId,motherId,gender);
            }
            if (st.equals("rename")){
                System.out.println("id?");
                int id=scanner.nextInt();
                scanner.nextLine();
                System.out.println("new name?");
                String name= scanner.nextLine();
                sys.findById(id).element().getValue().setName(name);
            }
            if (st.equals("findbyid")){
                System.out.println("id?");
                int id=scanner.nextInt();
                if (sys.findById(id) == null)
                    System.out.print("not found");
                else {
                    System.out.print("name: "+ sys.findById(id).element().getValue().getName()+"\n");
                    System.out.print("id: " + sys.findById(id).element().getValue().getId()+"\n");
                    System.out.print("bDay: "+sys.findById(id).element().getValue().getBirthDay()+"\n");
                    System.out.print("gender: "+sys.findById(id).element().getValue().getGender()+"\n");
                    System.out.print("father id: "+sys.findById(id).element().getValue().getFatherID()+"\n");
                    System.out.print("mother id: "+sys.findById(id).element().getValue().getMotherID()+"\n");
                }
            }
            if (st.equals("findbyname")){
                System.out.println("name?");
                scanner.nextLine();
                String name= scanner.nextLine();

                System.out.print("name: "+ sys.findByName(name).element().getValue().getName()+"\n");
                System.out.print("id: " + sys.findByName(name).element().getValue().getId()+"\n");
                System.out.print("bDay: "+sys.findByName(name).element().getValue().getBirthDay()+"\n");
                System.out.print("gender: "+sys.findByName(name).element().getValue().getGender()+"\n");
                System.out.print("father id: "+sys.findByName(name).element().getValue().getFatherID()+"\n");
                System.out.print("mother id: "+sys.findByName(name).element().getValue().getMotherID()+"\n");
            }
            if (st.equals("delete")){
                System.out.println("id?");
                int id=scanner.nextInt();
                sys.removeById(id);
            }
            if (st.equals("percentFemale")){
                System.out.print(sys.wemen/sys.all*100 + "%");
            }
            if (st.equals("percentMale")){
                System.out.print(sys.men/sys.all*100 + "%");
            }
            if (st.equals("list")){
                sys.printAll(sys.avlName.root);
            }
            if (st.equals("findbybirthrange")){
                System.out.println("from?");
                int t1=scanner.nextInt();
                System.out.println("to?");
                int t2=scanner.nextInt();
                sys.findbybirthrange(t1, t2);
            }
            if (st.equals("findrel1")){
                System.out.println("id?");
                 int id=scanner.nextInt();
                 sys.findrel1(id);
            }
            if (st.equals("findrel2")){
                System.out.println("id?");
                int id=scanner.nextInt();
                sys.findrel2(id);
            }
            System.out.println("Choose from operations: exit/ add/ rename/ findbyid/ findbyname/ delete/ percentFemale/ percentMale/ list/ findbybirthrange, findrel1, findrel2");
            st = scanner.next();
        }

        System.out.print(sys.avlName.root.element().getKey());

    }
}
