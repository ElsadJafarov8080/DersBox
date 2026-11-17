package org.example;

import java.util.Scanner;

public class Main {
    static Scanner scanner=new Scanner(System.in);
    static StudentsParts students = new StudentsParts();
    static TeacherParts teacher =new TeacherParts();
    public static void main(String[] args) {
        System.out.println("""
                Who are you?
                Teacher or Student ?""");
        String secim1 =scanner.next();
        String secim2="Teacher";
        String secim3 = "Student";
        if(secim1.equals(secim2)){
            teacher.main();
        } else if (secim1.equals(secim3)) {
            students.main();
        }else {
            System.out.println("Daxil etdiyiniz melumatin dogrulugunu yoxlayin.");
        }
    }
}