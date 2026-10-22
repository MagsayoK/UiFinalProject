/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import util.PhoneUtils;
import util.PhoneStorage;
/**
 *
 * @author USER
 */
public class PhoneController {
    
    public void addPhone(DefaultTableModel phoneModel) {
        String brand = JOptionPane.showInputDialog(
                null, "Enter phone brand:"
        );

        if (brand == null) return;
        brand = brand.trim();

        String model = JOptionPane.showInputDialog(
                null, "Enter phone model:"
        );

        if (model == null) return;
        model = model.trim();

        String date = JOptionPane.showInputDialog(
                null, "Enter release date (YYYY-MM-DD):"
        );

        if (date == null) return;
        date = date.trim();

        String priceText = JOptionPane.showInputDialog(
                null, "Enter price in PHP:"
        );

        if (priceText == null) return;
        priceText = priceText.trim();

        if (brand.isEmpty() || model.isEmpty()) {
            JOptionPane.showMessageDialog(
                    null, "Brand and model cannot be empty."
            );
            return;
        }

        if (!PhoneUtils.isValidDate(date)) {
            JOptionPane.showMessageDialog(
                    null, "Invalid date. Use YYYY-MM-DD."
            );
            return;
        }

        if (!PhoneUtils.isValidPrice(priceText)) {
            JOptionPane.showMessageDialog(
                    null, "Enter a valid non-negative whole-number price."
            );
            return;
        }

        int price = PhoneUtils.parsePrice(priceText);

        phoneModel.addRow(new Object[]{
            brand, model, date, price
        });

        JOptionPane.showMessageDialog(
                null, "Phone added successfully!"
        );
        PhoneStorage.savePhones(phoneModel);
    }
    

    public void removePhone(
            JTable tblPhones,
            DefaultTableModel phoneModel) {

        int selectedRow = tblPhones.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(
                    null, "Please select a phone to remove."
            );
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(
                null,
                "Are you sure you want to remove this phone in the inventory?",
                "Confirm Removal",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {
            int modelRow =
                    tblPhones.convertRowIndexToModel(selectedRow);

            phoneModel.removeRow(modelRow);
            PhoneStorage.savePhones(phoneModel);

            JOptionPane.showMessageDialog(
                    null, "Phone removed successfully!"
            );
        }
    }
}
