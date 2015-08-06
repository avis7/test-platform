package com.bionic.university.dao;

import com.bionic.university.interceptor.TxInterceptorBinding;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

@TxInterceptorBinding
public abstract class AbstractDAO<T> {
//
  //  private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.bionic.quiz");

    @PersistenceContext
    protected EntityManager em;

    private Class<T> entityClass;
//
    public AbstractDAO(Class<T> entityClass) {
        this.entityClass = entityClass;
    }
//
////    public void beginTransaction() {
////        em = emf.createEntityManager();
////        em.getTransaction().begin();
////    }
//
//    public void commit() {
//        em.getTransaction().commit();
//    }
//
//    public void rollback() {
//        em.getTransaction().rollback();
//    }
//
//    public void closeTransaction() {
//        em.close();
//    }
//
////    public static void closeEntityManagerFactory() {
////        emf.close();
////    }
//
////    public void commitAndCloseTransaction() {
////        commit();
////        closeTransaction();
////    }
//
//    public void flush() {
//        em.flush();
//    }
//
////    public void joinTransaction() {
////        em = emf.createEntityManager();
////        em.joinTransaction();
////    }
//
    public void save(T entity) {
        em.persist(entity);
    }

    public void delete(T entity) {
        T entityToBeRemoved = em.merge(entity);
        em.remove(entityToBeRemoved);

    }

    public T update(T entity) {
        return em.merge(entity);
    }

    public T find(int entityID) {
        System.out.println("fesdfc");
        return em.find(entityClass, entityID);
    }

//
//    public T findReferenceOnly(int entityID) {
//        return em.getReference(entityClass, entityID);
//    }
//
//    @SuppressWarnings({"unchecked', 'rawtypes"})
    public List<T> findAll() {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return em.createQuery(cq).getResultList();
    }
//
//    @SuppressWarnings("unchecked")
//    protected T findOneResult(String namedQuery, Map<String, Object> parameters) {
//        T result = null;
//
//        try {
//            Query query = em.createNamedQuery(namedQuery);
//
//            // Method that will populate parameters if they are passed not null and empty
//            if (parameters != null && !parameters.isEmpty()) {
//                populateQueryParameters(query, parameters);
//            }
//
//            result = (T) query.getSingleResult();
//
//        } catch (NoResultException e) {
//            System.out.println("No result found for named query: " + namedQuery);
//        } catch (Exception e) {
//            System.out.println("Error while running query: " + e.getMessage());
//            e.printStackTrace();
//        }
//
//        return result;
//    }
//
//    private void populateQueryParameters(Query query, Map<String, Object> parameters) {
//        for (Map.Entry<String, Object> entry : parameters.entrySet()) {
//            query.setParameter(entry.getKey(), entry.getValue());
//        }
//    }
}
