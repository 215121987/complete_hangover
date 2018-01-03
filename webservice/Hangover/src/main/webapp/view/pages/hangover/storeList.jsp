<%@ include file="/view/common/taglib.jsp" %>
<c:choose>
    <c:when test="${not empty entities}">
        <c:forEach items="${entities}" var="store">
            <div class="col-md-4 item-block">
                <div class="parallax-item sans-shadow text-center">
                    <i class="fa fa-align-justify fa-3x"></i>
                    <h4 class="searchable">${store.zipCode}</h4>
                    <p class="searchable">${store.address}, ${store.street}, ${store.city}, ${store.state}, ${store.country}</p>
                    <div class="item-info">
                        <h4>${store.zipCode}</h4>
                        <p>${store.contactPerson}:-<i class="fa fa-mobile-phone fa-1x"></i>${store.contactNumber}</p>
                        <ul class="item-meta">
                            <li>
                                <a href="" class="add-parallax-item" data_url="add_store" req_data="id:${store.id}" view="hangover/storeForm" title="Edit Store">
                                    <i class="fa fa-pencil fa-2x"></i>
                                </a>
                            </li>
                            <li>
                                <a href="" class="add-parallax-item" data_url="add_staff" req_data="storeId:${store.id}-supplierId:${store.supplier.id}-zipCode:${store.zipCode}"
                                   view="hangover/supplierStaffForm" title="Add Staff">
                                    <i class="fa fa-plus fa-2x"></i>
                                </a>
                            </li>
                            <li>
                                <a href="" class="delete-parallax-item" data_url="delete_store" data_id="${store.id}"
                                   title="Remove Store"><i class="fa fa-remove fa-2x"></i></a>
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
