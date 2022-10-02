<%@include file="taglibs.jsp"%>
<c:if test="${!empty sessionScope.isLogged }">
    <form class="border-2 mt-2" action="../twot" method="post">
        <textarea class="resize-none border-none" name="text" placeholder="Quoi de neuf ?" required></textarea><br>
        <input type="hidden" name="id" value=<c:out value="${userid}"></c:out> />
        <button type="submit" class="rounded-lg bg-gray-200">Twoter</button>
    </form>
</c:if>