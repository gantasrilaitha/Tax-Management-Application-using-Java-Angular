package com.taxmanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import com.taxmanagement.dto.TaxDTO;
import com.taxmanagement.service.TaxService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins="http://localhost:4200/")
@RequestMapping("/api/taxes")
public class TaxController {
	
	@Autowired
	private TaxService taxService;

	
	

	@PostMapping
	public ResponseEntity<TaxDTO> createTax(@RequestBody @Valid TaxDTO model) {
		TaxDTO td= taxService.createTax(model);
		
		return ResponseEntity.ok(td);//status:200:ok
		
	}

	@PutMapping("/{id}")
	public ResponseEntity<TaxDTO> updateTax(@RequestBody @Valid TaxDTO model) {
		
		TaxDTO tax_update=taxService.updateTax(model);
		return  ResponseEntity.ok(tax_update);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteTax(@PathVariable long id) {
		if (taxService.deleteTaxById(id)){
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

		
	}

	@GetMapping("/{id}")
	public ResponseEntity<TaxDTO> getTaxById(@PathVariable long id) {
		TaxDTO td=taxService.getTaxById(id);
		
		if (td==null){
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);//has no body
		}
		return  ResponseEntity.ok(td);
	}

	@GetMapping
	public ResponseEntity<List<TaxDTO>> getAllTaxes() {
		return ResponseEntity.status(200).body(taxService.getAllTaxes());
		
	}
}
