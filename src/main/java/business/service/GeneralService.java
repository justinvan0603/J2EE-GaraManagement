package business.service;

import java.util.List;

public interface GeneralService<T> {
	/**
	 * Find an entity by id
	 * 
	 * @param id
	 *            : id as criteria
	 * @param entityClass
	 *            : Class of entity for Hibernate to choose the table
	 * @return : Entity satisfies the criteria
	 */
	public T findById(long id, Class<T> entityClass);

	/**
	 * Get all entities from specific table based on {@link Class} as input
	 * argument
	 * 
	 * @return : {@link List} of entities with specific class as input argument
	 */
	public List<T> getAll(Class<T> entityClass);

	/**
	 * Get all entities satisfied HQL query as first input argument
	 * 
	 * @param query
	 *            : HQL query value
	 * @return : {@link List} of entities with specific class as second input
	 *         argument
	 */
	public List<T> query(String query, Class<T> entityClass);

	/**
	 * Update properties of an entity.
	 * 
	 * @param id
	 *            : id of entity in database
	 * @param newInfor
	 *            : new values to be saved
	 * @return : <code>true</code> if successes, otherwise, returns
	 *         <code>false</code>
	 */
	public boolean update(Long id, T newInfor);

	/**
	 * Remove entity from persistence layer
	 * 
	 * @param id
	 * @param entity
	 * @return <code>true</code> if successes, otherwise, returns
	 *         <code>false</code>
	 */
	public boolean delete(Long id, Class<T> entity);

	public boolean save(T newEntity);
}
