package br.com.felipe.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.felipe.models.BillToPay;
import br.com.felipe.services.BillToPayService;

@RestController
@RequestMapping("/bill")
public class BillToPayController {

	@Autowired
	private BillToPayService billService;

	@GetMapping()
	public ResponseEntity<Object> getAll() {
		return new ResponseEntity<>(billService.getAll(), HttpStatus.OK);
	}

	@PostMapping()
	public ResponseEntity<Object> saveBill(@RequestBody BillToPay bill) {
		return new ResponseEntity<>(billService.save(bill), HttpStatus.OK);
	}

}
