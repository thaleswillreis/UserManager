package com.will.dto;

import java.io.Serializable;
import java.util.UUID;

import org.hibernate.validator.constraints.Length;

import com.will.domain.Cliente;
import com.will.services.validation.ClienteUpdate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

@ClienteUpdate //Anotação personalizada
public class ClienteDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private UUID id;
	@NotEmpty(message = "Preenchimento obrigatório")
	@Length(min = 2, max = 100, message = "O tamanho deve ser de no mínimo 2 e no máximo 100 caracteres.")
	private String nome;
	
	@NotEmpty(message = "Preenchimento obrigatório")
	@Email(message = "Insira um endereço de e-mail válido")
	private String email;
	
	public ClienteDTO() {
	}
	
	public ClienteDTO(Cliente obj) {
		id = obj.getId();
		nome = obj.getNome();
		email = obj.getEmail();
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
