package ${packageInfo.serviceImpl};

import ${packageInfo.entity}.${entityName};
import ${packageInfo.mapper}.${tableInfo.mapperName};
import ${packageInfo.service}.${tableInfo.serviceName};
import com.lj.mp.standard.StandardServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * ${tableInfo.comment!} 服务实现类
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
@Service
public class ${tableInfo.serviceImplName} extends StandardServiceImpl<${tableInfo.mapperName}, ${entityName}> implements ${tableInfo.serviceName} {

}
