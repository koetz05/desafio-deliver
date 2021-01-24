package br.com.felipe.repositories;

import org.springframework.stereotype.Repository;

import br.com.felipe.models.BillToPay;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface BillToPayRepository extends JpaRepository<BillToPay, Long>{

}
