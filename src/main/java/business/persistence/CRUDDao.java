package business.persistence;

import java.io.Serializable;
import java.util.List;

public interface CRUDDao {
	/**
	 * Get all record class T
	 * 
	 * @param klass
	 *            : Specific class of the entity
	 * @return
	 */
	<T> List<T> getAll(Class<T> klass);

	<T> void Save(T klass);

	<T> T findByPrimaryKey(Class<T> klass, Serializable id);

	<T> T GetUniqueEntityByNamedQuery(String query, Object... params);

	<T> List<T> GetListByNamedQuery(String query, Object... params);

	<T> void delete(T klass);

	<T> Long getQueryCount(String query, Object... params);
}
