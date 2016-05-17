package cn.superion.equipment.dao;

import cn.superion.equipment.entity.EqChangeTypeDict;
import cn.superion.util.BaseHibernateDAO;
import java.util.List;
import java.util.Map;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A data access object (DAO) providing persistence and search support for
 * EqChangeTypeDict entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see cn.superion.equipment.entity.EqChangeTypeDict
 * @author MyEclipse Persistence Tools
 */

public class EqChangeTypeDictDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(EqChangeTypeDictDAO.class);

	public void save(EqChangeTypeDict transientInstance) {
		log.debug("saving EqChangeTypeDict instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(EqChangeTypeDict persistentInstance) {
		log.debug("deleting EqChangeTypeDict instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public EqChangeTypeDict findById(java.lang.String id) {
		log.debug("getting EqChangeTypeDict instance with id: " + id);
		try {
			EqChangeTypeDict instance = (EqChangeTypeDict) getSession().get(
					"cn.superion.equipment.entity.EqChangeTypeDict", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<EqChangeTypeDict> findByExample(EqChangeTypeDict instance) {
		log.debug("finding EqChangeTypeDict instance by example");
		try {
			List<EqChangeTypeDict> results = getSession().createCriteria(
					"cn.superion.equipment.entity.EqChangeTypeDict").add(
					Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<EqChangeTypeDict> findByProperty(String propertyName,
			Object value) {
		log.debug("finding EqChangeTypeDict instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from EqChangeTypeDict as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<EqChangeTypeDict> findAll() {
		log.debug("finding all EqChangeTypeDict instances");
		try {
			String queryString = "from EqChangeTypeDict order by typeCode";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public EqChangeTypeDict merge(EqChangeTypeDict detachedInstance) {
		log.debug("merging EqChangeTypeDict instance");
		try {
			EqChangeTypeDict result = (EqChangeTypeDict) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(EqChangeTypeDict instance) {
		log.debug("attaching dirty EqChangeTypeDict instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(EqChangeTypeDict instance) {
		log.debug("attaching clean EqChangeTypeDict instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void update(EqChangeTypeDict eqChangeTypeDict) {
		getSession().update(eqChangeTypeDict);
	}

	/**
	 * 通过Sql语句查询基础数据字典
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> findBaseData() {
		log.debug("finding EqChangeTypeDict BaseData");
		try {
			String queryString = "select new map(typeCode as eqChangeType,typeName as eqChangeTypeName) from EqChangeTypeDict order by typeCode";
			Query queryObject = getSession().createQuery(queryString);

			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find baseData failed", re);
			throw re;
		}
	}
}