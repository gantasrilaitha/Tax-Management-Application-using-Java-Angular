package com.taxmanagement.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taxmanagement.dto.TaxDTO;
import com.taxmanagement.entity.Tax;
import com.taxmanagement.exception.ResourceNotFoundException;
import com.taxmanagement.repo.TaxRepository;
import com.taxmanagement.service.TaxService;

@Service
public class TaxServiceImpl implements TaxService {
	@Autowired
	private  TaxRepository taxRepository;
	@Autowired
	private  ModelMapper modelMapper; //map dto object sto entities & vice versa

	

	@Override
	public TaxDTO createTax(TaxDTO taxDTO) {
		Tax t=modelMapper.map(taxDTO,Tax.class);
		Tax new_tax=taxRepository.save(t);
		return modelMapper.map(new_tax, TaxDTO.class);
		
	}

	@Override
	public boolean deleteTaxById(Long id) {
		Optional<Tax> tax=taxRepository.findById(id);
		if (tax.isPresent()){
			taxRepository.deleteById(id);
			return true;
		}
		else{
			throw new ResourceNotFoundException("Tax not found");
		}
		
	}

	@Override
	public List<TaxDTO> getAllTaxes() {
		List<Tax> list_tax=taxRepository.findAll();
		//return list_tax.stream().map(tax -> modelMapper.map(tax, TaxDTO.class)).collect(Collectors.toList());
		List<TaxDTO> list_taxDTO = list_tax.stream()
                                       .map(tax -> {
                                           TaxDTO dto = new TaxDTO();
                                           dto.setTaxFormId(tax.getTaxFormId());
                                           dto.setFormType(tax.getFormType());
                                           dto.setFilingDate(tax.getFilingDate());
                                           dto.setTotalTaxAmount(tax.getTotalTaxAmount());
                                           dto.setUserId(tax.getUserId());
                                           return dto;
                                       })
                                       .collect(Collectors.toList());
									   
        return list_taxDTO;
	}

	@Override
	public TaxDTO getTaxById(Long id) {
		Optional<Tax> tax=taxRepository.findById(id);
		
		if (tax.isPresent()){
		return modelMapper.map(tax.get(),TaxDTO.class);
		
		}else{
			throw new ResourceNotFoundException("Tax not found");
		}

	}

	@Override
	public TaxDTO updateTax(TaxDTO taxDTO) {
		System.out.println(taxDTO.getTaxFormId());
		try{
			
			TaxDTO tax=getTaxById(taxDTO.getTaxFormId());
			Tax updated_tax=modelMapper.map(taxDTO,Tax.class);
			taxRepository.save(updated_tax);
			return modelMapper.map(updated_tax,TaxDTO.class);
		}catch(ResourceNotFoundException ex){
			throw new ResourceNotFoundException(ex.getMessage());
		}
		
	}
}
