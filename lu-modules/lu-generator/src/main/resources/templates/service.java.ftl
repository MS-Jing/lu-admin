package ${service.packagePath};

import ${entity.packagePath}.${entity.className};
import com.lj.mp.standard.StandardService;
<#list service.packages as pkg>
 import ${pkg};
</#list>

/**
 * <p>
 * ${tableComment!} 服务类
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
public interface ${service.className} extends StandardService<${entity.className}> {

}

