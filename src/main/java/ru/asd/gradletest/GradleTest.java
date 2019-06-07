package ru.asd.gradletest;

import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.env.Environment;
import ru.asd.gradletest.configuration.ConfigurationControllers;
import ru.asd.gradletest.gui.AbstractJavaFxApplicationSupport;

//@Lazy
@SpringBootApplication
public class GradleTest extends AbstractJavaFxApplicationSupport {
    @Autowired
    private Environment env;

    @Autowired
    private ConfigurationControllers.View view;

    public static void main(String[] args) {
        launchApp(GradleTest.class, args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle(env.getProperty("ui.windowTitle"));
        primaryStage.setScene(new Scene(view.getView()));
        primaryStage.setResizable(true);
        primaryStage.centerOnScreen();
        primaryStage.show();
    }
}
