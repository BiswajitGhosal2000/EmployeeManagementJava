<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<option value="">Select a State</option>
<c:forEach items="${StateList}" var="state">
    <option value='${state.getStateCode()}' <c:if test="${state.getStateCode() == User.getStateCode()}" > selected </c:if>> 
        ${state.getStateName()}
    </option>
</c:forEach>