package Runing_Class_Mahasiswa;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Ketentuan extends Abstrak_TUBES_PBO {
    private final Label LblPersyaratan = new Label("\t\t\t\t\t=== PERSYARATAN ===");
    private final Label LblMahasiswa= new Label("1.\t==Ketentuan Username Mahasiswa==\n"+"\t\tHarus menggunakan Code INFORMATIKA (10370311)\n\n");
    private final Label LblDosen = new Label("2.\t==Ketentuan Username Dosen==\n"+"\t\tHarus menggunakan Code PT UMM (071024)\n\n");
    private final Label LblAdmin = new Label("3.\t==Ketentuan Username Admin==\n"+"\t\tAdmin Tidak memiliki ketentuan ( limited edition )\n\n");
    private final Label LblPasword = new Label("4.\t==Pasword tidak memiliki ketetntuan karena bersifat PRIVASI!!==");
    GridPane gp = new GridPane();
    void gui(){
        gp.setVgap(5);
        gp.setHgap(10);
        gp.add(LblPersyaratan,1,1);
        gp.add(LblMahasiswa,1,2);
        gp.add(LblDosen,1,3);
        gp.add(LblAdmin,1,4);
        gp.add(LblPasword,1,5);
        gp.add(BtnKembali,1,10);
    }
    Button BtnKembali = new Button("Kembali");
    void Kembali(Stage primaryStage){
        login A = new login();
        A.Start(primaryStage);
    }
    @Override
    public void Start(Stage primaryStage) {
        gui();
        Scene sc = new Scene(gp,430,300);
        gp.setPadding(new Insets(10));
        primaryStage.setScene(sc);
        primaryStage.setTitle("Perusahaan KW");
        primaryStage.show();
        BtnKembali.setOnAction(e->Kembali(primaryStage));
    }
}
