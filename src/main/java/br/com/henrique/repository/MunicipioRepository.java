package br.com.henrique.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.henrique.model.Municipio;

@Repository
public interface MunicipioRepository extends JpaRepository<Municipio, Integer>  {

}
