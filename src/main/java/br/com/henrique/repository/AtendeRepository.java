package br.com.henrique.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.henrique.model.Atende;

@Repository
public interface AtendeRepository extends JpaRepository<Atende, Integer>{

}
