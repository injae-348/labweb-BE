package labweb.labweb_be.common.image.domain;

import lombok.Getter;

@Getter
public enum ImageType {

    NEWS("NEWS"),
    PROFESSOR("PROFESSOR"),
    ALUMNI("ALUMNI"),
    CURRENT_STUDENT("CURRENT_STUDENT"),
    CURRENT_PROJECT("CURRENT_PROJECT"),
    PAST_PROJECT("PAST_PROJECT");

    private final String imageType;

    ImageType(String imageType) {
        this.imageType = imageType;
    }
}
