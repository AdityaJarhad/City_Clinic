package com.app.security;

import java.security.Key;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JwtUtils {

	@Value("${SECRET_KEY}")
	private String jwtSecret;

	@Value("${EXP_TIMEOUT}")
	private int jwtExpirationMs;
	
	
	private Key key;

	@PostConstruct
	public void init() {
		key = Keys.hmacShaKeyFor(jwtSecret.getBytes());
	}

	// will be invoked by Authentication controller) , upon successful
	// authentication
	public String generateJwtToken(Authentication authentication) {
		log.info("generate jwt token " + authentication);
		CustomUserDetails userPrincipal = (CustomUserDetails) authentication.getPrincipal();
//JWT : userName,issued at ,exp date,digital signature(does not typically contain password , can contain authorities
		return Jwts.builder() // JWTs : a Factory class , used to create JWT tokens
				.setSubject((userPrincipal.getUsername())) // setting subject part of the token(typically user
															// name/email)
				.setIssuedAt(new Date())// Sets the JWT Claims iat (issued at) value of current date
				.setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))// Sets the JWT Claims exp
																					// (expiration) value.
				// setting a custom claim , to add granted authorities
				.claim("authorities", getAuthoritiesInString(userPrincipal.getAuthorities()))
				// setting a custom claim , to add user id (remove it if not required in the project)
				.claim("user_id",userPrincipal.getUser().getId())
		
				.signWith(key, SignatureAlgorithm.HS512) // Signs the constructed JWT using the specified
															// algorithm with the specified key, producing a
															// JWS(Json web signature=signed JWT)

				// Using token signing algo : HMAC using SHA-512
				.compact();// Actually builds the JWT and serializes it to a compact, URL-safe string
	}

	// this method will be invoked by our custom JWT filter
	public String getUserNameFromJwtToken(Claims claims) {
		return claims.getSubject();
	}

	// this method will be invoked by our custom JWT filter
	public Claims validateJwtToken(String jwtToken) {
		// try {
		Claims claims = Jwts.parserBuilder() //create JWT parser
				.setSigningKey(key) //sets the SAME secret key for JWT signature verification
				.build()//rets the JWT parser set with the Key
				.parseClaimsJws(jwtToken) //rets JWT with Claims added in the body
				.getBody();// rets the Claims
		/*
		 * parseClaimsJws - 
		 * throws:UnsupportedJwtException -if the JWT body | payload does not represent any Claims 
		 * JWSMalformedJwtException - if the JWT body | payload is not a valid 
		 * JWSSignatureException - if the JWT signature validation fails
		 * ExpiredJwtException - if the specified JWT is expired 
		 * IllegalArgumentException - if the JWT claims body | payload is null or empty or only whitespace
		 */
		return claims;		
	}
	// Accepts Collection<GrantedAuthority> n rets comma separated list of it's
	// string form

	private String getAuthoritiesInString(Collection<? extends GrantedAuthority> authorities) {
		String authorityString = authorities.stream().
				map(authority -> authority.getAuthority())
				.collect(Collectors.joining(","));
		System.out.println(authorityString);
		return authorityString;
	}
	// this method will be invoked by our custom JWT filter to get list of granted authorities n store it in auth token
		public List<GrantedAuthority> getAuthoritiesFromClaims(Claims claims) {
		String authString = (String) claims.get("authorities");
		List<GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList(authString);
		authorities.forEach(System.out::println);
		return authorities;
	}
	// this method will be invoked by our custom JWT filter to get user id n store it in auth token
			public Long getUserIdFromJwtToken(Claims claims) {
				return Long.valueOf((int)claims.get("user_id"));			
			}
			
			public Authentication populateAuthenticationTokenFromJWT(String jwt) {
				// validate JWT n retrieve JWT body (claims)
				Claims payloadClaims = validateJwtToken(jwt);
				// get user name from the claims
				String email = getUserNameFromJwtToken(payloadClaims);
				// get granted authorities as a custom claim
				List<GrantedAuthority> authorities = getAuthoritiesFromClaims(payloadClaims);
				// get userId as the custom claim		
				Long userId=getUserIdFromJwtToken(payloadClaims);
				// add user name/email , user id n  granted authorities in Authentication object
				UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(email,userId,
						authorities);
				System.out.println("is authenticated "+token.isAuthenticated());
				return token;
		
			}

}
