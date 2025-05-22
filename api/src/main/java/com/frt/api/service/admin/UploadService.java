package com.frt.api.service.admin;

import com.frt.api.models.dtos.upload.UploadFileDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class UploadService {

    @Value("${upload.directory}")
    private String uploadDir;

    public String uploadArquivo(UploadFileDTO dto) throws IOException {
        MultipartFile arquivo = dto.arquivo();
        String nomeUnico = UUID.randomUUID() + "_" + arquivo.getOriginalFilename();
        Path caminho = Paths.get(uploadDir + nomeUnico);

        if (!arquivo.isEmpty()) {
            Files.createDirectories(caminho.getParent());
            Files.write(caminho, arquivo.getBytes());
            return nomeUnico;
        }
        throw new IOException("Arquivo vazio");
    }
}