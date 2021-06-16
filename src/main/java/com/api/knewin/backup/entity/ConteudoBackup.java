package com.api.knewin.backup.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class ConteudoBackup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long idOrganizacao;

    private String nomeOrganizacao;
    private Long idUsuario;
    private String email;
    private String nomeUsuario;
    private Long articleId;
    private String title;

    @Column(length = 1000)
    private String url_backup;

}
