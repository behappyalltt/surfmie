package com.movie.surfmie.service;

import com.movie.surfmie.api.MovieApiClient;
import com.movie.surfmie.config.Tmdb;
import com.movie.surfmie.dto.MovieDetailResponseDto;
import com.movie.surfmie.dto.MovieResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MovieService {
    private final MovieApiClient movieApiClient;

    public MovieResponseDto getTrending(String url)
    {
        return movieApiClient.requestMovieTrending(Tmdb.URL.getValue() + Tmdb.PATH_PARAM_TRENDING.getValue()
                + url + Tmdb.URL_MIDDLE.getValue() + Tmdb.API_KEY.getValue());
    }

    public MovieResponseDto getUpcoming()
    {
        return movieApiClient.requestMovieTrending(Tmdb.URL.getValue() + Tmdb.PATH_PARAM_UPCOMING.getValue()
                + Tmdb.URL_MIDDLE.getValue() + Tmdb.API_KEY.getValue() + "&region=KR");
    }

    public MovieDetailResponseDto getMovieDetail(String id)
    {
        return movieApiClient.requestMovieDetail(Tmdb.URL.getValue() + Tmdb.PATH_PARAM_Detail.getValue()
                + id + Tmdb.URL_MIDDLE.getValue() + Tmdb.API_KEY.getValue());
    }
}
