<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<option value=""> Select a district</option>
<c:forEach items="${DistList}" var="district">
    <option value='${district.getDistrictCode()}' <c:if test="${district.getDistrictCode() == User.getDistrictCode()}" > selected </c:if>> 
        ${district.getDistrictName()}
    </option>
</c:forEach>