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
public class ComentarioDAO extends AbstractDAO<Comentario> {
    
    /**
     * Constructor
     * @author desalessr
     */
    public ComentarioDAO() { 
        super(); 
    }

    /**
     * Método para almacenar un nuevo comentario
     * @param comentario Comentario a almacenar
     */
    public void save(Comentario comentario) {
        super.save(comentario);
    }

    /**
     * Método para actualizar un comentario
     *  @param comentario Comentario a actualizar 
     */
    public void update(Comentario comentario) {
        super.save(comentario);
    }

    /**
     *Método para eliminar un marcador
     @param comentario Comentario a eliminar
     */
    public void delete(Comentario comentario) {
        super.delete(comentario);
    }


    public Comentario find(int id) {
        Comentario u = null;
        Session session = this.sessionFactory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            String hql = "from Comentario where id = :id ";
            Query query = session.createQuery(hql);
            query.setParameter("id", id);
            u = (Comentario)query.uniqueResult();
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
     * Método que nos ayidará a mostrar todos los comentarios del marcador
     *@return comentarios Comentarios que serán mostrados
     */
    public List<Comentario> findAll(){
        return super.findAll(Comentario.class);
    }
    
    
    /**
     * Método que busca los comentarios dado un marcador
     * @param idmarcador id del marcador en cuestión
     * @return comentarios COmentarios correspóndientes a un marcador
     */
    public List<Comentario> findByMarker(int idmarcador) {
        List<Comentario> obj = null;
        Session session = this.sessionFactory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            String hql = "from Comentario where idmarcador = :id";
            Query query = session.createQuery(hql);
            query.setParameter("id", idmarcador);
            obj = (List<Comentario>) query.list();
            tx.commit();
        }catch(HibernateException e){
            if(tx != null)
                tx.rollback();
        }finally{
            session.close();
        }
        return obj;
    }
}