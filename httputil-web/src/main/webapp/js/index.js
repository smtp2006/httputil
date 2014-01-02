function addTab(id, config) {
	$('#' + id).tabs('add', config);
}

$("body").ready(function() {
//	$.getJSON(getPath() + "/index/menu.json", function(data) {
//		// 迭代data,构造accordion菜单
//		$.each(data.data, function(index, item) {
//			var items = [];
//			var id = "id_menu_list_" + index;
//			items.push("<ul id=\"" + id + "\" class=\"easyui-tree\" >");
//			// items.push("<ul id=\"" + id + "\" >");
//			// 迭代data.list,构造tree菜单
//			$.each(item.list, function(i, req) {
//				items.push("<li>");
//				items.push("<span>");
//				items.push(req.description);
//				items.push("</span>");
//				items.push("</li>");
//			});
//			items.push("</ul>");
//			// 添加当前节点到accordion菜单
//			$('#id_menu_list').accordion('add', {
//				title : item.description,
//				content : items.join("")
//			});
//
//		});
//
//	});
	$.getJSON(getPath() + "/index/menu.json", function(data) {
		var menu_container = $('#id_menu_list');
		// 迭代data,构造accordion菜单
		$.each(data.data, function(index, item) {
			var id_ul = "id_menu_list_" + index;
			var div = $('<div title="' + item.description + '" ></div>');
			var ul = $('<ul id="' + id_ul + '"></ul>');
			// 迭代data.list,构造tree菜单
			$.each(item.list, function(i, req) {
				var li = $('<li></li>');
				$('<span>' + req.description + '</span>').appendTo($(li));
				$(li).appendTo($(ul));
			});
			// 添加当前节点到accordion菜单
			$(ul).appendTo($(div));
			$(div).appendTo(menu_container);// /div/ul/li/span
			$('#' + id_ul).tree({
				onClick:function(node) {
					alert(node.text);
				}
			});
		});
		$(menu_container).accordion({});
	});
});
