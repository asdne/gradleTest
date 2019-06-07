package ru.asd.gradletest.controller;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.asd.gradletest.entity.User;
import ru.asd.gradletest.service.UserService;

import javax.annotation.PostConstruct;
import java.util.List;

@SuppressWarnings("SpringJavaAutowiringInspection")
public class MainController {

    // Инъекции Spring
    @Autowired
    private UserService userService;

    // Инъекции JavaFX
    @FXML
    private TableView<User> table;
    @FXML
    private TextField login;
    @FXML
    private TextField password;
    /*@FXML
    private TextField roles;
*/
    // Переменные
    private ObservableList<User> data;

    /**
     * Инициализация контроллера от JavaFX.
     * Метод вызывается после того как FXML загрузчик произвел инъекции полей.
     * <p>
     * Обратите внимание, что имя метода <b>обязательно</b> должно быть "initialize",
     * в противном случае, метод не вызовется.
     * <p>
     * Также на этом этапе еще отсутствуют бины спринга
     * и для инициализации лучше использовать метод,
     * описанный аннотацией @PostConstruct.
     * Который вызовется спрингом, после того,
     * как им будут произведены все оставшиеся инъекции.
     * {@link MainController#init()}
     */
    @FXML
    public void initialize() {
    }

    /**
     * На этом этапе уже произведены все возможные инъекции.
     */
    @PostConstruct
    public void init() {
        List<User> users = userService.getUserList();
        data = FXCollections.observableArrayList(users);

        // Добавляем столбцы к таблице
        TableColumn<User, String> idColumn = new TableColumn<>("ID");
        idColumn.setEditable(true);
        idColumn.maxWidthProperty().set(70);
        idColumn.minWidthProperty().set(50);
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<User, String> nameColumn = new TableColumn<>("Login");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("login"));
        nameColumn.maxWidthProperty().set(200);
        nameColumn.minWidthProperty().set(100);

        TableColumn<User, String> passwordColumn = new TableColumn<>("Пароль");
        passwordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));
/*

        TableColumn<User, String> emailColumn = new TableColumn<>("E-mail");
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
*/

        table.getColumns().setAll(idColumn, nameColumn, passwordColumn);

        // Добавляем данные в таблицу
        table.setItems(data);
        table.setEditable(true);
    }

    /**
     * Метод, вызываемый при нажатии на кнопку "Добавить".
     * Привязан к кнопке в FXML файле представления.
     */
    @FXML
    public void addUser() {
        User user = new User(login.getText(), password.getText());
        userService.saveUser(user);
        data.add(user);

        // чистим поля
        login.setText("");
        password.setText("");
        // txtEmail.setText("");
    }
}