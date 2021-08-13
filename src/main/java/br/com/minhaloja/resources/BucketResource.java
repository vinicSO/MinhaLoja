package br.com.minhaloja.resources;

import br.com.minhaloja.services.AmazonClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/storage")
public class BucketResource {

    @Autowired
    private AmazonClientService amazonClientService;

    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    public String uploadFile (@RequestPart(value = "arquivo") MultipartFile file) {
        return amazonClientService.uploadFile(file);
    }

    @RequestMapping(value = "/deleteFile", method = RequestMethod.DELETE)
    public String deleteFile (@RequestPart (value = "url") String fileUrl) {
        return amazonClientService.deleteFileFromS3Bucket(fileUrl);
    }

    @RequestMapping(value = "/updateFile/{id}", method = RequestMethod.PUT)
    public String updateFile (@RequestPart (value = "arquivo") MultipartFile file, @PathVariable (value = "id") Integer produtoId) {
        return amazonClientService.updateFile(file, produtoId);
    }
}
