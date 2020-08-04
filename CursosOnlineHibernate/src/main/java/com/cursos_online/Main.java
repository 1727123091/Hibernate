package com.cursos_online;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import com.cursos_online.entidades.Curso;
import com.cursos_online.entidades.Estudiante;



public class Main {

	static final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
				.configure() // configures settings from hibernate.cfg.xml
				.build();
		
		static SessionFactory sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
		
		public static void  main(String[] args) {
			
			//INGRESAR CURSO
			Curso cur1 = new Curso("Fundamentos de Java");
			Curso cur2 = new Curso("Hibernate para principiantes");
			Curso cur3 = new Curso("Programacion orientada a objetos");
			Curso cur4 = new Curso("Programacion V");
			ingresarCurso(cur1);
			ingresarCurso(cur2);
			ingresarCurso(cur3);
			ingresarCurso(cur4);
			
			
			//MODIFICAR CURSO			
			modificarCurso(3, "Ingles II");
			
			
			//ELIMINAR CURSO		
			eliminarCurso(1);
			
			
			
			
			//INGRESAR ESTUDIANTE
			Estudiante est1 = new Estudiante("Bianca", "Muñoz");
			Estudiante est2 = new Estudiante("Yessenia", "Arbelaez");
			Estudiante est3 = new Estudiante("Fiorella","Silva");	
			ingresarEstudiante(est1);
			ingresarEstudiante(est2);
			ingresarEstudiante(est3);
			
			//MODIFICAR ESTUDIANTE
			
			modificarEstudiante(6, "Germania", "Silva");
			
			
			//ELIMINAR ESTUDIANTE
			eliminarEstudiante(5);
			
			
			
				
			List<Curso> cursos = getCursos();
			
			for(Curso temp:cursos) {
				System.out.println(temp);
			}
			
			List<Estudiante> estudiantes = getEstudiantesPorNombre("Fiorella");
			for(Estudiante e: estudiantes) {
				System.out.println(e);
			}
			
			
			
		
		}
		

		
	 //CURSO
	 static void ingresarCurso(Curso curso) {
			
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(curso);
		
		session.getTransaction().commit();
		session.close();

	}
		
		static void modificarCurso(int id, String nombre) {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			Curso curso =
					(Curso)session.get(Curso.class,id);
			curso.setDescripcion(nombre);
			session.update(curso);
			session.getTransaction().commit();
			}
		
		static void eliminarCurso(int id) {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			Curso curso =
					(Curso)session.get(Curso.class,id);
			session.delete(curso);
			
			session.getTransaction().commit();
			session.close();
			
		}
		
		
		// ESTUDIANTE
		static void ingresarEstudiante(Estudiante estudiante) {
			
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			
			session.save(estudiante);
			
			session.getTransaction().commit();
			session.close();
			
			System.out.println(estudiante.getId());
}
		
		static void modificarEstudiante(int id, String nombre, String apellido) {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			Estudiante estudiante =
					(Estudiante)session.get(Estudiante.class,id);
			estudiante.setNombre(nombre);
			estudiante.setApellido(apellido);
			session.update(estudiante);
			session.getTransaction().commit();
			
			session.close();
			}
		
		static void eliminarEstudiante(int id) {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			Estudiante estudiante =
					(Estudiante)session.get(Estudiante.class,id);
			session.delete(estudiante);
			
			session.getTransaction().commit();
			session.close();
			
		}
		
		
		
		
		//LISTAS
		
		static List<Curso> getCursos(){
			Session session = sessionFactory.openSession();
			List<Curso> cursos = session.createQuery("from Curso", Curso.class).list();
			return cursos;
		}
		
		static List<Estudiante> getEstudiantes(){
			Session session = sessionFactory.openSession();
			List<Estudiante> estudiantes = session.createQuery("from Estudiante", Estudiante.class).list();
			return estudiantes;
		}
		
		
		 static List<Estudiante> getEstudiantesPorNombre(String nombre) {
				Session session =sessionFactory.openSession();
				Query query= session
						.createQuery("from Estudiante where nombre=:nombre");
				query.setParameter("nombre", nombre);
				
				List<Estudiante> estudiantes = (List<Estudiante>) query.getResultList();
				return estudiantes;
			}
		
}
