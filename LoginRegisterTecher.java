package org.example;

import java.util.Scanner;

public class LoginRegisterTecher {
    static Scanner scanner = new Scanner(System.in);
    static DBDAOTeacher dt = new DBDAOTeacher();

    static void login() {
        if (dt.user_pass_check()) {
            System.out.println("""
                    >1.TELEBELER<
                    >2.FENLER<
                    >3.DERSLIKLER<
                    >4.DAVAMIYET<
                    >5.SINAQ BALLARI<
                    """);
            String secim = scanner.next();
            String secim1, secim2, secim3, secim4, secim5;
            secim1 = "1";
            secim2 = "2";
            secim3 = "3";
            secim4 = "4";
            secim5 = "5";
            if (secim.equals(secim1)) {
                dt.all_students();
            } else if (secim.equals(secim2)) {
                dt.fenleri_goster();
            } else if (secim.equals(secim3)) {
                System.out.println("""
                        >1.Derslikleri gor<
                        >2.Derslik elave et<
                        """);
                String input = scanner.next();
                String input1, input2;
                input1 = "1";
                input2 = "2";
                if (input.equals(input1)) {
                    dt.Derslerik_linkini_goster();
                } else if (input.equals(input2)) {
                    dt.Derslerik_linkini_ELAVE_ET();
                } else {
                    System.out.println("Bele bir emeliyat yoxdur");
                }
            } else if (secim.equals(secim4)) {
                System.out.println("""
                        >1.Davamiyetleri gor<
                        >2.Davamiyet elave et<
                        """);
                String input = scanner.next();
                String input1, input2;
                input1 = "1";
                input2 = "2";
                if (input.equals(input1)) {
                    dt.davamiyetleri_goster();
                } else if (input.equals(input2)) {
                    dt.davamiyet_qeydi();
                } else {
                    System.out.println("Bele bir emeliyat yoxdur");
                }
            } else if (secim.equals(secim5)) {
                System.out.println("""
                        >1.Sinaq ballarini gor<
                        >2.Sinaq balini elave et<
                        """);
                String input = scanner.next();
                String input1, input2;
                input1 = "1";
                input2 = "2";
                if (input.equals(input1)) {
                    dt.ballari_goster();
                } else if (input.equals(input2)) {
                    dt.bali_daxilET();
                } else {
                    System.out.println("Bele bir emeliyat yoxdur");
                }
            } else {
                System.out.println("Bele bir emeliyat yoxdur");
            }

        } else {
            System.out.println("Zehmet olmasa yeni hesab yaradin.");
            return;
        }
    }

    public static void register() {
        System.out.println("Name: ");
        String ad = scanner.next();
        System.out.println("Surname: ");
        String soyad = scanner.next();
        System.out.println("Usernanme: ");
        String username = scanner.next();
        System.out.println("Password: ");
        String password = scanner.next();
        if (dt.register_user_pass_check(ad, username, password)) {
            System.out.println("Sizin hesabiniz var.");
            return;
        } else {
            dt.register_user_pass_addDB(ad, soyad, username, password);
            System.out.println("Hesabiniz ugurla yaradildi.");
        }
    }

}
