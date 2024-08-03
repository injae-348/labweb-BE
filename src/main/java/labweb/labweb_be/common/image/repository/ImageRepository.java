package labweb.labweb_be.common.image.repository;

import labweb.labweb_be.common.image.domain.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
