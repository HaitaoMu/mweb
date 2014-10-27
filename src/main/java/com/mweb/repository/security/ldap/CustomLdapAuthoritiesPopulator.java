package com.mweb.repository.security.ldap;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.ldap.userdetails.LdapAuthoritiesPopulator;

public class CustomLdapAuthoritiesPopulator implements LdapAuthoritiesPopulator
{
	@Autowired
	JdbcTemplate template;

	public List<GrantedAuthority> getGrantedAuthorities(DirContextOperations userData, String username) {
	      return template.query("select role from roles where username = ?",
                  new String[] {username},
                  new RowMapper<GrantedAuthority>() {
				/**
				*  We're assuming here that you're using the standard convention of using the role
				*  prefix "ROLE_" to mark attributes which are supported by Spring Security's RoleVoter.
				*/
				public GrantedAuthority mapRow(ResultSet rs, int rowNum) throws SQLException 
				{
				      return new GrantedAuthorityImpl("ROLE_" + rs.getString(1));
				}
	      	});
	}
}
