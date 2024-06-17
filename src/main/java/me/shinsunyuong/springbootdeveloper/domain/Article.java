package me.shinsunyuong.springbootdeveloper.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Article {

        @Id //id 필드를 기본키로 설정
        @GeneratedValue(strategy = GenerationType.IDENTITY) //기본키를 1씩 증가
        @Column(name = "Id", updatable = false)
        private Long id;

        @Column(name = "title", nullable = false)
        private String title;

        @Column(name = "content", nullable = false)
        private String content;

        @Builder //빌더 패턴으로 객체생성
        public Article(String title, String content) {
            this.title = title;
            this.content = content;
        }
        //엔티티에서 요청 받은 값을 수정하는 메서드
        public void update(String title, String content) {
            this.title = title;
            this.content = content;
        }
    }
