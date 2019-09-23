package br.edu.fema.crud.rest.lpbcc.seguranca;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.edu.fema.crud.rest.lpbcc.usuario.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {



	private static final String SENHA_API = "UMA SENHA MUITO SEGURA!";
	private static final Long TEMPO_JWT = 10000L;

	public String gerarToken(Authentication authentication) {
		Date hoje = new Date();
		Usuario logado = (Usuario) authentication.getPrincipal();
		Map<String, Object> informacoesUsuario = new HashMap<String, Object>();
		informacoesUsuario.put("email", logado.getEmail());
		informacoesUsuario.put("nome", logado.getNome());
		
		return Jwts.builder()
				.setClaims(informacoesUsuario)
				.setIssuer("API do Luizão")
				.setIssuedAt(hoje)
				.setSubject(logado.getId().toString())
				.setExpiration(new Date(hoje.getTime() + TokenService.TEMPO_JWT))
				.signWith(SignatureAlgorithm.HS256, TokenService.SENHA_API)
				.compact();
	}

	public boolean isTokenValido(String token) {
		try {
			Jwts.parser().setSigningKey(TokenService.SENHA_API)
			.parseClaimsJws(token);
			return true;
		}catch(Exception e) {
			return false;
		}

	}

	public Long getIdUsuario(String token) {
		Claims body = Jwts.parser().setSigningKey(TokenService.SENHA_API).parseClaimsJws(token).getBody();
		return Long.parseLong(body.getSubject());
	}
}