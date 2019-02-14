package com.productivity.web.tool;

import com.productivity.util.StringUtils;
import com.productivity.web.entity.WorkCustomer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

/**
 * 代码生成
 */
public class GenerateCode {

    private static String parentPath = "/Users/zqLuo/Desktop/generateCode";
    private static String column_name = "column_name";
    private static String camelName = "camelName";
    private static String column_comment = "column_comment";
    private static String data_type = "data_type";

    private static String name = "用户操作日志表";
    private static Class clazz = WorkCustomer.class;
    private static String tableName = "Operate_Log";
    private static String dataBase = "ydzw";
    private static String entityName = "OperateLog";
    private static String packageName = "com.free.mobile.model";
    /**
     * 排除显示字段
     */
    private static Map<String,String> excludeMap = new HashMap();
    /**
     * 需要的查询字段
     */
    private static Map<String,String> searchMap = new HashMap<>();

    private static String className = clazz.getSimpleName();
    private static String smallFirst = getMethodName(className);
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private static Connection connection = null;


    public static void main(String[] args) {
        try {
            File parentFile = new File(parentPath);
            if(parentFile.exists()){
                if(parentFile.listFiles() != null){
                    for(File file : parentFile.listFiles()){
                        file.delete();
                    }
                }
            }else {
                parentFile.mkdir();
            }
//            excludeMap.put("id","");
//            searchMap.put("customerName","");
////            excludeMap.put("password","");
//            generateService();
//            generateServiceImpl();
//            generateController();
//            generatePage();
            generateEntity();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 生成对象
     */
    private static void generateEntity() throws Exception{
        List<Map<String,String>> list = readColumnName(tableName,dataBase);
        File serviceFile = new File(GenerateCode.parentPath+File.separator+entityName+".java");
        BufferedWriter writer = new BufferedWriter(new FileWriter(serviceFile));
        writer.write("package "+packageName +";\n\n");
        writer.write("import javax.persistence.Column;\n");
        writer.write("import javax.persistence.Entity;\n");
        writer.write("import javax.persistence.Table;\n");
        writer.write("import java.util.Date;\n\n");
        writer.write("/**\n");
        writer.write(" * " + name + "\n");
        writer.write(" */\n");
        writer.write("@Entity\n");
        writer.write("@Table(name = \"" + tableName + "\")\n");
        writer.write("public class " + entityName + " {\n");
        StringBuilder method = new StringBuilder();
        for(Map<String,String> map : list){
            String typeName;
            String camelNameIn = map.get(camelName);
            if(map.get(data_type).toLowerCase().equals("datetime")){
                typeName = "Date";
            }else if (map.get(data_type).toLowerCase().equals("int")){
                typeName = "Integer";
            }else {
                typeName = "String";
            }
            writer.write("\t/**\n");
            writer.write("\t * " + map.get(column_comment) + "\n");
            writer.write("\t */\n");
            writer.write("\tprivate " + typeName + " " + camelNameIn + ";\n");
            //setmethod
            method.append("\tpublic void ").append(StringUtils.setMethodName(camelNameIn))
                    .append("(").append(typeName).append(" ").append(camelNameIn).append(") {\n")
                    .append("\t\tthis.").append(camelNameIn).append(" = ").append(camelNameIn).append(";\n\t}\n\n");
            //getmethod
            method.append("\t@Column(name = \"" + map.get(column_name) + "\")\n");
            method.append("\tpublic ").append(typeName).append(" ").append(StringUtils.getMethodName(camelNameIn)).append("() {\n")
                    .append("\t\treturn ").append(camelNameIn).append(";\n\t}\n\n");
        }
        writer.newLine();
        writer.write(method.toString());
        writer.write("}");
        writer.flush();
        writer.close();
    }

    /**
     * 获取数据库连接
     * @return
     */
    public static Connection getConnection() throws SQLException {
        if(GenerateCode.connection == null){
            String url = "jdbc:mysql://127.0.0.1:3306/ydzw?characterEncoding=utf8";
            String username = "root";
            String password = "111111";
            GenerateCode.connection = DriverManager.getConnection(url, username, password);
        }
        return GenerateCode.connection;
    }

    /**
     * 读取字段名和注释
     * @param tableName
     * @return
     */
    public static List<Map<String,String>> readColumnName(String tableName,String dataBase) throws Exception {
        List<Map<String,String>> resultMap = new ArrayList<>();
        //定义sql语句
        String sql = "select column_name,column_comment,data_type from INFORMATION_SCHEMA.Columns where table_name=? and table_schema=?";
        //获取预编译对象
        PreparedStatement ps = getConnection().prepareStatement(sql);
        ps.setString(1,tableName);
        ps.setString(2,dataBase);
        //执行查询操作获取结果集
        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            Map<String,String> map = new HashMap<>();
            map.put(column_name,rs.getString("column_name"));
            map.put(camelName, StringUtils.underline2Camel(map.get("column_name")));
            map.put(column_comment,rs.getString("column_comment"));
            map.put(data_type,rs.getString("data_type"));
            if(excludeMap.get(map.get("column_name")) == null){
                resultMap.add(map);
            }
        }
        rs.close();
        ps.close();
        return resultMap;
    }

    /**
     * 生成页面
     */
    private static void generatePage() throws Exception {
        List<Map<String,String>> mapList = readColumnName(tableName,dataBase);
        StringBuilder str = new StringBuilder("<!DOCTYPE html>\n" +
                "<html xmlns:th=\"http://www.thymeleaf.org\">\n" +
                "<head>\n" +
                "\n" +
                "    <meta charset=\"UTF-8\"/>\n" +
                "    <title>"+name+"列表</title>\n" +
                "    <link rel=\"stylesheet\" type=\"text/css\" th:href=\"@{/easyui/themes/bootstrap/easyui.css}\"/>\n" +
                "    <link rel=\"stylesheet\" type=\"text/css\" th:href=\"@{/easyui/themes/icon.css}\"/>\n" +
                "    <link rel=\"stylesheet\" type=\"text/css\" th:href=\"@{/css/index.css}\"/>\n" +
                "    <script type=\"text/javascript\" th:src=\"@{/easyui/jquery.min.js}\"></script>\n" +
                "    <script type=\"text/javascript\" th:src=\"@{/easyui/jquery.easyui.min.js}\"></script>\n" +
                "    <script type=\"text/javascript\" th:src=\"@{/easyui/easyui-lang-zh_CN.js}\"></script>\n" +
                "    <script type=\"text/javascript\" th:src=\"@{/js/index.js}\"></script>\n" +
                "    <script th:inline=\"none\">\n" +
                "        var optwidth = 130;\n" +
                "        var datagridListId = \"#datagridListId\";\n" +
                "        $(function(){\n" +
                "            $('#addOrUpdDialog').window('close');\n" +
                "            $(datagridListId).datagrid({\n" +
                "                url:'"+smallFirst+"List',\n" +
                "                width:size.width-20,\n" +
                "                height:size.height - $('#searchDiv').height() - 20,\n" +
                "                columns:[[\n" +
                "                    {field:'opt',title:'操作',width:optwidth-30,align:'center',\n" +
                "                        formatter:function(value,rowData){\n" +
                "                            var str = \"<a href='javascript:void(0);' onclick='editInfo(\\\"\"+rowData.id+\"\\\")'>编辑</a> \";\n" +
                "                            str += \"<a href='javascript:void(0);' onclick='deleteInfo(\\\"\"+rowData.id+\"\\\")'>删除</a> \";\n" +
                "                            return str;\n" +
                "                        }\n" +
                "                    },\n");
     str.append("                    {field:\"id\",title:\"ID\",hidden:true},\n");
     int size = mapList.size();
     DecimalFormat df = new DecimalFormat("#0.00");
     String columenWidth = df.format((1-0.1)/(size));
     for(Map<String,String> map : mapList){
     str.append("                    {field:\""+map.get("camelName")+"\",title:\""+map.get("column_comment")+"\",width:fixWidthWithOptWidth("+columenWidth+")},\n");
     }
     str = new StringBuilder(removeLastComma(str.toString())).append("\n");
     str.append("                ]],\n" +
                "                pagination : true,\n" +
                "                rownumbers:true,\n" +
                "                toolbar: [{\n" +
                "                    iconCls: 'icon-add',\n" +
                "                    text: '添加',\n" +
                "                    handler: function(){\n" +
                "                        $('#dataId').val(\"\");\n");
     for(Map<String,String> map : mapList){
     str.append("                        $('#"+map.get("camelName")+"').textbox(\"setValue\", \"\");\n");
     }
     str.append("                        $('#addOrUpdDialog').panel({\n" +
                "                            title:\"添加\"\n" +
                "                        });\n" +
                "                        $('#addOrUpdDialog').window('open');\n" +
                "                    }\n" +
                "                }],\n" +
                "                onClickRow: function () {\n" +
                "                    $(datagridListId).datagrid('clearSelections');\n" +
                "                }\n" +
                "            });\n" +
                "        });\n" +
                "        /**\n" +
                "         * 查询\n" +
                "         */\n" +
                "        function search() {\n" +
                "            var queryParams = {\n");
     for(Map<String,String> map : mapList) {
         if(searchMap.get(map.get("camelName"))==null)continue;
     str.append("                \""+map.get("camelName")+"\":$('#search_"+map.get("camelName")+"').val(),\n");
     }
        str = new StringBuilder(removeLastComma(str.toString())).append("\n");
     str.append("            };\n" +
                "            $(datagridListId).datagrid('load',queryParams);\n" +
                "        }\n" +
                "        /**\n" +
                "         * 编辑\n" +
                "         */\n" +
                "        function editInfo(id) {\n" +
                "            var rows = $(datagridListId).datagrid('getRows');\n" +
                "            var curRow;\n" +
                "            $.each(rows,function(i,row){\n" +
                "                if(id == row.id){\n" +
                "                    curRow = row;\n" +
                "                    return false;\n" +
                "                }\n" +
                "            });\n" +
                "            $('#dataId').val(id);\n");
     for(Map<String,String> map : mapList) {
     str.append("            $('#"+map.get("camelName")+"').textbox(\"setValue\", curRow."+map.get("camelName")+");\n");
     }
     str.append("            $('#addOrUpdDialog').panel({\n" +
                "                title:\"编辑\"\n" +
                "            });\n" +
                "            $('#addOrUpdDialog').window('open');\n" +
                "        }\n" +
                "        /**\n" +
                "         * 删除\n" +
                "         */\n" +
                "        function deleteInfo(id) {\n" +
                "            $.messager.confirm(\"操作提示\", \"您确定要删除吗？\", function (data) {\n" +
                "                if(data) {\n" +
                "                    $.ajax({\n" +
                "                        url:'del"+className+"',\n" +
                "                        type:'POST',\n" +
                "                        data : {'id':id},\n" +
                "                        async : false,\n" +
                "                        success:function (data) {\n" +
                "                            var success = data.success;\n" +
                "                            if(success){\n" +
                "                                $(datagridListId).datagrid('reload');//刷新\n" +
                "                            }else{\n" +
                "                                alert(data.msg);\n" +
                "                            }\n" +
                "                        }\n" +
                "                    });\n" +
                "                }\n" +
                "            });\n" +
                "        }\n" +
                "\n" +
                "        /**\n" +
                "         * 添加或更新\n" +
                "         */\n" +
                "        function addOrUpd"+className+"() {\n" +
                "            $.messager.confirm(\"操作提示\", \"您确定提交？\", function (data) {\n" +
                "                if(data) {\n" +
                "                    var params = {\n" +
                "                        'id': $('#dataId').val(),\n");
     for(Map<String,String> map : mapList) {
     str.append("                        '"+map.get("camelName")+"': $('#"+map.get("camelName")+"').val(),\n");
     }
        str = new StringBuilder(removeLastComma(str.toString())).append("\n");
     str.append("                    };\n" +
                "                    $.ajax({\n" +
                "                        url:'addOrUpd"+className+"',\n" +
                "                        type:'POST',\n" +
                "                        data : params,\n" +
                "                        async : false,\n" +
                "                        success:function (data) {\n" +
                "                            var success = data.success;\n" +
                "                            if(success){\n" +
                "                                $('#addOrUpdDialog').window('close');\n" +
                "                                reset();//刷新\n" +
                "                            }else{\n" +
                "                                alert(data.msg);\n" +
                "                            }\n" +
                "                        }\n" +
                "                    });\n" +
                "                }\n" +
                "            });\n" +
                "        }\n" +
                "        /**\n" +
                "         * 重置\n" +
                "         */\n" +
                "        function reset() {\n");
     for(Map<String,String> map : mapList) {
         if(searchMap.get(map.get("camelName"))==null)continue;
     str.append("            $('#search_"+map.get("camelName")+"').textbox(\"setValue\", \"\");\n");
     }
     str.append("            search();\n" +
                "        }\n" +
                "    </script>\n" +
                "</head>\n" +
                "<body style=\"overflow: hidden\">\n" +
                "    <div id=\"searchDiv\" style=\"height: 50px;\">\n" +
                "        <table>\n" +
                "            <tr>\n");
     for(Map<String,String> map : mapList) {
         if(searchMap.get(map.get("camelName"))==null)continue;
     str.append("                <td style=\"text-align: right\">"+map.get("column_comment")+"&nbsp;</td>\n");
     str.append("                <td>\n");
     str.append("                    <input id=\"search_"+map.get("camelName")+"\" class=\"easyui-textbox\" style=\"width:180px;height:25px\">\n");
     str.append("                </td>\n");
     }
     str.append("                <td onclick=\"search()\">\n" +
                "                    <a href=\"#\" class=\"easyui-linkbutton\" data-options=\"iconCls:'icon-search'\">查询</a>\n" +
                "                </td>\n" +
                "                <td>\n" +
                "                    <a href=\"#\" onclick=\"reset();\" class=\"easyui-linkbutton\" data-options=\"iconCls:'icon-reload'\">重置</a>\n" +
                "                </td>\n" +
                "            </tr>\n" +
                "        </table>\n" +
                "    </div>\n" +
                "    <div id=\"listParentDiv\" style=\"overflow: hidden;width: 100%;height: 98%\">\n" +
                "        <table id=\"datagridListId\"></table>\n" +
                "    </div>\n" +
                "    <!-- 添加或更新对话框 -->\n" +
                "    <div id=\"addOrUpdDialog\" class=\"easyui-window\" title=\"Window Layout\"\n" +
                "         data-options=\"iconCls:'icon-save',collapsible: false, minimizable: false, maximizable: false, resizable: false,modal:true\"\n" +
                "         style=\"width:400px;height:300px;display: none;\">\n" +
                "        <input type=\"hidden\" id=\"dataId\"/>\n" +
                "        <div class=\"easyui-layout\" data-options=\"fit:true\">\n" +
                "            <div data-options=\"region:'center'\" style=\"width: 100%\">\n" +
                "                <form>\n" +
                "                    <table class=\"formTable\" style=\"width: 100%\">\n");
     for(Map<String,String> map : mapList) {
     str.append(
                "                        <tr>\n" +
                "                            <td style=\"width: 25%;text-align: right\">"+map.get("column_comment")+":</td>\n" +
                "                            <td style=\"width: 75%\">\n" +
                "                                <input id=\""+map.get("camelName")+"\" class=\"easyui-textbox\" style=\"width:80%;height:25px\">\n" +
                "                            </td>\n" +
                "                        </tr>\n"
     );
     }
     str.append("                    </table>\n" +
                "                </form>\n" +
                "            </div>\n" +
                "            <div data-options=\"region:'south',border:false\" style=\"text-align:right;padding:5px 0 0;\">\n" +
                "                <a class=\"easyui-linkbutton\" data-options=\"iconCls:'icon-ok'\" href=\"javascript:void(0)\" onclick=\"addOrUpd"+className+"()\" style=\"width:80px\">确定</a>\n" +
                "                <a class=\"easyui-linkbutton\" data-options=\"iconCls:'icon-cancel'\" href=\"javascript:void(0)\" onclick=\"javascript:$('#addOrUpdDialog').window('close');\" style=\"width:80px\">取消</a>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "</body>\n" +
                "</html>");
        File serviceFile = new File(GenerateCode.parentPath+File.separator+smallFirst+"Index.html");
        BufferedWriter writer = new BufferedWriter(new FileWriter(serviceFile));
        writer.write(str.toString());
        writer.flush();
        writer.close();
    }

    /**
     * 生成controller
     * @throws Exception
     */
    private static void generateController()throws Exception{
        File serviceFile = new File(GenerateCode.parentPath+File.separator+className+"Controller.java");
        BufferedWriter writer = new BufferedWriter(new FileWriter(serviceFile));
        writer.write("package com.productivity.web.controller;\n\n");
        writer.write("import com.alibaba.fastjson.JSON;\n");
        writer.write("import com.productivity.web.entity."+className+";\n");
        writer.write("import com.productivity.web.service."+className+"Service;\n");
        writer.write("import com.productivity.web.vo.DataGridReturn;\n");
        writer.write("import com.productivity.web.vo.ReturnVo;\n");
        writer.write("import org.slf4j.Logger;\n");
        writer.write("import org.slf4j.LoggerFactory;\n");
        writer.write("import org.springframework.beans.factory.annotation.Autowired;\n");
        writer.write("import org.springframework.stereotype.Controller;\n");
        writer.write("import org.springframework.web.bind.annotation.RequestMapping;\n");
        writer.write("import org.springframework.web.bind.annotation.ResponseBody;\n\n");

        writer.write("/**\n");
        writer.write(" * " + name + "Controller\n");
        writer.write(" * @author luozeqiang\n");
        writer.write(" * @date "+simpleDateFormat.format(new Date())+"\n");
        writer.write(" */\n");
        writer.write("@Controller\n");
        writer.write("@RequestMapping(value = \""+smallFirst+"\")\n");
        writer.write("public class "+className+"Controller extends BaseController{\n\n");
        writer.write("\tLogger logger = LoggerFactory.getLogger("+className+"Controller.class);\n\n");
        writer.write("\t@Autowired\n");
        writer.write("\tprivate "+className+"Service "+smallFirst+"Service;\n\n");

        writer.write("\t/**\n");
        writer.write("\t * 进入"+name+"列表页面\n");
        writer.write("\t * @return\n");
        writer.write("\t */\n");
        writer.write("\t@RequestMapping(\"to"+className+"Index\")\n");
        writer.write("\tpublic String to"+className+"Index(){\n");
        writer.write("\t\treturn \""+smallFirst+"/"+smallFirst+"Index\";\n");
        writer.write("\t}\n\n");

        writer.write("\t/**\n");
        writer.write("\t * 获取"+name+"列表数据\n");
        writer.write("\t * @return\n");
        writer.write("\t */\n");
        writer.write("\t@RequestMapping(\""+smallFirst+"List\")\n");
        writer.write("\t@ResponseBody\n");
        writer.write("\tpublic DataGridReturn "+smallFirst+"List("+className+" "+smallFirst+"){\n");
        writer.write("\t\treturn "+smallFirst+"Service.list"+className+"("+smallFirst+",getPagination());\n");
        writer.write("\t}\n\n");

        writer.write("\t/**\n");
        writer.write("\t * 删除"+name+"\n");
        writer.write("\t * @param id\n");
        writer.write("\t * @return\n");
        writer.write("\t */\n");
        writer.write("\t@RequestMapping(\"del"+className+"\")\n");
        writer.write("\t@ResponseBody\n");
        writer.write("\tpublic ReturnVo del"+className+"(Integer id){\n");
        writer.write("\t\tReturnVo returnVo = new ReturnVo();\n");
        writer.write("\t\ttry {\n");
        writer.write("\t\t\t"+smallFirst+"Service.del"+className+"(id);\n");
        writer.write("\t\t}catch (Exception e){\n");
        writer.write("\t\t\tlogger.error(\"del"+className+",id=\" + id,e);\n");
        writer.write("\t\t\treturnVo.setSuccess(false);\n");
        writer.write("\t\t\treturnVo.setMsg(\"删除失败：\" + e.getMessage());\n");
        writer.write("\t\t}\n");
        writer.write("\t\treturn returnVo;\n");
        writer.write("\t}\n\n");

        writer.write("\t/**\n");
        writer.write("\t * 获取"+name+"\n");
        writer.write("\t * @param id\n");
        writer.write("\t * @return\n");
        writer.write("\t */\n");
        writer.write("\t@RequestMapping(\"get"+className+"\")\n");
        writer.write("\t@ResponseBody\n");
        writer.write("\tpublic ReturnVo get"+className+"(Integer id){\n");
        writer.write("\t\tReturnVo returnVo = new ReturnVo();\n");
        writer.write("\t\treturnVo.setObj("+smallFirst+"Service.get"+className+"(id));\n");
        writer.write("\t\treturn returnVo;\n");
        writer.write("\t}\n\n");

        writer.write("\t/**\n");
        writer.write("\t * 添加或更新"+name+"\n");
        writer.write("\t * @param "+smallFirst+"\n");
        writer.write("\t * @return\n");
        writer.write("\t */\n");
        writer.write("\t@RequestMapping(\"addOrUpd"+className+"\")\n");
        writer.write("\t@ResponseBody\n");
        writer.write("\tpublic ReturnVo addOrUpd"+className+"("+className+" "+smallFirst+"){\n");
        writer.write("\t\tReturnVo returnVo = new ReturnVo();\n");
        writer.write("\t\ttry {\n");
        writer.write("\t\t\tif("+smallFirst+".getId() != null){\n");
        writer.write("\t\t\t\t"+smallFirst+"Service.update"+className+"("+smallFirst+");\n");
        writer.write("\t\t\t}else{\n");
        writer.write("\t\t\t\t"+smallFirst+"Service.add"+className+"("+smallFirst+");\n");
        writer.write("\t\t\t}\n");
        writer.write("\t\t}catch (Exception e){\n");
        writer.write("\t\t\tlogger.error(\"addOrUpd"+className+":[\" + JSON.toJSONString("+smallFirst+") + \"]\",e);\n");
        writer.write("\t\t\treturnVo.setSuccess(false);\n");
        writer.write("\t\t\treturnVo.setMsg(\"失败：\" + e.getMessage());\n");
        writer.write("\t\t}\n");
        writer.write("\t\treturn returnVo;\n");
        writer.write("\t}\n\n");

        writer.write("}");
        writer.flush();
        writer.close();
    }

    /**
     * 生成serviceImpl
     * @throws Exception
     */
    private static void generateServiceImpl() throws Exception{
        File serviceFile = new File(GenerateCode.parentPath+File.separator+className+"ServiceImpl.java");
        BufferedWriter writer = new BufferedWriter(new FileWriter(serviceFile));
        writer.write("package com.productivity.web.service.impl;\n\n");
        writer.write("import com.productivity.web.entity."+className+";\n");
        writer.write("import com.productivity.web.mapper."+className+"Mapper;\n");
        writer.write("import com.productivity.web.service."+className+"Service;\n");
        writer.write("import com.productivity.web.vo.DataGridReturn;\n");
        writer.write("import com.productivity.web.vo.Pagination;\n");
        writer.write("import org.springframework.beans.factory.annotation.Autowired;\n");
        writer.write("import org.springframework.stereotype.Service;\n");
        writer.write("import org.springframework.transaction.annotation.Transactional;\n\n");

        writer.write("@Service\n");
        writer.write("@Transactional(readOnly = true)\n");
        writer.write("public class "+className+"ServiceImpl implements "+className+"Service {\n\n");

        writer.write("\t@Autowired\n");
        writer.write("\tprivate "+className+"Mapper "+smallFirst+"Mapper;\n\n");

        writer.write("\t@Override\n");
        writer.write("\tpublic DataGridReturn<"+className+"> list"+className+"("+className+" "+smallFirst+", Pagination pagination){\n");
        writer.write("\t\tDataGridReturn<"+className+"> dataGridReturn = new DataGridReturn<>(pagination);\n");
        writer.write("\t\tdataGridReturn.setPageHelperList("+smallFirst+"Mapper.list"+className+"("+smallFirst+"));\n");
        writer.write("\t\treturn dataGridReturn;\n");
        writer.write("\t}\n\n");

        writer.write("\t@Override\n");
        writer.write("\t@Transactional(rollbackFor = Exception.class)\n");
        writer.write("\tpublic void del"+className+"(Integer id){\n");
        writer.write("\t\t"+smallFirst+"Mapper.deleteByPrimaryKey(id);\n");
        writer.write("\t}\n\n");

        writer.write("\t@Override\n");
        writer.write("\tpublic "+className+" get"+className+"(Integer id){\n");
        writer.write("\t\treturn "+smallFirst+"Mapper.selectByPrimaryKey(id);\n");
        writer.write("\t}\n\n");

        writer.write("\t@Override\n");
        writer.write("\t@Transactional(rollbackFor = Exception.class)\n");
        writer.write("\tpublic void add"+className+"("+className+" "+smallFirst+"){\n");
        writer.write("\t\t"+smallFirst+"Mapper.insert("+smallFirst+");\n");
        writer.write("\t}\n\n");

        writer.write("\t@Override\n");
        writer.write("\t@Transactional(rollbackFor = Exception.class)\n");
        writer.write("\tpublic void update"+className+"("+className+" "+smallFirst+"){\n");
        writer.write("\t\t"+smallFirst+"Mapper.updateByPrimaryKeySelective("+smallFirst+");\n");
        writer.write("\t}\n\n");

        writer.write("}");
        writer.flush();
        writer.close();
    }

    /**
     * 生成service
     */
    private static void generateService() throws Exception{
        File serviceFile = new File(GenerateCode.parentPath+File.separator+className+"Service.java");
        BufferedWriter writer = new BufferedWriter(new FileWriter(serviceFile));
        writer.write("package com.productivity.web.service;\n\n");
        writer.write("import com.productivity.web.entity."+ className +";\n");
        writer.write("import com.productivity.web.vo.DataGridReturn;\n");
        writer.write("import com.productivity.web.vo.Pagination;\n\n");
        writer.write("/**\n");
        writer.write(" * "+name+"接口\n");
        writer.write(" * @author luozeqiang\n");
        writer.write(" * @date "+simpleDateFormat.format(new Date())+"\n");
        writer.write(" */\n");
        writer.write("public interface "+ className +"Service {\n");
        writer.newLine();
        writer.write("\t/**\n");
        writer.write("\t * 查询"+name+"列表\n");
        writer.write("\t * @param "+smallFirst+"\n");
        writer.write("\t * @param pagination\n");
        writer.write("\t * @return\n");
        writer.write("\t */\n");
        writer.write("\tDataGridReturn<"+className+"> list"+className+"("+className+" "+smallFirst+", Pagination pagination);\n");
        writer.newLine();

        writer.write("\t/**\n");
        writer.write("\t * 删除"+name+"\n");
        writer.write("\t * @param id\n");
        writer.write("\t */\n");
        writer.write("\tvoid del"+className+"(Integer id);\n");
        writer.newLine();

        writer.write("\t/**\n");
        writer.write("\t * 获取"+name+"\n");
        writer.write("\t * @param id\n");
        writer.write("\t */\n");
        writer.write("\t"+className+" get"+className+"(Integer id);\n");
        writer.newLine();

        writer.write("\t/**\n");
        writer.write("\t * 添加"+name+"\n");
        writer.write("\t * @param "+smallFirst+"\n");
        writer.write("\t */\n");
        writer.write("\tvoid add"+className+"("+className+" "+smallFirst+");\n");
        writer.newLine();

        writer.write("\t/**\n");
        writer.write("\t * 更新"+name+"\n");
        writer.write("\t * @param "+smallFirst+"\n");
        writer.write("\t */\n");
        writer.write("\tvoid update"+className+"("+className+" "+smallFirst+");\n");
        writer.newLine();
        writer.write("}");
        writer.flush();
        writer.close();
    }


    /**
     * 把一个字符串的第一个字母大写、效率是最高的
     * @param fildeName
     * @return
     * @throws Exception
     */
    public static String getMethodName(String fildeName){
        byte[] items = fildeName.getBytes();
        items[0] = (byte) ((char) items[0] - 'A' + 'a');
        return new String(items);
    }

    public static String removeLastComma(String str){
        int index = str.lastIndexOf(",");
        return str.substring(0,index) + str.substring(index+1);
    }
}
