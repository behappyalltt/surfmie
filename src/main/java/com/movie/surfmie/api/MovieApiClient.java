package com.movie.surfmie.api;

import com.movie.surfmie.dto.MovieCreditsDto;
import com.movie.surfmie.dto.MovieDetailResponseDto;
import com.movie.surfmie.dto.MovieResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

// tmdb api 연동
// RestTemplate 사용
@RequiredArgsConstructor
@Service
public class MovieApiClient {
    private RestTemplate restTemplate = new RestTemplate();
    private final String api_key = "c6335684800f46660a7357de28c19a68";

    public MovieResponseDto requestMovieTrending(String url) {
        final HttpHeaders headers = new HttpHeaders();
        final HttpEntity<String> entity = new HttpEntity<>(headers);

        return restTemplate.exchange(url, HttpMethod.GET, entity, MovieResponseDto.class, headers).getBody();
    }

    public MovieDetailResponseDto requestMovieDetail(String url) {
        final HttpHeaders headers = new HttpHeaders();
        final HttpEntity<String> entity = new HttpEntity<>(headers);

        return restTemplate.exchange(url, HttpMethod.GET, entity, MovieDetailResponseDto.class, headers).getBody();
    }

    public MovieCreditsDto getMovieCredits(String url) {
        final HttpHeaders headers = new HttpHeaders();
        final HttpEntity<String> entity = new HttpEntity<>(headers);

        return restTemplate.exchange(url, HttpMethod.GET, entity, MovieCreditsDto.class, headers).getBody();
    }
}
