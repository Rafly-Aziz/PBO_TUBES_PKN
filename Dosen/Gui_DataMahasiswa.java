package Dosen;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;
import java.util.Optional;
import java.util.Scanner;
import Runing_Class_Mahasiswa.Abstrak_TUBES_PBO;
import Runing_Class_Mahasiswa.login;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Rafly Aziz
 */
public class Gui_DataMahasiswa extends Abstrak_TUBES_PBO {

    private FileText file;

    TextField txtNim = new TextField();
    TextField txtNama = new TextField();
    TextField txtKota = new TextField();
    TextField txtGrup = new TextField();
    Region reg = new Region();
    Region reg1 = new Region();
    VBox vb1 = new VBox(5);
    HBox hb = new HBox(5);
    HBox hb1 = new HBox(5);
    HBox hb2 = new HBox(5);

    Button btnAdd = new Button("_Add");
    Button btnUpdate = new Button("_Update");
    Button btnDelete = new Button("_Delete");
    Button btnClose = new Button("_Close");
    Button btnSave = new Button("_Save");
    Button btnUndo = new Button("_Undo");
    Button btnCari = new Button("_Cari");
    Button btnFilter = new Button("_Filter");
    Button btnKembali = new Button("Kembali");
    void Kembali(Stage primaryStage) {
        login A = new login();
        A.Start(primaryStage);
    }

    TableView<Mahasiswa> tabelMhs;

    public ObservableList<Mahasiswa> getMahasiswa(){
        ObservableList<Mahasiswa> mhs = FXCollections.observableArrayList();
        this.file = new FileText("src\\Dosen\\Daftar_Mahasiswa.csv");
        String[] arrCsv = this.file.bacaBaris();
        String[] row;
        String nim, nama, kota, grup;
        for(int i=0;i<arrCsv.length;i++){
            row = arrCsv[i].split(", ");
            nim = row[0];
            nama = row[1];
            kota = row[2];
            grup = row[3];
            mhs.add(new Mahasiswa(nim, nama, kota, grup));
        }
        return mhs;
    }

    boolean baru = true;

    void buka(){
        txtNama.setEditable(true);
        txtKota.setEditable(true);
        btnAdd.setDisable(true);
        btnClose.setDisable(true);
        btnKembali.setDisable(true);
        btnDelete.setDisable(true);
        btnUpdate.setDisable(true);
        txtNim.setEditable(true);
        txtGrup.setEditable(true);
        btnCari.setDisable(true);
        btnFilter.setDisable(true);
        btnSave.setDisable(false);
        btnUndo.setDisable(false);
        txtNim.requestFocus();
    }

    void selesai(){
        txtNama.setEditable(false);
        txtKota.setEditable(false);
        txtNim.setEditable(false);
        txtGrup.setEditable(false);
        btnAdd.setDisable(false);
        btnClose.setDisable(false);
        btnKembali.setDisable(false);
        btnDelete.setDisable(false);
        btnUpdate.setDisable(false);
        btnCari.setDisable(false);
        btnFilter.setDisable(false);
        btnSave.setDisable(true);
        btnUndo.setDisable(true);
    }
    public void undo(){
        if(baru == true){
            txtNim.clear();
            txtNama.clear();
            txtKota.clear();
            txtGrup.clear();
        }
        else {
            String nim, nama, kota, grup;
            nim = txtNim.getText();
            nama = txtNama.getText();
            kota = txtKota.getText();
            grup = txtGrup.getText();
            Mahasiswa mhs = new Mahasiswa(nim, nama, kota, grup);
            tabelMhs.getItems().add(mhs);
            txtNim.clear();
            txtNama.clear();
            txtKota.clear();
            txtGrup.clear();
        }
    }

    private void delete(){
        ObservableList<Mahasiswa> pilih, semua;
        semua = tabelMhs.getItems();
        pilih = tabelMhs.getSelectionModel().getSelectedItems();
        Alert alert2 = new Alert(AlertType.CONFIRMATION);
        alert2.setTitle("Konfirmasi");
        alert2.setHeaderText(null);
        alert2.setContentText("Apakah Anda yakin menghapus data tersebut ?");
        Optional<ButtonType> jwb = alert2.showAndWait();
        if(jwb.get() == ButtonType.OK){
            pilih.forEach(semua::remove);
            System.out.println("Data telah dihapus");
        } else {
            System.out.println("Data tidak jadi dihapus");
        }
    }

    private void edit(){
        buka();
        baru = false;
        String id_pasien,nama, alamat, ruangan;
        ObservableList<Mahasiswa> pilih,semua;
        semua = tabelMhs.getItems();
        pilih = tabelMhs.getSelectionModel().getSelectedItems();
        id_pasien = pilih.get(0).getNim();
        nama = pilih.get(0).getNama();
        alamat = pilih.get(0).getKota();
        ruangan = pilih.get(0).getGrup();
        txtNim.setText(id_pasien);
        txtNama.setText(nama);
        txtKota.setText(alamat);
        txtGrup.setText(ruangan);
        pilih.forEach(semua::remove);
        txtNim.requestFocus();
    }

    public String getMhs(){
        String semua = "";
        for (int i = 0;i<this.tabelMhs.getItems().size();i++) {
            String nim = String.valueOf(tabelMhs.getItems().get(i).getNim());
            String nama = String.valueOf(tabelMhs.getItems().get(i).getNama());
            String kota = String.valueOf(tabelMhs.getItems().get(i).getKota());
            String grup = String.valueOf(tabelMhs.getItems().get(i).getGrup());

            semua += nim+ ", " + nama + ", " + kota +", " + grup + "\n";
        }
        return semua;

    }

    private void AlertWarning(){
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Perhatian");
        alert.setHeaderText("Isi Ulang");
        alert.setContentText("Ada Data yang Kosong");
        alert.showAndWait();
    }
    String dialogBox(){
        ObservableList<Mahasiswa> dafMhs = tabelMhs.getItems();
        dafMhs.sort(Comparator.comparing(Mahasiswa::getNim));
        Label lblNim = new Label("Kunci Pencarian");
        TextField txtNim = new TextField();
        txtNim.setMaxWidth(150);
        Button btnCari = new Button("_Find");
        ComboBox cb = new ComboBox();
        cb.getItems().addAll("NIM","Nama");
        cb.setValue("NIM");
        cb.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
                if(cb.getValue().equals("NIM")){
                    txtNim.setPromptText("Tulis NIM yang dicari");
                    dafMhs.sort(Comparator.comparing(Mahasiswa::getNim));
                }else {
                    txtNim.setPromptText("Tulis Nama yang dicari");
                    dafMhs.sort(Comparator.comparing(Mahasiswa::getNama));
                } }
        });
        HBox hb = new HBox(5, lblNim,cb, txtNim, btnCari);
        hb.setPadding(new Insets(15, 10, 10, 10));
        Stage window = new Stage();
        window.setScene(new Scene(hb));
        window.initModality(Modality.APPLICATION_MODAL);
        btnCari.setOnAction(e->window.close());
        window.showAndWait();
        return txtNim.getText();
    }

    void cari(){
        String baca = "",read = "",cari = dialogBox();
        ObservableList<Mahasiswa> dafMhs = tabelMhs.getItems();
        dafMhs.sort(Comparator.comparing(Mahasiswa::getNim));
        int idx, cacah = dafMhs.size(), iterasiBiner = 0, iterasiSekuensial = 0;
        int min, max, mid = 0;
        min = 0; max = cacah - 1;
        while(min <= max){
            iterasiBiner++;
            mid = (min+max)/2;
            baca = dafMhs.get(mid).getNim();
            if(baca.equals(cari) || read.contains(cari))
                break;
            if(baca.compareTo(cari)<0 && read.compareTo(cari)<0)
                min = mid + 1;
            else
                max = mid - 1;
        }
        for(idx=0 ; idx<cacah ; idx++ ){
            iterasiSekuensial++;
            if(dafMhs.get(idx).getNim().equals(cari) ||
                    dafMhs.get(idx).getNama().contains(cari)){
                break;
            }
        }
        if(baca.equals(cari) || idx<cacah){
            tabelMhs.getSelectionModel().select(idx);
            tabelMhs.scrollTo(idx);
            tabelMhs.requestFocus();
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setTitle("Cari");
            a.setHeaderText("Ditemukan");
            a.setContentText("Pencarian Biner: "+ iterasiBiner + " kali literasi"+"\n" +
                    "Pencarian Sekuensial: "+ iterasiSekuensial + " kali literasi");
            a.show();
        } else {
            Alert a = new Alert(Alert.AlertType.WARNING,"Data " + cari + " tidak ditemukan!");
            a.show();
        }
    }

    private void inisialisasiKontrol(){
        txtNim.setMaxWidth(70);
        txtNim.setPromptText("nim");
        txtNama.setMinWidth(150);
        txtNama.setPromptText("nama");
        txtKota.setMaxWidth(90);
        txtKota.setPromptText("kota");
        txtGrup.setMaxWidth(65);
        txtGrup.setPromptText("grup");

        hb.getChildren().addAll(txtNim,txtNama,txtKota,txtGrup);
        hb1.getChildren().addAll(btnAdd,btnUpdate,btnDelete,reg,btnSave,btnUndo);
        HBox.setHgrow(reg, Priority.ALWAYS);
        hb2.getChildren().addAll(btnCari,btnFilter,reg1,btnClose,btnKembali);
        HBox.setHgrow(reg1, Priority.ALWAYS);

        hb.setPadding(new Insets(0, 5, 5, 5));
        hb1.setPadding(new Insets(0, 6, 5, 5));
        hb2.setPadding(new Insets(0, 6, 5, 5));
        vb1.getChildren().addAll(tabelMhs,hb,hb1,hb2 );
    }

    public void add(){
        baru = true;
        ObservableList<Mahasiswa> mahasiswa = tabelMhs.getItems();
        String nim, nama, kota, grup;
        nim = txtNim.getText();
        nama = txtNama.getText();
        kota = txtKota.getText();
        grup = txtGrup.getText();
        Mahasiswa mhs = new Mahasiswa(nim, nama, kota, grup);
        int idx, cacah = mahasiswa.size();
        for(idx =0; idx<cacah;idx++){
            if(mahasiswa.get(idx).getNim().compareTo(nim)>0){
                break;
            }
        }
        if(idx<cacah){
            tabelMhs.getItems().add(idx, mhs);
            txtNim.clear();
            txtNama.clear();
            txtKota.clear();
            txtGrup.clear();
        } else {
            tabelMhs.getItems().add(idx, mhs);
            txtNim.clear();
            txtNama.clear();
            txtKota.clear();
            txtGrup.clear();
        }
    }

    void dialogBoxFilter(){
        TextField txtFilter = new TextField();
        txtFilter.setMinWidth(275);
        txtFilter.setPromptText("nim nama kota grup");
        Button btnClose = new Button("Close");
        HBox hb = new HBox(5, txtFilter, btnClose);
        hb.setPadding(new Insets(15, 10, 10, 10));
        Stage window = new Stage();
        window.setScene(new Scene(hb));
        window.initModality(Modality.APPLICATION_MODAL);
        btnClose.setOnAction(e->window.close());
        FilteredList<Mahasiswa> dataTersaring = new FilteredList<>(tabelMhs.getItems(),b -> true);
        txtFilter.textProperty().addListener((observable, oldValue, newValue)->{
            dataTersaring.setPredicate(mhs -> {
                String sumber = mhs.getNim() + mhs.getNama() + mhs.getKota() + mhs.getGrup();
                if(sumber.toLowerCase().contains(newValue.toLowerCase()))
                    return true;
                else
                    return false;
            });
            tabelMhs.setItems(dataTersaring);
        });
        window.setTitle("Filter");
        window.setX(500);
        window.setY(100);
        window.showAndWait();
        dataTersaring.setPredicate(t -> true);
    }


    @Override
    public void Start(Stage primaryStage) {
        btnKembali.setOnAction(e->Kembali(primaryStage));
        this.file = new FileText("src\\Tubes_PBO\\Daftar_Mahasiswa.csv.csv");
        txtNim.setEditable(false);
        txtKota.setEditable(false);
        txtNama.setEditable(false);
        txtGrup.setEditable(false);
        btnSave.setDisable(true);
        btnUndo.setDisable(true);

        TableColumn<Mahasiswa, String> kolNim = new TableColumn("NIM");
        kolNim.setCellValueFactory(new PropertyValueFactory<>("nim"));
        kolNim.setMinWidth(80);
        kolNim.setStyle("-fx-alignment:center");
        TableColumn<Mahasiswa, String> kolNama;
        kolNama = new TableColumn("Nama");
        kolNama.setCellValueFactory(new PropertyValueFactory<>("nama"));
        kolNama.setMinWidth(150);
        kolNama.setStyle("-fx-alignment:center");
        TableColumn<Mahasiswa, String> kolKota = new TableColumn("Kota");
        kolKota.setCellValueFactory(new PropertyValueFactory<>("kota"));
        kolKota.setMinWidth(100);
        kolKota.setStyle("-fx-alignment:center");
        TableColumn<Mahasiswa, String> kolGrup = new TableColumn("Grup");
        kolGrup.setCellValueFactory(new PropertyValueFactory<>("grup"));
        kolGrup.setMinWidth(60);
        kolGrup.setStyle("-fx-alignment:center");

        tabelMhs = new TableView<>();
        tabelMhs.setItems(getMahasiswa());
        tabelMhs.getColumns().addAll(kolNim, kolNama, kolKota, kolGrup);
        inisialisasiKontrol();
        primaryStage.setScene(new Scene(vb1, 550,510));
        primaryStage.setTitle("Daftar Mahasiswa");
        primaryStage.show();

        btnAdd.setOnAction((event) -> {
            buka();
        });
        btnSave.setOnAction((event) -> {
            if(txtNim.getText().isEmpty()|txtNama.getText().isEmpty()|txtKota.getText().isEmpty()
                    | txtGrup.getText().isEmpty()){
                AlertWarning();
                txtNim.clear();
                txtNama.clear();
                txtKota.clear();
                txtGrup.clear();
                txtNim.requestFocus();
            }else{
                selesai();
                add(); }
        });
        btnUndo.setOnAction((event) -> {
            undo();
            selesai();
        });
        btnCari.setOnAction((event) -> {
            cari();
        });
        btnFilter.setOnAction((event) -> {
            dialogBoxFilter();
        });
        btnDelete.setOnAction(e->delete());
        btnUpdate.setOnAction((event) -> {
            edit();
        });
        btnClose.setOnAction((event) -> {
            this.file.tulis(this.getMhs());
            System.exit(0);
        });
    }

    public class Mahasiswa {
        private String nim;
        private String nama;
        private String kota;
        private String grup;

        public Mahasiswa(String nim, String nama, String kota, String grup) {
            this.nim = nim;
            this.nama = nama;
            this.kota = kota;
            this.grup = grup;
        }
        public String getNim() {
            return nim;
        }
        public void setNim(String nim) {
            this.nim = nim;
        }
        public String getNama() {
            return nama;
        }
        public void setNama(String nama) {
            this.nama = nama;
        }
        public String getKota() {
            return kota;
        }
        public void setKota() {
            this.kota = kota;
        }
        public String getGrup() {
            return grup;
        }
        public void setGrup() {
            this.grup = grup;
        }
    }

    public class FileText {
        private String namaFile;

        FileText(String namaFile){
            this.namaFile=namaFile;
        }
        public void tulis(String teks){
            try{
                FileWriter myWriter = new FileWriter(this.namaFile);
                myWriter.write(teks);
                myWriter.close();
            }catch (IOException e) {
                System.out.println("An error occurred."+e.getMessage());
            }
        }
        public String baca(){
            String hasil ="";
            try {
                File myFile = new File(this.namaFile);
                Scanner fileReader = new Scanner(myFile);
                while(fileReader.hasNextLine()){
                    hasil = hasil + fileReader.nextLine();
                }
            } catch (FileNotFoundException e) {
                System.out.println("File : "+this.namaFile+" tidak ada!");
            }
            return hasil;
        }
        public String[] bacaBaris(){
            String s = "";
            try {
                File myObj = new File(namaFile);
                Scanner myReader = new Scanner(myObj);
                while (myReader.hasNextLine()) {
                    s = s + myReader.nextLine() + "\n";
                }
            } catch (FileNotFoundException e) {
                System.out.println("File : "+this.namaFile);
            }
            return s.split("\n");
        }
    }


}

