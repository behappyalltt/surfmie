package com.movie.surfmie.config;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Tmdb {
    API_KEY("c6335684800f46660a7357de28c19a68"),
    URL("https://api.themoviedb.org/3"),
    URL_MIDDLE("?language=ko-KR&api_key="),
    IMG_URL("https://image.tmdb.org/t/p/original"),

    PATH_PARAM_TRENDING("/trending"),
    PATH_PARAM_UPCOMING("/movie/upcoming"),
    PATH_PARAM_Detail("/movie/");

    private String value;
}
