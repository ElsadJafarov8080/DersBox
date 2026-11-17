package org.example;


import java.util.Scanner;

public class LoginRegisterStudent {
    static DBDAOStudents db = new DBDAOStudents();
    static Scanner scanner = new Scanner(System.in);


    static void loginStudent() {
        if (db.user_pass_check()) {
            System.out.println("""
                    >1.FENLER<
                    >2.DERSLIKLER<
                    >3.DAVAMIYET<
                    >3.SINAQ BALLARI<
                    """);
            String secim = scanner.next();
            String secim1, secim2, secim3, secim4;
            secim1 = "1";
            secim2 = "2";
            secim3 = "3";
            secim4 = "4";
            if (secim.equals(secim1)) {
                db.student_fen();
            } else if (secim.equals(secim2)) {
                db.Derslerik_linkini_goster();
            } else if (secim.equals(secim3)) {
                db.student_davamiyeti();
            } else if (secim.equals(secim4)) {
                db.student_bal();
            } else {
                System.out.println("Bele bir emeliyat yoxdur");
            }
        } else {
            System.out.println("Zehmet olmasa yeni hesab yaradin.");
            return;
        }
    }

    static void registerStudent() {
        System.out.println("Name: ");
        String ad = scanner.next();
        System.out.println("Surname: ");
        String soyad = scanner.next();
        System.out.println("Father name: ");
        String f_name = scanner.next();
        System.out.println("Usernanme: ");
        String username = scanner.next();
        System.out.println("Password: ");
        String password = scanner.next();
        if (db.register_user_pass_check(ad, username, password)) {  //ayrica metod olmalidir
            System.out.println("Sizin hesabiniz var.");
            return;
        } else {
            db.register_user_pass_addDB(ad, soyad, f_name, username, password);
            System.out.println("Hesabiniz ugurla yaradildi.");
        }
    }


}

