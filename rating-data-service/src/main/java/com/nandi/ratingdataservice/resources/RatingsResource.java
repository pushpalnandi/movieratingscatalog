package com.nandi.ratingdataservice.resources;


import com.nandi.ratingdataservice.models.Rating;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/ratingsdata")
@RestController
public class RatingsResource {

    @RequestMapping("/{movieId}")
    public Rating getRating(@PathVariable ("movieId") String movieId)
    {
        return new Rating(movieId, 4);
    }
}
