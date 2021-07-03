package Runing_Class_Mahasiswa;

import Admin.Hasil;
import Dosen.Gui_DataMahasiswa;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class login extends Abstrak_TUBES_PBO {

    private final Label LblUsername = new Label("Masukan Username :");
    private final Label LblPasword = new Label("Masukan Pasword :");
    private final Label LblPilih = new Label("Status anda :");

    private final TextField TxtUsername = new TextField();
    private final PasswordField TxtPasword = new PasswordField();

    GridPane gp = new GridPane();

    void gui() {
        ToggleGroup size = new ToggleGroup();
        mahasiswa.setToggleGroup(size);
        dosen.setToggleGroup(size);
        admin.setToggleGroup(size);
        TxtUsername.setMinWidth(100);
        TxtPasword.setMinWidth(100);

        HBox hb = new HBox();
        hb.getChildren().addAll(mahasiswa, dosen, admin);
        gp.setVgap(10);
        gp.setHgap(20);
        gp.add(LblUsername,0,1); gp.add(TxtUsername, 1, 1);
        gp.add(LblPasword,0,2); gp.add(TxtPasword,1,2);
        gp.add(LblPilih,0,3); gp.add(hb,1,3);
        gp.add(BtnKetentuan,0,5);
        gp.add(BtnLogin,1,5);
    }

    RadioButton mahasiswa = new RadioButton("mahasiswa");
    RadioButton dosen = new RadioButton("dosen");
    RadioButton admin = new RadioButton("admin");

    Button BtnLogin = new Button("Login");
    private TextField getTxtUsername() { return TxtUsername; }

    void Login(Stage Masuk) {
            if (mahasiswa.isSelected()) {
                JOptionPane.showMessageDialog(null, "Username dan Password Sesuai ketentuan ( Benar )");
                int K_U_Mahasiswa = 10370311;
                K_U_Mahasiswa = Integer.parseInt(getTxtUsername().getText()) + K_U_Mahasiswa;
                getTxtUsername().setText(String.valueOf(K_U_Mahasiswa));
                M_Menu_Mahasiswaa B = new M_Menu_Mahasiswaa();
                B.Start(Masuk);
            }
            if (dosen.isSelected()) {
                JOptionPane.showMessageDialog(null, "Username dan Password Sesuai ketentuan dan ( Benar )");
                int K_U_Dosen = 071024;
                K_U_Dosen = Integer.parseInt(getTxtUsername().getText()) + K_U_Dosen;
                getTxtUsername().setText(String.valueOf(K_U_Dosen));
                Gui_DataMahasiswa A = new Gui_DataMahasiswa();
                A.Start(Masuk);
            }
            if (admin.isSelected()) {
                Hasil H = new Hasil();
                H.Start(Masuk);
            }
        }
    Button BtnKetentuan = new Button("Ketentuan");
    void Ketentuan(Stage primaryStage){
        Ketentuan A = new Ketentuan();
        A.Start(primaryStage);
    }

    @Override
    public void Start(Stage primaryStage) {
        gui();
        Scene sc = new Scene(gp, 340, 170);
        gp.setPadding(new Insets(10));
        primaryStage.setScene(sc);
        primaryStage.setTitle("Perusahaan KW");
        primaryStage.show();
        BtnLogin.setOnAction(e -> {
            try {
                Login(primaryStage);
            } catch (Exception exception) {
                exception.printStackTrace();
            } });
        BtnKetentuan.setOnAction(e -> Ketentuan(primaryStage));
    }
}
