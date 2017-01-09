package io;

import java.util.Scanner;
import java.util.prefs.Preferences;

public class Ex33 {
    public static void main(String[] args) {
        Preferences pref = Preferences.userNodeForPackage(Ex33.class);
        String s = pref.get("base directory","Nothing there");
        System.out.println(s);
        System.out.print("Insert new value: ");
        Scanner sc = new Scanner(System.in);
        pref.put("base directory",sc.nextLine());
        sc.close();
        s = pref.get("base directory","Nothing there");
        System.out.println(s);
    }
}
