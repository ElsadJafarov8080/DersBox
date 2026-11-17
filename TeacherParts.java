package org.example;

import java.util.Scanner;

public class TeacherParts {
    static DBDAOTeacher db = new DBDAOTeacher();

    public void main() {
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
                LoginRegisterTecher.login();
            } else if (secim.equals(ikinciSecim)) {
                LoginRegisterTecher.register();
            }
        }
    }
}

