package labweb.labweb_be.image.service;

import labweb.labweb_be.image.domain.Image;
import labweb.labweb_be.image.domain.ImageType;
import labweb.labweb_be.image.repository.ImageRepository;
import labweb.labweb_be.utils.image.FileGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

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
            return savedImages;
        }
    }

}
