package id.ac.unpas.modul10;

import id.ac.unpas.modul10.controller.MahasiswaController;
import id.ac.unpas.modul10.model.MahasiswaModel;
import id.ac.unpas.modul10.view.MahasiswaView;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MahasiswaView view = new MahasiswaView();
            MahasiswaModel model = new MahasiswaModel();
            new MahasiswaController(model, view);
            view.setVisible(true);
        });
    }
}