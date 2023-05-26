package com.example.yourstar.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Blob;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostWriteFormDto {
    private String contents;
    private Blob meta;
}