<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
   
   
   검색어 예시 : del07@naver.com<br>

	<c:set var="lists" value="${lists}"/>
	<c:if test="${!empty lists}">
	<table border="1">
		<tr>
			<th>이메일</th>
			<th>이름</th>
			<th>전화번호</th>
			<th>권한</th>
		</tr>
		<c:forEach varStatus="vs" items="${lists}" var="dto">
			<tr>
				<td><a title="${dto.email}" href="./deliveryDetail.do?email=${dto.email}">${dto.email}</a></td>
				<td>${dto.name}</td>
				<td>${dto.phone_num}</td>
				<td>${dto.auth}</td>
			</tr>
		</c:forEach>
	</table>
	</c:if>
	
	<c:set var="TempList" value="${TempList}"/>
	<c:if test="${TempList != null}">
	<table border="1">
		<tr>
			<th>이메일</th>
			<th>이름</th>
			<th>전화번호</th>
			<th>권한</th>
		</tr>
		<c:forEach varStatus="vs" items="${TempList}" var="dto">
			<tr>
				<td><a title="${dto.email}" href="./deliveryDetail.do?email=${dto.email}">${dto.email}</a></td>
				<td>${dto.name}</td>
				<td>${dto.phone_num}</td>
				<td>${dto.auth}</td>
			</tr>
		</c:forEach>
	</table>
	</c:if>
	
	<c:set var="list" value="${list}"/>
	<c:if test="${empty list}">
	<div id="noResult"><p>결과없음<p></div>
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

