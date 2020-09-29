package com.regent.util;

import java.sql.Connection;
 import java.sql.DriverManager;
 import java.sql.ResultSet;
 import java.sql.ResultSetMetaData;
 import java.sql.Statement;
 import java.util.ArrayList;
 import java.util.Collection;
 
 
 public class JdbcConnection
 {
   public static Collection<Object[]> ejecutarConsulta(String sql, String urlDB, String userDB, String passwordDB) {
     Collection<Object[]> salida = new ArrayList();
 
     
     try {
       Class.forName("oracle.jdbc.driver.OracleDriver");
 
       
       Connection conn = DriverManager.getConnection(urlDB, userDB, passwordDB);
 
       
       Statement stmt = conn.createStatement();
       
       ResultSet rs = stmt.executeQuery(sql);
       ResultSetMetaData rsmd = rs.getMetaData();
 
       
       Integer columnsNumber = Integer.valueOf(rsmd.getColumnCount());
 
       
       Object[] cabecera = new Object[columnsNumber.intValue()];
       for (Integer i = Integer.valueOf(1); i.intValue() <= columnsNumber.intValue(); i = Integer.valueOf(i.intValue() + 1)) {
         cabecera[i.intValue() - 1] = rsmd.getColumnName(i.intValue());
       }
       salida.add(cabecera);
       
       while (rs.next()) {
         Object[] fila = new Object[columnsNumber.intValue()];
         
         for (Integer integer = Integer.valueOf(1); integer.intValue() <= columnsNumber.intValue(); integer = Integer.valueOf(integer.intValue() + 1)) {
           String columna = rsmd.getColumnName(integer.intValue());
           String campo = rs.getString(columna);
           
           fila[integer.intValue() - 1] = campo;
         } 
         salida.add(fila);
       } 
       conn.close();
     }
     catch (Exception e) {
       System.err.println("Error al conectar a la BD");
       System.err.println(e.getMessage());
     } 
     return salida;
   }
 }
