package me.shinsunyuong.springbootdeveloper.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import me.shinsunyuong.springbootdeveloper.domain.Article;
import me.shinsunyuong.springbootdeveloper.dto.AddArticleRequest;
import me.shinsunyuong.springbootdeveloper.dto.UpdateArticleRequest;
import me.shinsunyuong.springbootdeveloper.repository.BlogRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor //final,@NOTNULL 이 붙는 필드의 생성자 추가
@Service //빈으로 등록
public class BlogService {

    private final BlogRepository blogRepository;


    //블로그 글 추가 메서드
    public Article save(AddArticleRequest request) {
        return blogRepository.save(request.toEntity());
    }
    //조회 메서드
    public List<Article> findAll() {
        return blogRepository.findAll();
    }
    // 삭제 메서드
    public void delete(Long id) {
        blogRepository.deleteById(id);
    }
    //수정 메서드
   @Transactional
    public Article update(Long id, UpdateArticleRequest request) {
        Article article = blogRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("not found:" + id));

        article.update(request.getTitle(), request.getContent());

        return article;
   }
}
