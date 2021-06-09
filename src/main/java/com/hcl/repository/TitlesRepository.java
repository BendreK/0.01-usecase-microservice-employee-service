package com.hcl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.model.TitlePk;
import com.hcl.model.Titles;

@Repository
public interface TitlesRepository extends JpaRepository<Titles, TitlePk> {

}
