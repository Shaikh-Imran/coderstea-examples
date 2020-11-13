package core;

import static java.lang.System.out;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ComparableVsComparator {

	public static void sortingWithPrimitiveWrapperCls() {
		// //Primitive Data-type Integer Array sort
		int[] intArray = {8, 1, 10, 2};
		out.println("Primitive Data-type Integer Array Before sort : " + Arrays.toString(intArray));
		Arrays.sort(intArray);
		out.println("Primitive Data-type Integer Array After sort : " + Arrays.toString(intArray) + "\n");
		
		//In-build Class String Array sort
		String[] strArray = {"C", "O", "D", "E", "R", "S", "T", "E", "A"};
		out.println("In-build Class String Array Before sort : " + Arrays.toString(strArray));
		Arrays.sort(strArray);
		out.println("In-build Class String Array After sort : " + Arrays.toString(strArray) + "\n");
		
		//List Collection of String type sort
		List<String> listOfStringType = new ArrayList<>(Arrays.asList("C", "O", "D", "E", "R", "S", "T", "E", "A"));
		out.println("List Collection of String type Before sort : " + listOfStringType.stream().collect(Collectors.joining(", ")));
		Collections.sort(listOfStringType); //Sorting From Collection Class as Mentioned Above
		out.println("List Collection of String type After sort : " + listOfStringType.stream().collect(Collectors.joining(", ")));
	}
	
	public static void compareAndSortStudentsWithoutComparisionLogic() {
	    Student[] studentArray = new Student[4];
	    studentArray[0] = new Student(3, "Veronika", 10, "C");
	    studentArray[1] = new Student(1, "Sym", 8, "D");
	    studentArray[2] = new Student(31, "Mahesh", 11, "B");
	    studentArray[3] = new Student(2, "Imran", 9, "A");
	    
	    out.print("Custom Student Class Array Before sort : ");
	    Stream.of(studentArray).forEach(e -> out.print(e.getRollNo() + " "));
	    
	    Arrays.sort(studentArray);
	    
	    out.print("\nCustom Student Class Array After sort : ");
	    Stream.of(studentArray).forEach(e -> out.print(e.getRollNo() + " "));
	}
	
	public static void sortWithComparableOnStudent2() {
	    Student2[] student2Array = new Student2[4];
	    student2Array[0] = new Student2(3, "Veronika", 10, "C");
	    student2Array[1] = new Student2(1, "Sym", 8, "D");
	    student2Array[2] = new Student2(31, "Mahesh", 11, "B");
	    student2Array[3] = new Student2(2, "Imran", 9, "A");
	    out.print("Custom Student Class Array Before sort : ");
	    Stream.of(student2Array).forEach(e -> out.print(e.getRollNo() +" "));
	    Arrays.sort(student2Array);
	    out.print("\nCustom Student Class Array After sort : ");
	    Stream.of(student2Array).forEach(e -> out.print(e.getRollNo() +" "));
	    
	    //Using Collections Class
	    List<Student2> studentList = new ArrayList<>();
	    studentList.add(new Student2(3, "Veronika", 10, "C"));
	    studentList.add(new Student2(31, "Mahesh", 11, "B"));
	    studentList.add(new Student2(2, "Imran", 10, "A"));
	    studentList.add(new Student2(1, "Sym", 8, "D"));
	    studentList.add(new Student2());
	    studentList.add(new Student2());
	    out.print("\n\nCustom Student Class list Before sort : ");
	    studentList.forEach(e -> out.print(e.getRollNo() +" "));
	    
	    //Sort List as per mentioned logic in comparator
	    Collections.sort(studentList);
	    out.print("\nCustom Student Class list After sort : ");
	    studentList.forEach(e -> out.print(e.getRollNo() +" "));
	    
	    //Sort List in reverse as per mentioned logic in comparator
	    Collections.sort(studentList, Comparator.reverseOrder());
	    out.print("\nCustom Student Class list After reverse sort : ");
	    studentList.forEach(e -> out.print(e.getRollNo() +" "));
	}
	
	public static void compareAndSortOnComparator() {
	    //Using Collections Class
	    List<Student> studentList = new ArrayList<>();
	    studentList.add(new Student(3, "Veronika", 10, "C"));
	    studentList.add(new Student(31, "Mahesh", 11, "B"));
	    studentList.add(new Student(2, "Imran", 10, "A"));
	    studentList.add(new Student(1, "Sym", 8, "D"));
	    out.print("\nCustom Student Class list Before sort : ");
	    studentList.forEach(out::print);
	    
	    //--------- Sorting base on RollNo From StudentRollNoComparable class mentioned logic in comparator
	    StudentRollNoComparator studentRollNoComparator = new StudentRollNoComparator();
	    Collections.sort(studentList, studentRollNoComparator);
	    out.print("\n\nCustom Student Class list based on rollNo After sort : ");
	    studentList.forEach(e -> out.print(e.getRollNo() +" "));
	    
	    //In Reverse Order
	    Collections.sort(studentList, studentRollNoComparator.reversed());
	    out.print("\nCustom Student Class list based on rollNo After reversed sort  : ");
	    studentList.forEach(e -> out.print(e.getRollNo() +" "));
	    
	    //--------- Sorting base on Name From StudentRollNoComparable class mentioned logic in comparator
	    Comparator<Student> studentNameComparator = new Comparator<Student>() {
	        @Override
	        public int compare(Student student0, Student student1) {
	            return student0.getName().compareTo(student1.getName());
	        }
	    }; // with lambda ====>  (student0, student1) -> student0.getName().compareTo(student1.getName());
	    Collections.sort(studentList, studentNameComparator);
	    out.print("\n\nCustom Student Class list based on name After sort : ");
	    studentList.forEach(e -> out.print(e.getName() +" "));
	    
	    // you can also pass the at runtime without creating the implementation.
	    Collections.sort(studentList, (s1, s2) -> s1.getDivison().compareTo(s2.getDivison()));
	    out.print("\n\nyou can also pass the at runtime without creating the implementation. ");
	    studentList.forEach(e -> out.print(e.getName() +" "));
	    
	    // or use the comparators static method
	    Collections.sort(studentList, Comparator.comparing(Student::getDivison));
	    out.print("\n\nusing comparator's static method ");
	    studentList.forEach(e -> out.print(e.getName() +" "));
	    Collections.sort(studentList, studentNameComparator.reversed());
	    out.print("\nCustom Student Class list based on name After reversed sort : ");
	    studentList.forEach(e -> out.print(e.getName() +" "));
	}
	
	public static void main(String[] args) {
		
		out.println("---------------------------------------------------");
		out.println(">> Sorting With Primitive & Wrapper Class <<\n");
		sortingWithPrimitiveWrapperCls();
		
		out.println("\n\n---------------------------------------------------");
		out.println(">> Sorting Custom Student Class Student Without ComparisionLogic <<\n");
		try {
			compareAndSortStudentsWithoutComparisionLogic();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		out.println("\n\n---------------------------------------------------");
		out.println(">> Sorting Custom Student Class with Comparable <<\n");
		sortWithComparableOnStudent2();
		
		out.println("\n\n---------------------------------------------------");
		out.println(">> Sorting Custom Student Class with Comparator <<\n");
		compareAndSortOnComparator();
		
		out.println("\n\n---------------------------------------------------");
	}
}

class StudentRollNoComparator implements Comparator<Student> {
	
	  @Override
	  public int compare(Student student0, Student student1) {
	    int studentRollno0 = student0.getRollNo();
	    int studentRollno1 = student1.getRollNo();
	    return (studentRollno0 == studentRollno1 ? 0 : studentRollno0 < studentRollno1 ? -1 : 1 );
	  }
}

class Student {
    private int rollNo;
    private String name;
    private int classNo;
    private String divison;
    
    public Student() {}
	
	public Student(int rollNo, String name, int classNo, String divison) {
		this.rollNo = rollNo;
		this.name = name;
		this.classNo = classNo;
		this.divison = divison;
	}
	
	public int getRollNo() {
		return rollNo;
	}

	public String getName() {
		return name;
	}

	public int getClassNo() {
		return classNo;
	}

	public String getDivison() {
		return divison;
	}

	public void setRollNo(int rollNo) {
		this.rollNo = rollNo;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setClassNo(int classNo) {
		this.classNo = classNo;
	}

	public void setDivison(String divison) {
		this.divison = divison;
	}

	@Override
	public String toString() {
		return "Student [rollNo=" + rollNo + ", name=" + name + ", classNo=" + classNo + ", divison=" + divison + "]";
	}
}
 

class Student2 implements Comparable<Student2> {
    private int rollNo;
    private String name;
    private int classNo;
    private String divison;
    
	@Override
    // the comparisons between this and the another Student2 Object
    public int compareTo(Student2 anotherStudent2) {
        return (Integer.compare(this.rollNo, anotherStudent2.getRollNo()));
    }
	
	public Student2() {}
	
	public Student2(int rollNo, String name, int classNo, String divison) {
		this.rollNo = rollNo;
		this.name = name;
		this.classNo = classNo;
		this.divison = divison;
	}

	public int getRollNo() {
		return rollNo;
	}

	public String getName() {
		return name;
	}

	public int getClassNo() {
		return classNo;
	}

	public String getDivison() {
		return divison;
	}

	public void setRollNo(int rollNo) {
		this.rollNo = rollNo;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setClassNo(int classNo) {
		this.classNo = classNo;
	}

	public void setDivison(String divison) {
		this.divison = divison;
	}
}