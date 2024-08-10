package labweb.labweb_be.image.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import labweb.labweb_be.common.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "IMAGE")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Image extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String originalFileName;

    @NotNull
    private String storedFileName;

    private Long fileSize;

    @NotNull
    @Enumerated(EnumType.STRING)
    private ImageType imageType;

    @Builder
    public Image(String originalFileName, String storedFileName, Long fileSize, ImageType imageType) {
        this.originalFileName = originalFileName;
        this.storedFileName = storedFileName;
        this.fileSize = fileSize;
        this.imageType = imageType;
    }
}
