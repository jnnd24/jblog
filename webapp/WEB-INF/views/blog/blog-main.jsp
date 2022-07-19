<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> 

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JBlog</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.12.4.js"></script>
</head>

<body>
	<div id="wrap">

		<!-- 개인블로그 해더 -->
		<c:import url="/WEB-INF/views/includes/blog-header.jsp"></c:import>
		
		<div id="content" class="clearfix">
			<div id="profilecate_area">
				<div id="profile">
					
					<!-- 기본이미지 -->
					<img id="proImg" src="${pageContext.request.contextPath}/assets/images/spring-logo.jpg">
					
					<!-- 사용자업로드 이미지 -->
					<%-- <img id="proImg" src=""> --%>
					
					<div id="nick">${authUser.userName }(${authBlog.id })님</div>
					
					
				</div>
				<div id="cate">
					<div class="text-left">
						<strong>카테고리</strong>
					</div>
					<ul id="cateList" class="text-left">
						<c:forEach items="${cateList }" var="cateVo">
							<li id="cate${cateVo.cateNo }">
								<a href="?cateNo=${cateVo.cateNo }">
									${cateVo.cateName }
								</a>
							</li>
						</c:forEach>
					</ul>
				</div>
			</div>
			<!-- profilecate_area -->
			
			<div id="post_area">
				
				<div id="postBox" class="clearfix">
					<!-- 최초접속 시 최신글노출 -->
					<c:if test="${param.postNo == null && param.cateNo == null}">
						<div id="postTitle" class="text-left"><strong>${getRecentPost.postTitle }</strong></div>
						<div id="postDate" class="text-left"><strong>${getRecentPost.regDate }</strong></div>
					</c:if>
					<!-- //최초접속 시 최신글노출 -->
					<!-- 게시물선택 안된 경우 -->
					<c:if test="${param.postNo == null}">
						<div id="postTitle" class="text-left"><strong>${mainPost.postTitle }</strong></div>
						<div id="postDate" class="text-left"><strong>${mainPost.regDate }</strong></div>
					</c:if>
					<!-- //게시물선택 안된 경우 -->
					<!-- 게시물선택 된 경우 -->
					<c:if test="${param.postNo != null}">
						<div id="postTitle" class="text-left"><strong>${getPost.postTitle }</strong></div>
						<div id="postDate" class="text-left"><strong>${getPost.regDate }</strong></div>
					</c:if>
					<!-- //게시물선택 된 경우 -->
						<div id="postNick">${authUser.userName }(${authUser.id })님</div>
				</div>
				<!-- //postBox -->
			
				<div id="post">
					<!-- 최초접속 시 최신글노출 -->
					<c:if test="${param.postNo == null && param.cateNo == null}">
						${getRecentPost.postContent }
					</c:if>
					<!-- //최초접속 시 최신글노출 -->
					<!-- 게시물선택 안된 경우 -->
					<c:if test="${param.postNo == null}">
						${mainPost.postContent }
					</c:if>
					<!-- //게시물선택 안된 경우 -->
					<!-- 게시물선택 된 경우 -->
					<c:if test="${param.postNo != null}">
						${getPost.postContent }
					</c:if>
					<!-- //게시물선택 된 경우 -->
				</div>
				<!-- //post -->
				
				<!-- 글이 없는 경우 -->
				<!-- 
				<div id="postBox" class="clearfix">
							<div id="postTitle" class="text-left"><strong>등록된 글이 없습니다.</strong></div>
							<div id="postDate" class="text-left"><strong></strong></div>
							<div id="postNick"></div>
				</div>
			    
				<div id="post" >
				</div>
				-->
				
				<div id="list">
					<div id="listTitle" class="text-left"><strong>카테고리의 글</strong></div>
					<table>
						<colgroup>
							<col style="">
							<col style="width: 20%;">
						</colgroup>
						
						<!-- 카테고리의 글 노출영역 -->
						<c:forEach items="${postListCate }" var="postListCate">
							<tr>
								<td class="text-left"><a href="?cateNo=${postListCate.cateNo }&postNo=${postListCate.postNo }">${postListCate.postTitle }</a></td>
								<td class="text-right">${postListCate.regDate }</td>
							</tr>
						</c:forEach>
						<!-- 카테고리의 글 노출영역 -->
						
					</table>
				</div>
				<!-- //list -->
			</div>
			<!-- //post_area -->
			
			
			
		</div>	
		<!-- //content -->
		<div class=></div>
		<c:import url="/WEB-INF/views/includes/blog-footer.jsp"></c:import>
		
	
	
	</div>
	<!-- //wrap -->
</body>
</html>