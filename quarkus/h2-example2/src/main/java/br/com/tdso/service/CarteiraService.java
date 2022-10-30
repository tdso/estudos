package br.com.tdso.service;

import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import br.com.tdso.model.Carteira;

@RequestScoped
public class CarteiraService {

    @PersistenceContext
    private EntityManager em;

    public List<Carteira> getCarteira() {
        String query = "getCarteira";
        TypedQuery<Carteira> retorno = em.createNamedQuery(query, Carteira.class);
        List<Carteira> ListCarteira = retorno.getResultList();
        return ListCarteira;
    }

    public Carteira getCarteiraById(Long id) {
        String name_query = "getCarteiraById";
        TypedQuery<Carteira> query = em.createNamedQuery(name_query, Carteira.class);
        query.setParameter(1, id);
        Carteira c = null;
        try {
            c = query.getSingleResult();
        } catch (NoResultException e) {
            // TODO: handle exception
        }
        
        return c;
    }

    @Transactional
    public Carteira putCarteira(Carteira c) {
        String query = "getCarteiraById";
        TypedQuery<Carteira> retorno = em.createNamedQuery(query, Carteira.class);
        retorno.setParameter(1, c.getIdCarteira());
        Carteira carteiraRetorno = retorno.getSingleResult();

        if (carteiraRetorno == null) {
            return new Carteira();
        } else {
            query = "persist";
            Query query_update = em.createNativeQuery(query);
            query_update.setParameter(1, c.getMesCarteira());
            query_update.setParameter(2, c.getAnoCarteira());
            query_update.setParameter(3, c.getNomeCarteira());
            query_update.setParameter(4, c.getIdCarteira());
            query_update.executeUpdate();
            return carteiraRetorno;
        }

    }

    @Transactional
    public Carteira postCarteira(Carteira c) {
        em.persist(c);
        return c;
    }


}
