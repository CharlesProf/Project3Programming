package fi.tuni.prog3.sisu;

import com.google.gson.*;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.scene.shape.SVGPath;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.concurrent.Task;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * JavaFX Sisu
 */
public class Sisu extends Application {
   static ArrayList <DegreeModule> degreeModules=new ArrayList<>();
   static ArrayList<String> selectedCourses = new ArrayList<>();
    StudentData studentData;
   static int numOfDegreePrograms=5;

    MenuBar menuBar = createMenuBar();
    Button refreshButton = new Button("Refresh");


    @Override
    public void start(Stage stage) {

        // Create the main window layout
        GridPane root1 = new GridPane();
        root1.setPadding(new Insets(20));
        root1.setHgap(10);
        root1.setVgap(10);

        // Set background image
        File backgroundImageFile = new File("background.jpg");
        String backgroundImageUrl = backgroundImageFile.toURI().toString();
        BackgroundImage background = new BackgroundImage(new Image(backgroundImageUrl), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        root1.setBackground(new Background(background));

        // Add UI components
        Label nameLabel = new Label("Name");
        nameLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: grey;");
        nameLabel.setFont(Font.font("Arial", 18));

        TextField nameField = new TextField();
        nameField.setStyle("-fx-background-color: #ffffff; -fx-font-size: 16px; -fx-padding: 5px;");
        GridPane.setHgrow(nameField, Priority.ALWAYS);

        Label numberLabel = new Label("Student Number");
        numberLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: grey;");
        numberLabel.setFont(Font.font("Arial", 18));

        TextField numberField = new TextField();
        numberField.setStyle("-fx-background-color: #ffffff; -fx-font-size: 16px; -fx-padding: 5px;");
        GridPane.setHgrow(numberField, Priority.ALWAYS);

        Label startYearLabel = new Label("Start Year of study");
        startYearLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: grey;");
        startYearLabel.setFont(Font.font("Arial", 18));

        DatePicker startDatePicker = new DatePicker();
        startDatePicker.setStyle("-fx-font-size: 16px; -fx-padding: 5px;");
        GridPane.setHgrow(startDatePicker, Priority.ALWAYS);

        Label datePickerLabel = new Label("End Year of study");
        datePickerLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: grey;");
        datePickerLabel.setFont(Font.font("Arial", 18));

        DatePicker endDatePicker = new DatePicker();
        endDatePicker.setStyle("-fx-font-size: 16px; -fx-padding: 5px;");
        GridPane.setHgrow(endDatePicker, Priority.ALWAYS);

        Button submitButton = new Button("Submit");
        submitButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-size: 16px; -fx-padding: 10px 20px;");
        GridPane.setHalignment(submitButton, HPos.CENTER);
        GridPane.setMargin(submitButton, new Insets(20, 0, 0, 0));

        root1.addRow(0, nameLabel, nameField);
        root1.addRow(1, numberLabel, numberField);
        root1.addRow(2, startYearLabel, startDatePicker);
        root1.addRow(3, datePickerLabel, endDatePicker);
        root1.add(submitButton, 0, 4, 2, 1);


        // Create the scene
        Scene scene1 = new Scene(root1, 500, 400);

        // Set the scene for the primary stage
        stage.setScene(scene1);
        stage.setTitle("SisuGui");
        stage.show();
        //Creating a new BorderPane.
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(10, 10, 10, 10));

        // Adding the menu bar to the top of the BorderPane.

        root.setTop(menuBar);

        // Adding HBox to the center of the BorderPane.
        root.setCenter(getCenterHbox());

        // Adding a button to the BorderPane and aligning it to the right.
        BorderPane.setMargin(refreshButton, new Insets(10, 10, 0, 10));
        root.setBottom(refreshButton);
        BorderPane.setAlignment(refreshButton, Pos.TOP_RIGHT);
        Scene scene = new Scene(root, 960, 480);




        //button events
        // Event handler for the submit button
        submitButton.setOnAction(event -> {
            // Get the input values
            String name = nameField.getText();
            String number = numberField.getText();
            LocalDate startDate = startDatePicker.getValue();
            LocalDate endDate = endDatePicker.getValue();
            studentData= new StudentData(name,number,startDate,endDate,20);
            stage.setScene(scene);
            System.out.println(studentData);
            //

        });

        refreshButton.setOnAction(event -> {
            System.out.println("Refreshed");
            // Creating a new BorderPane.
            BorderPane root2 = new BorderPane();
            root2.setPadding(new Insets(10, 10, 10, 10));

            // Adding the menu bar to the top of the BorderPane.
            root2.setTop(menuBar);

            // Adding HBox to the center of the BorderPane.
            root2.setCenter(getCenterHbox());

            // Adding a button to the BorderPane and aligning it to the right.

            BorderPane.setMargin(refreshButton, new Insets(10, 10, 0, 10));
            root2.setBottom(refreshButton);
            BorderPane.setAlignment(refreshButton, Pos.TOP_RIGHT);
            Scene scene3 = new Scene(root2, 960, 480);

            // Set the scene for the primary stage
            stage.setScene(scene3);
            stage.setTitle("SisuGui");
            stage.show();

        });


    }
    // Create a method to build the root of the scene
    BorderPane createRoot() {
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(10));

        // Adding the menu bar to the top of the BorderPane.
        root.setTop(menuBar);

        // Adding HBox to the center of the BorderPane.
        root.setCenter(getCenterHbox());

        // Adding a button to the BorderPane and aligning it to the right.
        Button quitButton = getQuitButton();
        BorderPane.setMargin(quitButton, new Insets(10, 10, 0, 10));
        root.setBottom(quitButton);
        BorderPane.setAlignment(quitButton, Pos.TOP_RIGHT);

        return root;
    }



    public static void main(String[] args) throws IOException {
        loadAPIData();
        launch();
    }

    public static void loadAPIData(){
        degreeModules.clear();
        System.out.println("Making API calls to SISU API to fetch degree programs...");
        String apiUrl = "https://sis-tuni.funidata.fi/kori/api/module-search?curriculumPeriodId=uta-lvv-2021&universityId=tuni-university-root-id&moduleType=DegreeProgramme&limit="+numOfDegreePrograms;
        iAPI sisuAPI = new SisuAPIImpl();
        JsonObject jsonObject = sisuAPI.getJsonObjectFromApi(apiUrl);

        if (jsonObject != null) {
            JsonArray searchResults = jsonObject.getAsJsonArray("searchResults");
            for (JsonElement result : searchResults) {
                JsonObject program = result.getAsJsonObject();
                String id = program.get("id").getAsString();
                String groupId = program.get("groupId").getAsString();
                String programName = program.get("name").getAsString();
                String code= program.get("code").getAsString();
                JsonObject credits = program.getAsJsonObject("credits");
                int minCredits = credits.get("min").getAsInt();
                int maxCredits = credits.has("max") && !credits.get("max").isJsonNull()
                        ? credits.get("max").getAsJsonPrimitive().getAsInt()
                        : -1;


                degreeModules.add(new DegreeModule(programName, id, groupId, minCredits,code) {
                });

            }
        } else {
            System.out.println("Failed to retrieve JSON response from the API.");
        }
        //System.out.println(degreeModules);

        //extracting module rules for each degree program and store them in the object list

        for (int i=0; i<degreeModules.size()  ; i++){
            String apiUrl1 = "https://sis-tuni.funidata.fi/kori/api/modules/by-group-id?groupId="+degreeModules.get(i).getGroupId()+"&universityId=tuni-university-root-id";
            List<String> moduleGroupIds = sisuAPI.fetchModuleGroupIdsFromAPI(apiUrl1);
            System.out.println("For degree Program = "+degreeModules.get(i).getName()+" moduleGroupIds "+moduleGroupIds);
            degreeModules.get(i).setModuleGroupIds(moduleGroupIds);
        }


        //System.out.println(degreeModules);

        System.out.println("Processing API calls to recursively fetch study and course modules for degree program...");

        for (int i = 0; i < degreeModules.size(); i++) {
            List<StudyModule> studyModulesList = new ArrayList<>();
            List<CourseModule> courseModuleList = new ArrayList<>();
            for (int j = 0; j < degreeModules.get(i).getModuleGroupIds().size(); j++) {
                String moduleGroupId = degreeModules.get(i).getModuleGroupIds().get(j);
                String apiUrl2 = "https://sis-tuni.funidata.fi/kori/api/modules/by-group-id?groupId=" + moduleGroupId + "&universityId=tuni-university-root-id";
                List<StudyModule> studyModules = sisuAPI.fetchStudyModulesFromAPI(apiUrl2);
                studyModulesList.addAll(studyModules);
            }
            degreeModules.get(i).setStudyModules(studyModulesList);

            for (int k=0 ; k< degreeModules.get(i).getStudyModules().size();k++){
                StudyModule stdModule = degreeModules.get(i).getStudyModules().get(k);

                for (int x=0 ; x <stdModule.getModuleGroupIds().size(); x++){
                    String apiUrl3 = "https://sis-tuni.funidata.fi/kori/api/modules/by-group-id?groupId=" + stdModule.getModuleGroupIds().get(x) + "&universityId=tuni-university-root-id";
                    List<CourseModule> courseModules = sisuAPI.fetchCourseModulesFromAPI(apiUrl3);
                    courseModuleList.addAll(courseModules);
                }
                stdModule.setCourseModules(courseModuleList);
            }
        }


        System.out.println(degreeModules);


    }

    private MenuBar createMenuBar() {
        // Creating the menu bar.
        MenuBar menuBar = new MenuBar();

        // Creating the "File" menu.
        Menu fileMenu = new Menu("File");

        // Creating the "Settings" menu item.
        MenuItem saveMenuItem = new MenuItem("Save");
        saveMenuItem.setOnAction(event -> {
            iReadAndWriteToFile writeToFile= new  iReadAndWriteToFileImplement();
            try {
                writeToFile.writeToFile("student_data.json",studentData);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        rightVBox = getRightVBox();
        MenuItem loadMenuItem = new MenuItem("Load");
        loadMenuItem.setOnAction(event -> {

            iReadAndWriteToFile iRWtoFile=new iReadAndWriteToFileImplement();
            try {
                iRWtoFile.readFromFile("student_data.json",studentData);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            // Clear the existing items in the rightVBox
            rightVBox.getChildren().clear();
            // Add the items from the list to the rightVBox
            for (String course : studentData.getList()) {
                RadioButton radioButton = new RadioButton();
                radioButton.setText(course);
                radioButton.setAlignment(Pos.CENTER_LEFT);
                radioButton.setContentDisplay(ContentDisplay.LEFT);
                radioButton.setFont(Font.font("System", FontWeight.LIGHT, 14));

                rightVBox.getChildren().add(radioButton);
            }
        });

        // Creating the "Settings" menu item.
        MenuItem settingsMenuItem = new MenuItem("Settings");
        settingsMenuItem.setOnAction(event -> {
            showPopupWindow();
        });


        // Creating the "Exit" menu item.
        MenuItem exitMenuItem = new MenuItem("Exit");
        exitMenuItem.setOnAction(event -> {
            // Handle exit menu item action here.
            Platform.exit();
        });

        // Adding menu items to the "File" menu.
        fileMenu.getItems().addAll(saveMenuItem,new SeparatorMenuItem(),loadMenuItem,new SeparatorMenuItem(),settingsMenuItem, new SeparatorMenuItem(), exitMenuItem);

        // Creating the "Help" menu.
        Menu helpMenu = new Menu("Help");

        // Creating the "Help" menu item.
        MenuItem helpMenuItem = new MenuItem("Help");
        helpMenuItem.setOnAction(event -> {


            // Create a custom dialog
            Dialog<Void> dialog = new Dialog<>();
            dialog.setTitle("Help");

            // Create a text area to display the message
            TextArea textArea = new TextArea();
            textArea.setEditable(false);
            textArea.setText("The program fetches 20 different degree programs results from SISU API however you can go to settings menu and set the limit on the number of degree programs to be fetched after setting the limit wait for api calls to process and then press Refresh button which will refresh the window with updated degree programs");

            // Enable vertical scrolling for the text area
            textArea.setWrapText(true);
            textArea.setMaxWidth(Double.MAX_VALUE);
            textArea.setMaxHeight(Double.MAX_VALUE);
            GridPane.setVgrow(textArea, Priority.ALWAYS);
            GridPane.setHgrow(textArea, Priority.ALWAYS);

            // Create a close button
            Button closeButton = new Button("Close");
            closeButton.setOnAction(closeEvent -> dialog.close());

            // Set the content of the dialog
            DialogPane dialogPane = dialog.getDialogPane();
            dialogPane.setContent(new GridPane());
            dialogPane.getButtonTypes().add(ButtonType.CLOSE);
            dialogPane.setHeader(textArea);
            dialogPane.getButtonTypes().setAll(ButtonType.CLOSE);

            // Show the dialog
            dialog.showAndWait();
        });

        // Creating the "Help" menu.
        Menu aboutMenu = new Menu("About");

        // Creating the "About" menu item.
        MenuItem aboutMenuItem = new MenuItem("About");
        aboutMenuItem.setOnAction(event -> {
            // Create a custom dialog
            Dialog<Void> dialog = new Dialog<>();
            dialog.setTitle("About");

            // Create a text area to display the message
            TextArea textArea = new TextArea();
            textArea.setEditable(false);
            textArea.setText("The program starts with a window that asks for student's name, ID, start date of the degree and end date of the degree\nAfter that 20 degree programs are pre fetched for the student and the student has the choice to go to the settings and fetch any amount of degree programs available in the API\nThe student can double click on a program and select it\nThere is option to save and load student data to and from JSON file");

            // Enable vertical scrolling for the text area
            textArea.setWrapText(true);
            textArea.setMaxWidth(Double.MAX_VALUE);
            textArea.setMaxHeight(Double.MAX_VALUE);
            GridPane.setVgrow(textArea, Priority.ALWAYS);
            GridPane.setHgrow(textArea, Priority.ALWAYS);

            // Create a close button
            Button closeButton = new Button("Close");
            closeButton.setOnAction(closeEvent -> dialog.close());

            // Set the content of the dialog
            DialogPane dialogPane = dialog.getDialogPane();
            dialogPane.setContent(new GridPane());
            dialogPane.getButtonTypes().add(ButtonType.CLOSE);
            dialogPane.setHeader(textArea);
            dialogPane.getButtonTypes().setAll(ButtonType.CLOSE);

            // Show the dialog
            dialog.showAndWait();
        });

        // Adding the "About" menu item to the "Help" menu.
        helpMenu.getItems().add(helpMenuItem);

        // Adding the "About" menu item to the "Help" menu.
        aboutMenu.getItems().add(aboutMenuItem);

        // Adding menus to the menu bar.
        menuBar.getMenus().addAll(fileMenu, helpMenu, aboutMenu);

        return menuBar;
    }

    private void showLoadingIndicator(VBox root) {
        ProgressIndicator loadingIndicator = new ProgressIndicator();
        root.getChildren().add(loadingIndicator);
    }

    private HBox getCenterHbox() {
        // Creating an HBox.
        HBox centerHBox = new HBox(10);

        // Creating a label.
        Label topLabel = new Label("Study Structure");

        // Setting the label style to center it horizontally
        topLabel.setStyle("-fx-alignment: center;");
        topLabel.setFont(Font.font("System", FontWeight.BOLD, 23));

        // Adding two VBox to the HBox.
        VBox leftVBox = getLeftVBox();
        VBox rightVBox = getRightVBox();
        centerHBox.getChildren().addAll(leftVBox, rightVBox);

        VBox wrapperVBox = new VBox(topLabel, centerHBox);
        wrapperVBox.setAlignment(Pos.TOP_CENTER);

        HBox wrapperHBox = new HBox(wrapperVBox);
        wrapperHBox.setAlignment(Pos.TOP_CENTER);

        return wrapperHBox;
    }

    private VBox getLeftVBox() {
        // Creating a VBox for the left side.
        VBox leftVBox = new VBox();
        leftVBox.setPrefWidth(900);
        leftVBox.setPrefHeight(800);

        // Add the TreeView to the VBox
        TreeView<String> treeView = getRightTreeView();
        leftVBox.getChildren().add(treeView);
        return leftVBox;
    }

    private VBox rightVBox;

    private VBox getRightVBox() {
        if (rightVBox == null) {
            rightVBox = new VBox();
            rightVBox.setPrefWidth(700);
        }
        rightVBox.setStyle("-fx-spacing: 8px;");

        return rightVBox;
    }

    // Declare fields to store the previous stage and scene references
    private Stage previousStage;
    private Scene previousScene;

    private void showPopupWindow() {
        Stage popupStage = new Stage();
        popupStage.initOwner(getPrimaryStage());

        VBox root = new VBox(10);
        root.setPadding(new Insets(10));

        // Create a label with the message
        Label messageLabel = new Label("By default first 20 degree programs are fetched and recursively processed from SISU API\nYou can set the limit here and process result accordingly\nPlease note that processing more degree programs will take time to fetch the result from API\nEnter limit(Max 273 Degree programs):");
        root.getChildren().add(messageLabel);

        // Create an input field
        TextField inputField = new TextField();
        root.getChildren().add(inputField);

        // Create a button to set the variable value
        Button setButton = new Button("Set Limit");
        setButton.setOnAction(event -> {
            Sisu.numOfDegreePrograms = Integer.parseInt(inputField.getText());
            System.out.println(numOfDegreePrograms);
            setButton.setDisable(true); // Disable the button while processing

            ProgressBar loadingIndicator = new ProgressBar();
            loadingIndicator.setProgress(-1); // Set indeterminate progress
            root.getChildren().add(loadingIndicator);

            Task<Void> task = new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                    Thread.sleep(2000);
                    loadAPIData();

                    return null;
                }
            };
            task.setOnSucceeded(successEvent -> {
                root.getChildren().remove(loadingIndicator);
                popupStage.close();

            });

            Thread processingThread = new Thread(task);
            processingThread.start();
        });

        root.getChildren().add(setButton);

        Scene scene = new Scene(root, 540, 200);
        popupStage.setScene(scene);
        popupStage.setTitle("Set Search Results Limit");
        popupStage.show();

    }

    private void showPopupWindow1(String message) {
        Stage popupStage = new Stage();
        popupStage.initOwner(getPrimaryStage());

        VBox root = new VBox(10);
        root.setPadding(new Insets(10));

        // Create a label with the message
        Label messageLabel = new Label(message);
        root.getChildren().add(messageLabel);

        // Create an input field
        TextField inputField = new TextField();
        root.getChildren().add(inputField);

        // Create a button to set the variable value
        Button setButton = new Button("Set Limit");
        setButton.setOnAction(event -> {
            Sisu.numOfDegreePrograms = Integer.parseInt(inputField.getText());
            System.out.println(numOfDegreePrograms);
            setButton.setDisable(true); // Disable the button while processing

            ProgressBar loadingIndicator = new ProgressBar();
            loadingIndicator.setProgress(-1); // Set indeterminate progress
            root.getChildren().add(loadingIndicator);

            Task<Void> task = new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                    Thread.sleep(2000);
                    loadAPIData();

                    return null;
                }
            };
            task.setOnSucceeded(successEvent -> {
                root.getChildren().remove(loadingIndicator);
                popupStage.close();

            });

            Thread processingThread = new Thread(task);
            processingThread.start();
        });

        root.getChildren().add(setButton);

        Scene scene = new Scene(root, 540, 200);
        popupStage.setScene(scene);
        popupStage.setTitle("Set Search Results Limit");
        popupStage.show();

    }



    private Stage getPrimaryStage() {
        return (Stage) getScene().getWindow();
    }

    private Scene getScene() {
        return menuBar.getScene();
    }

    private TreeView<String> getRightTreeView() {
        // Create the root tree item
        TreeItem<String> rootItem = new TreeItem<>("Degree Programs");
        rootItem.setExpanded(true);

        // Add degree programs and their study modules to the tree
        for (DegreeModule degreeModule : degreeModules) {
            TreeItem<String> degreeItem = new TreeItem<>(degreeModule.getName());

            for (StudyModule studyModule : degreeModule.getStudyModules()) {
                TreeItem<String> studyModuleItem = new TreeItem<>(studyModule.getName());

                for (CourseModule courseModule : studyModule.getCourseModules()) {
                    TreeItem<String> courseModuleItem = new TreeItem<>(courseModule.getName());

                    studyModuleItem.getChildren().add(courseModuleItem);
                }

                degreeItem.getChildren().add(studyModuleItem);
            }

            rootItem.getChildren().add(degreeItem);
        }

        // Create the TreeView
        TreeView<String> treeView = new TreeView<>(rootItem);

        // Set custom cell factory
        treeView.setCellFactory(param -> new TreeCell<String>() {
            private Tooltip tooltip = new Tooltip();

            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setText(null);
                    setTooltip(null);
                } else {
                    setText(item);
                    TreeItem<String> treeItem = getTreeItem();
                    if (treeItem != null && treeItem.getParent() != null) {
                        String courseModuleName = treeItem.getValue();
                        CourseModule courseModule = findCourseModule(courseModuleName);
                        if (courseModule != null) {
                            String txt= "Course Code : "+courseModule.getCode()+" Credits : Min : "+courseModule.getMinCredits()+" Max : "+courseModule.getMaxCredits();
                            tooltip.setText(txt);
                            setTooltip(tooltip);
                        } else {
                            setTooltip(null);
                        }
                    } else {
                        setTooltip(null);
                    }
                }
            }
        });

        // Add double-click event handler
        treeView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                System.out.println("clicked");
                TreeItem<String> selectedItem = treeView.getSelectionModel().getSelectedItem();
                if (selectedItem != null && selectedItem.getParent() != null) {
                    String studyModuleName = selectedItem.getValue();
                    try {
                        addStudyModuleRadioButton(studyModuleName);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });

        return treeView;
    }

    private CourseModule findCourseModule(String courseModuleName) {
        // Logic to find the CourseModule
        for (int i = 0; i < degreeModules.size(); i++) {
            for (int j = 0; j < degreeModules.get(i).getStudyModules().size(); j++) {
                StudyModule std = degreeModules.get(i).getStudyModules().get(j);
                for (int k = 0; k < std.getCourseModules().size(); k++) {
                    CourseModule crs = std.getCourseModules().get(k);
                    if (crs.getName().equals(courseModuleName)) {
                        return crs;
                    }
                }
            }
        }

        return null;
    }

    private void addStudyModuleRadioButton(String studyModuleName) throws Exception {

        selectedCourses.add(studyModuleName);
        studentData.setList(selectedCourses);

        RadioButton radioButton = new RadioButton();
        radioButton.setText(studyModuleName);
        radioButton.setAlignment(Pos.CENTER_LEFT);
        radioButton.setContentDisplay(ContentDisplay.LEFT);
        radioButton.setFont(Font.font("System", FontWeight.LIGHT, 14));

        VBox rightVBox = getRightVBox();
        rightVBox.getChildren().add(radioButton);
    }

    private Button getQuitButton() {
        //Creating a button.
        Button button = new Button("Quit");
        
        //Adding an event to the button to terminate the application.
        button.setOnAction((ActionEvent event) -> {
            Platform.exit();
        });
        
        return button;
    }
}