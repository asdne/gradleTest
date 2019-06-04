package ru.asd.gradletest;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import ru.asd.gradletest.entity.User;
import ru.asd.gradletest.gui.UserTableModel;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import static javax.swing.GroupLayout.Alignment.LEADING;

@SpringBootApplication
public class GradleTest extends JFrame {
    public GradleTest() {

        initUI();
    }

  /*  @Autowired
    private Environment env;*/

    private void initUI() {

        JButton quitButton = new JButton("Quit");

        quitButton.addActionListener((ActionEvent event) -> {
            System.exit(0);
        });

        String text;
        JButton anotherButton = new JButton("другая кнопка");
        anotherButton.addActionListener((ActionEvent event) -> {
            JOptionPane jOptionPane = new JOptionPane("Нажали другую кнопку", JOptionPane.INFORMATION_MESSAGE);
            jOptionPane.createDialog("hjhjh");
            //      setSize(900,500);
            jOptionPane.showMessageDialog(jOptionPane.getParent().getParent(), "<html><h2>Сообщение</h2><br><i>Запись удалена!</i>", "Удаление записи", JOptionPane.INFORMATION_MESSAGE);
        });

        createLayout(anotherButton, quitButton);
//        System.out.println(env.getProperty("title"));
        setTitle("title");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void createLayout(JComponent... arg) {
        JLabel label = new JLabel("Поиск строки :");
        JTextField textField = new JTextField();
        JCheckBox cbCaseSensitive = new JCheckBox("Учет регистра");
        JCheckBox cbWholeWords = new JCheckBox("Целое слово");
        JCheckBox cbBackward = new JCheckBox("Поиск назад");
        JButton btnFind = new JButton("Найти");
        JButton btnCancel = new JButton("Отменить");
        Object[][] ddd1;
        User user = new User("admin", "password");
        User user2 = new User("admin1", "password1");
        User user1 = new User("admin15", "password1555");
        List<User> userList = new ArrayList<>();
        userList.add(user);
        userList.add(user1);
        userList.add(user2);

        TableModel model = new UserTableModel(userList);
        JTable jTable = new JTable(model);
        jTable.setGridColor(Color.LIGHT_GRAY);
        jTable.setShowGrid(true);
         JScrollPane scrollPane = new JScrollPane(jTable);


        cbCaseSensitive.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        cbWholeWords.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        cbBackward.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));


        Container pane = getContentPane();
        GroupLayout gl = new GroupLayout(pane);
        pane.setLayout(gl);
        gl.setAutoCreateGaps(true);
        gl.setAutoCreateContainerGaps(true);
        gl.setHorizontalGroup(gl.createSequentialGroup()
                //    .addGroup(gl.createSequentialGroup()
                .addGroup(gl.createParallelGroup()
                        .addComponent(scrollPane)
                        .addGroup(gl.createSequentialGroup()
                                .addComponent(label).addComponent(arg[0])
                                .addComponent(arg[1])
                        )
                )

        );
        gl.setVerticalGroup(gl.createSequentialGroup()
                .addGroup(gl.createSequentialGroup()
                        .addGroup(gl.createParallelGroup(LEADING)
                                .addComponent(label)
                                .addComponent(arg[0])
                                .addComponent(arg[1])
                        )
                        .addComponent(scrollPane)
                )
        );

        //       gl.setHorizontalGroup(gl.createParallelGroup(LEADING)

//                .addComponent(label)
      /*          .addGroup((gl.createParallelGroup(LEADING)
                        .addComponent(arg[0])
                ))*/
/*
                .addGroup(gl.createParallelGroup(LEADING)
                        .addComponent(textField)
                        .addGroup(gl.createSequentialGroup()
                                .addGroup(gl.createParallelGroup(LEADING)
                                        .addComponent(cbCaseSensitive)
                                        .addComponent(cbBackward))
                                .addGroup(gl.createParallelGroup(LEADING)
                                        .addComponent(cbWholeWords))))
                .addGroup(gl.createParallelGroup(LEADING)
                        .addComponent(btnFind)
                        .addComponent(btnCancel))
*/
        //      );

        //       gl.linkSize(SwingConstants.HORIZONTAL, btnFind, btnCancel);

        // Создание вертикальной группы
/*
        gl.setVerticalGroup(gl.createSequentialGroup()
                .addGroup(gl.createParallelGroup(BASELINE)
                        .addComponent(label)
                        .addComponent(textField)
                        .addComponent(btnFind))
                .addGroup(gl.createParallelGroup(LEADING)
                        .addGroup(gl.createSequentialGroup()
                                .addGroup(gl.createParallelGroup(BASELINE)
                                        .addComponent(cbCaseSensitive)
                                        .addComponent(cbWholeWords))
                                .addGroup(gl.createParallelGroup(BASELINE)
                                        .addComponent(cbBackward)))
                        .addComponent(btnCancel))
        );
*/

        setTitle("Поиск");
        pack();

     /*   gl.setHorizontalGroup(gl.createSequentialGroup()
                .addGroup(gl.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(arg[0])
                        .addComponent(arg[1])
                )

  //              .addComponent(arg[0])
   //             .addComponent(arg[1])
        );

        gl.setVerticalGroup(gl.createSequentialGroup()
    //            .addComponent(arg[0])
     //           .addComponent(arg[1])
        );*/
    }

    public static void main(String[] args) {

        ConfigurableApplicationContext ctx = new SpringApplicationBuilder(GradleTest.class)
                .headless(false).run(args);


        EventQueue.invokeLater(() -> {
            GradleTest ex = ctx.getBean(GradleTest.class);
            ex.setVisible(true);
        });
    }

    private class MyTableModel extends AbstractTableModel {
        @Override
        public int getRowCount() {
            return 3;
        }

        @Override
        public int getColumnCount() {
            return 4;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            return null;
        }
    }
}
