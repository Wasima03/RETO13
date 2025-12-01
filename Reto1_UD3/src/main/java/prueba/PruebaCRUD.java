package prueba;

import java.util.List;
import java.util.Scanner;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class PruebaCRUD {

    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("MascotasPropietarios");
    static EntityManager em = emf.createEntityManager();
    static EntityTransaction transaction = em.getTransaction();

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        int opc = 0;

        try {
            while (opc != 5) {
                System.out.println("\n------------Menú de opciones-------------");
                System.out.println("1. Añadir propietario");
                System.out.println("2. Mostrar propietarios");
                System.out.println("3. Actualizar nombre");
                System.out.println("4. Eliminar propietario");
                System.out.println("5. Salir");
                System.out.print("Elige una opción: ");

                while (!sc.hasNextInt()) {
                    System.out.println("Introduce un número válido.");
                    sc.next();
                }

                opc = sc.nextInt();
                sc.nextLine(); 

                switch (opc) {
                    case 1: addPropietario(); break;
                    case 2: mostrarPropietarios(); break;
                    case 3: actualizarNombre(); break;
                    case 4: eliminarPropietario(); break;
                    case 5: System.out.println("Saliendo..."); break;
                    default: System.out.println("Opción inválida");
                }
            }
        } finally {
            sc.close();
            em.close();
            emf.close();
        }
    }

    public static void addPropietario() {

        System.out.print("Introduce el nombre del propietario: ");
        String nombre = sc.nextLine();

        System.out.print("Introduce el apellido: ");
        String apellido = sc.nextLine();

        System.out.print("Introduce la edad: ");
        int edad = sc.nextInt();
        sc.nextLine();

        Propietario p = new Propietario(nombre, apellido, edad);

        System.out.print("¿Quieres añadir una mascota? (s/n): ");
        String respuesta = sc.nextLine();

        if (respuesta.equalsIgnoreCase("s")) {

            System.out.print("Nombre de la mascota: ");
            String nb = sc.nextLine();

            System.out.print("Especie: ");
            String especie = sc.nextLine();

            System.out.print("Fecha nacimiento (yyyy-mm-dd): ");
            String fecha = sc.nextLine();

            System.out.print("Género (F/M): ");
            String gn = sc.nextLine();

            Mascota m;
            if (gn.equalsIgnoreCase("F")) {
                m = new Mascota(nb, especie, Genero.F, fecha);
            } else {
                m = new Mascota(nb, especie, Genero.M, fecha);
            }

            p.addMascota(m);
        }

        transaction.begin();
        em.persist(p);
        transaction.commit();

        System.out.println("Propietario añadido correctamente.");
    }

    public static void mostrarPropietarios() {
        System.out.println("\nMostrando propietarios:");

        List<Propietario> propietarios = em
                .createQuery("SELECT p FROM Propietario p", Propietario.class)
                .getResultList();

        for (Propietario p : propietarios) {
            System.out.println(p);
        }
    }

    public static void actualizarNombre() {
        System.out.print("Introduce el ID del propietario: ");
        long id = sc.nextLong();
        sc.nextLine();

        System.out.print("Introduce el nuevo nombre: ");
        String nombre = sc.nextLine();

        transaction.begin();
        Propietario p = em.find(Propietario.class, id);

        if (p != null) {
            p.setNombre(nombre);
            em.merge(p);
            System.out.println("Nombre actualizado.");
        } else {
            System.out.println("Propietario no encontrado.");
        }

        transaction.commit();
    }

    public static void eliminarPropietario() {
        System.out.print("Introduce el ID del propietario a eliminar: ");
        long id = sc.nextLong();
        sc.nextLine();

        transaction.begin();
        Propietario p = em.find(Propietario.class, id);

        if (p != null) {
            em.remove(p);
            System.out.println("Propietario eliminado.");
        } else {
            System.out.println("No existe un propietario con ese ID.");
        }

        transaction.commit();
    }
}

