package com.xiao.weather.util;

import com.xiao.weather.util.annotation.DbInfo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URL;
import java.util.*;

public class GenerateCode {

    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    private static final String BASE_DIR = "G:\\IDEA_Project\\newseawin\\weather\\";

    private static final String MAPPER_WORK = "src\\main\\resources\\mappers\\";

    private static final String DAO_WORK_MODEL = "src\\main\\java\\";

    private static final String SERVICE_WORK_MODEL = "src\\main\\java\\";

    private static final String FACAD_WORK_MODEL = "src\\main\\java\\";


    @SuppressWarnings({"rawtypes"})
    public static void main(String[] args) throws Exception {


        Scanner scan = new Scanner(System.in);


        System.out.println("是否要生成 Mapper：y,n");
        String str2 = scan.next().trim();

        System.out.println("是否要生成Dao：y,n");
        String str3 = scan.next().trim();

        System.out.println("是否要生成Service：y,n");
        String str4 = scan.next().trim();

        System.out.println("是否要生成 Facade：y,n");
        String str5 = scan.next().trim();

        System.out.println("请输入要生成model的全名 ,按回车结束 ,可生成多个。如：com.oasis.wyvern.model.sys.tool.PredefinedCode");
        while (scan.hasNext()) {
            String str0 = scan.next().trim();
            Class clazz = Class.forName(str0);
            GenerateCode gen = new GenerateCode();

            if ("y".equalsIgnoreCase(str2)) {
                gen.genMapper(clazz, gen.getParentAndSelfFileds(clazz), gen);
            }

            if ("y".equalsIgnoreCase(str3)) {
                gen.genDao(clazz);
                gen.genDaoImpl(clazz);
            }

            if ("y".equalsIgnoreCase(str4)) {
                gen.genService(clazz);
            }

            if ("y".equalsIgnoreCase(str5)) {
                gen.genFacade(clazz);
            }
        }
        scan.close();
        return;
    }

    @SuppressWarnings("rawtypes")
    private void genDao(Class clazz) throws Exception {

        String modelName = clazz.getSimpleName();
        System.out.println(modelName);
        URL url = this.getClass().getResource("/templete/dao");
        genDaoInterface(modelName, url.getPath(), BASE_DIR + DAO_WORK_MODEL + getDaoPackeName(clazz), "Dao", clazz,
                getDaoPackeName(clazz));
        // genDaoInterface(modelName);
    }

    @SuppressWarnings("rawtypes")
    private void genDaoImpl(Class clazz) throws Exception {

        String modelName = clazz.getSimpleName();
        URL url = this.getClass().getResource("/templete/daoImpl");
        genDaoInterface(modelName, url.getPath(), BASE_DIR + DAO_WORK_MODEL + getDaoPackeName(clazz), "DaoImpl", clazz,
                getDaoPackeName(clazz));
        // genDaoInterface(modelName);
    }

    @SuppressWarnings("rawtypes")
    private String genDaoInterface(String name, String tdir, String dir, String lay, Class clazz, String packege) throws Exception {
        FileReader fr = null;
        FileWriter fw = null;
        BufferedReader br = null;
        StringBuffer sb = new StringBuffer();
        try {
            fr = new FileReader(new File(tdir));
            File file = new File(dir);
            if (!file.exists()) {
                boolean bool = file.mkdirs();
                if (!bool) {
                    throw new Exception("文件路径生成失败");
                }
            }
            System.out.println(dir + File.separator + name + lay + ".java");
            fw = new FileWriter(new File(dir + File.separator + name + lay + ".java"));
            br = new BufferedReader(fr);
            String line = br.readLine();
            while (line != null) {
                sb.append(line + "\n");
                line = br.readLine();
            }

            String sb_temp = sb.toString();
            sb_temp = sb_temp.replace("$", name);
            sb_temp = sb_temp.replace("#", packege.replace("\\", "."));
            sb_temp = sb_temp.replace("&", clazz.getName());
            fw.write(sb_temp);
        } catch (FileNotFoundException e) {
            LOG.error("生成接口异常,{}", e);
        } catch (IOException e) {
            LOG.error("生成接口异常,{}", e);
        } finally {
            try {
                if (fr != null) {
                    fr.close();
                }
                if (br != null) {
                    br.close();
                }
                if (fw != null) {
                    fw.close();
                }
            } catch (IOException e) {
                LOG.error("关闭数据流文件异常,{}", e);
            }
        }
        return null;
    }

    @SuppressWarnings("rawtypes")
    private String getDaoPackeName(Class clazz) {
        String module_name = clazz.getName();
        String temp = module_name.replace(".entity.", ".dao.");
        int a = temp.lastIndexOf(".");
        return temp.substring(0, a).replace(".", "\\");
    }

    @SuppressWarnings("rawtypes")
    private String getVoPackName(Class clazz) {
        String module_name = clazz.getName();
        String temp = module_name.replace(".entity.", ".common.biz.vo.");
        int a = temp.lastIndexOf(".");
        return temp.substring(0, a).replace(".", "\\");
    }

    @SuppressWarnings("rawtypes")
    private String getServicePackeName(Class clazz) {
        String module_name = clazz.getName();
        String temp = module_name.replace(".entity.", ".service.");
        int a = temp.lastIndexOf(".");
        return temp.substring(0, a).replace(".", "\\");
    }

    @SuppressWarnings("rawtypes")
    private String getFacadePackeName(Class clazz) {
        String module_name = clazz.getName();
        String temp = module_name.replace(".entity.", ".controller.");
        int a = temp.lastIndexOf(".");
        return temp.substring(0, a).replace(".", "\\");
    }

    @SuppressWarnings("rawtypes")
    private void genService(Class clazz) throws Exception {
        String modelName = clazz.getSimpleName();
        System.out.println(modelName);
        genDaoInterface(modelName, this.getClass().getResource("/templete/service").getPath(), BASE_DIR + SERVICE_WORK_MODEL
                + getServicePackeName(clazz), "Service", clazz, getServicePackeName(clazz));
        genDaoInterface(modelName, this.getClass().getResource("/templete/serviceImpl").getPath(), BASE_DIR + SERVICE_WORK_MODEL
                + getServicePackeName(clazz), "ServiceImpl", clazz, getServicePackeName(clazz));
    }

    @SuppressWarnings("rawtypes")
    private void genFacade(Class clazz) throws Exception {

        String modelName = clazz.getSimpleName();
        System.out.println(modelName);
        genDaoInterface(modelName, this.getClass().getResource("/templete/facade").getPath(), BASE_DIR + FACAD_WORK_MODEL
                + getFacadePackeName(clazz), "Controller", clazz, getFacadePackeName(clazz));

    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    private String genPrimaryKey(Class clazz) {
        StringBuffer key = new StringBuffer();

        if (clazz.isAnnotationPresent(DbInfo.class)) {
            // 该class存在Table类型的注解，获取指定的表名
            DbInfo table = (DbInfo) clazz.getAnnotation(DbInfo.class);
            String tableName = table.tableName();
            key.append("--create key " + key + "\n");
            key.append("alter table " + tableName + " add primary key (ID);");
            key.append("\n");
        }
        System.out.println(key.toString());
        return key.toString();
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public String genTable(Class clazz, List<Field> fields) {

        String table = null;
        if (clazz.isAnnotationPresent(DbInfo.class)) {
            table = ((DbInfo) clazz.getAnnotation(DbInfo.class)).tableName();
        } else {
            String name = clazz.getSimpleName();
            table = this.className2TableName(name);
        }

        StringBuffer sb = new StringBuffer();
        sb.append("--create table  " + table + "\n");
        sb.append("create table " + table + " (\n");

        List<String> columns = getColumnToString(fields);
        for (String s : columns) {
            sb.append(s);
        }

        if (columns.size() > 0) {
            sb.delete(sb.length() - 2, sb.length());
            sb.append("\n");
        }
        sb.append(");\n");
        sb.append("-------" + table + "create end------\n");

        System.out.println(sb.toString());

        return sb.toString();
    }

    private List<String> getColumnToString(List<Field> fields) {
        List<InnerField> totalFieldList = new ArrayList<InnerField>();
        for (Field field : fields) {
            totalFieldList.addAll(getAllFields(field, field.getType(), field.getName()));
        }
        List<String> list = new ArrayList<String>();
        for (InnerField field : totalFieldList) {

//            String name = field.getFieldName();
            String type = field.getType();

            String col_sql = getColumnSql(field.getColumnName(), type);
            if (col_sql != null && !col_sql.isEmpty()) {
                list.add(col_sql);
            }
        }
        return list;
    }

    private String getColumnSql(String name, String type) {
        String name_temp = name;
        if ("java.lang.String".equals(type)) {
            return name_temp + "  varchar2(255),\n";
        } else if ("int".equalsIgnoreCase(type) || "java.lang.Integer".equalsIgnoreCase(type)) {
            return name_temp + "  NUMBER(19),\n";
        } else if ("double".equalsIgnoreCase(type) || "java.lang.Double".equalsIgnoreCase(type)) {
            return name_temp + "  NUMBER(19,4),\n";
        } else if ("float".equalsIgnoreCase(type) || "java.lang.Float".equalsIgnoreCase(type)) {
            return name_temp + "  NUMBER(19,4),\n";
        } else if ("date".equalsIgnoreCase(type) || "java.util.Date".equalsIgnoreCase(type)) {
            return name_temp + "  TIMESTAMP(6),\n";
        } else if ("long".equalsIgnoreCase(type) || "java.lang.Long".equalsIgnoreCase(type)) {
            return name_temp + "  NUMBER(19),\n";
        } else if ("java.util.Set".equalsIgnoreCase(type) || "java.util.Map".equalsIgnoreCase(type)
                || "java.util.List".equalsIgnoreCase(type)) {
            return null;
        } else {
            return name_temp + "  varchar2(255),\n";
        }
    }

    private String converColumnName(String name) {
        name = name.trim();
        StringBuffer ret_name = new StringBuffer();
        ret_name.append(name.charAt(0));
        for (int i = 1; i < name.length(); i++) {

            if ((name.charAt(i) < 91 && name.charAt(i) > 64)) {
                ret_name.append("_");
            }
            ret_name.append(name.charAt(i));
        }
        return ret_name.toString().toLowerCase();
    }

    @SuppressWarnings({"rawtypes", "unused"})
    private static String getTypesByField(Field field) {
        String res = "VARCHAR";
        Class type = field.getType();
        if (type == String.class) {
            res = "VARCHAR";
        } else if (type == Integer.class || type == Long.class || type == int.class || type == long.class || type == Double.class
                || type == double.class || type == Float.class || type == float.class || type == BigDecimal.class) {
            res = "NUMERIC";
        } else if (type == Date.class) {
            res = "TIMESTAMP";
        } else if (type == Boolean.class || type == boolean.class) {
            res = "BIT";
        }
        return res;
    }

    /**
     * 获取当前类和父类的字段
     *
     * @param clazz
     * @return
     */
    @SuppressWarnings("rawtypes")
    private List<Field> getParentAndSelfFileds(Class clazz) {

        Field[] parentFields = this.getParentFields(clazz);
        Field[] fields = this.getFields(clazz);

        List<Field> all = Arrays.asList(parentFields);
        List<Field> temp = new ArrayList<Field>(all);
        for (Field f : fields) {
            boolean flag = true;
            for (int i = 0; i < parentFields.length; i++) {
                if (parentFields[i].getName().equalsIgnoreCase(f.getName())) {
                    flag = false;
                    break;
                }
            }

            if (flag) {
                temp.add(f);
            }
        }

        return temp;
    }

    /**
     * 生成insert 语句
     *
     * @param clazz
     * @return
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    private String genInsert(Class clazz, List<Field> fieldList) {
        String table = null;
        if (clazz.isAnnotationPresent(DbInfo.class)) {
            table = ((DbInfo) clazz.getAnnotation(DbInfo.class)).tableName();
        } else {
            String name = clazz.getSimpleName();
            table = this.className2TableName(name);
        }

        StringBuffer sb = new StringBuffer();
        sb.append("\t\tinsert into " + table + "(\n");
        int index1 = 0;
        boolean isLastOuterItem1 = false;
        List<InnerField> totalFieldList = new ArrayList<InnerField>();
        for (int i = 0; i < fieldList.size(); i++) {
            Field field = fieldList.get(i);
            totalFieldList.addAll(getAllFields(field, field.getType(), field.getName()));
        }

        for (int i = 0; i < totalFieldList.size(); i++) {
            if (i == totalFieldList.size() - 1) {
                isLastOuterItem1 = true;
            }
            InnerField field = totalFieldList.get(i);
            if ("id".equals(field.getColumnName())) {
                continue;
            }
            sb.append("\t\t<!-- " + index1 + " -->" + field.getColumnName());
            if (isLastOuterItem1) {
                sb.append("\n");
            } else {
                sb.append(",\n");
            }
            index1++;
        }
        sb.append("\t\t) \n");
        sb.append("\t\tvalues(\n");
        int index2 = 0;
        boolean isLastOuterItem2 = false;
        for (int i = 0; i < totalFieldList.size(); i++) {
            if (i == totalFieldList.size() - 1) {
                isLastOuterItem2 = true;
            }

            InnerField field = totalFieldList.get(i);
            if ("id".equals(field.getTotalName())) {
                continue;
            }
            sb.append("\t\t<!-- " + index2 + " -->#{" + field.getTotalName() + "}");
            if (isLastOuterItem2) {
                sb.append("\n");
            } else {
                sb.append(",\n");
            }
            index2++;
        }
        sb.append("\t\t)\n");
        sb.append("\t\t<selectKey keyProperty=\"id\" resultType=\"java.lang.Long\" order=\"AFTER\">\n");
        sb.append(" \t\t\tselect LAST_INSERT_ID()\n");
        sb.append("\t\t</selectKey>\n");
        return sb.toString();
    }


    /**
     * 生成batchInsert 语句
     *
     * @return
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    private String genBatchInsert(Class clazz, List<Field> fieldList) {
        String table = null;
        String seq_name = null;
        if (clazz.isAnnotationPresent(DbInfo.class)) {
            table = ((DbInfo) clazz.getAnnotation(DbInfo.class)).tableName();
            DbInfo seq = (DbInfo) clazz.getAnnotation(DbInfo.class);
            seq_name = seq.seqName();
        } else {
            String name = clazz.getSimpleName();
            table = this.className2TableName(name);
            seq_name = table + "_seq";
        }

        StringBuffer sb = new StringBuffer();

        sb.append("insert into " + table + "(\n");
        int index1 = 0;
        boolean isLastOuterItem1 = false;
        List<InnerField> totalFieldList = new ArrayList<InnerField>();
        for (int i = 0; i < fieldList.size(); i++) {
            Field field = fieldList.get(i);
            totalFieldList.addAll(getAllFields(field, field.getType(), field.getName()));
        }

        for (int i = 0; i < totalFieldList.size(); i++) {
            if (i == totalFieldList.size() - 1) {
                isLastOuterItem1 = true;
            }
            InnerField field = totalFieldList.get(i);
            sb.append("\t\t<!-- " + index1 + " -->" + field.getColumnName());
            if (isLastOuterItem1) {
                sb.append("\n");
            } else {
                sb.append(",\n");
            }
            index1++;
        }
        sb.append("\t\t) \n");

        sb.append("\t\tSELECT " + seq_name + ".NEXTVAL,A.* FROM (\n");
        sb.append("\t\t<foreach collection=\"list\" item=\"item\" index=\"index\" separator=\"union all\">\n");
        sb.append("\t\t\tSELECT\n");
        int index2 = 0;
        boolean isLastOuterItem2 = false;
        for (int i = 0; i < totalFieldList.size(); i++) {
            if (i == totalFieldList.size() - 1) {
                isLastOuterItem2 = true;
            }
            InnerField field = totalFieldList.get(i);
            String fieldName = field.getName();
            if ("id".equalsIgnoreCase(fieldName)) {
                continue;
            }
            sb.append("\t\t\t<!-- " + index2 + " -->#{item." + field.getTotalName() + "}");
            if (isLastOuterItem2) {
                sb.append("\n");
            } else {
                sb.append(",\n");
            }
            index2++;
        }
        sb.append("\t\t\tFROM DUAL\n");
        sb.append("\t\t</foreach>\n");
        sb.append("\t\t) A");
        // System.out.println(sb.toString());
        return sb.toString();
    }


    private String className2TableName(String name) {
        String[] tempArry = name.split("");
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < tempArry.length; i++) {
            String temp = tempArry[i];
            String trimTemp = StringUtils.trim(temp);
            if (!StringUtils.isEmpty(trimTemp)) {
                if (StringUtils.isAllUpperCase(trimTemp)) {
                    sb.append("_");
                    sb.append(trimTemp.toLowerCase());
                } else {
                    sb.append(trimTemp);
                }
            }
        }
        String ret = sb.toString();
        ret = StringUtils.removeStart(ret, "_");
        ret = "btrcrm_" + ret;
        return ret;
    }


    /**
     * .
     * 生成update语句
     *
     * @param clazz
     * @return
     */

    @SuppressWarnings({"unchecked", "rawtypes"})
    private String genUpdate(Class clazz) {
        String table = null;
        if (clazz.isAnnotationPresent(DbInfo.class)) {
            table = ((DbInfo) clazz.getAnnotation(DbInfo.class)).tableName();
        } else {
            String name = clazz.getSimpleName();
            table = this.className2TableName(name);
        }

        StringBuffer sb = new StringBuffer();
        sb.append("\t update " + table);
        // System.out.println(sb.toString());
        return sb.toString();
    }

    private String genUpdateSetSql(List<Field> fieldList) {
        StringBuffer sb = new StringBuffer();
        int index = 0;
        boolean isLastOuterItem = false;

        List<InnerField> totalFieldList = new ArrayList<InnerField>();
        for (int i = 0; i < fieldList.size(); i++) {
            Field field = fieldList.get(i);
            totalFieldList.addAll(getAllFields(field, field.getType(), field.getName()));
        }

        for (int i = 0; i < totalFieldList.size(); i++) {
            if (i == totalFieldList.size() - 1) {
                isLastOuterItem = true;
            }
            InnerField field = totalFieldList.get(i);
            String fieldName = field.getName();
            if ("id".equalsIgnoreCase(fieldName)) {
                continue;
            }

            if ("lockVersion".equalsIgnoreCase(fieldName)) {
                sb.append("\t\t<!-- " + index + " -->" + field.getColumnName() + "=#{" + field.getTotalName()
                        + "}+1");
                if (isLastOuterItem) {
                    sb.append("\n");
                } else {
                    sb.append(",\n");
                }
            } else {
                sb.append("\t\t<if test=\"" + field.getTotalName() + "!=null and " + field.getTotalName() + "!='' \">\n");
                sb.append("\t\t<!-- " + index + " -->" + field.getColumnName() + "=#{" + field.getTotalName() + "}");
                if (isLastOuterItem) {
                    sb.append("\n");
                } else {
                    sb.append(",\n");
                }
                sb.append("\t\t</if>\n");
            }
            index++;
        }
        // System.out.println(sb.toString());
        return sb.toString();
    }

//    private String convert2VoName(String className){
//        StringBuffer sb = new StringBuffer();
//        String[] splitArray = className.split("\\.");
//
//        if (splitArray.length > 0) {
//            for (int i =0; i<splitArray.length;i++) {
//                if (splitArray[i] != null) {
//                    sb.append(splitArray[i]);
//                    sb.append("Vo.");
//                }
//
//            }
//        }
//        return sb.toString();
//    }

    private String convert2SoName(String className) {
        StringBuffer sb = new StringBuffer();
        String[] splitArray = className.split("\\.");

        if (splitArray.length > 0) {
            for (int i = 0; i < splitArray.length; i++) {
                if (splitArray[i] != null) {
                    sb.append(splitArray[i]);
                    sb.append("So.");
                }

            }
        }
        return sb.toString();
    }

    /**
     * 生成条件语句
     *
     * @param clazz
     * @return
     */

    @SuppressWarnings("rawtypes")
    private String genPageListClause(Class clazz, List<Field> fieldList) {

        StringBuffer sb = new StringBuffer();
        List<InnerField> totalFieldList = new ArrayList<InnerField>();
        for (Field field : fieldList) {
            totalFieldList.addAll(getAllFields(field, field.getType(), field.getName()));
        }

        for (int i = 0; i < totalFieldList.size(); i++) {
            InnerField field = totalFieldList.get(i);
            String fieldName = field.getName();
            if ("createdTime".equals(fieldName) ||
                    "creatorId".equals(fieldName) ||
                    "creator".equals(fieldName) ||
                    "updatedTime".equals(fieldName) ||
                    "updaterId".equals(fieldName) ||
                    "updater".equals(fieldName) ||
                    "lockVersion".equals(fieldName) ||
                    "id".equals(fieldName)) {
                continue;
            }
            String soFieldName = fieldName;
            if (field.getClassName() != null) {
                soFieldName = convert2SoName(field.getClassName()) + fieldName;
            }

            if ("java.lang.String".equals(field.getOrgClassName())) {
                sb.append("\t\t<if test=\"" + soFieldName + " !=null and " + soFieldName + " !='' \">\n");
            } else {
                sb.append("\t\t<if test=\"" + soFieldName + " !=null \">\n");
            }
            sb.append("\t\t\t and t." + field.getColumnName() + " = #{" + soFieldName + "}\n");
            sb.append("\t\t</if>\n");
        }
        // System.out.println(sb.toString());
        return sb.toString();
    }

    /**
     * 生成Bo resultmap
     */
    @SuppressWarnings("rawtypes")
    private String genResultMap(Class clazz, List<Field> fieldList) {
        StringBuffer sb = new StringBuffer();

        List<InnerField> totalFieldList = new ArrayList<InnerField>();
        for (Field field : fieldList) {
            totalFieldList.addAll(getAllFields(field, field.getType(), field.getName()));
        }

        int index = 0;
        for (int i = 0; i < totalFieldList.size(); i++) {
            InnerField field = totalFieldList.get(i);
            sb.append("\t\t<!-- " + index + " --><result property=\"" + field.getTotalName() + "\"  column=\"" + field.getColumnName()
                    + "\" />\n");
            index++;
        }

        // System.out.println(sb.toString());
        return sb.toString();
    }

    /**
     * 生成mapper 文件
     *
     * @param clazz
     * @return
     */

    @SuppressWarnings({"rawtypes", "unchecked"})
    private String genMapper(Class clazz, List<Field> fields, GenerateCode gen) throws Exception {
        FileReader fr = null;
        BufferedReader br = null;
        FileWriter fw = null;
        String name = clazz.getSimpleName();

        try {

            StringBuffer sb = new StringBuffer();
            URL url = this.getClass().getResource("/templete/MapperTemplate.xml");
             fr = new FileReader(url.getFile());
            br = new BufferedReader(fr);

            String line = br.readLine();

            while (line != null) {
                sb.append(line + "\n");
                line = br.readLine();
            }

            String mapper = sb.toString();

            Properties prop = new Properties();

            String modelname = clazz.getSimpleName();
            String insert = this.genInsert(clazz, fields);
            //String batchInsert = this.genBatchInsert(clazz, fields);
            String update = this.genUpdate(clazz);
            String updateSetSql = this.genUpdateSetSql(fields);
            String where = this.genPageListClause(clazz, fields);
            String resultMap = this.genResultMap(clazz, fields);
            if (clazz.isAnnotationPresent(DbInfo.class)) {
                prop.put("tablename", ((DbInfo) clazz.getAnnotation(DbInfo.class)).tableName());
            } else {
                String table = this.className2TableName(name);
                prop.put("tablename", table);
            }

            prop.put("modelname", modelname);
            prop.put("insert", insert);
            //prop.put("batchInsert", batchInsert);
            prop.put("where", where);
            prop.put("update", update);
            prop.put("updateSetSql", updateSetSql);
            prop.put("classvo", clazz.getName() + "Vo");
            prop.put("resultMap", resultMap);
            prop.put("class", clazz.getName());
            prop.put("modelpackge", getDaoPackeName(clazz).replace("\\", "."));
            prop.put("vopackge", getVoPackName(clazz).replace("\\", "."));

            Enumeration<Object> keys = prop.keys();
            while (keys.hasMoreElements()) {
                String key = (String) keys.nextElement();
                String value = prop.getProperty(key);
                // System.out.println(key + "=" + value);
                mapper = mapper.replaceAll("[$]\\{" + key + "\\}", value);
            }
            System.out.println(mapper);

            File ma_lo = new File(BASE_DIR + MAPPER_WORK + name + "Mapper.xml");
            if (!ma_lo.getParentFile().exists()) {
                boolean bool = ma_lo.getParentFile().mkdirs();
                if (!bool) {
                    throw new Exception("文件路径生成失败");
                }
            }
            fw = new FileWriter(new File(BASE_DIR + MAPPER_WORK + name + "Mapper.xml"));
            System.out.println("====" + BASE_DIR + MAPPER_WORK + name + "Mapper.xml");
            fw.write(mapper);

        } catch (FileNotFoundException e) {
            LOG.error("文件找不到,{}", e);
        } catch (IOException e) {
            LOG.error("IO异常,{}", e);
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
                if (br != null) {
                    br.close();
                }
                if (fw != null) {
                    fw.close();
                }
            } catch (IOException e) {
                LOG.error("IO异常,{}", e);
            }
        }

        return null;
    }

    /**
     * 判断一个类是否为基本数据类型。
     *
     * @param clazz 要判断的类。
     * @return true 表示为基本数据类型。
     */
    private static boolean isBaseDataType(@SuppressWarnings("rawtypes") Class clazz) {
        return (
                clazz.equals(String.class) ||
                        clazz.equals(Integer.class) ||
                        clazz.equals(Byte.class) ||
                        clazz.equals(Long.class) ||
                        clazz.equals(Double.class) ||
                        clazz.equals(Float.class) ||
                        clazz.isEnum() ||
                        clazz.equals(Character.class) ||
                        clazz.equals(Short.class) ||
                        clazz.equals(BigDecimal.class) ||
                        clazz.equals(BigInteger.class) ||
                        clazz.equals(Boolean.class) ||
                        clazz.equals(Date.class) ||
                        clazz.isPrimitive()
        );
    }

    /**
     * 得到类字段
     *
     * @param clazz
     * @return
     */
    @SuppressWarnings("rawtypes")
    private Field[] getFields(Class clazz) {

        Field[] fields = clazz.getDeclaredFields();
        List<Field> temp = new ArrayList<Field>();
        for (Field f : fields) {
            if ("serialVersionUID".equalsIgnoreCase(f.getName())) {
                continue;
            }

            temp.add(f);
        }
        Field[] ret = temp.toArray(new Field[0]);
        return ret;
    }

    /**
     * 得到父类的字段
     *
     * @param clazz
     * @return
     */
    @SuppressWarnings("rawtypes")
    private Field[] getParentFields(Class clazz) {
        Class sClass = clazz.getSuperclass();
        List<Field> temp = new ArrayList<Field>();
        while (sClass != null) {
            Field[] superfields = sClass.getDeclaredFields();
            //就是为了mapp好看
            CollectionUtils.reverseArray(superfields);
            for (Field f : superfields) {
                if ("serialVersionUID".equalsIgnoreCase(f.getName())) {
                    continue;
                }
                temp.add(f);
            }
            sClass = hasParent(sClass);
        }
        Field[] ret = temp.toArray(new Field[0]);
        //就是为了mapp好看
        CollectionUtils.reverseArray(ret);
        return ret;
    }

    @SuppressWarnings("rawtypes")
    private Class hasParent(Class clazz) {
        Class sclass = clazz.getSuperclass();
        if (sclass != null && sclass.getSuperclass() != null) {
            return sclass;
        }
        return null;
    }

    /**
     * 得到所有字段
     *
     * @return
     */
    @SuppressWarnings("rawtypes")
    private List<InnerField> getAllFields(Field inputField, Class clazz, String className) {
        Field thisField = inputField;
        List<InnerField> ret = new ArrayList<>();
        while (thisField != null && !isBaseDataType(thisField.getType())) {
            className = buildClassName(className, thisField.getName());
            List<Field> allFields = getParentAndSelfFileds(thisField.getType());
            for (Field field : allFields) {
                ret.addAll(getAllFields(field, thisField.getType(), className));
            }
            thisField = null;
        }
        if (thisField != null) {
            if (isBaseDataType(clazz)) {
                className = null;
            }
            InnerField retField = buildInnerField(thisField, clazz, className);
            ret.add(retField);
        }
        return ret;
    }

    private String buildClassName(String className, String subClassName) {
        if (subClassName.equals(className)) {
            return className;
        } else {
            className = className + "." + subClassName;
        }
        return className;
    }

    @SuppressWarnings("rawtypes")
    private InnerField buildInnerField(Field field, Class clazz, String className) {
        InnerField retField = new InnerField();
        retField.setClassName(className);
        retField.setName(field.getName());
        retField.setColumnName(converColumnName(field.getName()));
        retField.setType(field.getType().getName());
        retField.setOrgClassName(clazz.getSimpleName());
        return retField;
    }

    class InnerField {
        private String className;
        private String name;
        private String columnName;
        private String type;
        private String orgClassName;

        public String getClassName() {
            return className;
        }

        public void setClassName(String className) {
            this.className = className;
        }

        public String getName() {
            return name;
        }

        public void setName(String fieldName) {
            this.name = fieldName;
        }

        public String getColumnName() {
            return columnName;
        }

        public void setColumnName(String columnName) {
            this.columnName = columnName;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getOrgClassName() {
            return orgClassName;
        }

        public void setOrgClassName(String orgClassName) {
            this.orgClassName = orgClassName;
        }

        public String getTotalName() {
            if (getClassName() != null && getName() != null) {
                return getClassName() + "." + getName();
            } else {
                return getName();
            }
        }
    }

}
