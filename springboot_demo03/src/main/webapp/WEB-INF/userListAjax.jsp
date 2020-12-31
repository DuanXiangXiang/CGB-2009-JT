<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>您好Springboot</title>
	<script type="text/javascript" src="../js/jquery-3.4.1.min.js"></script>
	<script type="text/javascript">
		$(function () {
			/*$.get("/userAjax",function (data) {
				/!*for (let i = 0;i< data.length;i++){
					let user = data[i]
					console.log(user)
				}

				for (let index in data){
					let user = data[index]
					console.log(user)
				}*!/
				// console.log(data)
				let tr = ''
				for (let user of data){
					console.log(user)
					let id = user.id
					let name = user.name
					let age = user.age
					let sex = user.sex
					tr += "<tr><td>"+id+"</td><td>"+name+"</td><td>"+age+"</td><td>"+sex+"</td></tr>"
				}
				$("#tab1").append(tr)
			})*/
			$.ajax({
				type : "get",
				url : "/userAjax",
				async: true,
				success : function (data) {
				let tr = ''
				for (let user of data){
					console.log(user)
					let id = user.id
					let name = user.name
					let age = user.age
					let sex = user.sex
					tr += "<tr><td>"+id+"</td><td>"+name+"</td><td>"+age+"</td><td>"+sex+"</td></tr>"
				}
				$("#tab1").append(tr)
				},
				error: function (data) {
					alert("当前服务器异常！")
				}
			})
		})
	</script>
</head>
<body>
	<table border="1px" width="65%" align="center" id="tab1">
		<tr>
			<td colspan="6" align="center"><h3>学生信息</h3></td>
		</tr>
		<tr>
			<th>编号</th>
			<th>姓名</th>
			<th>年龄</th>
			<th>性别</th>
			<th></th>
		</tr>
	</table>
</body>
</html>