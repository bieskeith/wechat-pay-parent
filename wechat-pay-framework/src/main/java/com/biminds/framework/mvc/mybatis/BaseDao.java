package com.biminds.framework.mvc.mybatis;

import com.biminds.framework.mvc.entity.PageForm;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * 
 * @ClassName: BaseDao
 * @Description: Mybatis通用泛型Dao
 * @author Kevin.Lv
 * @date 2015年3月6日 下午2:15:48
 * 
 * @param <T>
 * @param <PK>
 */
public class BaseDao<T, PK extends Serializable> extends SqlSessionDaoSupport {

	private final String POSTFIX = "Dao";

	private final String _INSERT = ".insert";

	private final String _INSERTSELECTIVE = ".insertSelective";

	private final String _SELECTBYPRIMARYKEY = ".selectByPrimaryKey";

	private final String _UPDATEBYPRIMARYKEY = ".updateByPrimaryKey";

	private final String _UPDATEBYPRIMARYKEYSELECTIVE = ".updateByPrimaryKeySelective";

	private final String _DELETEBYPRIMARYKEY = ".deleteByPrimaryKey";

	@Autowired
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		super.setSqlSessionFactory(sqlSessionFactory);
	}

	@SuppressWarnings("unchecked")
	public String getNampSpace() {
		ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
		@SuppressWarnings("rawtypes")
		Class<T> clazz = (Class) pt.getActualTypeArguments()[0];
		String simpleName = clazz.getSimpleName() + POSTFIX;
		return simpleName;
	}

	/**
	 * 
	 * @Title: insert
	 * @Description: 添加一条记录
	 * @param @param record 实体参数
	 * @param @return 影响条数
	 * @return int
	 * @throws
	 */
	public int insert(T record) {
		return getSqlSession().insert(
				(this.getNampSpace().contains("Entity") ? this.getNampSpace().replace("Entity", "")
						: this.getNampSpace()) + _INSERT, record);
	}

	/**
	 * 
	 * @Title: insertSelective
	 * @Description: 根据条件添加记录
	 * @param @param record 实体参数
	 * @param @return 影响条数
	 * @return int
	 * @throws
	 */
	public int insertSelective(T record) {
		return getSqlSession().insert(
				(this.getNampSpace().contains("Entity") ? this.getNampSpace().replace("Entity", "")
						: this.getNampSpace()) + _INSERTSELECTIVE, record);
	}

	/**
	 * 
	 * @Title: selectByPrimaryKey
	 * @Description: 根据主键查询一条记录
	 * @param @param id 主键
	 * @param @return 实体
	 * @return T
	 * @throws
	 */
	public T selectByPrimaryKey(PK id) {
		return getSqlSession().selectOne(
				(this.getNampSpace().contains("Entity") ? this.getNampSpace().replace("Entity", "")
						: this.getNampSpace()) + _SELECTBYPRIMARYKEY, id);
	}

	/**
	 * 
	 * @Title: updateByPrimaryKey
	 * @Description: 修改记录
	 * @param @param 实体参数
	 * @param @return 影响条数
	 * @return int
	 * @throws
	 */
	public int updateByPrimaryKey(T record) {
		return getSqlSession().update(
				(this.getNampSpace().contains("Entity") ? this.getNampSpace().replace("Entity", "")
						: this.getNampSpace()) + _UPDATEBYPRIMARYKEY, record);
	}

	/**
	 * 
	 * @Title: updateByPrimaryKeySelective
	 * @Description: 根据条件修改记录
	 * @param @param 实体参数
	 * @param @return 影响条数
	 * @return int
	 * @throws
	 */
	public int updateByPrimaryKeySelective(T record) {
		return getSqlSession().update(
				(this.getNampSpace().contains("Entity") ? this.getNampSpace().replace("Entity", "")
						: this.getNampSpace()) + _UPDATEBYPRIMARYKEYSELECTIVE, record);
	}

	/**
	 * 
	 * @Title: deleteByPrimaryKey
	 * @Description: 根据主键删除一条记录
	 * @param @param id 主键
	 * @param @return 影响条数
	 * @return int
	 * @throws
	 */
	public int deleteByPrimaryKey(PK id) {
		return getSqlSession().delete(
				(this.getNampSpace().contains("Entity") ? this.getNampSpace().replace("Entity", "")
						: this.getNampSpace()) + _DELETEBYPRIMARYKEY, id);
	}

	/**
	 * 通用分页封装
	 * 
	 * @param statementKey 查询方法
	 * @param pageForm 分页信息
	 * @param parameter 查询参数
	 * @param isSimplePage 是否是简单分页
	 * @return
	 * @author Kevin Lv
	 * @date 2015年5月22日 上午10:01:01
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected PageInfo<T> pageFind(String statementKey, PageForm pageForm, Object parameter,
								   Boolean isSimplePage) {
		PageHelper.startPage(pageForm.getPage(), pageForm.getRows());
		List<T> list = getSqlSession().selectList(statementKey, parameter);
		PageInfo<T> pageInfo = new PageInfo(list);

		return pageInfo;
	}

	/**
	 * 根据不同的数据库方言，生成类似于T-SQL中的<code>SELECT TOP N</code>查询语句
	 * 
	 * @param top 查询限定数量
	 * @param statement statement key
	 * @return 结果集合
	 * @author 何珏 2014-8-30
	 */
	protected List<T> findTop(int top, String statement) {
		return findTop(top, statement, null);
	}

	/**
	 * 返回前top条记录
	 * 
	 * @param top 返回条数
	 * @param statementKey SQL命名空间
	 * @param parameter 查询参数
	 * @return
	 * @author Kevin Lv
	 * @date 2015年6月4日 下午1:40:51
	 */
	protected List<T> findTop(int top, String statementKey, Object parameter) {
		List<T> list = getSqlSession().selectList(statementKey, parameter, new RowBounds(0, top));
		return list;
	}

	/**
	 * 根据不同的数据库方言，生成类似于T-SQL中的<code>SELECT TOP 1</code>查询语句
	 * 
	 * @return 结果实体
	 * @author 何珏 2014-11-4
	 */
	protected T findTopOne(String statementKey) {
		return findTopOne(statementKey, null);
	}

	/**
	 * 返回第一条
	 * 
	 * @param statementKey SQL命名空间
	 * @param parameter 查询参数
	 * @return
	 * @author Kevin Lv
	 * @date 2015年6月4日 下午1:41:14
	 */
	protected T findTopOne(String statementKey, Object parameter) {
		List<T> list = findTop(1, statementKey, parameter);
		return CollectionUtils.isEmpty(list) ? null : list.get(0);
	}

	/**
	 * 通用分页查询Model
	 * 
	 * @param statementKey 查询方法
	 * @param pageForm 分页信息
	 * @param parameter 查询参数
	 * @return 分页结果
	 * @author Jades.He
	 * @date 2015年6月27日 下午5:52:36
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected <M> PageInfo<M> pageFindModel(String statementKey, PageForm pageForm, Object parameter) {
		PageHelper.startPage(pageForm.getPage(), pageForm.getRows());
		List<M> list = getSqlSession().selectList(statementKey, parameter);
		PageInfo<M> pageInfo = new PageInfo(list);

		return pageInfo;
	}

}
