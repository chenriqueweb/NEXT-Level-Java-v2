package br.com.henrique.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.henrique.model.Microzona;

@Repository
public interface MicrozonaRepository extends JpaRepository<Microzona, Integer> {

}
