package com.parqueadero.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.parqueadero.model.Persona;

@Repository
public class PersonaRepository {

	@Autowired
	private NamedParameterJdbcTemplate namedJdbcTemplate;

	public List<Persona> listar() {

		String sql = "select * from personas";
		List<Persona> lstPersona = namedJdbcTemplate.query(sql, new RowMapper<Persona>() {

			@Override
			public Persona mapRow(ResultSet rs, int rowNum) throws SQLException {
				Persona persona = new Persona();
				persona.setId(rs.getInt("id"));
				persona.setNombres(rs.getString("nombres"));
				persona.setIdentificacion(rs.getString("identificacion"));
				persona.setPrimerApellido(rs.getString("primer_apellido"));
				persona.setSegundoApellido(rs.getString("segundo_apellido"));
				return persona;
			}
		});

		return lstPersona;
	}
	
	public void insertar(Persona persona) {
		
		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("nombres", persona.getNombres());
		parameter.addValue("identificacion", persona.getIdentificacion());
		parameter.addValue("primer_apellido", persona.getPrimerApellido());
		parameter.addValue("segundo_apellido", persona.getSegundoApellido());
		
		String sql = "insert into personas( nombres, identificacion, primer_apellido, segundo_apellido) values( :nombres, :identificacion, :primer_apellido, :segundo_apellido)";
		
		namedJdbcTemplate.update(sql, parameter);
		
	}
	
	public void modificar(Persona persona) {
		
		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("id", persona.getId());
		parameter.addValue("nombres", persona.getNombres());
		parameter.addValue("identificacion", persona.getIdentificacion());
		parameter.addValue("primer_apellido", persona.getPrimerApellido());
		parameter.addValue("segundo_apellido", persona.getSegundoApellido());
		
		String sql = "update personas set identificacion=:identificacion, nombres=:nombres, primer_apellido=:primer_apellido, segundo_apellido=:segundo_apellido where id=:id";
		
		namedJdbcTemplate.update(sql, parameter);
		
	}
	
	public void eliminar(int id) {
		
		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("id", id);
		String sql = "delete from personas where id=:id";
		
		namedJdbcTemplate.update(sql, parameter);
		
	}
	
	public List<Persona> listarPorIdentificacion(String identificacion) {
		
		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("identificacion", identificacion);
		String sql = "select * from personas where identificacion = :identificacion";
		
		List<Persona> persona = namedJdbcTemplate.query(sql, parameter,new RowMapper<Persona>() {

			@Override
			public Persona mapRow(ResultSet rs,int rowNum) throws SQLException {
				Persona persona = new Persona();
				persona.setId(rs.getInt("id"));
				persona.setNombres(rs.getString("nombres"));
				persona.setIdentificacion(rs.getString("identificacion"));
				persona.setPrimerApellido(rs.getString("primer_apellido"));
				persona.setSegundoApellido(rs.getString("segundo_apellido"));
				return persona;
			}
		});

		return persona;
		
	}
}
