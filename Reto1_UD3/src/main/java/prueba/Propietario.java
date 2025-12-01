package prueba;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Propietario implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	long id;	
	String nombre;
	String apellido;
	int edad;
	@OneToMany(cascade=CascadeType.ALL,mappedBy="propietario", fetch=FetchType.EAGER)
	Set<Mascota> mascotas = new HashSet<Mascota>();
	public Propietario(String nombre, String apellido, int edad) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.edad = edad;
	}
	
	public Propietario() {
		super();
	}
	
	public Mascota addMascota(Mascota m) {
		m.setPropietario(this);
		mascotas.add(m);
		return m;
	}
	public Mascota addMascota(String nombre,String especie, Genero genero,String nacimiento) {
		Mascota m = new Mascota(nombre,especie,genero,nacimiento);
		return this.addMascota(m);
	}
	
	public void delMascota(Mascota m) {
		mascotas.remove(m);
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
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public int getEdad() {
		return edad;
	}
	public void setEdad(int edad) {
		this.edad = edad;
	}
	
	@Override
	public String toString() {
		return "Propietario [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", edad=" + edad
				+ ", mascotas=" + mascotas + "]";
	}
	
}
