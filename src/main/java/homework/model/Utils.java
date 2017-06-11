package homework.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Utils {

    public static void printBorder() {
        System.out.println("-------------------------------------------------------");

    }

    public static Integer provideIntInputStream() {
        return provideIntInputStreamWithMessage("Select choice (confirm Enter): ");
    }


    public static Integer provideIntInputStreamWithMessage(String message) {
        System.out.print(message);
        String line;
        BufferedReader br;
        try {
            br = new BufferedReader(new InputStreamReader(System.in));
            line = br.readLine();
            try {
                return Integer.valueOf(line);
            } catch (NumberFormatException e) {
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String provideStringInputStream(String message) {
        System.out.print(message);
        BufferedReader br;
        try {
            br = new BufferedReader(new InputStreamReader(System.in));
            return deleteSpaces(br.readLine());
        } catch (IOException e) {
            return null;
        }
    }

    public static boolean isValidString(String str) {
        return !(str == null || str.isEmpty() || str.split(" ").length == 0);
    }

    private static String deleteSpaces(String str) {

        String newString = "";

        final String[] split = str.split(" ");

        for (int i = 0; i < split.length; i++) {
            if (isValidString(split[i])) {
                newString += split[i];
                if (i < split.length - 1)
                    newString += " ";
            }
        }
        return newString;
    }

}
