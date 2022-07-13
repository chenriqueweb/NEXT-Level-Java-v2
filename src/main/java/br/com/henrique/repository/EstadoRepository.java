package br.com.henrique.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.henrique.model.Estado;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, String> {

}
