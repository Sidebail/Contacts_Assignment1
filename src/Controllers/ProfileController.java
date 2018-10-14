package Controllers;

import Models.DBConnect;
import Models.Profile;
import Views.SceneChanger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ProfileController implements Initializable {


    @FXML
    private ImageView imgImage;

    @FXML
    private Button bImage;

    @FXML
    private GridPane t;

    @FXML
    private TextField tfFirstName;

    @FXML
    private TextField tfLastName;

    @FXML
    private TextField tfAddress;

    @FXML
    private TextField tfPhone;

    @FXML
    private DatePicker dpBirthday;

    @FXML
    private RadioButton rbMale;

    @FXML
    private RadioButton rbFemale;

    @FXML
    private RadioButton rbOther;

    @FXML
    private TextField tfEmail;

    @FXML
    private TextArea tfBio;

    @FXML
    private Button bSave;

    @FXML
    private Button bCancel;

    private String sex = "";

    private Stage stage;

    @FXML
    /**
     * Cancel button click listener, changes scene to a Contacts view
     */
    void onCancelClick(ActionEvent event) {
        SceneChanger changer = new SceneChanger();
        try {
            changer.changeScenes(event,"contacts.fxml","Contacts");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    /**
     * Image button click listener, opens a tab where user is prompted to choose a picture
     */
    void onChooseImageClick(ActionEvent event) throws MalformedURLException {
/*
This part of code is taken from https://teamtreehouse.com/community/setting-selected-image-file-to-imageview-in-javafx
 */
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Open File");
        chooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files",
                        "*.bmp", "*.png", "*.jpg", "*.gif")); // limit chooser options to image files
        File file = chooser.showOpenDialog(new Stage());
        if(file != null) {
            String imagepath = file.toURI().toURL().toString();

            Image image = new Image(imagepath);
            imgImage.setImage(image);
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText("Please Select a File");
            /*alert.setContentText("You didn't select a file!");*/
            alert.showAndWait();
        }

    }

    @FXML
    /**
     * Radio Button adjustment
     */
    void onFemaleChosen(ActionEvent event) {
        rbMale.setSelected(false);
        rbOther.setSelected(false);

        sex = "Female";

    }

    @FXML
    /**
     * Radio Button adjustment
     */
    void onMaleChosen(ActionEvent event) {
        rbFemale.setSelected(false);
        rbOther.setSelected(false);

        sex = "Male";
    }

    @FXML
    /**
     * Radio Button adjustment
     */
    void onOtherChosen(ActionEvent event) {
        rbMale.setSelected(false);
        rbFemale.setSelected(false);

        sex = "Other";
    }



    @FXML
    /**
     * Save button click listener, responds to incorrect input and saves the data to DB
     */
    void onSaveClick(ActionEvent event) {

        // public Profile(String firstName,String lastName,String eMail,String bio,String address,String phone,String sex,String birthday ){
       String testBirthday = "";
        try{
            testBirthday = dpBirthday.getValue().toString();
        }catch (NullPointerException e){
            testBirthday = "";
        }

        Profile prof = new Profile(tfFirstName.getText(),tfLastName.getText(),tfEmail.getText(),tfBio.getText(),tfAddress.getText(),tfPhone.getText(),sex,testBirthday);

        if (prof.getFailureArray().size() != 0){
            for (int i = 0; i<prof.getFailureArray().size();i++){
                respondToMistake(prof.getFailureArray().get(i));
            }
        }else{
            try {
                DBConnect.insertProfileIntoDB(prof);
                SceneChanger changer = new SceneChanger();
                try {
                    changer.changeScenes(event,"contacts.fxml","Contacts");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }




    }

    /**
     * Stage variable setter
     * @param stage
     */
    public void setStage(Stage stage){
        this.stage = stage;
    }

    /*
    This code part template was taken from the web
     */

    /**
     * Bio textfield listener, sets the maximum amount of characters for bio tab
     * @param limit
     */
    void setBioMaxCharListener(int limit){
        tfBio.lengthProperty().addListener(new ChangeListener<Number>() {

            @Override
            public void changed(ObservableValue<? extends Number> observable,
                                Number oldValue, Number newValue) {
                if (newValue.intValue() > oldValue.intValue()) {
                    // Check if the new character is greater than LIMIT
                    if (tfBio.getText().length() >= limit) {

                        // if it's 11th character then just setText to previous
                        // one
                        tfBio.setText(tfBio.getText().substring(0, limit));
                    }
                }
            }
        });
    }

    /**
     * Gets a string as an input and using switch block makes changes to a view, in order to provide the information about the invalid input
     * @param field
     */
    void respondToMistake(String field){
        switch (field) {
            case "firstName": tfFirstName.clear(); tfFirstName.setPromptText("First Name can't be blank"); tfFirstName.setStyle("-fx-background-color:  #ff8282");
                break;
            case "lastName": tfLastName.clear(); tfLastName.setPromptText("Last Name can't be blank"); tfLastName.setStyle("-fx-background-color:  #ff8282");
                break;
            case "eMail": tfEmail.clear(); tfEmail.setPromptText("E-Mail is not valid"); tfEmail.setStyle("-fx-background-color:  #ff8282");
                break;
            case "bio": tfBio.clear(); tfBio.setPromptText("Bio can't be blank"); tfBio.setStyle("-fx-background-color:  #ff8282");
                break;
            case "phone":  tfPhone.clear();tfPhone.setPromptText("Phone can contain only numeric characters"); tfPhone.setStyle("-fx-background-color:  #ff8282");
                break;
            case "sex":  rbMale.setStyle("-fx-background-color:  #ff8282"); rbFemale.setStyle("-fx-background-color:  #ff8282"); rbOther.setStyle("-fx-background-color:  #ff8282");
                break;


        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setBioMaxCharListener(1024);
    }
}
