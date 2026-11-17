package org.example;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class StudentsParts {
    static DBDAOStudents db = new DBDAOStudents();

    public void main() {
        Map<String, String> musteriler = new HashMap<>();
        while (true) {
            System.out.println("""
                    1.Login
                    2.Register""");
            Scanner scanner = new Scanner(System.in);
            String secim, birinciSecim, ikinciSecim;
            secim = scanner.next();
            birinciSecim = "Login";
            ikinciSecim = "Register";
            if (secim.equals(birinciSecim)) {
                LoginRegisterStudent.loginStudent();
            } else if (secim.equals(ikinciSecim)) {
                LoginRegisterStudent.registerStudent();
            }
        }
    }
}
