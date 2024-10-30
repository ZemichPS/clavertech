package by.zemich.newsms.api.controller;

import by.zemich.newsms.api.controller.dto.request.NewsRequest;
import by.zemich.newsms.api.controller.dto.response.NewsResponse;
import by.zemich.newsms.core.domain.News;
import by.zemich.newsms.core.service.NewsRestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/news")
@RequiredArgsConstructor
public class NewsController {

    private final NewsRestService newsRestService;

    @PostMapping
    public ResponseEntity<URI> create(@RequestBody NewsRequest newsRequest) {
        News savedNews = newsRestService.save(newsRequest);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedNews.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<NewsResponse> getById(@PathVariable UUID id) {
        NewsResponse newsResponse = newsRestService.findById(id);
        return ResponseEntity.ok(newsResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NewsResponse> updateById(@PathVariable UUID id, NewsRequest newsRequest) {
        NewsResponse newsResponse = newsRestService.update(id, newsRequest);
        return ResponseEntity.ok(newsResponse);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<NewsResponse> patchById(@PathVariable UUID id, NewsRequest newsRequest) {
        NewsResponse newsResponse = newsRestService.patchById(id, newsRequest);
        return ResponseEntity.ok(newsResponse);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable UUID id) {
        newsRestService.deleteById(id);
        return ResponseEntity.noContent().build();
    }


}
