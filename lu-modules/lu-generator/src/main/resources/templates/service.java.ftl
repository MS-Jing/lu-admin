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

    <#if genPage>
    IPage<${pageResult.className}> page(${pageParam.className} param);
    </#if>

    <#if genInfo>
    ${infoResult.className} info(${pkType} id);
    </#if>

    <#if genSave>
    void save(${saveParam.className} param);
    </#if>

    <#if genUpdate>
    void update(${updateParam.className} param);
    </#if>
}
