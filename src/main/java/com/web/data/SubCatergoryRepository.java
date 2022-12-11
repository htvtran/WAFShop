package com.web.data;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.web.category.Category;
import com.web.subcategory.SubCategory;
import com.web.subcategory.SubCategoryPosition;

@EnableJpaRepositories
@Repository("subcatrepo")
public interface SubCatergoryRepository extends JpaRepository<SubCategory, Integer>, Serializable {

	public final static String GET_SUB_BY_CAT = "SELECT * FROM heroku_844f026e121e684.SUBCATEGORY  WHERE CATEGORY_ID = :id";

	public final static String GET_SUB_BY_CAT_MAP = "SELECT su FROM SubCategory su WHERE su.category.id = :id";

	public final static String GET_PRO_BY_SUB = "SELECT su FROM SubCategory su JOIN PRODUCT pro su WHERE su.category.id = :id";

	public final static String GET_SUB_BY_POSIT = "SELECT su FROM SubCategory su where su.category.id = :cid  and  LOWER(su.positions) = LOWER(:po)";

	public final static String GET_SUB_BY_POSIT_2 = "SELECT new com.web.subcategory.SubCategoryPosition(su.positions, su.category.id ,group_concat(su.id), group_concat(su.name))  FROM SubCategory su "
			+
			"where su.category.id = :cid GROUP BY su.positions";

	// SubCategoryPosition

	@Query(value = GET_SUB_BY_CAT, nativeQuery = true) // navtivequery???
	List<SubCategory> findByCatID(@Param("id") final Integer id);

	@Query(value = GET_SUB_BY_CAT_MAP) // navtivequery???
	List<SubCategory> findByCatIDs(@Param("id") final Integer id);

	@Query(value = GET_SUB_BY_POSIT)
	List<SubCategory> getSubCatByPosition(@Param("cid") final Integer cid, @Param("po") final String po);

	public List<SubCategory> findByCategory(Category category);

	public List<SubCategory> findByPositions(String position);

	@Query(value = GET_SUB_BY_POSIT_2)
	public List<SubCategoryPosition> filterSubByPositionWithCat(@Param("cid") Integer cid);
	// List<SubCategory> findByPostionsAndCategory(String positions, String
	// category);
	// public interface ArtistRepository extends CrudRepository<Artist, Integer>

}
