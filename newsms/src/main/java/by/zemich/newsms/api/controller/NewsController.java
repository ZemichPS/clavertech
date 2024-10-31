package by.zemich.newsms.api.controller;

import by.zemich.newsms.api.controller.dto.request.NewsPageRequest;
import by.zemich.newsms.api.controller.dto.request.NewsRequest;
import by.zemich.newsms.api.controller.dto.response.CommentFullResponse;
import by.zemich.newsms.api.controller.dto.response.NewsResponse;
import by.zemich.newsms.api.controller.dto.response.ShortCommentResponse;
import by.zemich.newsms.core.domain.News;
import by.zemich.newsms.core.service.NewsRestService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageImpl;
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

    @GetMapping("/{id}/comments")
    public ResponseEntity<PageImpl<ShortCommentResponse>> getCommentsByNewsId(
            @PathVariable UUID id,
            @RequestParam(name = "page_number", defaultValue = "1") int pageNumber,
            @RequestParam(name = "page_size", defaultValue = "10") int pageSize,
            @RequestParam(name = "sort_by") String sortBy
    ) {

        NewsPageRequest pageRequest = NewsPageRequest.builder()
                .id(id)
                .pageNumber(pageNumber)
                .pageSize(pageSize)
                .sortBy(sortBy)
                .build();
        PageImpl<ShortCommentResponse> page = newsRestService.getCommentsPage(pageRequest);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{news_id}/comments/{comment_id}")
    public ResponseEntity<CommentFullResponse> getCommentByNewsId(
            @PathVariable(name = "news_id") UUID newsId,
            @PathVariable(name = "comment_id") UUID commentId
    ) {
        CommentFullResponse response = newsRestService.getCommentByNewsIdAndCommentId(newsId, commentId);
        return ResponseEntity.ok(response);
    }


//    @GetMapping("/{news_id}/comments/{comment_id}")
//    public ResponseEntity<CommentFullResponse> getCommentByAuthorId(
//            @PathVariable(name = "news_id") UUID newsId,
//            @PathVariable(name = "comment_id") UUID commentId
//    ) {
//        CommentFullResponse response = newsRestService.getCommentByNewsIdAndCommentId(newsId, commentId);
//        return ResponseEntity.ok(response);
//    }


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
