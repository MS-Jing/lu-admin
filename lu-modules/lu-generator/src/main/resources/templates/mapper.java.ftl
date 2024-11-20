package ${packageInfo.mapper};

import ${packageInfo.entity}.${entityName};
import com.lj.mp.standard.StandardMapper;
import org.springframework.stereotype.Repository;


/**
 * <p>
 * ${tableInfo.comment!} Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
@Repository
public interface ${tableInfo.mapperName} extends StandardMapper<${entityName}> {

}

