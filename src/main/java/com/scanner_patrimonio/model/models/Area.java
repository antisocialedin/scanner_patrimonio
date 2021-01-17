package com.scanner_patrimonio.model.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "TAB_AREA")
public class Area {
	
	private Integer id;
	private String nome;
	
	private List<Area> area;
	
	
	public Area() {
	}


	public Area(Integer id, String nome) {
		this.id = id;
		this.nome = nome;
	}

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}

    @Column(name = "area_nome", nullable = false, length = 50)
	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}
	
	//um para muitos 
	
	@OneToMany(mappedBy = "area")
	public List<Area> getPatrimonio() {
		return area;
	}


	public void setPatrimonio(List<Area> patrimonio) {
		this.area = patrimonio;
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
		Area other = (Area) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return nome;
	}
	
	
	
	

}
