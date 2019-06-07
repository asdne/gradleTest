package ru.asd.gradletest.gui;

import javafx.application.Application;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

public abstract class AbstractJavaFxApplicationSupport extends Application {

    private static String[] savedArgs;
    protected ConfigurableApplicationContext configurableApplicationContext;

    @Override
    public void init() throws Exception {
        configurableApplicationContext = SpringApplication.run(getClass(), savedArgs);
        configurableApplicationContext.getAutowireCapableBeanFactory().autowireBean(this);
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        configurableApplicationContext.close();
    }

    protected static void launchApp(Class<? extends AbstractJavaFxApplicationSupport> clas, String[] args) {
        AbstractJavaFxApplicationSupport.savedArgs = args;
        Application.launch(clas, args);
    }
}
