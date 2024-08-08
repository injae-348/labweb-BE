package labweb.labweb_be.common.image.repository;

import labweb.labweb_be.common.image.domain.Image;
import labweb.labweb_be.common.image.domain.ImageType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
    //TODO : 이미지 타입에 따라, 해당 게시글의 PK에 따라 구분하기 -> 이게 맞을까..?
    List<Image> findImage(ImageType imageType, String storedFileName);
}
