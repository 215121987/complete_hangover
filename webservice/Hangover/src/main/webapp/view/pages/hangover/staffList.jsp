<%@ include file="/view/common/taglib.jsp" %>
<c:choose>
    <c:when test="${not empty entities}">
        <c:forEach items="${entities}" var="staff">
            <div class="col-md-4 item-block">
                <div class="parallax-item sans-shadow text-center">
                    <i class="fa fa-user fa-3x"></i>
                    <h4 class="searchable">Name:- ${staff.user.name}</h4>
                    <p class="searchable">Store:- ${staff.store.zipCode}</p>
                    <p class="searchable">Mobile:-<i class="fa fa-mobile-phone fa-1x"></i>${staff.user.mobile}</p>
                    <div class="item-info">
                        <h4 class="searchable">Name:- ${staff.user.name}</h4>
                        <p>Email:- ${staff.user.email}</p>
                        <%--<p>${staff.supplier.companyName}</p>--%>
                        <ul class="item-meta">
                            <li>
                                <a href="" class="add-parallax-item" data_url="add_staff" req_data="id:${staff.id}" view="hangover/supplierStaffForm" title="Edit Staff">
                                    <i class="fa fa-pencil fa-2x"></i>
                                </a>
                            </li>
                            <li>
                                <a href="" class="delete-parallax-item" data_url="delete_staff" data_id="${staff.id}"
                                   title="Remove Staff"><i class="fa fa-remove fa-2x"></i></a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </c:forEach>
    </c:when>
    <c:otherwise>
        <div>No record</div>
    </c:otherwise>
</c:choose>
