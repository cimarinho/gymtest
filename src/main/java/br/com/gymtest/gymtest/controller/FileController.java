package br.com.gymtest.gymtest.controller;

import br.com.gymtest.gymtest.domain.Race;
import br.com.gymtest.gymtest.facade.RaceFacade;
import br.com.gymtest.gymtest.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.nio.charset.StandardCharsets;

@RestController
public class FileController {

    @Autowired
    private RaceFacade fileFacade;

    @Autowired
    private FileUtils fileUtils;

    @PostMapping("/uploadFile")
    public Race uploadFile(@RequestParam("file") MultipartFile multipart) throws Exception {
        String content = new String(multipart.getBytes(), StandardCharsets.UTF_8);
        String file[] = content.split("\n");
        valideFile(file);
        return fileFacade.read(file);
    }


    void valideFile(String file[]){
        // TODO validate file
    }

}
