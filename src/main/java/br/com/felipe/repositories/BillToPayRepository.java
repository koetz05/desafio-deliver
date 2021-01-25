package br.com.felipe.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.felipe.models.BillToPay;

/*Interface das Contas a Pagar*/
@Repository
public interface BillToPayRepository extends JpaRepository<BillToPay, Long>{
		

}
