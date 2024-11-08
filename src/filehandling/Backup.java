package filehandling;

import model.Movie;
import model.MovieCollection;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.io.*;


public class Backup {



    public String loadMovieFile(MovieCollection collection) {
        File file = new File("save.txt");
        Scanner sc = null;
        try {
            sc = new Scanner(file);
            sc.nextLine();

            Movie checkFile = null;
            while (sc.hasNext()) {
                String line = sc.nextLine();
                String[] attributes = line.split(";");


                checkFile = new Movie((attributes[0]), (attributes[1]),
                        (Integer.parseInt(attributes[2])), (Boolean.parseBoolean(attributes[3])),
                        (Integer.parseInt(attributes[4])), (attributes[5]));

                boolean duplicate = false;
                for (Movie movie : collection.getCollection()) {
                    if (movie.getTitle().equals(checkFile.getTitle())) {
                        duplicate = true;
                        break;
                    }
                }
                if (!duplicate) {
                    collection.addMovie(checkFile);
                }
            }
            sc.close();
        } catch (FileNotFoundException | NullPointerException | NoSuchElementException ignored) {
        }
        return "\nLoaded successfully.";
    }

    public String saveMovieFile(MovieCollection collection) {
        File file = new File("save.txt");
        try {
            FileWriter writer = new FileWriter(file);
            writer.write("Title,Director,Year created,Is it in color?,Leng th in minutes,Genre.\n");
            for (Movie movie : collection.getCollection()) {
                writer.write(collection.getMovieDesc(movie));
                writer.append("\n");
            }
            writer.close();
            return "\"You have succesfully saved your movie(s) to a save.txt\"";
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public String deleteFile() {
        File file = new File("save.txt");
        if (file.delete()) {
            return "You have deleted a file ";
        } else {
            return "You need a file, before you can delete.";
        }
    }
}
