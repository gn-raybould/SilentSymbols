import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class SilentSymbols extends Application {

    private static final double IMAGE_WIDTH = 75;  // Desired width for all images
    private static final double IMAGE_HEIGHT = 75; // Desired height for all images

    private HashMap<String, List<String>> wordBank; // HashMap to hold words and their corresponding image paths
    private List<String> letterBank; // List to hold letters for the Easy mode
    private Random random; // Random object for selecting images
    private String currentWord; // To store the current word for answer checking
    private String currentLetter; // To store the current letter for answer checking

    @Override
    public void start(Stage primaryStage) {
        // Initialize the random object and load the word bank and letter bank
        random = new Random();
        wordBank = loadWordBank(); // Load the word bank from the file
        letterBank = loadLetterBank(); // Load the letter bank from the file

        // Create Labels
        Label difficultyLabel = new Label("Please choose your difficulty");
        Label welcomeLabel = new Label("Welcome to Silent Symbols!");

        // Create buttons
        Button easyBtn = new Button("Easy");
        Button hardBtn = new Button("Hard");

        // Set actions for buttons
        easyBtn.setOnAction(event -> showEasyScene(primaryStage));
        hardBtn.setOnAction(event -> showHardScene(primaryStage));

        // Create a layout for the main scene
        Pane mainLayout = new Pane();
        easyBtn.setLayoutX(75);
        easyBtn.setLayoutY(190);
        hardBtn.setLayoutX(175);
        hardBtn.setLayoutY(190);
        difficultyLabel.setLayoutX(60);
        difficultyLabel.setLayoutY(150);
        welcomeLabel.setLayoutX(35);
        welcomeLabel.setLayoutY(100);
        difficultyLabel.setStyle("-fx-font-size: 15px; -fx-text-fill: blue;");
        welcomeLabel.setStyle("-fx-font-size: 20px; -fx-text-fill: red;");

        // Add buttons to the main layout
        mainLayout.getChildren().addAll(easyBtn, hardBtn, difficultyLabel, welcomeLabel);

        // Create a scene with the layout
        Scene mainScene = new Scene(mainLayout, 300, 400);

        // Set the title and scene for the primary stage
        primaryStage.setTitle("Silent Symbols");
        primaryStage.setScene(mainScene);
        primaryStage.show();
    }

    private void showEasyScene(Stage primaryStage) {
        // Create a new scene for the Easy difficulty
        Pane easyLayout = new Pane();

        // Select a random letter from the letter bank
        currentLetter = letterBank.get(random.nextInt(letterBank.size()));
        String randomImagePathEasy = "ASL_Images/" + currentLetter + ".png"; // Relative path

        // Load the image
        Image imageEasy = new Image(getClass().getClassLoader().getResourceAsStream(randomImagePathEasy));
        if (imageEasy.isError()) {
            // System.out.println("Error loading image: " + randomImagePathEasy); // Removed debugging output
        }

        ImageView imageViewEasy = new ImageView(imageEasy);
        imageViewEasy.setFitWidth(IMAGE_WIDTH); // Set the desired width
        imageViewEasy.setFitHeight(IMAGE_HEIGHT); // Set the desired height
        imageViewEasy.setPreserveRatio(true); // Preserve the aspect ratio

        imageViewEasy.setLayoutX(100);
        imageViewEasy.setLayoutY(100);

        TextField userInput = new TextField();
        userInput.setLayoutX(50);
        userInput.setLayoutY(160);
        userInput.setPrefWidth(200);

        // Create a submit button
        Button submitButton = new Button("Submit");
        submitButton.setLayoutX(100);
        submitButton.setLayoutY(190);

        Label displayLabel = new Label();
        displayLabel.setLayoutX(100);
        displayLabel.setLayoutY(220);

        submitButton.setOnAction(event -> {
            String inputText = userInput.getText();
            if (inputText.equalsIgnoreCase(currentLetter)) {
                displayLabel.setText("Correct!");
            } else {
                displayLabel.setText("Try again!");
            }
        });

        // New Letter button
        Button newLetterButton = new Button("New Letter");
        newLetterButton.setLayoutX(50);
        newLetterButton.setLayoutY(350);
        newLetterButton.setOnAction(event -> {
            showEasyScene(primaryStage); // Refresh the Easy scene with a new letter
        });

        // Back button to return to main menu
        Button backButton = new Button("Back");
        backButton.setLayoutX(200);
        backButton.setLayoutY(350);
        backButton.setOnAction(event -> primaryStage.setScene(createMainScene(primaryStage)));

        easyLayout.getChildren().addAll(imageViewEasy, userInput, submitButton, displayLabel, newLetterButton, backButton);

        Scene easyScene = new Scene(easyLayout, 300, 400);
        primaryStage.setScene(easyScene);
    }

    private void showHardScene(Stage primaryStage) {
        // Create a new scene for the Hard difficulty using a Pane
        Pane hardLayout = new Pane();

        // Select a random word from the word bank
        List<String> words = new ArrayList<>(wordBank.keySet());
        currentWord = words.get(random.nextInt(words.size())); // Store the current word
        List<String> imagePathsForWord = wordBank.get(currentWord);

        // Create ImageViews for each image corresponding to the selected word
        List<ImageView> imageViews = new ArrayList<>();
        for (int i = 0; i < Math.min(4, imagePathsForWord.size()); i++) { // Limit to 4 images
            String imagePath = imagePathsForWord.get(i);
            // System.out.println("Loading image from path: " + imagePath); // Removed debugging output
            Image image = new Image(getClass().getClassLoader().getResourceAsStream(imagePath));
            if (image == null) {
                // System.out.println("Error: Image not found at path: " + imagePath); // Removed debugging output
            } else if (image.isError()) {
                // System.out.println("Error loading image from path: " + imagePath); // Removed debugging output
            }
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(IMAGE_WIDTH); // Set the desired width
            imageView.setFitHeight(IMAGE_HEIGHT); // Set the desired height
            imageView.setPreserveRatio(true); // Preserve the aspect ratio
            imageViews.add(imageView);
        }

        // Layout the images
        for (int i = 0; i < imageViews.size(); i++) {
            imageViews.get(i).setLayoutX(10 + (i * (IMAGE_WIDTH + 10))); // Spacing between images
            imageViews.get(i).setLayoutY(50);
            hardLayout.getChildren().add(imageViews.get(i));
        }

        TextField userInput = new TextField();
        userInput.setLayoutX(50);
        userInput.setLayoutY(160);
        userInput.setPrefWidth(200);

        // Create a submit button
        Button submitButton = new Button("Submit");
        submitButton.setLayoutX(125);
        submitButton.setLayoutY(190);

        Label displayLabel = new Label();
        displayLabel.setLayoutX(125);
        displayLabel.setLayoutY(220);

        submitButton.setOnAction(event -> {
            String inputText = userInput.getText();
            if (inputText.equalsIgnoreCase(currentWord)) {
                displayLabel.setText("Correct!");
            } else {
                displayLabel.setText("Try again!");
            }
        });

        // New Word button
        Button newWordButton = new Button("New Word");
        newWordButton.setLayoutX(50);
        newWordButton.setLayoutY(350);
        newWordButton.setOnAction(event -> {
            showHardScene(primaryStage); // Refresh the Hard scene with a new word
        });

        // Back button to return to main menu
        Button backButton = new Button("Back");
        backButton.setLayoutX(200);
        backButton.setLayoutY(350);
        backButton.setOnAction(event -> primaryStage.setScene(createMainScene(primaryStage)));

        hardLayout.getChildren().addAll(userInput, submitButton, displayLabel, newWordButton, backButton);

        Scene hardScene = new Scene(hardLayout, 300, 400);
        primaryStage.setScene(hardScene);
    }

    private Scene createMainScene(Stage primaryStage) {
        Pane mainLayout = new Pane();

        // Create Labels
        Label difficultyLabel = new Label("Please choose your difficulty");
        Label welcomeLabel = new Label("Welcome to Silent Symbols!");

        // Create buttons
        Button easyBtn = new Button("Easy");
        Button hardBtn = new Button("Hard");

        // Set layout for labels and buttons
        easyBtn.setLayoutX(75);
        easyBtn.setLayoutY(190);
        hardBtn.setLayoutX(175);
        hardBtn.setLayoutY(190);
        difficultyLabel.setLayoutX(60);
        difficultyLabel.setLayoutY(150);
        welcomeLabel.setLayoutX(35);
        welcomeLabel.setLayoutY(100);
        difficultyLabel.setStyle("-fx-font-size: 15px; -fx-text-fill: blue;");
        welcomeLabel.setStyle("-fx-font-size: 20px; -fx-text-fill: red;");

        // Set actions for buttons
        easyBtn.setOnAction(event -> showEasyScene(primaryStage));
        hardBtn.setOnAction(event -> showHardScene(primaryStage));

        // Add labels and buttons to the main layout
        mainLayout.getChildren().addAll(easyBtn, hardBtn, difficultyLabel, welcomeLabel);

        return new Scene(mainLayout, 300, 400);
    }

    private HashMap<String, List<String>> loadWordBank() {
        HashMap<String, List<String>> wordBank = new HashMap<>();
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("wordbank.txt");
             BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            if (inputStream == null) {
                throw new IOException("Resource not found: wordbank.txt");
            }
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                String word = parts[0].trim();
                List<String> images = new ArrayList<>();
                for (int i = 1; i < parts.length; i++) {
                    // Corrected path construction
                    images.add("ASL_Images/" + parts[i].trim() + ".png"); // Assuming images are named by the letter
                }
                wordBank.put(word, images);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return wordBank;
    }

    private List<String> loadLetterBank() {
        List<String> letterBank = new ArrayList<>();
        for (char letter = 'A'; letter <= 'Z'; letter++) {
            letterBank.add(String.valueOf(letter));
        }
        return letterBank;
    }

    public static void main(String[] args) {
        launch(args);
    }
}