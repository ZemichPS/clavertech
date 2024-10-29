package by.zemich.newsms.api.controller;

import by.zemich.newsms.api.controller.dto.request.AddNewsRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/comment")
public class CommentController {

    public ResponseEntity<URI> add(@RequestBody AddNewsRequest addNewsRequest){

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(registered.getId())
                .toUri();
    }
}
