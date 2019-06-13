package ru.asd.gradletest.controller;


import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.Callback;
import org.springframework.beans.factory.annotation.Autowired;
import ru.asd.gradletest.entity.User;
import ru.asd.gradletest.entity.UserRole;
import ru.asd.gradletest.service.UserService;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SuppressWarnings("SpringJavaAutowiringInspection")
public class MainController {

    // Инъекции Spring
    @Autowired
    private UserService userService;

    // Инъекции JavaFX

    @FXML
    private TableView<User> table;
    @FXML
    private TableColumn<User, Long> id;
    @FXML
    private TableColumn<User, String> login;
    @FXML
    private TableColumn<User, String> password;
    @FXML
    private TableColumn<User, UserRole> roles;
    @FXML
    private TextField logined;
    @FXML
    private TextField passworded;
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
        table.setEditable(true);
        // Добавляем столбцы к таблице
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        //     TableColumn login = new TableColumn("Login");
        login.setCellValueFactory(
                new PropertyValueFactory<User, String>("login"));

        login.setCellFactory(TextFieldTableCell.forTableColumn());

        login.setEditable(true);

        login.setOnEditCommit((TableColumn.CellEditEvent<User, String> event) -> {
            TablePosition<User, String> position = event.getTablePosition();
            String newLogin = event.getNewValue();
            int row = position.getRow();
            User user = event.getTableView().getItems().get(row);
            user.setLogin(newLogin);
            userService.saveUser(user);
        });
        password.setCellValueFactory(new PropertyValueFactory<>("password"));

        ObservableList<UserRole> userRoleList = FXCollections.observableArrayList(new UserRole("ROLE_USER"),new UserRole("ROLE_ADMIN"));
/* вот здесь не понимаю как роли считать
в сете roles они в итоге получены, а вот что делать дальше с ними не пойму пока
 */
       roles.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<User, UserRole>, ObservableValue<UserRole>>() {
           @Override
           public ObservableValue<UserRole> call(TableColumn.CellDataFeatures<User, UserRole> param) {
               Set<UserRole> roles= new HashSet<>();
               User user = param.getValue();
               roles=user.getRoles();
               return null;
           }
       });
/*

*/
        roles.setCellFactory(ComboBoxTableCell.forTableColumn());

        // Добавляем данные в таблицу
        table.setItems(data);
    }

    /**
     * Метод, вызываемый при нажатии на кнопку "Добавить".
     * Привязан к кнопке в FXML файле представления.
     */
    @FXML
    public void addUser() {
        User user = new User(logined.getText(), passworded.getText());
        userService.saveUser(user);
        data.add(user);

        // чистим поля
        logined.setText("");
        passworded.setText("");
        // txtEmail.setText("");
    }
}