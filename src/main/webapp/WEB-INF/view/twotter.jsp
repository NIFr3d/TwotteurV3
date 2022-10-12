<%@include file="taglibs.jsp"%>
<c:if test="${!empty sessionScope.isLogged }">
    <form class="border-2 mt-2 w-full" action="../twot" method="post">
        <textarea class="resize-none border-none bg-blue-900 w-full" name="text" placeholder="Quoi de neuf ?" required></textarea>
        <input type="hidden" name="id" value=<c:out value="${userid}"></c:out> />
        <button type="submit" class="rounded-lg bg-blue-900 w-full">Twoter</button>
    </form>
</c:if>