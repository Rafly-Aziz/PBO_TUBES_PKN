package Runing_Class_Mahasiswa;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class M_Pengajuan_Proposal extends Abstrak_TUBES_PBO {

    @Override
    public void Start(Stage primaryStage) {
        primaryStage.setTitle("Form Biodata");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(12);
        grid.setVgap(12);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Scene scene = new Scene(grid, 650, 350);
        primaryStage.setScene(scene);

        Text scenetitle = new Text("ISI FORM BIODATA PENGAJUAN PROPOSAL!");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 16));
        grid.add(scenetitle, 0, 0, 2, 1);

        //Label
        Label nim = new Label("NIM");
        grid.add(nim, 0, 1);
        TextField NimInputField = new TextField();
        grid.add(NimInputField, 1, 1, 4, 1);

        Label nama = new Label("NAMA");
        grid.add(nama, 0, 2);
        TextField NamaInputField = new TextField();
        grid.add(NamaInputField, 1, 2, 4, 1);

        Label fakultas = new Label("FAKULTAS");
        grid.add(fakultas, 0, 3);
        //RadioButton
        RadioButton fai = new RadioButton("FAI");
        grid.add(fai, 1, 3);
        RadioButton feb = new RadioButton("FEB");
        grid.add(feb, 2, 3);
        RadioButton fh = new RadioButton("FH");
        grid.add(fh, 3, 3);
        RadioButton fisip = new RadioButton("FISIP");
        grid.add(fisip, 4, 3);
        RadioButton fkip = new RadioButton("FKIP");
        grid.add(fkip, 5, 3);
        RadioButton fk = new RadioButton("FK");
        grid.add(fk, 6, 3);
        RadioButton fpp = new RadioButton("FPP");
        grid.add(fpp, 7, 3);
        RadioButton fik = new RadioButton("FIK");
        grid.add(fik, 8, 3);
        RadioButton fp = new RadioButton("FP");
        grid.add(fp, 9, 3);
        RadioButton ft = new RadioButton("FT");
        grid.add(ft, 10, 3);

        ToggleGroup size = new ToggleGroup();
        fai.setToggleGroup(size);
        feb.setToggleGroup(size);
        fh.setToggleGroup(size);
        fisip.setToggleGroup(size);
        fkip.setToggleGroup(size);
        fk.setToggleGroup(size);
        fpp.setToggleGroup(size);
        fik.setToggleGroup(size);
        fp.setToggleGroup(size);
        ft.setToggleGroup(size);


        Label jurusan = new Label("JURUSAN");
        grid.add(jurusan, 0, 4);
        TextField JurusanInputField = new TextField();
        grid.add(JurusanInputField, 1, 4, 4, 1);

        Label alamat = new Label("ALAMAT");
        grid.add(alamat, 0, 5);
        TextField AlamatInputField = new TextField();
        grid.add(AlamatInputField, 1, 5, 4, 1);

        Label kota = new Label("KOTA");
        grid.add(kota, 0, 6);
        TextField KotaInputField = new TextField();
        grid.add(KotaInputField, 1, 6, 4, 1);

        Label hobby = new Label("PENGALAMAN");
        grid.add(hobby, 0, 7);
        TextField HobbyInputField = new TextField();
        grid.add(HobbyInputField, 1, 7, 4, 1);

        Button BtnKembali = new Button("Kembali");
        Button btn = new Button("Create");
        HBox HbBtn = new HBox(10);

        HbBtn.getChildren().add(btn);
        grid.add(HbBtn, 1, 8, 4, 1);
        BtnKembali.setOnAction(e->Kembali(primaryStage));
        grid.add(BtnKembali,2,8,4,1);

        btn.setOnAction(event -> {
            String faculty = null;
            if (fai.isSelected()) {
                faculty = fai.getText();
            } else if (feb.isSelected()) {
                faculty = feb.getText();
            } else if (fh.isSelected()) {
                faculty = fh.getText();
            } else if (fisip.isSelected()) {
                faculty = fisip.getText();
            } else if (fkip.isSelected()) {
                faculty = fkip.getText();
            } else if (fk.isSelected()) {
                faculty = fk.getText();
            } else if (fpp.isSelected()) {
                faculty = fpp.getText();
            } else if (fik.isSelected()) {
                faculty = fik.getText();
            } else if (fp.isSelected()) {
                faculty = fp.getText();
            } else if (ft.isSelected()) {
                faculty = ft.getText();
            } else {
                systemWarning("Warning", "Fakultas Kosong", "Fakultas Harus diisi!!");
            }
            Pengajuan_Proposal_2 newWindow = new Pengajuan_Proposal_2(NimInputField.getText(), NamaInputField.getText(), faculty, JurusanInputField.getText(), AlamatInputField.getText(), KotaInputField.getText(), HobbyInputField.getText());
            NimInputField.setDisable(true);
            NamaInputField.setDisable(true);
            JurusanInputField.setDisable(true);
            AlamatInputField.setDisable(true);
            KotaInputField.setDisable(true);
            HobbyInputField.setDisable(true);
        });
        primaryStage.show();
    }

    void Kembali(Stage primaryStage){
        M_Menu_Mahasiswaa A = new M_Menu_Mahasiswaa();
        A.Start(primaryStage);
    }
    public void systemWarning(String title, String header, String isi) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(isi);
        alert.showAndWait();
    }
    static class Pengajuan_Proposal_2 {
        public Pengajuan_Proposal_2 (String NIM, String NAMA, String FAKULTAS, String JURUSAN, String ALAMAT, String KOTA, String HOBBY){

            GridPane gridPane = new GridPane();
            gridPane.setAlignment(Pos.CENTER);
            gridPane.setHgap(10);
            gridPane.setVgap(10);

            Text judulTxt = new Text("Data Diri PKN");
            judulTxt.setFont(Font.font("Tahoma", FontWeight.NORMAL,20));
            gridPane.add(judulTxt,0,0);

            Label nimm = new Label("NIM");
            Label namaa = new Label("NAMA");
            Label fakultass = new Label("FAKULTAS");
            Label jurusann = new Label("JURUSAN");
            Label alamatt = new Label("ALAMAT");
            Label kotaa = new Label("KOTA");
            Label hobbyy = new Label("PENGALAMAN");

            gridPane.add(nimm,0,1);
            gridPane.add(namaa,0,2);
            gridPane.add(fakultass,0,3);
            gridPane.add(jurusann,0,4);
            gridPane.add(alamatt,0,5);
            gridPane.add(kotaa,0,6);
            gridPane.add(hobbyy,0,7);

            Label nimLb1 = new Label(NIM);
            Label namaaLb1 = new Label(NAMA);
            Label fakultassLb1 = new Label(FAKULTAS);
            Label jurusannLb1 = new Label(JURUSAN);
            Label alamattLb1 = new Label(ALAMAT);
            Label kotaaLb1 = new Label(KOTA);
            Label hobbyyLb1 = new Label(HOBBY);

            gridPane.add(nimLb1,1,1);
            gridPane.add(namaaLb1,1,2);
            gridPane.add(fakultassLb1,1,3);
            gridPane.add(jurusannLb1,1,4);
            gridPane.add(alamattLb1,1,5);
            gridPane.add(kotaaLb1,1,6);
            gridPane.add(hobbyyLb1,1,7);

            Scene scene = new Scene(gridPane,300,300);
            Stage secondStage = new Stage();
            secondStage.setTitle("Tampilan Data Mahasiswa");
            secondStage.setScene(scene);
            secondStage.show();
        }
    }
}
