package ${mapper.packagePath};

import ${entity.packagePath}.${entity.className};
import com.lj.mp.standard.StandardMapper;
import org.springframework.stereotype.Repository;
import org.apache.ibatis.annotations.Mapper;


/**
 * <p>
 * ${tableComment!} Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
@Repository
@Mapper
public interface ${mapper.className} extends StandardMapper<${entity.className}> {

}

