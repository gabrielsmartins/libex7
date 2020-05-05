package br.edu.utfpr.libex7.adapters.persistence.adapters.checkouts;

import java.util.List;
import java.util.Optional;

import br.edu.utfpr.libex7.adapters.persistence.entity.checkouts.CheckOutEntity;
import br.edu.utfpr.libex7.adapters.persistence.mapper.checkouts.CheckOutPersistenceMapper;
import br.edu.utfpr.libex7.adapters.persistence.service.checkouts.CheckOutPersistenceService;
import br.edu.utfpr.libex7.application.domain.books.Book;
import br.edu.utfpr.libex7.application.domain.checkouts.CheckOut;
import br.edu.utfpr.libex7.application.ports.out.checkout.SaveCheckOutPort;
import br.edu.utfpr.libex7.application.ports.out.checkout.SearchCheckOutPort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CheckOutPersistenceAdapter implements SaveCheckOutPort, SearchCheckOutPort{
	
	private final CheckOutPersistenceService service;
	private final CheckOutPersistenceMapper mapper;
	
	
	@Override
	public CheckOut save(CheckOut checkIn) {
		CheckOutEntity checkOutEntity = mapper.mapToEntity(checkIn);
		service.save(checkOutEntity);
		return mapper.mapToDomain(checkOutEntity);
	}


	@Override
	public Optional<Book> findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<CheckOut> findByUserId(Long userId) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<CheckOut> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	

}
