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
	  $(":input[name=lastName]").change(function(){
		  var val=$(this).val();
		  val =$.trim(val);
		  
		  var $this=$(this);
		  if(val!=""){
			  $this.nextAll("font").remove();
			  var url="emp-validateLastName";
			  var arg={"lastName":val};
			  $.post(url,arg,function(data){
				  if(data=="1"){
					 $this.after("<font color='green'>可以注册</font>");
				  }else if(data=="0"){
					  $this.after("<font color='red'>用户名已经存在</font>");
				  }else{
					  alert("服务器异常");
				  }
			  });
		  }else{
			  alert("请输入用户名");
			  
		  }
		  
		  
		  
	  });
	  
	  
  });



</script>
</head>
<body>
	<h5>add new emploee</h5>
	<s:form action="emp-add" method="post">
	<s:if test="id!=null">
	   <s:textfield name="lastName" label="lastName" disabled="true"></s:textfield>
	</s:if>
	
     <s:else>
     
      <s:textfield name="lastName" label="lastName"></s:textfield>
     </s:else>
		
		<s:hidden name="id"></s:hidden>
		<s:textfield name="email" label="Email"></s:textfield>
		<s:textfield name="birth" label="Birth"></s:textfield>
		<s:select list="#request.departments" listKey="id" listValue="departmentName" label="Department" name="department.id">
		</s:select>
       <s:submit></s:submit>

	</s:form>
</body>
</html>