<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:forEach items="${lists}" var="list">
	<tr onclick="chkAuth('${mem.email}', '${vo.email}', '${mem.auth}', '${vo.refer}')">
		<td>${list.seq}</td>
		<td>${list.regdate}</td>
		<td>${list.title}</td>
		<td>${list.email}</td>
	</tr>
</c:forEach>