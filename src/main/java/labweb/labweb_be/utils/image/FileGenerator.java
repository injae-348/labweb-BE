package labweb.labweb_be.utils.image;

import labweb.labweb_be.image.domain.Image;
import labweb.labweb_be.image.domain.ImageType;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Component
public class FileGenerator {
    public List<Image> parseImageInfo(List<MultipartFile> multipartFiles, ImageType imageType) throws Exception {

        List<Image> images = new ArrayList<>();

        if(multipartFiles.isEmpty()) {
            return images;
        }

        String absoultePath = new File("").getAbsolutePath() + "\\";
        String path = "src/main/resources/static/images/";

        File file = new File(path);

        if(!file.exists()) {
            file.mkdirs();
        }

        for(MultipartFile multipartFile : multipartFiles) {
            if(!multipartFile.isEmpty()) {
                String contentType = multipartFile.getContentType();
                String originalFileExtension;

                if(ObjectUtils.isEmpty(contentType)) {
                    break;
                } else {
                    if (contentType.contains("image/jpeg")) {
                        originalFileExtension = ".jpg";
                    } else if (contentType.contains("image/png")) {
                        originalFileExtension = ".png";
                    } else if (contentType.contains("image/gif")) {
                        originalFileExtension = ".gif";
                    }
                    else {
                        break;
                    }
                }
                String storedFileName = System.nanoTime() + originalFileExtension;

                Image image = createImageObject(multipartFile, path, storedFileName, imageType);

                images.add(image);

                file = new File(absoultePath + path + "/" + storedFileName);
                multipartFile.transferTo(file);
            }
        }
        return images;
    }

    private Image createImageObject(MultipartFile multipartFile, String path, String storedFileName, ImageType imageType) {
        return Image.builder()
                .originalFileName(multipartFile.getOriginalFilename())
                .storedFileName(storedFileName)
                .fileSize(multipartFile.getSize())
                .imageType(imageType)
                .build();
    }
}
