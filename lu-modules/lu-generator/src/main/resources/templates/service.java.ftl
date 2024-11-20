package ${packageInfo.service};

import ${packageInfo.entity}.${entityName};
import com.lj.mp.standard.StandardService;
<#list packageInfo.servicePackages as pkg>
 import ${pkg};
</#list>

/**
 * <p>
 * ${tableInfo.comment!} 服务类
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
public interface ${tableInfo.serviceName} extends StandardService<${entityName}> {

}

