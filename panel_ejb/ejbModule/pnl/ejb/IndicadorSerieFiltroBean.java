package pnl.ejb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import pnl.interfaz.IndicadorSerieFiltroBeanRemote;
import pnl.modelo.IndicadorSerieFiltro;
import pnl.modelo.IndicadorSerieFiltroPK;
import pnl.qualificadores.AuditorGeneral;


/**
 * @generated DT_ID=none
 */
@AuditorGeneral
@SuppressWarnings("unchecked")
@Stateless(name = "IndicadorSerieFiltroBean")
public class IndicadorSerieFiltroBean
        implements IndicadorSerieFiltroBeanRemote,Serializable {
        	private static final long serialVersionUID = 1L;
    /**
     * @generated DT_ID=none
     */
	@Resource
	SessionContext sessionContext;

    /**
     * @generated DT_ID=none
     */
	    @PersistenceContext(unitName="Panel-EJB")
        private EntityManager em;

    /**
     * @generated DT_ID=none
     */
    public IndicadorSerieFiltroBean() {
    }
    
    

    /**
     * @generated DT_ID=none
     */
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public Object queryByRange(String jpqlStmt, int firstResult,
                               int maxResults) {
        Query query = em.createQuery(jpqlStmt);
        if (firstResult > 0) {
            query = query.setFirstResult(firstResult);
        }
        if (maxResults > 0) {
            query = query.setMaxResults(maxResults);
        }

        return query.getResultList();
    }

    /**
     * @generated DT_ID=none
     */
    public IndicadorSerieFiltro persistIndicadorSerieFiltro(IndicadorSerieFiltro indicadorSerieFiltro) {
        em.persist(indicadorSerieFiltro);
        return indicadorSerieFiltro;
    }

    /**
     * @generated DT_ID=none
     */
    public IndicadorSerieFiltro mergeIndicadorSerieFiltro(IndicadorSerieFiltro indicadorSerieFiltro) {
        return em.merge(indicadorSerieFiltro);
    }

    /**
     * @generated DT_ID=none
     */
    public void removeIndicadorSerieFiltro(IndicadorSerieFiltro indicadorSerieFiltro) {
        IndicadorSerieFiltroPK indicadorSerieFiltroPK = new IndicadorSerieFiltroPK();
        indicadorSerieFiltroPK.setIdIndicador(indicadorSerieFiltro.getId().getIdIndicador());
        indicadorSerieFiltroPK.setIdSerie(indicadorSerieFiltro.getId().getIdSerie());
        indicadorSerieFiltroPK.setIdFiltro(indicadorSerieFiltro.getId().getIdFiltro());
        indicadorSerieFiltro = em.find(IndicadorSerieFiltro.class, indicadorSerieFiltroPK);
        em.remove(indicadorSerieFiltro);
    }

    /**
     * @generated DT_ID=none
     */
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<IndicadorSerieFiltro> getIndicadorSerieFiltroFindAll() {
        return em.createNamedQuery("IndicadorSerieFiltro.findAll").getResultList();
    }



	@Override
	public List<IndicadorSerieFiltro> obtenerSerieFiltrosPorIdIndicadorIdFiltro(long idIndicador, long idFiltro) throws Exception {
		
		try{
			String queryStr = "SELECT isf FROM IndicadorSerieFiltro isf  " 
					+ " LEFT JOIN isf.filtro f "
					+ " LEFT JOIN f.indicador i "
					+ " LEFT JOIN isf.indicadorSery s "
					+ " WHERE i.idIndicador = :idIndicador ";
					
					
			if(idFiltro != -1){
				
				queryStr+= " AND f.idFiltro = :idFiltro ";
				
			}
			
			queryStr+= " ORDER BY s.idSerie, f.indiceFiltro ";

			TypedQuery<IndicadorSerieFiltro> query = em.createQuery(queryStr,
					IndicadorSerieFiltro.class);

			query.setParameter("idIndicador", idIndicador);
			
			if(idFiltro != -1){
				
				query.setParameter("idFiltro", idFiltro);
				
			}
			
			
			

			List<IndicadorSerieFiltro> indicadorSerieFiltros = query.getResultList();

			return indicadorSerieFiltros;
			
		}catch(NoResultException nr){
			return new ArrayList<IndicadorSerieFiltro>();
		}
		
		
		
	}



	@Override
	public void persistIndicadorSerieFiltros(
			List<IndicadorSerieFiltro> indicadorSerieFiltros) throws Exception {
		
		
		for(IndicadorSerieFiltro indicadorSerieFiltro :indicadorSerieFiltros ){

				this.persistIndicadorSerieFiltro (indicadorSerieFiltro);
			
		}
		
	}


	@Override
	public IndicadorSerieFiltro obtenerIndicadorSerieFiltroPorId(IndicadorSerieFiltroPK id) throws Exception {
		
		try{
			String queryStr = "SELECT isf FROM IndicadorSerieFiltro isf  " 
					+ " WHERE isf.id = :id ";


			TypedQuery<IndicadorSerieFiltro> query = em.createQuery(queryStr,IndicadorSerieFiltro.class);

			query.setParameter("id", id);
				

			IndicadorSerieFiltro indicadorSerieFiltro = query.getSingleResult();

			return indicadorSerieFiltro;
			
		}catch(NoResultException nr){
			return null;
		}
		
		
		
	}



}
