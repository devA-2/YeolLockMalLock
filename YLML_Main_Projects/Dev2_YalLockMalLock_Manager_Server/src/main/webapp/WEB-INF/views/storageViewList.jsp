<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
   

	<c:set var="lists" value="${lists}"/>
	<c:if test="${!empty lists}">
	<table border="1" class="table table-hover">
		<tr id="th">
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
			<td><a title="${dto.storageId}" href="./storageDetail.do?storageId=${dto.storageId}">${dto.storageId}</a></td>
			<td>${dto.storageName}</td>
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
	
	<c:set var="subwaylists" value="${subwaylists}"/>
	<c:if test="${!empty subwaylists}">
	<table border="1" class="table table-hover">
		<tr id="th">
			<th>보관함 ID</th>
			<th>보관함 이름</th>
			<th>주 소</th>
			<th>지하철역</th>
			<th>좌표 LAT</th>
			<th>좌표 LNG</th>
			<th>상 세</th>
			<th>담당자</th>
		</tr>
		<c:forEach varStatus="vs" items="${subwaylists}" var="dto">
		<tr>
			<td><a title="${dto.storageId}" href="./storageDetail.do?storageId=${dto.storageId}">${dto.storageId}</a></td>
			<td>${dto.storageName}</td>
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
	
	<c:set var="subwaylists" value="${subwaylists}"/>
	<c:if test="${empty subwaylists}">
	<div id="noResult"></div>
	</c:if>
	
	<c:set var="list" value="${list}"/>
	<c:if test="${list == null}">
	<div>
		<input id="noResult" type="hidden" value="검색결과가 없습니다">
	</div>
	</c:if>
	
	<c:if test="${!empty list}">
	<table border="1" class="table table-hover">
		<tr id="th">
			<th>보관함 ID</th>
			<th>보관함 이름</th>
			<th>주 소</th>
			<th>지하철역</th>
			<th>좌표 LAT</th>
			<th>좌표 LNG</th>
			<th>상 세</th>
			<th>담당자</th>
		</tr>
		<tr>
			<td><a title="${list.storageId}" href="./storageDetail.do?storageId=${list.storageId}">${list.storageId}</a></td>
			<td>${list.storageName}</td>
			<td>${list.address}</td>
			<td>${list.subway}</td>
			<td>${list.lat}</td>
			<td>${list.lng}</td>
			<td>${list.detail}</td>
			<td>${list.manager}</td>
		</tr>
	</table>
	</c:if>

