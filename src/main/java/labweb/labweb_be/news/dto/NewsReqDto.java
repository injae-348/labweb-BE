package labweb.labweb_be.news.dto;

import labweb.labweb_be.common.image.domain.Image;
import labweb.labweb_be.common.image.dto.ImageReqDto;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record NewsReqDto(
        LocalDateTime date,
        String activity,
        String content,
        List<ImageReqDto> images
) {
}
