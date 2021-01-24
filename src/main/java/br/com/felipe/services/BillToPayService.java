package br.com.felipe.services;

import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.felipe.models.BillToPay;
import br.com.felipe.repositories.BillToPayRepository;

@Service
public class BillToPayService {

	@Autowired
	private BillToPayRepository billRepository;

	public BillToPay save(BillToPay bill) {
		if (beforeSave(bill).length() <= 0) {
			bill = verifyDelay(bill);
			bill = billRepository.save(bill);
		}
		return bill;
	}

	public List<BillToPay> getAll() {
		return billRepository.findAll();
	}

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
		}
		return bill;
	}

	@Scheduled(cron = "0 0 12 ? * *")
	public void calculateValueDelay() {
		List<BillToPay> billList = billRepository.findAll();
		for (BillToPay billToPay : billList) {
			billToPay = verifyDelay(billToPay);
			billRepository.save(billToPay);
		}
	}

}
