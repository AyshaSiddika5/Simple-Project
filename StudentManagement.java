package com.mycompany.studentmanagement;
import java.util.Scanner;

class Student {
    // Object variables
    private int id;
    private String name;
    private String department;
    private double cgpa;

    // Class variable
    private static int studentCount = 0; 
    private static final String university = "XYZ University"; 

    // Parameterized constructor
    public Student(int id, String name, String department, double cgpa) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.cgpa = cgpa;
        studentCount++; 
    }

    // Method to display student details
    public void displayDetails() {
        System.out.println("Student ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Department: " + department);
        System.out.println("CGPA: " + cgpa);
        System.out.println("University: " + university);
        System.out.println(); 
    }

    // Class method to display total number of students
    public static void displayTotalStudents() {
        System.out.println("Total number of students: " + studentCount);
    }
}

public class StudentManagement {
    public static void main(String[] args) {
        Scanner s= new Scanner(System.in);
        Student[] students = new Student[3]; // Array to hold 3 student objects

        // Input details for 3 students
        for (int i = 0; i < 3; i++) {
            System.out.println("Enter details for Student " + (i + 1) + ":");
            System.out.print("ID: ");
            int id = s.nextInt();
            s.nextLine(); 
            System.out.print("Name: ");
            String name = s.nextLine();
            System.out.print("Department: ");
            String department = s.nextLine();
            System.out.print("CGPA: ");
            double cgpa = s.nextDouble();

            // Create a new Student object
            students[i] = new Student(id, name, department, cgpa);
        }

        // Display details of each student
        System.out.println("\nStudent Details:");
        for (Student student : students) {
            student.displayDetails();
        }

        // Display total number of students
        Student.displayTotalStudents();

        s.close();
    }
}

