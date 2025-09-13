package org.sup.supabaseconfx.bd;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class BaseDatos {
    //PARTE POSTGRES LOCAL
    private static final Properties props = new Properties();

    static {
        try (InputStream in = BaseDatos.class.getClassLoader().getResourceAsStream("mysql.properties")){
            if (in == null) throw new RuntimeException("No se encontraron las propiedades");
            props.load(in);
        }catch (Exception e){
            throw new RuntimeException("error en la conexion");
        }
    }


    public static Connection getConnection(){
        try {

            String url = props.getProperty("jdbc.url");
            String user = props.getProperty("jdbc.username");
            String pass = props.getProperty("jdbc.password");

            return DriverManager.getConnection(url,user,pass);


        }catch (Exception e){
            throw new RuntimeException("no se pudo abrir la conexion " + e);
        }
    }


    //PARTE SUPABASE
    /*private static final Properties props = new Properties();
    static {
        try (InputStream in = BaseDatos.class.getClassLoader().getResourceAsStream("config.properties")){
            if (in == null) throw new RuntimeException("No se encontraron las propiedades");
            props.load(in);
        }catch (Exception e){
            throw new RuntimeException("error en la conexion");
        }
    }

    public static Connection getConnection(){
        try {
            String host= props.getProperty("db.host");
            String port = props.getProperty("db.port", "5432");
            String db = props.getProperty("db.name", "postgres");
            String user = props.getProperty("db.user");
            String pass = props.getProperty("db.password");
            String ssl = props.getProperty("db.sslmode", "require");



            String url = String.format("jdbc:postgresql://%s:%s/%s?sslmode=%s",host,port,db,ssl);
            return DriverManager.getConnection(url,user,pass);
        }catch (Exception e){
            throw new RuntimeException("no se pudo abrir la conexion " + e);
        }
    }*/
}
