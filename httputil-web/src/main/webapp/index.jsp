<%@ page contentType="text/html;charset=utf-8" pageEncoding="UTF-8"%>
<%@ include file="context.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Complex Layout - jQuery EasyUI Demo</title>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/jquery-easyui-1.3.5/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/jquery-easyui-1.3.5/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/jquery-easyui-1.3.5/demo.css">
    <script type="text/javascript" src="<%=basePath%>/jquery-easyui-1.3.5/jquery.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>/jquery-easyui-1.3.5/jquery.easyui.min.js"></script>
    <script type="text/javascript">
        $.getJSON( "<%=basePath%>/index/menu.json", function( data ) {
        	 var items = [];
        	  $.each( data.data, function(index, item) {
        		  $('#id_menu_list').accordion('add',{
                      title:item.description,
                      content:item.uri
                  });
        	  });
        	 
        
        });
    </script>
   
</head>
<body>
    <h2>主页面</h2><div id="result"></div>
    <div class="demo-info">
        <div class="demo-tip icon-tip"></div>
        <div>提示信息：……</div>
    </div>
    <div style="margin:10px 0;"></div>
    <div class="easyui-layout" style="width:100%;height:1024px;">
        <!-- <div data-options="region:'north'" style="height:50px"></div> -->
       <!--  <div data-options="region:'south',split:true" style="height:50px;"></div> -->
        <div data-options="region:'east',split:true" title="East" style="width:180px;">
            <ul class="easyui-tree" data-options="url:'tree_data1.json',method:'get',animate:true,dnd:true"></ul>
        </div>
        <div data-options="region:'west',split:true" title="服务列表" style="width:200px;">
            <div id="id_menu_list" class="easyui-accordion" data-options="fit:true,border:false">
                <!-- 这里加载左边菜单列表
                <div title="ACloudDB服务列表" style="padding:10px;">
                    content1
                </div>
                 -->
            </div>
        </div>
        <div data-options="region:'center',title:'Main Title',iconCls:'icon-ok'">
            <div class="easyui-tabs" data-options="fit:true,border:false,plain:true">
                <div title="About" data-options="href:'_content.html'" style="padding:10px"></div>
                <div title="DataGrid" style="padding:5px">
                    <table class="easyui-datagrid"
                            data-options="url:'datagrid_data1.json',method:'get',singleSelect:true,fit:true,fitColumns:true">
                        <thead>
                            <tr>
                                <th data-options="field:'itemid'" width="80">Item ID</th>
                                <th data-options="field:'productid'" width="100">Product ID</th>
                                <th data-options="field:'listprice',align:'right'" width="80">List Price</th>
                                <th data-options="field:'unitcost',align:'right'" width="80">Unit Cost</th>
                                <th data-options="field:'attr1'" width="150">Attribute</th>
                                <th data-options="field:'status',align:'center'" width="50">Status</th>
                            </tr>
                        </thead>
                    </table>
                </div>
            </div>
        </div>
    </div>
</body>
</html>