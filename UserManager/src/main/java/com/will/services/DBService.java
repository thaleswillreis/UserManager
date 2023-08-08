package com.will.services;

import java.text.ParseException;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.will.domain.Cidade;
import com.will.domain.Cliente;
import com.will.domain.Endereco;
import com.will.domain.Estado;
import com.will.domain.enums.Perfil;
import com.will.domain.enums.TipoCliente;
import com.will.repositories.CidadeRepository;
import com.will.repositories.ClienteRepository;
import com.will.repositories.EnderecoRepository;
import com.will.repositories.EstadoRepository;

@Service
public class DBService {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	public void instantiateTestDatabase() throws ParseException {

		Estado uf1 = new Estado(null, "Maranhão");
		Estado uf2 = new Estado(null, "São Paulo");
		Estado uf3 = new Estado(null, "Paraná");

		Cidade mun1 = new Cidade(null, "Imperatriz", uf1);
		Cidade mun2 = new Cidade(null, "Indaiatuba", uf2);
		Cidade mun3 = new Cidade(null, "Maringá", uf3);
		Cidade mun4 = new Cidade(null, "Jundiaí", uf2);

		uf1.getCidades().addAll(Arrays.asList(mun1));
		uf2.getCidades().addAll(Arrays.asList(mun2, mun4));
		uf3.getCidades().addAll(Arrays.asList(mun3));

		estadoRepository.saveAll(Arrays.asList(uf1, uf2, uf3));
		cidadeRepository.saveAll(Arrays.asList(mun1, mun2, mun3, mun4));

		Cliente cliente1 = new Cliente(null, "Maninho", "maninho@gmail.com", "11618116487", TipoCliente.PESSOAFISICA, passwordEncoder.encode("123456"));
		cliente1.getTelefones().addAll(Arrays.asList("99991001122", "99984003344"));
		cliente1.addPerfil(Perfil.USER);
		
		Cliente cliente2 = new Cliente(null, "Minininha", "minininha@gmail.com", "40505846381", TipoCliente.PESSOAFISICA, passwordEncoder.encode("654321"));
		cliente2.getTelefones().addAll(Arrays.asList("99991005566", "99984007788"));
		cliente2.addPerfil(Perfil.ADMIN);

		Endereco end1 = new Endereco(null, "Rua A", "100", "Apto 102", "Vila do rato", "65900000", cliente1, mun1);
		Endereco end2 = new Endereco(null, "Rua B", "200", "Apto 204", "Centro", "13330670", cliente1, mun2);
		Endereco end3 = new Endereco(null, "Av. industrial", "1122", "Apto 105", "Parque Industrial", "87069001", cliente2, mun3);
		Endereco end4 = new Endereco(null, "Rua Barão de Jundiaí", "599", null, "Centro", "13201010", cliente2, mun4);
		
		cliente1.getEnderecos().addAll(Arrays.asList(end1, end2));
		cliente2.getEnderecos().addAll(Arrays.asList(end3, end4));

		clienteRepository.saveAll(Arrays.asList(cliente1, cliente2));
		enderecoRepository.saveAll(Arrays.asList(end1, end2, end3, end4));
	}
}