package me.shinsunyuong.springbootdeveloper.repository;

import me.shinsunyuong.springbootdeveloper.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Article, Long> {
}
