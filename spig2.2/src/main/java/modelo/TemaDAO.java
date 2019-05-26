/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;


/**
 *
 * @author desales
 */
public class TemaDAO extends AbstractDAO<Tema> {

    /**
     * Constructor
     * @author desalessr
     */
    public TemaDAO() {
        super();
    }

    /**
     * Método para almacenar un nuevo tema
     * @param tema Tema a almacenar
     */
    @Override
    public void save(Tema tema) {
        super.save(tema);
    }

    /**
     * Método para actualizar un tema
     *  @param tema Tema a actualizar
     */
    @Override
    public void update(Tema tema) {
        super.save(tema);
    }

    /**
     *Método para eliminar un tema
     @param tema Tema a eliminar
     */
    @Override
    public void delete(Tema tema) {
        super.delete(tema);
    }


    public Tema find(int id) {
        Tema u = null;
        Session session = this.sessionFactory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            String hql = "from Tema where id = :id ";
            Query query = session.createQuery(hql);
            query.setParameter("id", id);
            u = (Tema)query.uniqueResult();
            tx.commit();

        }catch(HibernateException e){
            if(tx!=null){
                tx.rollback();
            }
            e.printStackTrace();
        }finally{
            session.close();
        }
        return u;
    }
    /**
     * Método que nos ayidará a mostrar todos los temas
     *@return temas Temas que serán mostrados
     */
    public List<Tema> findAll(){
        return super.findAll(Tema.class);
    }

    public List<Tema> buscaPorNombreLikeAndInformador(String nombre, String correo_inf){
       if(nombre.equals(""))
           return null;
        List<Tema> temas = null;
        InformadorDAO inf_db = new InformadorDAO();
        Session session = this.sessionFactory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            String hql = "From Tema where informador = :informador and nombre like concat('%',:nombre,'%')";
            Query query = session.createQuery(hql);
            Informador inf = inf_db.buscaPorCorreo(correo_inf);
            query.setParameter("informador", inf);
            query.setParameter("nombre", nombre);
            query.setMaxResults(10);
            temas = (List<Tema>)query.list();
            tx.commit();
        }catch(HibernateException e){
            if(tx!=null){
                tx.rollback();
            }
            e.printStackTrace();
        }finally{
            session.close();
        }
        return temas;
    }

    public Tema buscaPorNombreAndInformador(String nombre, String correo_inf){
       if(nombre.equals(""))
           return null;
        Tema tema = null;
        InformadorDAO inf_db = new InformadorDAO();
        Session session = this.sessionFactory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            String hql = "From Tema where informador = :informador and nombre = :nombre";
            Query query = session.createQuery(hql);
            Informador inf = inf_db.buscaPorCorreo(correo_inf);
            query.setParameter("informador", inf);
            query.setParameter("nombre", nombre);
            tema = (Tema)query.uniqueResult();
            tx.commit();
        }catch(HibernateException e){
            if(tx!=null){
                tx.rollback();
            }
            e.printStackTrace();
        }finally{
            session.close();
        }
        return tema;
    }


    public List<Tema> buscaPorInformador(Informador informador){
       if(informador == null)
           return null;
        List<Tema> temas = null;
        Session session = this.sessionFactory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            String hql = "From Tema where informador = :informador";
            Query query = session.createQuery(hql);
            query.setParameter("informador", informador);
            temas = (List<Tema>)query.list();
            tx.commit();
        }catch(HibernateException e){
            if(tx!=null){
                tx.rollback();
            }
            e.printStackTrace();
        }finally{
            session.close();
        }
        return temas;
    }
}
