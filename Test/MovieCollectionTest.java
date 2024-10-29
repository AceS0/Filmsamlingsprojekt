import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class MovieCollectionTest {
    MovieCollection coll = new MovieCollection();
    Movie movie1 = new Movie("batman", "julemanden", 2003, "yes", 200, "coolMovie");

    Movie movie2 = new Movie("batman 2", "julemanden", 2006, "yes", 200, "coolMovie");
    Movie movie3 = new Movie("nissbanden", "julemanden", 1960, "no", 200, "coolMovie");

    @Test
    void addMovie() {
        coll.addMovie(movie1);
        coll.addMovie(movie2);
        coll.addMovie(movie3);
        assertEquals(coll.getCollection(), 3);

    }

    @Test
    void movieList() {
        System.out.println(coll.movieList());
    }

    @Test
    void movieFinder() {
        coll.addMovie(movie1);
        coll.addMovie(movie3);
        ArrayList<Movie> mList =  coll.movieFinder("bat");
        coll.addMovie(movie2);
        ArrayList<Movie> mList2 =  coll.movieFinder("bat");
        assertEquals(mList.size(), 1);
        assertEquals(mList2.size(),2);

    }
}