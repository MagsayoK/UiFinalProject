/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author USER
 */
public class PhoneStorage {
    
    private static final Path FILE =
            Paths.get("phones.csv").toAbsolutePath();

    private PhoneStorage() {
    }

    public static void savePhones(DefaultTableModel model) {
        try (BufferedWriter writer = Files.newBufferedWriter(
                FILE, StandardCharsets.UTF_8)) {

            for (int row = 0; row < model.getRowCount(); row++) {
                String brand = csv(model.getValueAt(row, 0));
                String phoneModel = csv(model.getValueAt(row, 1));
                String date = csv(model.getValueAt(row, 2));
                String price = csv(model.getValueAt(row, 3));

                writer.write(
                    brand + "," + phoneModel + ","
                    + date + "," + price
                );
                writer.newLine();
            }

        } catch (IOException e) {
            System.err.println("Could not save phones: " + e.getMessage());
        }
    }

    public static boolean loadPhones(DefaultTableModel model) {
        if (!Files.exists(FILE)) {
            return false;
        }

        try (BufferedReader reader = Files.newBufferedReader(
                FILE, StandardCharsets.UTF_8)) {

            model.setRowCount(0);

            String line;

            while ((line = reader.readLine()) != null) {
                String[] data = parseCsv(line);

                if (data.length != 4) {
                    continue;
                }

                try {
                    int price = Integer.parseInt(data[3]);

                    model.addRow(new Object[]{
                        data[0], data[1], data[2], price
                    });
                } catch (NumberFormatException e) {
                    System.err.println("Skipping invalid phone price.");
                }
            }

            return true;

        } catch (IOException e) {
            System.err.println("Could not load phones: " + e.getMessage());
            return false;
        }
    }

    private static String csv(Object value) {
        String text = String.valueOf(value);
        return "\"" + text.replace("\"", "\"\"") + "\"";
    }

    private static String[] parseCsv(String line) {
        java.util.List<String> values = new java.util.ArrayList<>();
        StringBuilder value = new StringBuilder();
        boolean quoted = false;

        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);

            if (c == '"') {
                if (quoted && i + 1 < line.length()
                        && line.charAt(i + 1) == '"') {
                    value.append('"');
                    i++;
                } else {
                    quoted = !quoted;
                }
            } else if (c == ',' && !quoted) {
                values.add(value.toString());
                value.setLength(0);
            } else {
                value.append(c);
            }
        }

        values.add(value.toString());
        return values.toArray(new String[0]);
    }
    
}
