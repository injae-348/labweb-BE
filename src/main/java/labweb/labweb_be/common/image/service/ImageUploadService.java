package labweb.labweb_be.common.image.service;

import labweb.labweb_be.common.image.domain.Image;
import labweb.labweb_be.common.image.domain.ImageType;
import labweb.labweb_be.common.image.repository.ImageRepository;
import labweb.labweb_be.utils.image.FileGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ImageUploadService {
    private final ImageRepository imageRepository;
    private final FileGenerator fileGenerator;

    public List<Image> addImage(List<MultipartFile> files, ImageType imageType) throws Exception {
        List<Image> images = fileGenerator.parseImageInfo(files, imageType);

        if(images.isEmpty()) {
            return new ArrayList<>(1);
        } else {
            List<Image> savedImages = new ArrayList<>();
            for (Image image : images) {
                savedImages.add(imageRepository.save(image));
            }
        }
        return images;
    }

}
