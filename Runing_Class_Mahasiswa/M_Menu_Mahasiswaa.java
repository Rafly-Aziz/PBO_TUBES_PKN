package Runing_Class_Mahasiswa;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javax.swing.*;

public class M_Menu_Mahasiswaa extends Abstrak_TUBES_PBO {

    private final Label LblBIO = new Label(" بِسْمِ اللّهِ الرَّحْمَنِ الرَّحِيْ " + "\n\n===BIO DATA PENGAJUAN PROPOSAL===");
    private final Label LblNama  = new Label("Masukan Nama :");
    private final Label LblNik = new Label("Masukan NIK :");
    private final Label LblTTL = new Label("Maukan TTL :");

    private final TextField TxtNama = new TextField();
    private final TextField TxtNik = new TextField();
    private final TextField TxtTTL = new TextField();

    private final Label LblBIOO = new Label("==BIO DATA PENDAFTARAN UJIAN==");
    private final Label LblSemester = new Label("Masukan semester (5/6) :");
    private final Label LblUsia = new Label("masukan usia (<30) :");
    private final Label LblPengalaman = new Label("Masukan pengalaman :");
    private final Label LblPilih = new Label("Silahkan Pilih");

    private final TextField TxtSerjana = new TextField();
    private final TextField TxtUsia = new TextField();
    private final TextField TxtPengalaman = new TextField();

    GridPane gp = new GridPane();

    void Gui(){
        TxtNama.setMinWidth(100);
        TxtNik.setMinWidth(100);
        TxtTTL.setMinWidth(100);
        TxtSerjana.setMinWidth(100);
        TxtUsia.setMinWidth(100);
        TxtPengalaman.setMinWidth(100);

        HBox hb = new HBox();
        hb.getChildren().addAll(P_Proposal,P_Pendaftaran);
        gp.setVgap(10);
        gp.setHgap(20);
        gp.add(LblBIO, 1,0);
        gp.add(LblNama,0,1); gp.add(TxtNama,1,1);
        gp.add(LblNik,0,2); gp.add(TxtNik,1,2);
        gp.add(LblTTL,0,3); gp.add(TxtTTL,1,3);

        gp.add(LblBIOO,1,5);
        gp.add(LblSemester,0,6); gp.add(TxtSerjana,1,6);
        gp.add(LblUsia,0,7); gp.add(TxtUsia,1,7);
        gp.add(LblPengalaman,0,8); gp.add(TxtPengalaman,1,8);
        gp.add(LblPilih,0,9); gp.add(hb,1,9);
        gp.add(Btncek,0,11,2,1);
        gp.add(Btnkembali,0,12,2,1);
    }
    RadioButton P_Proposal= new RadioButton("Pengajuan_Proposal");
    RadioButton P_Pendaftaran = new RadioButton("Pendaftaran_Ujian");
    Button Btncek = new Button("mengecek");
    void mengecek(Stage primaryStage){
        if (P_Proposal.isSelected()){
            JOptionPane.showMessageDialog(null, "SELAMAT ANDA TELAH BERHASIL MASUK KE PENGAJUAN PROPOSAL");
            M_Pengajuan_Proposal A = new M_Pengajuan_Proposal();
            A.Start(primaryStage);
        }
        if (P_Pendaftaran.isSelected()){
            JOptionPane.showMessageDialog(null, "SELAMAT ANDA TELAH BERHASIL MASUK KE PENGAJUAN UJIAN");
            M_Pengajuan_Ujian B = new M_Pengajuan_Ujian();
            B.Start(primaryStage);
        }
    }
    Button Btnkembali = new Button ("Kembali");
    void Kembali(Stage primaryStage) {
        login A = new login();
        A.Start(primaryStage);
    }

    @Override
    public void Start(Stage primaryStage) {
        Gui();
        Scene sc = new Scene(gp,420,450);
        gp.setPadding(new Insets(10));
        primaryStage.setScene(sc);
        primaryStage.setTitle("Perusahaan KW");
        primaryStage.show();
        Btncek.setOnAction(e->mengecek(primaryStage));
        Btnkembali.setOnAction(e->Kembali(primaryStage));
    }
}
