package com.gobatis.demo.core;

import com.gobatis.demo.core.mapper.BaseMapper;
import com.google.common.base.CaseFormat;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.scripting.xmltags.XMLLanguageDriver;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.stream.Stream;

/**
 * @author liuzh
 * @since 2017/10/23.
 */
public class SimpleMapperHelper {

    public static final XMLLanguageDriver XML_LANGUAGE_DRIVER = new XMLLanguageDriver();

    /**
     * 获取泛型类型
     *
     * @param mapperClass
     * @return
     */
    public static Class getEntityClass(Class<?> mapperClass){
        Type[] types = mapperClass.getGenericInterfaces();
        Class<?> entityClass = null;
        for (Type type : types) {
            if (type instanceof ParameterizedType) {
                ParameterizedType t = (ParameterizedType) type;
                //判断父接口是否为 BaseMapper.class
                if (t.getRawType() == BaseMapper.class) {
                    //得到泛型类型
                    entityClass = (Class<?>) t.getActualTypeArguments()[0];
                    break;
                }
            }
        }
        return entityClass;
    }

    /**
     * 替换 SqlSource
     *
     * @param ms
     * @throws Exception
     */
    public static void changeMs(MappedStatement ms) throws Exception {
        String msId = ms.getId();
        //标准msId为 包名.接口名.方法名
        int lastIndex = msId.lastIndexOf(".");
        String methodName = msId.substring(lastIndex + 1);
        String interfaceName = msId.substring(0, lastIndex);
        Class<?> mapperClass = Class.forName(interfaceName);
        //判断是否继承了通用接口
        if(BaseMapper.class.isAssignableFrom(mapperClass)){
            //判断当前方法是否为通用 select 方法
            if ("updateIgnoreNull".equals(methodName)) {
                Class entityClass = getEntityClass(mapperClass);
                Field[] fields = entityClass.getDeclaredFields();
                StringBuilder sb = new StringBuilder();
                sb.append("<script>");
                sb.append("UPDATE " + tableName(entityClass) + "\n ");
                sb.append("<trim prefix=\"set\" suffixOverrides=\",\">\n ");
                for(Field field : fields){
                    String sql = "<if test=\"" + field.getName() + "!=null\">\n" +
                            columnName(field) + "=" + "#{" + field.getName() + "}, \n" +
                            "</if>\n";
                    sb.append(sql);
                }
                sb.append("</trim>\n");
                sb.append("WHERE id=#{id}");
                sb.append("</script>");
                //解析 sqlSource
                SqlSource sqlSource = XML_LANGUAGE_DRIVER.createSqlSource(ms.getConfiguration(), sb.toString(), entityClass);
                //替换
                MetaObject msObject = SystemMetaObject.forObject(ms);
                msObject.setValue("sqlSource", sqlSource);
            }
        }
    }

    protected static String tableName(Class<?> entityType) {
        return CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, entityType.getSimpleName());
    }

    protected static String columnName(Field field) {
        return CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, field.getName());
    }

}