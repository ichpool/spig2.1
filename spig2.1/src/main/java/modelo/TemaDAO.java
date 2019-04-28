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
    
}
