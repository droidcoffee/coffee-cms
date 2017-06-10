/**
 * 删除指定sid的记录，并跳到到url上
 *   注意：该from需要有一个id为tableForm的form 还需要有两个hidder名字分别为 action,sid
 * 	 <form id="tableForm">
 * 		<input type="hidden" name="action"/>
 *		<input type="hidden" name="sid"/>
 *    </form>
 * @param url
 * @param sid
 */
function del(url, sid) {
	if (!confirm('您确定要删除吗?')) {
		return;
	}
	var frm = $("#tableForm");
	frm.attr("action", url);
	$("[name='sid']").val(sid);
	$("[name='action']").val('delete');

	frm.submit();
}
var i = -1;
// 选中‘反选 复选框
function doChecked(nodeName) {
	i = i * -1;
	if (i > 0) {
		$("[name=\"" + nodeName + "\"]").attr("checked", 'true');// 全选
	} else {
		$("[name=\"" + nodeName + "\"]").removeAttr("checked");// 取消全选
	}
}
// 批量删除
function delBatch(url) {
	var sid = "";
	var checked = $("[name=\"cid\"]:checked");
	if (checked.length <= 0) {
		alert("请选择您要操作的数据");
		return;
	}
	if (!confirm("您确定删除吗？")) {
		return;
	}
	checked.each(function() {
		sid += $(this).val() + ",";
	});
	sid = sid.substring(0, sid.length - 1);

	var frm = $("#tableForm");
	frm.attr("action", url);
	$("[name='sid']").val(sid);
	$("[name='action']").val('delete');
	frm.submit();
}
/**
 * 设置表单中所有的input
 */
function setInputReadOnly()
{
	var ipt =  document.getElementsByTagName("input");
	for(var i=0;i<ipt.length;i++)
	{
		if(ipt[i].type=="text" || ipt[i].type=="password")
		{
			ipt[i].readOnly = "true";
		}
	}
}