package ru.asd.gradletest.gui;

import org.springframework.beans.factory.annotation.Autowired;
import ru.asd.gradletest.entity.User;
import ru.asd.gradletest.service.UserService;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserTableModel implements TableModel {
    @Autowired
    private UserService userService;

    private Set<TableModelListener> listeners = new HashSet<>();
    @Autowired
    private List<User> userList;

    public UserTableModel() {
        //     this.userList = userList;
        this.userList = userService.getUserList();
    }

    @Override
    public void addTableModelListener(TableModelListener listener) {
        listeners.add(listener);
    }

    @Override
    public int getRowCount() {
        return userList.size();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return "login";
            case 1:
                return "Пароль";
        }
        return "";
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return User.class;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        User curUser = userList.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return curUser.getLogin();
            case 1:
                return curUser.getPassword();
        }
        return null;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

    }


    @Override
    public void removeTableModelListener(TableModelListener l) {
        listeners.remove(l);
    }
}
