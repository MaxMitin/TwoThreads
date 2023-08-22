package com.example.twothreads;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.Callback;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class HelloController {

    @FXML
    Button sender1, sender2;

    ObservableList<String> messages = FXCollections.observableArrayList();
    ObservableList<String> messages2 = javafx.collections.FXCollections.synchronizedObservableList(messages);

    BlockingQueue<String> mesQueue = new LinkedBlockingQueue<>();

    @FXML
    TableView<String> table;

    public void initialize()
    {
        MyMessage worker1 = new MyMessage(10, "1-ый Генератор", messages2);
        MyMessage worker2 = new MyMessage(20, "2-ой Генератор", messages2);
        sender1.setOnAction(actionEvent -> worker1.start());
        sender2.setOnAction(actionEvent -> worker2.start());

        messages2.addListener((ListChangeListener<String>) change -> {
            while(change.next())
                if(change.wasAdded())
                    for (String mes : change.getAddedSubList() ) {
                        System.out.println(mes);
                        mesQueue.add(mes);
                    }
        });

        initTable();
    }

    private void initTable() {
        table.setItems(messages2);

        TableColumn<String, String> col1 = new TableColumn<>("Отправитель");
        col1.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<String, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<String, String> x) {
                return new SimpleStringProperty(x.getValue());
            }
        });
        table.getColumns().add(col1);

        TableColumn<String, String> col2 = new TableColumn<>("Сообщение");
        col1.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<String, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<String, String> y) {
                return new SimpleStringProperty(y.getValue());
            }
        });
        table.getColumns().add(col2);

        TableColumn<String, String> col3 = new TableColumn<>("Время");
        col1.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<String, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<String, String> p) {
                return new SimpleStringProperty(p.getValue());
            }
        });
        table.getColumns().add(col3);
    }

}