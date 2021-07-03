package Runing_Class_Mahasiswa;

import javafx.application.Application;
import javafx.stage.Stage;

public class A_Main_Class_Utama extends Application {
    public static void main(String[] args){ launch(args); }

    @Override
    public void start(Stage primaryStage) {
        login A = new login();
        A.Start(primaryStage);
    }
}