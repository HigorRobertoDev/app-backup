package com.api.knewin.backup.entity;

import lombok.Data;

import javax.persistence.Column;

@Data
public class ConteudoBackupCsv {

    private Long id_organizacao;

    private String nome_organizacao;
    private Long id_usuario;
    private String email;
    private String nome_usuario;
    private Long article_id;
    private String title;

}
