package labweb.labweb_be.news.service;

import jakarta.transaction.Transactional;
import labweb.labweb_be.image.domain.Image;
import labweb.labweb_be.image.domain.ImageType;
import labweb.labweb_be.image.repository.ImageRepository;
import labweb.labweb_be.image.service.ImageUploadService;
import labweb.labweb_be.news.domain.News;
import labweb.labweb_be.news.dto.NewsReqDto;
import labweb.labweb_be.news.repository.NewsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NewsService {

    private final ImageUploadService imageUploadService;

    private final NewsRepository newsRepository;
    private final ImageRepository imageRepository;

    @Transactional
    public Long createNews(NewsReqDto newsReqDto, List<MultipartFile> files, ImageType imageType) throws Exception {
        List<Image> images = Collections.emptyList();

        if (files != null && !files.isEmpty()) {
            images = imageUploadService.addImage(files, imageType);
        }

        News news = newsReqDto.toEntity(images);
        return newsRepository.save(news).getId();
    }

}
