package me.shinsunyuong.springbootdeveloper.controller;

import lombok.RequiredArgsConstructor;
import me.shinsunyuong.springbootdeveloper.domain.Article;
import me.shinsunyuong.springbootdeveloper.dto.AddArticleRequest;
import me.shinsunyuong.springbootdeveloper.dto.ArticleResponse;
import me.shinsunyuong.springbootdeveloper.dto.UpdateArticleRequest;
import me.shinsunyuong.springbootdeveloper.service.BlogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController // HTTP RESPONSE BODY에 객체 데이터를 JSON 형식으로 반환하는 컨드롤러
public class BlogApiController {

    private final BlogService blogService;

    // HTTP 메서드가 POST일때 전달받은 URL과 동일하면 메서드 진행
    @PostMapping("/api/articles")
    //@Request Body로 요청 본문 값 매핑
    public ResponseEntity<Article> addArticle(@RequestBody AddArticleRequest request) {
        Article savedArticle = blogService.save(request);

        //유효한 자원이 성공적으로 생성되었으면 저장된 블로그 글 정보를 응딥 객체에 담아 전송
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedArticle);
    }
    @GetMapping("/api/articles")
    public ResponseEntity<List<ArticleResponse>> findAllArticles() {
        List<ArticleResponse> articles = blogService.findAll()
                .stream()
                .map(ArticleResponse::new)
                .toList();
        return ResponseEntity.ok().body(articles);
    }
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable Long id) {
        blogService.delete(id);

        return ResponseEntity.ok()
                .build();
    }
    @PutMapping("/api/articles/{id}")
    public ResponseEntity<Article> updateArticle(@PathVariable Long id, @RequestBody UpdateArticleRequest request) {
        Article updateArticle = blogService.update(id, request);

        return ResponseEntity.ok().body(updateArticle);
    }
}
