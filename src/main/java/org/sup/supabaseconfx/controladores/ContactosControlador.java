package org.sup.supabaseconfx.controladores;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.sup.supabaseconfx.agendaDao.ContactosCRUDDao;
import org.sup.supabaseconfx.agendaDao.TipoContactoCRUD;
import org.sup.supabaseconfx.modelos.ContactoModelo;
import org.sup.supabaseconfx.modelos.TipoContactoModelo;

import java.util.ArrayList;
import java.util.List;

public class ContactosControlador {
    private final Alert CONFIRMACION = new Alert(Alert.AlertType.CONFIRMATION);
    private final Alert ADVERTENCIA = new Alert(Alert.AlertType.WARNING);


    TipoContactoCRUD tipoContactoCRUD = new TipoContactoCRUD();
    ContactosCRUDDao contactosCRUDDao = new ContactosCRUDDao();

    TipoContactoModelo categoriaSeleccionada;


    List<TipoContactoModelo> listaTipoContacto = new ArrayList<>();


    @FXML
    private TableView<ContactoModelo> TableContactos;
    @FXML
    private TableColumn<ContactoModelo, Integer> idColumn;
    @FXML
    private TableColumn<ContactoModelo, String> nombreColumn;
    @FXML
    private TableColumn<ContactoModelo, String> telefonoColumn;
    @FXML
    private TableColumn<ContactoModelo, String> emailColumn;
    @FXML
    private TableColumn<ContactoModelo, Integer> categoriaColumn;

    @FXML
    private TableView<TipoContactoModelo> TableTipocontacto;
    @FXML
    private TableColumn<TipoContactoModelo, Integer> columnIdCategoria;
    @FXML
    private TableColumn<TipoContactoModelo, String> columnNombreCategoria;



    @FXML
    private TextField txtNombreContacto, txtTelefonoContacto, txtEmailContacto, txtNombreTipoCategoria;
    @FXML
    private SplitMenuButton btnCategoriaContacto;

    public void initialize() throws Exception{
        btnCategoriaContacto.setText("ELIJA CATEGORIA");


        poblarSplit();
        inicializarTablaContactos();
        inicializarTablaTipoContacto();
    }


    public void poblarSplit() throws Exception {
        listaTipoContacto = tipoContactoCRUD.listarTipoContacto();
        btnCategoriaContacto.getItems().clear();

        for (TipoContactoModelo TipoContactoModelo : listaTipoContacto){
            MenuItem item = new MenuItem(TipoContactoModelo.getTipo());
            item.setOnAction(e -> {
                this.categoriaSeleccionada = TipoContactoModelo;
                btnCategoriaContacto.setText(item.getText());
            });
            btnCategoriaContacto.getItems().add(item);
        }


        btnCategoriaContacto.setText("ELIJA CATEGORIA");
    }

    public void crearContacto() throws Exception {
        String nombre = txtNombreContacto.getText();
        String telefono = txtTelefonoContacto.getText();
        String email = txtEmailContacto.getText();
        String categoria = btnCategoriaContacto.getText();
        int id = categoriaSeleccionada.getId();
        if (nombre.isEmpty() || telefono.isEmpty() || email.isEmpty() || categoria.isEmpty()) {
            ADVERTENCIA.setTitle("ERROR DE VERIFICACION");
            ADVERTENCIA.setContentText("LLENE TODOS LOS CAMPOS");
            ADVERTENCIA.show();
            return;
        }
        contactosCRUDDao.CrearContacto(nombre, telefono, email, id);
        poblarTablaContactos();
    }

    public void crearTipoContacto() throws Exception{
        String nuevoTipoUsuario = txtNombreTipoCategoria.getText();

        if (nuevoTipoUsuario.isEmpty()){
            ADVERTENCIA.setTitle("ERROR DE VERIFICACION");
            ADVERTENCIA.setContentText("LLENE EL CAMPO");
            ADVERTENCIA.show();
            return;
        }

        tipoContactoCRUD.insertarTipoContacto(nuevoTipoUsuario);
        CONFIRMACION.setTitle("CONTACTO CREADO");
        CONFIRMACION.setContentText("CONTACTO CREADO EXITOSAMENTE");
        CONFIRMACION.show();
        try {
            poblarSplit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        poblarTablaTipoContacto();

    }

    public void inicializarTablaContactos() throws Exception{
        idColumn.setCellValueFactory(new PropertyValueFactory<>("idContacto"));
        nombreColumn.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        telefonoColumn.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        categoriaColumn.setCellValueFactory(new PropertyValueFactory<>("idTipoContacto"));

        poblarTablaContactos();
    }
    public void poblarTablaContactos() throws Exception {
        List<ContactoModelo> listaContactos = contactosCRUDDao.listarContactos();
        TableContactos.setItems(FXCollections.observableArrayList(listaContactos));
        TableContactos.refresh();
    }

    public void inicializarTablaTipoContacto() throws Exception{
        columnIdCategoria.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnNombreCategoria.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        poblarTablaTipoContacto();
    }


    public void poblarTablaTipoContacto() throws Exception {
        List<TipoContactoModelo> listaTipoContacto = tipoContactoCRUD.listarTipoContacto();
        TableTipocontacto.setItems(FXCollections.observableArrayList(listaTipoContacto));
        TableTipocontacto.refresh();
    }
}
