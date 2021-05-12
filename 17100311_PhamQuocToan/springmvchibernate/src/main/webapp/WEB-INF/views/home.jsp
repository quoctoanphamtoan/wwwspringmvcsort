<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>

<!doctype html>
<html lang="en">
<head>
<title>Trang chủ</title>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>
<body>
	<div class="container">
		<security:authorize access="isAuthenticated()">
			<security:authentication property="principal" var="user" />
			<a href="dangxuat" class="btn btn-success" role="button">Đăng
				xuất</a>
		</security:authorize>
		<h1>List Person</h1>
		<div class="row">
			<div class="col-9">
				<table class="table">
					<thead>
						<tr>
							<th>stt</th>
							<c:set var="idtemp" scope="session" value="${idtemp}" />


							<th><c:choose>

									<c:when test="${idtemp == 1}">
										<a href="2">Last Name</a>
									</c:when>

									<c:when test="${idtemp == 2}">
										<a href="1">Last Name</a>
									</c:when>

									<c:otherwise>
										<a href="1">First Name</a>
									</c:otherwise>
								</c:choose></th>
							<th><c:choose>

									<c:when test="${idtemp == 3}">
										<a href="4">First Name</a>
									</c:when>

									<c:when test="${idtemp == 4}">
										<a href="3">First Name</a>
									</c:when>

									<c:otherwise>
										<a href="3">First Name</a>
									</c:otherwise>
								</c:choose></th>


							<th><c:choose>

									<c:when test="${idtemp == 5}">
										<a href="6">Monney</a>
									</c:when>

									<c:when test="${idtemp == 6}">
										<a href="5">Monney</a>
									</c:when>

									<c:otherwise>
										<a href="6">Monney</a>
									</c:otherwise>
								</c:choose></th>
							<th>Action</th>
						</tr>
					</thead>
					<tbody>

						<c:forEach items="${listperson }" var="item" varStatus="lop">
							<tr>
								<td>${lop.index+1}</td>
								<td>${item.getLastName()}</td>
								<td>${item.getFirstName()}</td>
								<td>${item.getMoney()}</td>
								<td><a href="card/${item.getId()}" class="btn btn-success"
									role="button">Card</a> <a href="editPerson/${item.getId()}"
									class="btn btn-success" role="button">Edit</a> <a
									href="remove/${item.getId()}" class="btn btn-danger"
									role="button">Delete</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<div class="col-3">

				<a href="addperson" class="btn btn-info" role="button">Add
					person</a>
			</div>
		</div>
	</div>

	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
		integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
		integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
		crossorigin="anonymous"></script>
</body>
</html>