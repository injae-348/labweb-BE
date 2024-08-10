package labweb.labweb_be.common.image.repository;

import labweb.labweb_be.common.image.domain.Image;
import labweb.labweb_be.common.image.domain.ImageType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {

    List<Image> findImageByStoredFileName(String storedFileName);
}
