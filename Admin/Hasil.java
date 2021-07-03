package Admin;

import Runing_Class_Mahasiswa.Abstrak_TUBES_PBO;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Callback;

public class Hasil extends Abstrak_TUBES_PBO {

    private final TableView<Person> table = new TableView<Person>();
    private final ObservableList<Person> data =
            FXCollections.observableArrayList(
                    new Person("Pelatihan PKN", "07:00", "\t\t\t1","721","Pak Wildan"),
                    new Person("P Disiplin", "08:30", "\t\t\t2","722","Pak Galih"),
                    new Person("P Kesehatan", "09:30", "\t\t\t3","723","Pak Sofyan"),
                    new Person("P Kreatif", "10:30", "\t\t\t4","724","Mister Bayu")
            );
    final HBox hb = new HBox();


    @Override
    public void Start(Stage primaryStage) {
        Scene scene = new Scene(new Group());
        primaryStage.setTitle("PKN");
        primaryStage.setWidth(640);
        primaryStage.setHeight(530);

        final Label label = new Label("MENU ADMIN, UJIAN PKN");
        label.setFont(new Font("Arial", 20));

        table.setEditable(true);
        Callback<TableColumn, TableCell> cellFactory =
                p -> new EditingCell();

        TableColumn mataKuliahCol = new TableColumn("Mata Kuliah");
        mataKuliahCol.setMinWidth(100);
        mataKuliahCol.setCellValueFactory(
                new PropertyValueFactory<Person, String>("mataKuliah"));
        mataKuliahCol.setCellFactory(cellFactory);
        mataKuliahCol.setOnEditCommit(
                (EventHandler<TableColumn.CellEditEvent<Person, String>>) t -> t.getTableView().getItems().get(
                        t.getTablePosition().getRow()).setMataKuliah(t.getNewValue())
        );


        TableColumn waktuCol = new TableColumn("Waktu");
        waktuCol.setMinWidth(100);
        waktuCol.setCellValueFactory(
                new PropertyValueFactory<Person, String>("waktu"));
        waktuCol.setCellFactory(cellFactory);
        waktuCol.setOnEditCommit(
                (EventHandler<TableColumn.CellEditEvent<Person, String>>) t -> t.getTableView().getItems().get(
                        t.getTablePosition().getRow()).setWaktu(t.getNewValue())
        );

        TableColumn GKBCol = new TableColumn("GKB");
        GKBCol.setMinWidth(200);
        GKBCol.setCellValueFactory(
                new PropertyValueFactory<Person, String>("GKB"));
        GKBCol.setCellFactory(cellFactory);
        GKBCol.setOnEditCommit(
                (EventHandler<TableColumn.CellEditEvent<Person, String>>) t -> t.getTableView().getItems().get(
                        t.getTablePosition().getRow()).setGKB(t.getNewValue())
        );

        TableColumn roomCol = new TableColumn("room");
        roomCol.setMinWidth(100);
        roomCol.setCellValueFactory(
                new PropertyValueFactory<Person, String>("room"));
        roomCol.setCellFactory(cellFactory);
        roomCol.setOnEditCommit(
                (EventHandler<TableColumn.CellEditEvent<Person, String>>) t -> t.getTableView().getItems().get(
                        t.getTablePosition().getRow()).setRoom(t.getNewValue())
        );

        TableColumn dosenCol = new TableColumn("dosen");
        dosenCol.setMinWidth(100);
        dosenCol.setCellValueFactory(
                new PropertyValueFactory<Person, String>("dosen"));
        dosenCol.setCellFactory(cellFactory);
        dosenCol.setOnEditCommit(
                (EventHandler<TableColumn.CellEditEvent<Person, String>>) t -> t.getTableView().getItems().get(
                        t.getTablePosition().getRow()).setRoom(t.getNewValue())
        );

        table.setItems(data);
        table.getColumns().addAll(mataKuliahCol, waktuCol, GKBCol, roomCol, dosenCol);

        final TextField addMataKuliah = new TextField();
        addMataKuliah.setPromptText("Mata Kuliah");
        addMataKuliah.setMaxWidth(mataKuliahCol.getPrefWidth());

        final TextField addWaktu = new TextField();
        addWaktu.setMaxWidth(waktuCol.getPrefWidth());
        addWaktu.setPromptText("Waktu");

        final TextField addGKB = new TextField();
        addGKB.setMaxWidth(GKBCol.getPrefWidth());
        addGKB.setPromptText("GKB");

        final TextField addroom = new TextField();
        addroom.setMaxWidth(roomCol.getPrefWidth());
        addroom.setPromptText("Room");

        final TextField adddosen = new TextField();
        adddosen.setMaxWidth(roomCol.getPrefWidth());
        adddosen.setPromptText("Dosen");

        final Button addButton = new Button("Add");
        addButton.setOnAction(e -> {
            data.add(new Person(
                    addMataKuliah.getText(),
                    addWaktu.getText(),
                    addGKB.getText(),
                    addroom.getText(),
                    adddosen.getText()));
            addMataKuliah.clear();
            addWaktu.clear();
            addGKB.clear();
            addroom.clear();
            adddosen.clear();
        });
        final Button delButton = new Button("Delete");
        delButton.setOnAction(e -> {
            Person data = table.getSelectionModel().getSelectedItem();
            table.getItems().remove(data);
        });

        hb.getChildren().addAll(addMataKuliah, addWaktu, addGKB, adddosen, addroom, addButton, delButton);
        hb.setSpacing(3);

        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(label, table, hb);

        ((Group) scene.getRoot()).getChildren().addAll(vbox);

        primaryStage.setScene(scene);
        primaryStage.show();

    }
    public static class Person {

        private final SimpleStringProperty mataKuliah;
        private final SimpleStringProperty waktu;
        private final SimpleStringProperty GKB;
        private final SimpleStringProperty room;
        private final SimpleStringProperty dosen;

        private Person(String fName, String lName, String email, String Room, String Dosen) {
            this.mataKuliah = new SimpleStringProperty(fName);
            this.waktu = new SimpleStringProperty(lName);
            this.GKB = new SimpleStringProperty(email);
            this.room = new SimpleStringProperty(Room);
            this.dosen = new SimpleStringProperty(Dosen);
        }

        public String getMataKuliah() {
            return mataKuliah.get();
        }

        public void setMataKuliah(String mKuliah) {
            mataKuliah.set(mKuliah);
        }

        public String getWaktu() {
            return waktu.get();
        }

        public void setWaktu(String fName) {
            waktu.set(fName);
        }

        public String getGKB() {
            return GKB.get();
        }

        public void setGKB(String fName) {
            GKB.set(fName);
        }

        public String getRoom(){
            return room.get();
        }
        public void setRoom(String Room){
            room.set(Room);
        }

        public String getDosen(){
            return dosen.get();
        }
        public void setDosen(String Dosen){
            dosen.set(Dosen);
        }
    }

}

