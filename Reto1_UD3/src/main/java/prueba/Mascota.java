package prueba;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;


@Entity
@Table(name = "Mascota")
public class Mascota{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "mascota_id")
	long id;

	@Column(name = "nombre")
	String nombre;
	
	@ManyToOne
	@JoinColumn(name = "propietario_fk")
	Propietario propietario;

	@Column(name = "especie")
	String especie;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "genero")
	Genero genero;

	@Column(name = "fecha_nacimiento")
	String nacimiento;
	
	public Mascota(String nombre, String especie, Genero genero, String nacimiento) {
		super();
		this.nombre = nombre;
		this.especie = especie;
		this.genero = genero;
		this.nacimiento = nacimiento;
	}
	
	public Mascota() {
		
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Propietario getPropietario() {
		return propietario;
	}
	public void setPropietario(Propietario propietario) {
		this.propietario = propietario;
	}
	public String getEspecie() {
		return especie;
	}
	public void setEspecie(String especie) {
		this.especie = especie;
	}
	public Genero getGenero() {
		return genero;
	}
	public void setGenero(Genero genero) {
		this.genero = genero;
	}
	public String getNacimiento() {
		return nacimiento;
	}
	public void setNacimiento(String nacimiento) {
		this.nacimiento = nacimiento;
	}
	@Override
	public String toString() {
		return "Mascota [id=" + id + ", nombre=" + nombre + ", especie=" + especie
				+ ", genero=" + genero + ", nacimiento=" + nacimiento + "]";
	}

}
