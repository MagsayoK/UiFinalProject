/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
/**
 *
 * @author USER
 */
public class PhoneUtils {
    
     private PhoneUtils() {
        // Prevent creating unnecessary utility objects.
    }

    public static boolean isValidDate(String date) {
        try {
            LocalDate.parse(date);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public static boolean isValidPrice(String price) {
        try {
            return Integer.parseInt(price) >= 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static int parsePrice(String price) {
        return Integer.parseInt(price);
    }
    
}
