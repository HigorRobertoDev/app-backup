package com.api.knewin.backup.dao;

import com.api.knewin.backup.entity.ConteudoBackup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConteudoBackupDao extends JpaRepository<ConteudoBackup, Long> {

}
