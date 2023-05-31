/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

/**
 *
 * @author PC PRAKTIKUM
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import Model.MovieModel;
import View.MovieView;

public class MovieController {
    MovieModel movieModel;
    MovieView movieView;
    Object header[] = {"Title", "Plot", "Character", "Acting","Score"};

    public MovieController(MovieModel movieModel, MovieView movieView) {
        this.movieModel = movieModel;
        this.movieView = movieView;
        
        if (movieModel.getMovieData()!=0) {
            String dataMovie[][] = movieModel.readMovie();
            movieView.table.setModel((new JTable(dataMovie, header)).getModel());
        }
        else {
            JOptionPane.showMessageDialog(null, "No Data");
        }
        
        movieView.tambah.addActionListener(new ActionListener() {           
            @Override
            public void actionPerformed(ActionEvent arg0) {
                String judul = movieView.getJudul();
                String alur = movieView.getAlur();
                String penokohan = movieView.getPenokohan();
                String akting = movieView.getAkting();
                movieModel.TambahMovie(judul, alur, penokohan, akting);
                String dataMovie[][] = movieModel.readMovie();
                movieView.table.setModel((new JTable(dataMovie, header)).getModel());
            }
        });
        
        movieView.update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                String judul = movieView.getJudul();
                String alur = movieView.getAlur();
                String penokohan = movieView.getPenokohan();
                String akting = movieView.getAkting();
                movieModel.updateMovie(judul, alur, penokohan, akting);
                String dataMovie[][] = movieModel.readMovie();
                movieView.table.setModel((new JTable(dataMovie, header)).getModel());
            }
        });
        
        movieView.delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                String title = movieView.getJudul();
                
                movieModel.deleteMovie(title);
                String dataMovie[][] = movieModel.readMovie();
                movieView.table.setModel((new JTable(dataMovie, header)).getModel());
            }
        });
    }
    
    
}