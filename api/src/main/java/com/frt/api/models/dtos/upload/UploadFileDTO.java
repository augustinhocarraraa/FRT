package com.frt.api.models.dtos.upload;

import org.springframework.web.multipart.MultipartFile;

public record UploadFileDTO(
    MultipartFile arquivo,
    String descricao,
    String categoria
) {}