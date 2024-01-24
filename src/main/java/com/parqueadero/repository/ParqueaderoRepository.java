package com.parqueadero.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.parqueadero.model.Parqueadero;
import com.parqueadero.model.ParqueaderoPersona;
import com.parqueadero.model.Persona;

@Repository
public class ParqueaderoRepository {

	@Autowired
	private NamedParameterJdbcTemplate namedJdbcTemplate;
	
	public List<Parqueadero> listar(){
		
		String sql = "select * from parqueadero";
		List<Parqueadero> lstParqueadero = namedJdbcTemplate.query(sql, new RowMapper<Parqueadero>() {
		
			@Override
			public Parqueadero mapRow(ResultSet rs, int rowNum) throws SQLException{
				
				Parqueadero parqueadero = new Parqueadero();
				parqueadero.setId(rs.getInt("id"));
				parqueadero.setNombre(rs.getString("nombre"));
				parqueadero.setEstado(rs.getString("estado"));
				return parqueadero;
			}
		});
		
		return lstParqueadero;
	}
	
	public void insertar(Parqueadero parqueadero) {
		
		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("nombre", parqueadero.getNombre());
		parameter.addValue("estado", parqueadero.getEstado());
	
		String sql = "insert into parqueadero (nombre, estado) values(:nombre,:estado)";
		namedJdbcTemplate.update(sql, parameter);
		
	}
	
	public List<Parqueadero> listarPorEstado(){
		String sql = "select * from parqueadero where estado='libre'";
		List<Parqueadero> lstParqueadero = namedJdbcTemplate.query(sql, new RowMapper<Parqueadero>() {
		
			@Override
			public Parqueadero mapRow(ResultSet rs, int rowNum) throws SQLException{
				
				Parqueadero parqueadero = new Parqueadero();
				parqueadero.setId(rs.getInt("id"));
				parqueadero.setNombre(rs.getString("nombre"));
				parqueadero.setEstado(rs.getString("estado"));
				return parqueadero;
			}
		});
		
		return lstParqueadero;
	}
	
	public List<ParqueaderoPersona> listarParqueaderoConPersona(){
		
		String sql = "select pp.id as pp_id, p.id, p.nombre, pe.nombres, pe.primer_apellido, pe.segundo_apellido, pe.identificacion from parqueadero p inner join parqueadero_persona pp on p.id = pp.id_parqueadero inner join personas pe on pp.id_persona = pe.id where pp.estado = 'En Vigencia'";
		List<ParqueaderoPersona> lstParqueaderoConPersona = namedJdbcTemplate.query(sql, new RowMapper<ParqueaderoPersona>() {
		
			@Override
			public ParqueaderoPersona mapRow(ResultSet rs, int rowNum) throws SQLException{
				
				ParqueaderoPersona parqueaderoConPersona = new ParqueaderoPersona();
				
				Parqueadero parqueadero = new Parqueadero();
				parqueadero.setId(rs.getInt("id"));
				parqueadero.setNombre(rs.getString("nombre"));
				
				Persona persona = new Persona();
				persona.setNombres(rs.getString("nombres"));
				persona.setIdentificacion(rs.getString("identificacion"));
				persona.setPrimerApellido(rs.getString("primer_apellido"));
				persona.setSegundoApellido(rs.getString("segundo_apellido"));
				
				parqueaderoConPersona.setId(rs.getInt("pp_id"));
				parqueaderoConPersona.setParqueadero(parqueadero);
				parqueaderoConPersona.setPersona(persona);
				
				return parqueaderoConPersona;
			}
		});
		
		return lstParqueaderoConPersona;
		
	}
	
	public void desocupar(int id) {
		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("id", id);
		String sql = "update parqueadero_persona set estado = 'Despachado' where id = :id";
		namedJdbcTemplate.update(sql, parameter);
	}
	
	public void habilitarParqueadero(int id) {
		
		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("id", id);
		String sql = "update parqueadero set estado = 'Libre' FROM parqueadero p inner join parqueadero_persona pp on p.id = pp.id_parqueadero WHERE pp.id = :id";
		namedJdbcTemplate.update(sql, parameter); 
	}
	
	public void asignar(ParqueaderoPersona parqueaderoPersona) {
		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("parqueadero", parqueaderoPersona.getParqueadero().getId());
		parameter.addValue("persona", parqueaderoPersona.getPersona().getId());
	
		String sql = "insert into parqueadero_persona (id_parqueadero, id_persona) values(:parqueadero,:persona)";
		namedJdbcTemplate.update(sql, parameter);
	}
	
	public void deshabilitarParqueadero(int id) {
		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("id", id);
		String sql = "update parqueadero set estado = 'Ocupado' WHERE id = :id";
		namedJdbcTemplate.update(sql, parameter); 
	}
	
public void modificar(Parqueadero parqueadero) {
		
		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("id", parqueadero.getId());
		parameter.addValue("nombre", parqueadero.getNombre());
		parameter.addValue("estado", parqueadero.getEstado());
		
		String sql = "update parqueadero set nombre=:nombre, estado=:estado where id=:id";
		
		namedJdbcTemplate.update(sql, parameter);
		
	}
	
	public void eliminar(int id) {
		
		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("id", id);
		String sql = "delete from parqueadero where id=:id";
		
		namedJdbcTemplate.update(sql, parameter);
		
	}
	
	public List<ParqueaderoPersona> validar(int idPersona) {
		
		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("idPersona", idPersona);
		String sql = "select * FROM parqueadero_persona WHERE id_persona = :idPersona AND estado = 'En Vigencia'";
		List<ParqueaderoPersona> parqueaderoPersona = namedJdbcTemplate.query(sql, parameter, new RowMapper<ParqueaderoPersona>() {
		
			@Override
			public ParqueaderoPersona mapRow(ResultSet rs, int rowNum) throws SQLException{
				
				ParqueaderoPersona parqueaderoConPersona = new ParqueaderoPersona();
				
				Parqueadero parqueadero = new Parqueadero();
				parqueadero.setId(rs.getInt("id_parqueadero"));
				
				Persona persona = new Persona();

				persona.setId(rs.getInt("id_persona"));

				parqueaderoConPersona.setParqueadero(parqueadero);
				parqueaderoConPersona.setPersona(persona);
				
				return parqueaderoConPersona;
			}
		});
		return parqueaderoPersona;
	}
	
}
