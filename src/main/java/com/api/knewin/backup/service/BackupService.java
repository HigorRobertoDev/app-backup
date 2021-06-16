package com.api.knewin.backup.service;

import com.api.knewin.backup.dao.ConteudoBackupDao;
import com.api.knewin.backup.entity.ArticleId;
import com.api.knewin.backup.entity.ConteudoBackup;
import com.api.knewin.backup.entity.ConteudoBackupCsv;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class BackupService {

    @Autowired
    private ConteudoBackupDao conteudoBackupRepository;

    public void executeBackup() throws IOException {

        Reader reader = Files.newBufferedReader(Paths.get("C:\\projetos-spring\\backup\\csv\\conteudo_principal_A1.csv"));

        CsvToBean<ConteudoBackupCsv> csvToBean = new CsvToBeanBuilder(reader)
                .withType(ConteudoBackupCsv.class)
                .build();

        List<ConteudoBackupCsv> conteudoBackupCsvList = csvToBean.parse();

        // ######################################################################

        Reader readerB = Files.newBufferedReader(Paths.get("C:\\projetos-spring\\backup\\csv\\conteudo_principal_B1.csv"));

        CsvToBean<ArticleId> csvToBeanB = new CsvToBeanBuilder(readerB)
                .withType(ArticleId.class)
                .build();

        List<ArticleId> conteudoBackupCsvListB = csvToBeanB.parse();

        System.out.println(conteudoBackupCsvList.size());

        AtomicInteger totalBackup = new AtomicInteger();
        totalBackup.addAndGet(conteudoBackupCsvList.size());

        AtomicInteger countBackup = new AtomicInteger();
        conteudoBackupCsvList.forEach(c -> {

            countBackup.addAndGet(1);
            System.out.println("Numero do backup: " + countBackup.get() + " de: " + totalBackup.get());

            ArticleId articleId = conteudoBackupCsvListB.stream()
                    .filter(a -> a.getArticle_id().equals(c.getArticle_id()))
                    .reduce((a, b) -> {
                        throw new RuntimeException();
                    }).get();

            ConteudoBackup backup = new ConteudoBackup();

            backup.setIdOrganizacao(c.getId_organizacao());
            backup.setNomeOrganizacao(c.getNome_organizacao());
            backup.setIdUsuario(c.getId_usuario());
            backup.setEmail(c.getEmail());
            backup.setNomeUsuario(c.getNome_usuario());
            backup.setArticleId(c.getArticle_id());
            backup.setTitle(c.getTitle());

            backup.setUrl_backup(articleId.getUrl_original());

            conteudoBackupRepository.save(backup);

        });
    }

}
