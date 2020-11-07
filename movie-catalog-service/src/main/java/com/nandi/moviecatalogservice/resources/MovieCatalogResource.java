package com.nandi.moviecatalogservice.resources;

import com.nandi.moviecatalogservice.models.CatalogItem;
import com.nandi.moviecatalogservice.models.Movie;
import com.nandi.moviecatalogservice.models.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/catalog")
@RestController
public class MovieCatalogResource {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId")  String userId)
    {

        List<Rating> ratings = Arrays.asList(
                new Rating("1234", 4),
                new Rating("2234", 3)
                );

        return ratings.stream().map( rating -> {

            Movie movie = restTemplate.getForObject("http://localhost:8085/movies/"+rating.getMovieId(), Movie.class);
            return new CatalogItem(movie.getName(), "Description Empty" ,rating.getRating());
         }).collect(Collectors.toList());

        //return Collections.singletonList(new CatalogItem("Transformers", "Tests", 4));
    }
}
