package labweb.labweb_be.common.image.dto;

import labweb.labweb_be.common.image.domain.ImageType;
import lombok.Builder;

@Builder
public record ImageReqDto(
        String originalFileName,
        String storedFileName,
        Long fileSize,
        ImageType imageType
) {
}
