所有项目的公共依赖，主要是一些工具性的东西

相关依赖:
spring-boot：注意是boot的依赖，没有web依赖，如果你要创建的模块不需要引入web依赖可以直接引入该依赖
lombok: 所有项目支持lombok
hutool-core：hutool的核心依赖，需要hutool的其他依赖可以自己加
hutool-json: json工具
hutool-extra: spring工具



没有web依赖，web相关的在lu-common-web 项目中
没有mybatis-plus的依赖 关注lu-mybatis-plus