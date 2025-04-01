import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList; // Import ArrayList
import java.util.Random;

public class SilentSymbols extends Application {

    private ArrayList<String> imagePaths; // Use ArrayList to hold image paths
    private Random random; // Random object for selecting images

    @Override
    public void start(Stage primaryStage) {
        // Initialize the random object and load images
        random = new Random();
        imagePaths = loadImagesFromFolder("C:/Users/Administrator/Object Oriented Programming/SilentSymbols/ASL_Images");

        // Create buttons
        Button easyBtn = new Button("Easy");
        Button hardBtn = new Button("Hard");
        Label title = new Label("Welcome to Silent Symbols!");
        

        // Set actions for buttons
        easyBtn.setOnAction(event -> showEasyScene(primaryStage));
        hardBtn.setOnAction(event -> showHardScene(primaryStage));

        // Create a layout for the main scene
        Pane mainLayout = new Pane();
        easyBtn.setLayoutX(75); // X position for Easy button
        easyBtn.setLayoutY(200); // Y position for Easy button
        hardBtn.setLayoutX(175); // X position for Hard button
        hardBtn.setLayoutY(200); // Y position for Hard button

        // Add buttons to the main layout
        mainLayout.getChildren().addAll(easyBtn, hardBtn);

        // Create a scene with the layout
        Scene mainScene = new Scene(mainLayout, 300, 400);

        // Set the title and scene for the primary stage
        primaryStage.setTitle("Select Difficulty");
        primaryStage.setScene(mainScene);
        primaryStage.show();
    }

    private void showEasyScene(Stage primaryStage) {
        // Create a new scene for the Easy difficulty
        Pane easyLayout = new Pane();

        String randomImagePathEasy = getRandomImagePath();
        Image imageEasy = new Image("file:///" + randomImagePathEasy);
        ImageView imageViewEasy = new ImageView(imageEasy);
        imageViewEasy.setFitWidth(50); // Set the width of the image
        imageViewEasy.setPreserveRatio(true); // Preserve the aspect ratio

        imageViewEasy.setLayoutX(100);
        imageViewEasy.setLayoutY(100);

        TextField userInput = new TextField();
        userInput.setLayoutX(50); // X position for the text field
        userInput.setLayoutY(160); // Y position for the text field
        userInput.setPrefWidth(200); // Set preferred width for the text field

        // Create a submit button
        Button submitButton = new Button("Submit");
        submitButton.setLayoutX(50); // X position for the submit button
        submitButton.setLayoutY(190); // Y position for the submit button

        Button easyButton = new Button("You selected Easy!");
        easyButton.setLayoutX(50); // X position for Easy button
        easyButton.setLayoutY(50); // Y position for Easy button

        Label displayLabel = new Label();
        displayLabel.setLayoutX(50);
        displayLabel.setLayoutY(220);

        submitButton.setOnAction(event -> {
            String inputText = userInput.getText();
            displayLabel.setText("You entered: " +inputText);
        });

        easyLayout.getChildren().addAll(easyButton, imageViewEasy, userInput, submitButton, displayLabel);
        
        Scene easyScene = new Scene(easyLayout, 300, 400);
        primaryStage.setScene(easyScene);
    }

    private void showHardScene(Stage primaryStage) {
        // Create a new scene for the Hard difficulty using a Pane
        Pane hardLayout = new Pane();

        // Load random images from the list
        String randomImagePath1 = getRandomImagePath();
        Image image1 = new Image("file:///" + randomImagePath1);
        ImageView imageView1 = new ImageView(image1);
        imageView1.setFitWidth(50); // Set the width of the image
        imageView1.setPreserveRatio(true); // Preserve the aspect ratio

        String randomImagePath2 = getRandomImagePath();
        Image image2 = new Image("file:///" + randomImagePath2);
        ImageView imageView2 = new ImageView(image2);
        imageView2.setFitWidth(50);
        imageView2.setPreserveRatio(true);

        String randomImagePath3 = getRandomImagePath();
        Image image3 = new Image("file:///" + randomImagePath3);
        ImageView imageView3 = new ImageView(image3);
        imageView3.setFitWidth(50);
        imageView3.setPreserveRatio(true);

        String randomImagePath4 = getRandomImagePath();
        Image image4 = new Image("file:///" + randomImagePath4);
        ImageView imageView4 = new ImageView(image4);
        imageView4.setFitWidth(50);
        imageView4.setPreserveRatio(true);

        // Set the position of the images
        imageView1.setLayoutX(50); // X position for image 1
        imageView1.setLayoutY(50); // Y position for image 1

        imageView2.setLayoutX(120); // X position for image 2 (next to image 1)
        imageView2.setLayoutY(50); // Y position for image 2

        imageView3.setLayoutX(190); // X position for image 3 (next to image 2)
        imageView3.setLayoutY(50); // Y position for image 3

        imageView4.setLayoutX(260 ); // X position for image 4 (next to image 3)
        imageView4.setLayoutY(50); // Y position for image 4

        // Add a button to indicate the selection
        Button hardButton = new Button("You selected Hard!");
        hardButton.setLayoutX(50); // X position for the button
        hardButton.setLayoutY(120); // Y position for the button

        // Create a TextField for user input
        TextField userInput = new TextField();
        userInput.setLayoutX(50); // X position for the text field
        userInput.setLayoutY(160); // Y position for the text field
        userInput.setPrefWidth(200); // Set preferred width for the text field

        // Create a submit button
        Button submitButton = new Button("Submit");
        submitButton.setLayoutX(50); // X position for the submit button
        submitButton.setLayoutY(190); // Y position for the submit button

        Label displayLabel = new Label();
        displayLabel.setLayoutX(50);
        displayLabel.setLayoutY(220);

        submitButton.setOnAction(event -> {
            String inputText = userInput.getText();
            displayLabel.setText("You entered: " +inputText);
        });

        // Add all components to the hard layout
        hardLayout.getChildren().addAll(imageView1, imageView2, imageView3, imageView4, hardButton, userInput, submitButton, displayLabel);

        // Create a scene for the Hard difficulty
        Scene hardScene = new Scene(hardLayout, 300, 400);
        primaryStage.setScene(hardScene);
    }

    private String getRandomImagePath() {
        // Select a random image path from the ArrayList
        return imagePaths.get(random.nextInt(imagePaths.size()));
    }

    private ArrayList<String> loadImagesFromFolder(String folderPath) {
        ArrayList<String> imagePaths = new ArrayList<>();
        File folder = new File(folderPath);
        File[] files = folder.listFiles((dir, name) -> name.toLowerCase().endsWith(".png") || name.toLowerCase().endsWith(".jpg") || name.toLowerCase().endsWith(".jpeg"));
        if (files != null) {
            for (File file : files) {
                imagePaths.add(file.getAbsolutePath());
            }
        }
        return imagePaths;
    }

    public static void main(String[] args) {
        launch(args);
    }
}