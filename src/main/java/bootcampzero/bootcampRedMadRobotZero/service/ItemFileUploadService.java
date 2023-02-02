package bootcampzero.bootcampRedMadRobotZero.service;

import bootcampzero.bootcampRedMadRobotZero.models.Item;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ItemFileUploadService {

    boolean uploadItemAvatar(MultipartFile file, Item item);
    byte[] getItemAvatar(String token) throws IOException;

}
