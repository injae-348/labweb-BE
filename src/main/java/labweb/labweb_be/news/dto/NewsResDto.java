package labweb.labweb_be.news.dto;

import labweb.labweb_be.common.image.domain.Image;
import labweb.labweb_be.common.image.dto.ImageResDto;
import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Builder
public record NewsResDto(
        Long id,
        LocalDateTime date,
        String activity,
        String content,
        List<ImageResDto> images
) {
}
