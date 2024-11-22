package ${serviceImpl.packagePath};

import ${entity.packagePath}.${entity.className};
import ${mapper.packagePath}.${mapper.className};
import ${service.packagePath}.${service.className};
import com.lj.mp.standard.StandardServiceImpl;
import org.springframework.stereotype.Service;
<#if genPage>
import com.lj.mp.utils.PageQueryUtils;
</#if>
<#list service.packages as pkg>
import ${pkg};
</#list>
<#list serviceImpl.packages as pkg>
import ${pkg};
</#list>

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
    <#if genPage>

    @Override
    public IPage<${pageResult.className}> page(${pageParam.className} param){
        return this.page(PageQueryUtils.getPage(param),lambdaQueryWrapper()
                // todo 查询条件
                       ).convert(${pageResult.className}::of);
    }
    </#if>
    <#if genInfo>

    @Override
    public ${infoResult.className} info(${pkType} id){
        return ${infoResult.className}.of(this.getById(id));
    }
    </#if>
    <#if genSave>

    @Override
    public void save(${saveParam.className} param){
        ${entity.className} entity = param.toEntity();
        this.save(entity);
    }
    </#if>
    <#if genUpdate>

    @Override
    public void update(${updateParam.className} param) {
        ${entity.className} entity = param.toEntity();
        this.updateById(entity);
    }
    </#if>
}
