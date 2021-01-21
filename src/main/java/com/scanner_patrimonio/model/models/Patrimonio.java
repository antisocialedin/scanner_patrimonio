package com.scanner_patrimonio.model.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="TAB_PATRIMONIO")
public class Patrimonio {
	

	private Integer id;
	private String name;
	private String codigo;
	private String estado;
	
	private Area area;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PATRIMONIO_ID")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name = "PATRIMONIO_NAME", length = 60, nullable = false)
	public String getName() {
		return name;
	}
	public void setName(String p_name) {
		this.name = p_name;
	}
	
	@Column(name = "PATRIMONIO_CODIGO", length = 100, nullable = false, unique = true)
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	@Column(name = "PATRIMONIO_ESTADO", length = 100, nullable = false)
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	// muitos para um 
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "AREA_ID", nullable = false)
    public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
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
		Patrimonio other = (Patrimonio) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Patrimonio [id=" + id + ", p_name=" + name + ", codigo=" + codigo + ", estado=" + estado
				+ "]";
	}
	
	
}
