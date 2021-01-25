package br.com.felipe.services;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.felipe.dtos.BillToPayDTO;
import br.com.felipe.models.BillToPay;
import br.com.felipe.repositories.BillToPayRepository;

@Service
public class BillToPayService {

	@Autowired
	private BillToPayRepository billRepository;

	/*Metodo Para efetuar o Save das Contas a Pagar
	 * Recebe um JSON do front com todas as informações NonNull da Entidade
	 * e Retorna uma entidade Salva*/
	public BillToPay save(BillToPay bill) {
		if (beforeSave(bill).length() <= 0) {
			bill = verifyDelay(bill);
			bill = billRepository.save(bill);
		}
		return bill;
	}
	
	public BillToPay getById(Long id) {
		return billRepository.getOne(id);
	}

	/*Metodo para buscar as Contas a Pagar Cadastradas
	 * Vare todas as contas a pagar Cadastradas e apos 
	 * cria uma nova lista com com o DTO somente com as informações
	 * necessarias para a view*/
	public List<BillToPayDTO> getAll() {
		List<BillToPay> billList = billRepository.findAll();
		List<BillToPayDTO> dtoList = new ArrayList<>();
		for (BillToPay billToPay : billList) {
			dtoList.add(new BillToPayDTO(billToPay.getName(), billToPay.getOriginalValue(), billToPay.getCorretValue(), billToPay.getDelayDays(), billToPay.getPaymentDate()));
		}
		return dtoList;
	}

	/*Metodo BeforeSave para validar se todos os campos necessarios estão preenchidos
	 * se algum nao estiver retorna um Bad Request com a descrição do campo que nao foi informado*/
	private String beforeSave(BillToPay bill) {
		if (bill.getName().trim().isEmpty()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Nome deve ser Informado");
		}
		if (bill.getPaymentDate() == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Data de Pagamento deve ser Informada");
		}
		if (bill.getDueDate() == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Data de Vencimento deve ser Informada");
		}
		if (bill.getOriginalValue() == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Valor deve ser Informado");
		}
		return "";
	}

	/*Metodo para verificar se a data de pagamento é posterior ao vencimento
	 * em caso da data de pagamento nao ser posterior somente retorna a entidade sem alteração
	 * em caso de ser posterior retorna um dos seguintes casos
	 * se a data de pagamento for posterior ate 3 dias apos o vencimento é aplicada a regra sobre o valor de
	 * 2% de multa e 0,1% de juros ao dia;
	 * se a data de pagamento for posterior a 3 dias apos o vencimento e ate 5 dias é aplicada a regra sobre o valor de
	 * 3% de multa e 0,2% de juros ao dia;
	 * se a data de pagamento for posterior a 5 dias apos o vencimento é aplicada a regra sobre o valor de
	 * 5% de multa e 0,3% de juros ao dia;*/
	private BillToPay verifyDelay(BillToPay bill) {
		if (bill.getPaymentDate().after(bill.getDueDate())) {
			long daysBetween = ChronoUnit.DAYS.between(bill.getDueDate().toInstant(),
					bill.getPaymentDate().toInstant());
			bill.setDelayDays(daysBetween);
			if (daysBetween <= 3) {
				Double penalty = (bill.getOriginalValue() * (2 / 100.0));
				Double interest = (bill.getOriginalValue() * (0.1 / 100.0)) * daysBetween;
				bill.setCorretValue(bill.getOriginalValue() + penalty + interest);
			} else if (daysBetween > 3 && daysBetween <= 5) {
				Double penalty = (bill.getOriginalValue() * (3 / 100.0));
				Double interest = (bill.getOriginalValue() * (0.2 / 100.0)) * daysBetween;
				bill.setCorretValue(bill.getOriginalValue() + penalty + interest);
			} else {
				Double penalty = (bill.getOriginalValue() * (5 / 100.0));
				Double interest = (bill.getOriginalValue() * (0.3 / 100.0)) * daysBetween;
				bill.setCorretValue(bill.getOriginalValue() + penalty + interest);
			}
		}else {
			bill.setCorretValue(0.0);
			bill.setDelayDays(0);
		}
		return bill;
	}

}
