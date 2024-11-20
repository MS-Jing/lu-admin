package ${serviceImpl.packagePath};

import ${entity.packagePath}.${entity.className};
import ${mapper.packagePath}.${mapper.className};
import ${service.packagePath}.${service.className};
import com.lj.mp.standard.StandardServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * ${tableComment!} 服务实现类
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
@Service
public class ${serviceImpl.className} extends StandardServiceImpl<${mapper.className}, ${entity.className}> implements ${service.className} {

}
