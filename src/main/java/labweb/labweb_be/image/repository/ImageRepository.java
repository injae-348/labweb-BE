package labweb.labweb_be.image.repository;

import labweb.labweb_be.image.domain.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
    // Todo: QueryDsl 적용
    List<Image> findByStoredFileName(String storedFileName);
}
