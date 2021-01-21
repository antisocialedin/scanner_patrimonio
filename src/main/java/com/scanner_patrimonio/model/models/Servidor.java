package com.scanner_patrimonio.model.models;

import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "TAB_SERVIDOR")
public class Servidor {
	

	private Integer id;
	private String name;
	private String prontuario;
	private String password;
	
	private boolean voluntario = false;
	private boolean admin = false;
	
	private List<Role> roles;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "SERVIDOR_ID")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name = "SERVIDOR_USERNAME", length = 60, nullable = false)
	public String getName() {
		return name;
	}
	public void setName(String username) {
		this.name = username;
	}
	
	@Column(name = "SERVIDOR_PRONTUARIO", length = 100, nullable = false, unique = true)
	public String getProntuario() {
		return prontuario;
	}
	public void setProntuario(String prontuario) {
		this.prontuario = prontuario;
	}
	
	@Column(name = "SERVIDOR_PASSWORD", length = 100, nullable = false)
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Column(nullable = false)
	public boolean isVoluntario() {
		return voluntario;
	}
	public void setVoluntario(boolean voluntario) {
		this.voluntario = voluntario;
	}
	
	@Column(nullable = false)
	public boolean isAdmin() {
		return admin;
	}
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
	
	// MUITOS PARA MUITOS
	   
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "TAB_SERVIDOR_ROLE",
		joinColumns = @JoinColumn(name="SERVIDOR_ID"),
		inverseJoinColumns = @JoinColumn(name="ROLE_ID"))
	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Servidor other = (Servidor) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Usuario [id=" + id + ", username=" + name + ", prontuario=" + prontuario + ", password=" + password
				+ ", voluntario=" + voluntario + ", admin=" + admin + "]";
	}
	
	
}