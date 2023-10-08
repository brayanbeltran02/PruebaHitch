package com.codeup.jdvc;

import java.sql.*;
import org.example.Producto;

public class ConexionDB {
    public void insertar(Producto base) {
        String url = "jdbc:mysql://localhost:3306/computadores?serverTimezone=UTC";
        String username = "root";
        String password = "";

        try {
            Connection conection = DriverManager.getConnection(url, username, password);
            Statement statement = conection.createStatement();

            String sql = "insert into productos(nombre, precio, codigo, url)"
                    + "values (?, ?, ?, ?)";

            PreparedStatement preparedStmt = conection.prepareStatement(sql);
            preparedStmt.setString(1, base.nombre);
            preparedStmt.setString(2, base.precio);
            preparedStmt.setString(3, base.codigo);
            preparedStmt.setString(4, base.url);

            preparedStmt.execute();

            conection.close();


        }catch (SQLException e){
            System.err.println();
            e.printStackTrace();
            System.out.println(e);

        }

    }
}
