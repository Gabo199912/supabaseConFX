module org.sup.supabaseconfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires java.sql;

    opens org.sup.supabaseconfx to javafx.fxml;
    opens org.sup.supabaseconfx.modelos to javafx.base;
    opens org.sup.supabaseconfx.agendaDao to javafx.base;
    opens org.sup.supabaseconfx.bd to javafx.base;
    opens org.sup.supabaseconfx.controladores to javafx.fxml;
    opens org.sup.supabaseconfx.bd.hash to javafx.base;

    exports org.sup.supabaseconfx;
    exports org.sup.supabaseconfx.modelos;
    exports org.sup.supabaseconfx.agendaDao;
    exports org.sup.supabaseconfx.bd;
    exports org.sup.supabaseconfx.controladores;
    exports org.sup.supabaseconfx.bd.hash;
}