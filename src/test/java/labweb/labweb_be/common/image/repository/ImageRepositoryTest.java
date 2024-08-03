package labweb.labweb_be.common.image.repository;

import labweb.labweb_be.common.image.domain.Image;
import labweb.labweb_be.common.image.domain.ImageType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;



@SpringBootTest
public class ImageRepositoryTest {

    @Autowired
    private ImageRepository imageRepository;

    @Test
    @DisplayName("이미지 파일 저장 확인")
    void saveImageFile() {
        Image image = Image.builder()
                .originalFileName("test-image.png")
                .storedFileName("stored-image.png")
                .fileSize(38694L)
                .imageType(ImageType.NEWS)
                .build();

        Image savedImage = imageRepository.save(image);
        assertThat(savedImage).isNotNull();
        assertThat(savedImage.getId()).isNotNull();
    }

}