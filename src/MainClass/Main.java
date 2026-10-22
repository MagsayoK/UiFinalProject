/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MainClass;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import view.PhoneCatalogFrame;
/**
 *
 * @author USER
 */
public class Main {
    
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName()
                );
            } catch (Exception e) {
                e.printStackTrace();
            }

            new PhoneCatalogFrame().setVisible(true);
        });
    }
}
