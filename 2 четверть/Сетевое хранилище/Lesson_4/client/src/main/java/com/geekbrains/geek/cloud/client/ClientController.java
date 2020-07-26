package com.geekbrains.geek.cloud.client;

import com.geekbrains.geek.cloud.common.ProtoFileSender;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.ResourceBundle;

public class ClientController implements Initializable {
    ClientNetty client;

    @FXML
    ListView<String> clientfilesList;

    @FXML
    ListView<String> serverfilelist;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        client = new ClientNetty(this);
        client.run();
        System.out.println("Подключение к серверу прошло успешно");
        refreshLocalFilesList();
    }

    public void pressOnUploadBtn(ActionEvent actionEvent) {
        if (clientfilesList!=null && clientfilesList.getSelectionModel().getSelectedItem()!=null) {
            try {
                ProtoFileSender.sendFile(Paths.get("client_repository/" + clientfilesList.getSelectionModel().getSelectedItem()), client.getClientChannel(), future -> {
                    if (!future.isSuccess()) {
                        future.cause().printStackTrace();
                    }
                    if (future.isSuccess()) {
                        System.out.println("Файл "+clientfilesList.getSelectionModel().getSelectedItem()+" успешно передан");
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void pressOnDownloadBtn(ActionEvent actionEvent) {
        if (serverfilelist!=null && serverfilelist.getSelectionModel().getSelectedItem()!=null) {
            ProtoFileSender.sendCommand("/download "+serverfilelist.getSelectionModel().getSelectedItem(),client.getClientChannel(), null);
        }
    }

    public void refreshLocalFilesList() {
        updateUI(() -> {
            try {
                clientfilesList.getItems().clear();
                Files.list(Paths.get("client_repository")).map(p -> p.getFileName().toString()).forEach(o -> clientfilesList.getItems().add(o));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void refreshServerFileList(String[] files) {
        updateUI(() -> {
            serverfilelist.getItems().clear();
            Arrays.stream(files).forEach(o -> serverfilelist.getItems().add(o));
        });
    }

    public static void updateUI(Runnable r) {
        if (Platform.isFxApplicationThread()) {
            r.run();
        } else {
            Platform.runLater(r);
        }
    }
}
