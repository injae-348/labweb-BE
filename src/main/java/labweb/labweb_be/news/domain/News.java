package labweb.labweb_be.news.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import labweb.labweb_be.common.BaseEntity;
import labweb.labweb_be.image.domain.Image;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "NEWS")
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 생성자를 통한 값 변경을 목적으로 하는 메시지 차단
public class News extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 일자
    @NotNull
    private LocalDateTime date;

    // 활동 내용 요약 ex) 야구 관람
    @NotNull
    private String activity;

    // 추가 내용 및 설명
    @NotNull
    private String content;

    // 이미지들
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "news_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Image> images = new ArrayList<>();

    @Builder
    public News(LocalDateTime date, String activity, String content, List<Image> images) {
        this.date = date;
        this.activity = activity;
        this.content = content;
        if (images != null) {
            this.images = images;
        }
    }

    public void updateNews(LocalDateTime date, String activity, String content) {
        this.date = date;
        this.activity = activity;
        this.content = content;
    }

}
