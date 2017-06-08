<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="script/jquery-2.1.1.min.js"></script>
<script type="text/javascript">
$(function(){
	//1. 点击 delete 时, 弹出 确定是要删除 xx 的信息吗 ? 若确定, 执行删除, 若不确定, 则取消
	$(".delete").click(function(){
		var lastName=$(this).next(":hidden").val();
	    var flag =confirm("确定要删除"+lastName+"吗?");
	    if(flag){
	    	var url=this.href;
	    	var $tr=$(this).parent().parent();
	    	var arg ={"time":new Date()};
	    	$.post(url,arg,function(data){
	    		if(data=="1"){
	    			alert("删除成功！");
	    			$tr.remove();
	    		}else{
	    			alert("删除失败!");
	    		}
	    		
	    		
	    	});
	    }
		//取消超链接的默认行为
		return false;
	});		
})


</script>
</head>
<body>
	<h4>list all</h4>
	<s:if test="#request.employees==null||#request.employees.size()==0">
     没有员工信息！
  </s:if>

	<s:else>
		<table border="1" cellpadding="1" cellspacing="0">
			<tr>
				<td>ID</td>
				<td>Lastname</td>
				<td>Email</td>
				<td>Birth</td>
				<td>CreatTime</td>
				<td>Department</td>
				<td>Delete</td>
				<td>Edit</td>
			</tr>
			<s:iterator value="#request.employees">
			<tr>
			<td>${id}</td>
			<td>${lastName}</td>
			<td>${email}</td>
			<td><s:date name="birth" format="yyyy-MM-dd"/></td>
			<td><s:date name="createTime" format="yyyy-MM-dd hh:mm:ss"/></td>
			<td>${department.departmentName}</td>
            <td><a href="emp-delete?id=${id}" class="delete">Delete</a>
               <input type="hidden" name="lastName" value="${lastName}"/>
            </td>
         <td><a href="emp-input?id=${id}">Edit</a></td>
   </tr>

</s:iterator>
		</table>



	</s:else>
</body>
</html>