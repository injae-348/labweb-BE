package labweb.labweb_be.news.controller;

import labweb.labweb_be.image.domain.Image;
import labweb.labweb_be.image.domain.ImageType;
import labweb.labweb_be.image.service.ImageUploadService;
import labweb.labweb_be.news.dto.NewsReqDto;
import labweb.labweb_be.news.service.NewsService;
import labweb.labweb_be.utils.api.response.ApiResponse;
import labweb.labweb_be.utils.exception.model.CustomException;
import labweb.labweb_be.utils.exception.model.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/news")
public class NewsController {

    private final NewsService newsService;

    @PostMapping
    public ApiResponse<Long> createNews(
            @RequestPart("files") List<MultipartFile> files, // 이미지(MultipartFile)
            @RequestPart("newsReqDto") NewsReqDto newsReqDto, // JSON 객체
            @RequestParam("image-type") ImageType imageType // 쿼리파라미터
    ) {
        try {
            return ApiResponse.created(newsService.createNews(newsReqDto, files, imageType));
        } catch (Exception e) {
            return ApiResponse.fail(new CustomException(ErrorCode.INTERNAL_SERVER_ERROR));
        }
    }
}
