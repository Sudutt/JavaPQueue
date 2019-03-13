import java.io.*;
import java.util.*;

class Student {
    private String name;
    private double CGPA;
    private int token;

    // default constructor
    public Student() {
        name = "";
        CGPA = 0.0;
        token = 0;
    }

    // parametrized constructor
    public Student(String nm, double cg, int tk) {
        this.name = nm;
        this.CGPA = cg;
        this.token = tk;
    }

    // getter methods
    public String getName() {        return this.name;    }
    public double getCGPA() {        return this.CGPA;    }
    public int getToken() {          return this.token;    }

    // setter methods
    public void setName(String nm) {        this.name = nm;    }
    public void setCGPA(double cg) {        this.CGPA = cg;    }
    public void setToken(int tk) {          this.token = tk;    }
}

public class Assignment {
    //Method to convert data from ENTER command to Student class object
    static Student getObject(String com) {
        String[] data = com.split(" ", 4);  //Get individual strings
        Student stu = new Student();
        stu.setName(data[1]);
        stu.setCGPA(Double.parseDouble(data[2]));
        stu.setToken(Integer.parseInt(data[3]));
        return stu;
    }

    //Method to implement scheduling
    static List<String> getStudents(List<String> comm) {
        PriorityQueue<Student> q = new PriorityQueue<Student>(1, 
        Comparator.comparing(Student::getCGPA).reversed()
        .thenComparing(Student::getName)
        .thenComparing(Student::getToken));
        /**Here custom comparator is used to follow ordering given in question 
         * CGPA is reversed as higher CGPA means higher priority
         * For strings, compareTo() return positive meaning higher priority lexically
         * Token is ascending => small token, higher priority
         * */

        int i, lim=comm.size();
        String f;
        String[] c;
        for(i=0; i<lim; i++){
            f = comm.get(i);                //Get command
            c = f.split(" ");               //Get type of command, saved in c[0]
            if(c[0].compareTo("ENTER")==0)
                q.add(getObject(f));        //Add to queue
            else if(c[0].compareTo("SERVED")==0)
                q.poll();                   //Serve student, remove from queue
            else{
                System.out.println("Error! Invalid Input!");
                System.exit(1);
            }
        }

        //Getting names from remaining students
        List<String> names = new ArrayList<String>();
        Student s;
        while( (s=q.poll()) != null) {
            names.add(s.getName());
        }
        return names;
    }

    public static void main(String args[]) throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);

        //Input number of commands
        int i, num = 0;
        num = Integer.parseInt(br.readLine());

        //Reading & saving all commands
        List<String> coms = new ArrayList<String>(num);
        for(i=0; i<num; i++)
            coms.add(br.readLine());

        //Passing commands to get result - list of names
        List<String> res = getStudents(coms);

        //Output
        if(res.size()==0)
            System.out.println("EMPTY");
        else
            for(i=0; i<res.size(); i++)
                System.out.println(res.get(i));
    }
}
//End of file