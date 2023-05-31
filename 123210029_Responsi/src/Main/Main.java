/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Main;

/**
 *
 * @author PC PRAKTIKUM
 */
import Controller.MovieController;
import Model.MovieModel;
import View.MovieView;
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        MovieView movieView = new MovieView();
        MovieModel movieModel = new MovieModel();
        MovieController movieController = new MovieController(movieModel, movieView);
    }
    
}
