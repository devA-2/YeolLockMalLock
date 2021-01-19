<%@page import="com.min.edu.dto.MemberDto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>

<tr>
	<th>제목</th>
	<th>작성일</th>
	<th>작성자</th>
	<th>내용</th>
</tr>
<tr>
	<td>${dto.title}</td>
	<td>${dto.regdate}</td>
	<td>${dto.email}</td>
	<td>${dto.content}</td>
</tr>