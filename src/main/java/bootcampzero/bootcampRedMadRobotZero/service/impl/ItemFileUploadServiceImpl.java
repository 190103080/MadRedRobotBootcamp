package bootcampzero.bootcampRedMadRobotZero.service.impl;

import bootcampzero.bootcampRedMadRobotZero.models.Item;
import bootcampzero.bootcampRedMadRobotZero.service.ItemFileUploadService;
import bootcampzero.bootcampRedMadRobotZero.service.ItemService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class ItemFileUploadServiceImpl implements ItemFileUploadService {

    @Autowired
    private ItemService itemService;

    @Value("${myloadURL}")
    private String myLoadURL;

    @Value("${uploadURL}")
    private String uploadURL;

    @Override
    public boolean uploadItemAvatar(MultipartFile file, Item item) {

        try {
            if (file.getContentType().equals("image/jpeg") || file.getContentType().equals("image/png")) {

                String fileName = DigestUtils.sha1Hex(item.getId() + " image") +".png";
                byte bytes[] = file.getBytes();
                Path path = Paths.get(uploadURL + fileName);
                Files.write(path, bytes);

                item.setImage(fileName);
                itemService.saveItem(item);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return true;
    }

    @Override
    public byte[] getItemAvatar(String token) throws IOException {
        String picURL = myLoadURL + "image.png";
        if (token != null) {
            if (token.equals(itemService.getCurrentItem().getImage()))
                picURL = myLoadURL + token;
        }

        InputStream in;

        try {
            ClassPathResource resource = new ClassPathResource(picURL);
            in = resource.getInputStream();
        } catch (Exception e) {
            picURL = myLoadURL + "image.png";
            ClassPathResource resource = new ClassPathResource(picURL);
            in = resource.getInputStream();
        }

        return IOUtils.toByteArray(in);
    }

}
