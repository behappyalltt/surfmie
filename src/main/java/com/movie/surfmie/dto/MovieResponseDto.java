package com.movie.surfmie.dto;

import lombok.Data;

@Data
public class MovieResponseDto {
    private int page;
    private int total_pages;
    private int total_results;
    private String maximum;
    private String minimum;
    private Movie[] results;

    static class Movie {
        public String poster_path;
        public boolean adult;
        public String overview;
        public String release_date;
        public int[] genre_ids;
        public String original_title;
        public String original_language;
        public String title;
        public String backdrop_path;
        public int popularity;
        public int vote_count;
        public boolean video;
        public int vote_average;
    }
}
