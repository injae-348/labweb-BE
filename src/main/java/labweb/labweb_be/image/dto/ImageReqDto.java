package labweb.labweb_be.image.dto;

import labweb.labweb_be.image.domain.Image;
import labweb.labweb_be.image.domain.ImageType;
import lombok.Builder;

@Builder
public record ImageReqDto(
        String originalFileName,
        String storedFileName,
        Long fileSize,
        ImageType imageType
) {

    public Image toEntity() {
        return Image.builder()
                .originalFileName(originalFileName)
                .storedFileName(storedFileName)
                .fileSize(fileSize)
                .imageType(imageType)
                .build();
    }

}
