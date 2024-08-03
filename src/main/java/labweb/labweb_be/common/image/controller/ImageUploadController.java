package labweb.labweb_be.common.image.controller;

import labweb.labweb_be.common.image.domain.Image;
import labweb.labweb_be.common.image.domain.ImageType;
import labweb.labweb_be.common.image.service.ImageUploadService;
import labweb.labweb_be.utils.api.response.ApiResponse;
import labweb.labweb_be.utils.exception.model.CustomException;
import labweb.labweb_be.utils.exception.model.ErrorCode;
import labweb.labweb_be.utils.exception.model.ExceptionDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ImageUploadController {
    private final ImageUploadService imageUploadService;

    @PostMapping("/image")
    public ApiResponse<List<Image>> uploadImages(
            @RequestParam("files") List<MultipartFile> files,
            @RequestParam("imageType") ImageType imageType
            ) {
        try {
            List<Image> images = imageUploadService.addImage(files, imageType);
            if(images.isEmpty()) {
                return ApiResponse.noContent();
            }
            return ApiResponse.created(images);
        } catch (Exception e) {
            return ApiResponse.fail(new CustomException(ErrorCode.INTERNAL_SERVER_ERROR));
        }
    }

}
