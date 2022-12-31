package controller;

import bo.BoFactory;
import bo.custom.CustomerBo;
import dao.DaoFactory;
import dao.custom.CustomerDao;
import dao.custom.ItemDao;
import dao.custom.impl.CustomerDaoImpl;
import dto.CustomerDto;
import entity.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import view.tm.CustomerTm;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

public class CustomerFormController {
    public TextField txtId;
    public TextField txtName;
    public TextField txtAddress;
    public TextField txtSalary;
    public Button btnSaveCustomer;
    public TextField txtSearch;
    public TableView<CustomerTm> tblCustomer;
    public TableColumn colId;
    public TableColumn colName;
    public TableColumn colAddress;
    public TableColumn colSalary;
    public TableColumn colOption;
    public AnchorPane customerContext;
    private CustomerBo bo= BoFactory.getInstance().getBo(BoFactory.BoType.CUSTOMER);



    public void initialize() throws SQLException, ClassNotFoundException {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        colOption.setCellValueFactory(new PropertyValueFactory<>("btn"));

        loadAllCustomers();

        //Listeners====

        tblCustomer.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            setData(newValue);
        });
    }

    private void setData(CustomerTm tm) {
        txtId.setText(tm.getId());
        txtName.setText(tm.getName());
        txtAddress.setText(tm.getAddress());
        txtSalary.setText(String.valueOf(tm.getSalary()));
        btnSaveCustomer.setText("Update Customer");
    }

    private void loadAllCustomers() throws SQLException, ClassNotFoundException {
        ObservableList<CustomerTm> obList= FXCollections.observableArrayList();
        for (CustomerDto c:bo.loadAllCustomers()) {
            Button btn = new Button("Delete");
            CustomerTm tm = new CustomerTm(c.getId(), c.getName(), c.getAddress(), c.getSalary(), btn);
            obList.add(tm);


            btn.setOnAction(e -> {
                        try {
                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.NO, ButtonType.YES);
                            Optional<ButtonType> buttonType = alert.showAndWait();
                            if (buttonType.get() == ButtonType.YES) {
                                if (bo.deleteCustomer(tm.getId())) {
                                    new Alert(Alert.AlertType.CONFIRMATION, "Customer is Deleted").show();
                                    loadAllCustomers();
                                } else {
                                    new Alert(Alert.AlertType.CONFIRMATION, "Try Again").show();
                                }
                            }
                        } catch (Exception exception) {
                            new Alert(Alert.AlertType.CONFIRMATION, "Something went wrong!").show();
                            exception.printStackTrace();
                        }
                    }
            );
            tblCustomer.setItems(obList);
        }
    }

    public void saveCustomerOnAction(ActionEvent actionEvent) {
        CustomerDto c1=new CustomerDto(txtId.getText(),txtName.getText(),txtAddress.getText(),Double.parseDouble(txtSalary.getText()));

        if (btnSaveCustomer.getText().equalsIgnoreCase("Save Customer")){
            try {
                boolean isCustomerSaved= bo.saveCustomer(c1);
                if (isCustomerSaved){
                    new Alert(Alert.AlertType.CONFIRMATION,"Customer is Saved").show();
                    loadAllCustomers();
                    clear();
                }else {
                    new Alert(Alert.AlertType.CONFIRMATION,"Try Again!").show();
                }
            } catch (ClassNotFoundException | SQLException e) {
                new Alert(Alert.AlertType.CONFIRMATION,e.getMessage()).show();
            }
        }else {
            try {
                boolean isCustomerUpdated= bo.updateCustomer(c1);
                if (isCustomerUpdated){
                    new Alert(Alert.AlertType.CONFIRMATION,"Customer is Saved").show();
                    loadAllCustomers();
                    clear();
                }else {
                    new Alert(Alert.AlertType.CONFIRMATION,"Try Again!").show();
                    clear();
                }
            } catch (ClassNotFoundException | SQLException e) {
                new Alert(Alert.AlertType.CONFIRMATION,e.getMessage()).show();

            }
        }
    }

        private void clear(){
            txtId.clear();
            txtName.clear();
            txtAddress.clear();
            txtSalary.clear();
        }

    public void newCustomerOnAction(ActionEvent actionEvent) {
        clear();
        btnSaveCustomer.setText("Save Customer");
    }

    public void backToHomeOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) customerContext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/MainForm.fxml"))));
        stage.centerOnScreen();
    }
}
