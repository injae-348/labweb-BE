package labweb.labweb_be.news.service;

import jakarta.transaction.Transactional;
import labweb.labweb_be.common.image.domain.Image;
import labweb.labweb_be.common.image.domain.ImageType;
import labweb.labweb_be.common.image.repository.ImageRepository;
import labweb.labweb_be.news.domain.News;
import labweb.labweb_be.news.dto.NewsReqDto;
import labweb.labweb_be.news.repository.NewsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NewsService {

    private final NewsRepository newsRepository;
    private final ImageRepository imageRepository;

    @Transactional
    public Long createNews(NewsReqDto newsReqDto) {

        List<Image> images = newsReqDto.images().stream()
                .map(imageReqDto -> new Image(
                        imageReqDto.originalFileName(),
                        imageReqDto.storedFileName(),
                        imageReqDto.fileSize(),
                        imageReqDto.imageType()
                )).collect(Collectors.toList());

        News news = News.builder()
                .date(newsReqDto.date())
                .activity(newsReqDto.activity())
                .content(newsReqDto.content())
                .images(images)
                .build();

        return newsRepository.save(news).getId();
    }


}
