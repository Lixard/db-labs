package ru.student.ui;


import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Import;
import ru.student.services.ServicesConfiguration;
import ru.student.ui.configuration.SpringFxmlLoader;

@SpringBootApplication
@Import(ServicesConfiguration.class)
public class App extends Application {

    private ConfigurableApplicationContext context;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void init() {
        context = SpringApplication.run(App.class);
    }

    @Override
    public void start(Stage stage) throws Exception {
        SpringFxmlLoader loader = context.getBean(SpringFxmlLoader.class);
        Parent root = loader.load("/fxmls/start.fxml");
        stage.setTitle("new scene");
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
    }
}
