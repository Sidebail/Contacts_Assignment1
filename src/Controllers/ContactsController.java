package Controllers;

import Models.DBConnect;
import Models.Profile;
import Views.SceneChanger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class ContactsController implements Initializable {

    @FXML
    private TableView<Profile> table;

    @FXML
    private TableColumn<Profile, String> colID;

    @FXML
    private TableColumn<Profile, String> colFirstName;

    @FXML
    private TableColumn<Profile, String> colLastName;

    @FXML
    private  TableColumn<Profile, String> colSex;

    @FXML
    private TableColumn<Profile, String> colBirthday;

    @FXML
    private TableColumn<Profile, String> colEmail;

    @FXML
    private TableColumn<Profile, String> colBio;

    @FXML
    private TableColumn<Profile, String> colAddress;

    @FXML
    private TableColumn<Profile, String> colPhone;

    private Stage stage;


    @FXML
    private TextField tfSearchField;

    @FXML
    private Button bSearch;

    @FXML
    private Button bEdit;

    @FXML
    private Button bCreate;



    @FXML
    /**
     * Click listener for the Create Button, changes scene to Profile creator
     */
    void onCreateClick(ActionEvent event) throws IOException {
        SceneChanger changer = new SceneChanger();
        changer.changeScenes(event,"profile.fxml","Create New Profile");
    }

    @FXML
    void onEditContactClick(ActionEvent event) {

    }

    @FXML
    /**
     * Click listener for the Search Button, clears table first, sets CellValuesFactory on all the table fields and inserting search-sorted contacts to the table from DB
     */
    void onSearchClick(ActionEvent event) {
        table.getItems().clear();

        colFirstName.setCellValueFactory(new PropertyValueFactory<Profile,String>("firstName"));
        colLastName.setCellValueFactory(new PropertyValueFactory<Profile,String>("lastName"));
        colSex.setCellValueFactory(new PropertyValueFactory<Profile,String>("sex"));
        colBirthday.setCellValueFactory(new PropertyValueFactory<Profile,String>("birthday"));
        colEmail.setCellValueFactory(new PropertyValueFactory<Profile,String>("eMail"));
        colBio.setCellValueFactory(new PropertyValueFactory<Profile,String>("bio"));
        colAddress.setCellValueFactory(new PropertyValueFactory<Profile,String>("address"));
        colPhone.setCellValueFactory(new PropertyValueFactory<Profile,String>("phone"));



        try {
            table.getItems().addAll(DBConnect.getSearchedContacts(tfSearchField.getText()));
            DBConnect.getSearchedContacts(tfSearchField.getText());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Stage variable setter
     * @param stage
     */
    public void setStage(Stage stage){
        this.stage = stage;

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //colMake.setCellValueFactory(new PropertyValueFactory<MobilePhone, String>("make"));
        colFirstName.setCellValueFactory(new PropertyValueFactory<Profile,String>("firstName"));
        colLastName.setCellValueFactory(new PropertyValueFactory<Profile,String>("lastName"));
        colSex.setCellValueFactory(new PropertyValueFactory<Profile,String>("sex"));
        colBirthday.setCellValueFactory(new PropertyValueFactory<Profile,String>("birthday"));
        colEmail.setCellValueFactory(new PropertyValueFactory<Profile,String>("eMail"));
        colBio.setCellValueFactory(new PropertyValueFactory<Profile,String>("bio"));
        colAddress.setCellValueFactory(new PropertyValueFactory<Profile,String>("address"));
        colPhone.setCellValueFactory(new PropertyValueFactory<Profile,String>("phone"));



        try {
            table.getItems().addAll(DBConnect.getContacts());
            DBConnect.getContacts();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
