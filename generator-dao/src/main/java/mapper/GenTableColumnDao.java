package mapper;

import com.pengji.linker.gencode.entity.GenTableColumn;

/**
 * 业务表字段DAO接口
 *
 * @author Michael
 * @version 2013-10-15
 */
public interface GenTableColumnDao extends CrudDao<GenTableColumn> {

    public void deleteByGenTableId(String genTableId);
}
