package com.movie.surfmie.dto;

import lombok.Data;

@Data
public class MovieDetailResponseDto {
    private boolean adult;
    private String backdrop_path;
    private Object belongs_to_collection;
    private int budget;
    private Genre genres[];
    private String homepage;
    private int id;
    private String imdb_id;
    private String original_language;
    private String original_title;
    private String overview;
    private int popularity;
    private String poster_path;
    private Production_company production_companies[];
    private Production_country production_countries[];
    private String release_date;
    private int revenue;
    private int runtime;
    private Spoken_language spoken_languages[];
    private String status;
    private String tagline;
    private String title;
    private boolean video;
    private int vote_average;
    private int vote_count;

    static class Genre {
        public int id;
        public String name;
    }

    static class Production_company {
        public String name;
        public int id;
        public String logo_path;
        public String origin_country;
    }

    static class Production_country {
        public String iso_3166_1;
        public String name;
    }

    static class Spoken_language {
        public String iso_639_1;
        public String name;
    }
}