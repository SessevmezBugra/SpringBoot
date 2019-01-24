package com.javaegitimleri.petclinic.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.javaegitimleri.petclinic.dao.OwnerRepository;
import com.javaegitimleri.petclinic.model.Owner;

@Repository("ownerRepository")
public class OwnerRepositoryJdbcImpl implements OwnerRepository {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private RowMapper<Owner> rowMapper = new RowMapper<Owner>() {
		
		@Override
		public Owner mapRow(ResultSet rs, int rowNum) throws SQLException {
			Owner owner = new Owner();
			return null;
		}
	};

	@Override
	public List<Owner> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Owner findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Owner> findByLastName(String lastName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void create(Owner owner) {
		// TODO Auto-generated method stub

	}

	@Override
	public Owner update(Owner owner) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub

	}

}
