/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author PC PRAKTIKUM
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class MovieModel extends connector implements Calculation{    
//    String DBurl = "jdbc:mysql://localhost:3306/movie_db";
//    String DBusername = "root";
//    String DBpassword = "";
//    Connection connection;
//    Statement statement;
//    public MovieModel(){
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            connection=DriverManager.getConnection(DBurl, DBusername, DBpassword);
//            System.out.println("Koneksi Berhasil");
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, e.getMessage());
//            System.out.println("Koneksi Gagal");
//        }
//    }
    @Override
    public double average(double plot, double character, double acting) {
        return (plot+character+acting)/3;
    }
    
    public int getMovieData(){
        try {
            int totalData = 0;
            String query = "SELECT * FROM `movie`";
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while(resultSet.next()){
                totalData++;
            }
            statement.close();
            return totalData;
        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
            return 0;
        }
    }
    
    public void TambahMovie(String judul, String alur, String penokohan, String akting){
        try {
            double al, pen, akt, nilai;
            al = Double.parseDouble(alur);
            pen = Double.parseDouble(penokohan);
            akt = Double.parseDouble(akting);
            
            System.out.println(pen);
            if(al < 0.0 || al > 5.0){
                throw new Exception("out");
            }
            if(pen < 0.0 || pen > 5.0){
                throw new Exception("out");
            }
            if(akt < 0.0 || akt > 5.0){
                throw new Exception("out");
            }
            nilai = average(al, pen, akt);
            
            String query = "INSERT INTO `movie` (`judul`, `alur`, `penokohan`, `akting`, `nilai`) " + 
                        "VALUES ('" + judul + "'," + al + "," + pen + "," + akt + "," + nilai + ")";
            
            statement = connection.createStatement();
            statement.executeUpdate(query);
            
            JOptionPane.showMessageDialog(null, "Register Success");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            if(e.getMessage().equals("out")){
                JOptionPane.showMessageDialog(null, "Out of Bound");
            }else{
                JOptionPane.showMessageDialog(null, "Register Failed");
            }     
        }
    }
    
    public String[][] readMovie(){
        try{
            int totalData = 0;
            
            String data[][] = new String[getMovieData()][5];
            
            String query = "SELECT * FROM `movie`"; 
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                data[totalData][0] = resultSet.getString("judul");
                data[totalData][1] = resultSet.getString("alur");                
                data[totalData][2] = resultSet.getString("penokohan");
                data[totalData][3] = resultSet.getString("akting");
                data[totalData][4] = resultSet.getString("nilai");
                totalData++;
            }
            statement.close();
            return data;
               
        }catch(SQLException e){
            System.out.println("Error : " + e.getMessage());
            return null;
        }
    }
    
    public void updateMovie(String judul, String alur, String penokohan, String akting){
        try {
            double al, pen, akt, score;
            al = Double.parseDouble(alur);
            pen = Double.parseDouble(penokohan);
            akt = Double.parseDouble(akting);
            
            System.out.println(pen);
            if(al < 0.0 || al > 5.0){
                throw new Exception("out");
            }
            if(pen < 0.0 || pen > 5.0){
                throw new Exception("out");
            }
            if(akt < 0.0 || akt > 5.0){
                throw new Exception("out");
            }
            score = average(al, pen, akt);
            
            String query = "UPDATE `movie` "
                    + "SET "
                    + "`alur`=" + alur + ","
                    + "`penokohan`=" + pen + ","
                    + "`akting`=" + akt + ","
                    + "`nilai`=" + score
                    + " WHERE judul='" + judul + "'";
            
            statement = connection.createStatement();
            statement.executeUpdate(query);
            
            JOptionPane.showMessageDialog(null, "Update Success");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            if(e.getMessage().equals("out")){
                JOptionPane.showMessageDialog(null, "Out of Bound");
            }else{
                JOptionPane.showMessageDialog(null, "Update Failed");
            }     
        }
    }
    
    public void deleteMovie (String judul) {
        try{
            String query = "DELETE FROM movie WHERE judul = '"+judul+"'";
            statement = connection.createStatement();
            statement.executeUpdate(query);
            JOptionPane.showMessageDialog(null, "Delete Success");
            
        }catch(SQLException e) {
            System.out.println("Error : " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Delete Failed");
        }
    }
}