package com.movie.surfmie.dto;

import lombok.Data;

@Data
public class MovieCreditsDto {
    private int id;
    private Cast cast[];
    private Cast crew[];

    static class Cast {
        public boolean adult;
        public int gender;
        public int id;
        public String known_for_department;
        public String name;
        public String original_name;
        public int popularity;
        public String profile_path;
        public int cast_id;
        public String character;
        public String credit_id;
        public int order;
    }

    static class Crew {
        public boolean adult;
        public int gender;
        public int id;
        public String known_for_department;
        public String name;
        public String original_name;
        public int popularity;
        public String profile_path;
        public int credit_id;
        public String department;
        public String job;
    }
}