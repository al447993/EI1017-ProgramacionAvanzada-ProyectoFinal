package es.uji.al447993.clasificarGavaraRamos.modelo;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class SongReader {
    public List<String> loadSongs() {

        List<String> songs = new ArrayList<>();

        try {

            InputStream is = getClass()
                    .getResourceAsStream("/recsys/songs_train_names.csv");

            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            String line;

            while ((line = br.readLine()) != null) {

                if (!line.isEmpty()) {
                    songs.add(line);
                }
            }

            br.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return songs;
    }
}
