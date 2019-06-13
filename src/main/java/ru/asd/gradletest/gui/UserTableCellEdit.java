package ru.asd.gradletest.gui;

import javax.swing.*;
import java.awt.*;

public class UserTableCellEdit extends AbstractCellEditor {
    private JEditorPane editor;
public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected,int  row, int column){
    editor.setText(value.toString());
    return editor;

}

    @Override
    public Object getCellEditorValue() {
        return editor.getText();
    }
}
