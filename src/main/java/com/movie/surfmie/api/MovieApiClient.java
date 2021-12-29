package com.movie.surfmie.api;

import com.movie.surfmie.dto.MovieCreditsDto;
import com.movie.surfmie.dto.MovieDetailResponseDto;
import com.movie.surfmie.dto.MovieResponseDto;
import lombok.RequiredArgsConstructor;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

// tmdb api 연동
// RestTemplate 사용
@RequiredArgsConstructor
@Service
public class MovieApiClient {
    private RestTemplate restTemplate = getRestTemplate();

    // connection pool 설정
    public RestTemplate getRestTemplate() {
        HttpClient httpClient = HttpClientBuilder.create()
                .setMaxConnTotal(100)           // 최대로 오픈되는 커넥션 수
                .setMaxConnPerRoute(5)          // IP, 포트 1쌍에 대해 수행할 커넥션 수
                .build();
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setReadTimeout(5000);           // 읽기시간초과 (ms)
        factory.setConnectTimeout(3000);        // 연결시간초과 (ms)
        factory.setHttpClient(httpClient);

        return new RestTemplate(factory);
    }

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
