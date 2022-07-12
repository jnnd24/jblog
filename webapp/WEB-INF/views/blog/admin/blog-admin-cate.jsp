<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JBlog</title>
<!-- css -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">

<!-- js -->
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.12.4.js"></script>

</head>

<body>
	<div id="wrap">
		
		<!-- 개인블로그 해더 -->
		<c:import url="/WEB-INF/views/includes/blog-header.jsp"></c:import>


		<div id="content">
			<ul id="admin-menu" class="clearfix">
				<li class="tabbtn selected"><a href="${pageContext.request.contextPath}/${authUser.id}/admin/basic">기본설정</a></li>
				<li class="tabbtn"><a href="${pageContext.request.contextPath}/${authUser.id}/admin/category">카테고리</a></li>
				<li class="tabbtn"><a href="">글작성</a></li>
			</ul>
			<!-- //admin-menu -->
			
			<div id="admin-content">
			
				<table id="admin-cate-list">
					<colgroup>
						<col style="width: 50px;">
						<col style="width: 200px;">
						<col style="width: 100px;">
						<col>
						<col style="width: 50px;">
					</colgroup>
		      		<thead>
			      		<tr>
			      			<th>번호</th>
			      			<th>카테고리명</th>
			      			<th>포스트 수</th>
			      			<th>설명</th>
			      			<th>삭제</th>      			
			      		</tr>
		      		</thead>
		      		
		      		<tbody id="cateList">
		      			<!-- 리스트 영역 -->
						<%-- <tr>
							<td>2</td>
							<td>오라클</td>
							<td>5</td>
							<td>오라클 설치와 sql문</td>
						    <td class='text-center'>
						    	<img class="btnCateDel" src="${pageContext.request.contextPath}/assets/images/delete.jpg">
						    </td>
						</tr> --%>
						<!-- 리스트 영역 -->
					</tbody>
					
				</table>
      	
		      	<table id="admin-cate-add" >
		      		<colgroup>
						<col style="width: 100px;">
						<col style="">
					</colgroup>
		      		<tr>
		      			<td class="t">카테고리명</td>
		      			<td><input type="text" name="name" value=""></td>
		      		</tr>
		      		<tr>
		      			<td class="t">설명</td>
		      			<td><input type="text" name="desc"></td>
		      		</tr>
		      	</table> 
			
				<div id="btnArea">
		      		<button id="btnAddCate" class="btn_l" type="submit" >카테고리추가</button>
		      	</div>
			
			</div>
			<!-- //admin-content -->
		</div>	
		<!-- //content -->
		
		
		<!-- 개인블로그 푸터 -->
		<c:import url="/WEB-INF/views/includes/blog-footer.jsp"></c:import>
		
	
	
	</div>
	<!-- //wrap -->
</body>
<script type="text/javascript">


	//-------------------------------리스트가져오기-------------------------------------
	//리스트 요청 -1
	$(document).ready(function(){
		console.log("리스트요청 진입")
		
		getList();
		
	})
	
	//리스트요청 함수
	function getList(){
		
	$.ajax({
			
			url : "${pageContext.request.contextPath }/admin/categoryList",		
			type : "post",
			//contentType : "application/json",
			//data : {name: "홍길동"},

			dataType : "json",
			success : function(categoryList){
				/*성공시 처리해야될 코드 작성*/
				
				for(var i = 0; i<categoryList.length; i++){
					render(categoryList[i]);
				}
				
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});

	}
	//리스트호출 html문 -2
	function render(categoryVo){
		//html문 작성
		var str = '';
		str += '<tr>';
		str += '	<td>'+categoryVo.cateNo+'</td>';
		str += '	<td>'+categoryVo.cateName+'</td>';
		str += '	<td>7</td>';
		str += '	<td>'+categoryVo.description+'</td>';
		str += '   <td class="text-center">';
		str += '    	<img class="btnCateDel" src="${pageContext.request.contextPath}/assets/images/delete.jpg" data-no="'+categoryVo.cateNo+'">';
		str += '    </td>';
		str += '</tr>';
		

		
		$("#cateList").append(str);
		
		/* if(pos == "ex"){ // 기존꺼 로딩
			$("#cateList").append(str);
		}else{
			$("#cateLIst").prepend(str); 
		} */
	}
	//-------------------------------리스트가져오기-------------------------------------
	
	
	//-------------------------------카테고리 추가-------------------------------------
	//카테고리 추가
	$("#btnAddCate").on("click", function(){
		console.log("카테고리추가");
		
		//vo만들기
		var cateName = $("[name='name']").val();
		var desc = $("[name='desc']").val();
		
		var categoryVo = {
			cateName: cateName,
			description: desc
		};
		
		$.ajax({
			
			url : "${pageContext.request.contextPath }/admin/categoryAdd",		
			type : "post",
			//contentType : "application/json",
			data : categoryVo,

			//dataType : "json",
			success : function(result){
				/*성공시 처리해야될 코드 작성*/
				
				//입력폼초기화
				$("[name='name']").val("");
				$("[name='desc']").val("");
				
				//카테고리 목록 초기화
				$("#cateList").empty();
				
				//목록 재로딩
				getList();
				
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
		
	});
	//-------------------------------카테고리 추가-------------------------------------
	//var cateNo = data-no
	//console.log(cateNo);
	
	$("#cateList").on("click",".btnCateDel",function(){
		console.log("삭제버튼");
		
	});
	
	
	
	//-------------------------------카테고리 삭제-------------------------------------
	
	
	
</script>
</html>