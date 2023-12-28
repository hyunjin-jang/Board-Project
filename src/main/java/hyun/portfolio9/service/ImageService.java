package hyun.portfolio9.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class ImageService {
    @Value("${upload.path}")
    private String uploadPath;

    public String uploadImage(MultipartFile file) {
        try {
            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            File dest = new File(uploadPath + "/" + fileName);
            file.transferTo(dest);
            return fileName;
        } catch (IOException e) {
            return "Failed to upload image";
        }
    }

    public ResponseEntity<Resource> downloadImage(String fileName) {
        Path filePath = Paths.get(uploadPath, fileName).normalize();
        Resource resource;
        try {
            resource = (Resource) new UrlResource(filePath.toUri());
        } catch (MalformedURLException e) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=" + resource.getFilename())
                .body(resource);
    }
}
