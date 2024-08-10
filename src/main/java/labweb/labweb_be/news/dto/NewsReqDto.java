package labweb.labweb_be.news.dto;

import labweb.labweb_be.image.domain.Image;
import labweb.labweb_be.image.dto.ImageReqDto;
import labweb.labweb_be.news.domain.News;
import lombok.Builder;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record NewsReqDto(
        LocalDateTime date,
        String activity,
        String content
) {

    public News toEntity(List<Image> images) {
        return News.builder()
                .date(date)
                .activity(activity)
                .content(content)
                .images(images)
                .build();
    }
}
