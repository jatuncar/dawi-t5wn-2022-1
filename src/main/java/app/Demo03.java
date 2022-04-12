package app;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Usuario;

public class Demo03 {

	public static void main(String[] args) {
		// Obj >>> eliminar un Usuario
		
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql");
		EntityManager em = fabrica.createEntityManager();

		// proceso
		em.getTransaction().begin();

		// forma 1. borrado l�gico --> no elimina, sino cambia
		// forma 2. borrado f�sico --> delete ..where id ...--> remove
		
		Usuario u = new Usuario();
		u.setCodigo(13);

		em.remove(u); // obtener el obj Usuario, mediante una b�squeda
		
		em.getTransaction().commit();

		em.close();
		
	}
}
