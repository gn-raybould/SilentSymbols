import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class SilentSymbols extends Application {
    @Override
    public void start(Stage primaryStage) {
        // Create a button
        Button btn = new Button("Click Me!");
        
        // Set an action for the button
        btn.setOnAction(event -> System.out.println("Hello, World!"));

        // Create a layout and add the button to it
        StackPane root = new StackPane();
        root.getChildren().add(btn);

        // Create a scene with the layout
        Scene scene = new Scene(root, 300, 200);

        // Set the title of the window
        primaryStage.setTitle("Silent Symbols Application");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args); // This line launches the JavaFX application
    }
}