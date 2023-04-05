package com.alura.stickers;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {

        //Create HTTP connection
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        var adress = URI.create(url);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(adress).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();

//        System.out.println(body);

        //Parse data (title, poster, rating)
        JsonParser parser = new JsonParser();
        List<Map<String, String>> moviesList = parser.parse(body);


        //manipulate and display data
        var stickerMaker = new StickerMaker();
        for (Map<String, String> movie: moviesList) {

            String urlImage = movie.get("image");
            String title = movie.get("title");

            InputStream inputStream = new URL(urlImage).openStream();
            String fileName = title + ".png";


            stickerMaker.create(inputStream, fileName);

            System.out.println(title);
            System.out.println();
        }
    }
}
