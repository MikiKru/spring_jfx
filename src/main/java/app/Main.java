package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Main extends Application {

    private ConfigurableApplicationContext springContext;
    private Parent rootNode;
    private FXMLLoader fxmlLoader;

    @Override
    public void init(){
        // inicjalizacja springa
        springContext = SpringApplication.run(Main.class);
        fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(springContext::getBean);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        fxmlLoader.setLocation(getClass().getResource("/view/login.fxml"));
        rootNode = fxmlLoader.load();
        primaryStage.setTitle("login window");
        primaryStage.setScene(new Scene(rootNode));
        primaryStage.show();
    }

    @Override
    public void stop(){
        System.out.println("Closed");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
