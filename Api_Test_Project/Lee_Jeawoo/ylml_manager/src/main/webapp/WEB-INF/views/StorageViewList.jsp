<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
   
   


	<c:set var="lists" value="${lists}"/>
	<c:if test="${!empty lists}">
	<table border="1">
		<tr>
			<th>보관함 ID</th>
			<th>보관함 이름</th>
			<th>주 소</th>
			<th>지하철역</th>
			<th>좌표 LAT</th>
			<th>좌표 LNG</th>
			<th>상 세</th>
			<th>담당자</th>
		</tr>
		<c:forEach varStatus="vs" items="${lists}" var="dto">
			<tr>
				<td><a title="${dto.storage_id}" href="./storageDetail.do?storage_id=${dto.storage_id}">${dto.storage_id}</a></td>
				<td>${dto.storage_name}</td>
				<td>${dto.address}</td>
				<td>${dto.subway}</td>
				<td>${dto.lat}</td>
				<td>${dto.lng}</td>
				<td>${dto.detail}</td>
				<td>${dto.manager}</td>
			</tr>
		</c:forEach>
	</table>
	</c:if>
	
	
	
	<c:set var="list" value="${list}"/>
	<c:if test="${list == null}">
	<div id="noResult"></div>
	</c:if>
	
	<c:if test="${!empty list}">
	<table border="1">
		<tr>
			<th>이메일</th>
			<th>이름</th>
			<th>전화번호</th>
			<th>권한</th>
		</tr>
			<tr>
				<td><a title="${list.email}" href="./deliveryDetail.do?email=${list.email}">${list.email}</a></td>
				<td>${list.name}</td>
				<td>${list.phone_num}</td>
				<td>${list.auth}</td>
			</tr>
	</table>
	</c:if>

