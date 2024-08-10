package labweb.labweb_be.image.service;

import labweb.labweb_be.image.domain.Image;
import labweb.labweb_be.image.domain.ImageType;
import labweb.labweb_be.image.repository.ImageRepository;
import labweb.labweb_be.utils.image.FileGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ImageUploadServiceTest {
    @Mock
    private ImageRepository imageRepository;

    @Mock
    private FileGenerator fileGenerator;

    @InjectMocks
    private ImageUploadService imageUploadService;

    @BeforeEach
    void setUP() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("이미지 파일 저장 성공")
    void addImageSuccess() throws Exception {
        // given
        List<MultipartFile> files = new ArrayList<>();
        MultipartFile file = mock(MultipartFile.class);
        files.add(file);

        ImageType imageType = ImageType.NEWS;
        List<Image> images = new ArrayList<>();
        Image image = Image.builder()
                .originalFileName("test-image.png")
                .storedFileName("stored-image.png")
                .fileSize(38694L)
                .imageType(imageType)
                .build();
        images.add(image);

        // when
        when(fileGenerator.parseImageInfo(files, imageType)).thenReturn(images);
        when(imageRepository.save(image)).thenReturn(image);

        List<Image> result = imageUploadService.addImage(files, imageType);

        // then
        assertEquals(1, result.size());
        verify(imageRepository, times(1)).save(image);
    }

    @Test
    @DisplayName("이미지 파일 비어있는 경우")
    void addImageEmtyFiles() throws Exception {
        // given
        List<MultipartFile> files = new ArrayList<>();
        List<Image> images = new ArrayList<>();

        ImageType imageType = ImageType.NEWS;

        // when
        when(fileGenerator.parseImageInfo(files, ImageType.NEWS)).thenReturn(images);
        List<Image> result = imageUploadService.addImage(files, imageType);

        // then
        assertTrue(result.isEmpty());
        verify(imageRepository, never()).save(any(Image.class));
    }

}