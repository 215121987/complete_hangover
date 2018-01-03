<%@ include file="/view/common/taglib.jsp" %>
<c:choose>
    <c:when test="${not empty entities}">
        <c:forEach items="${entities}" var="supplier">
            <div class="col-md-4 item-block">
                <div class="parallax-item sans-shadow text-center">
                    <i class="fa fa-align-justify fa-3x"></i>
                    <h4 class="searchable">${supplier.companyName}</h4>

                    <p>${supplier.description}</p>

                    <div class="item-info">
                        <h4>${supplier.companyName}<span>(${supplier.code})</span></h4>

                        <p>${supplier.contactPerson}:-<i class="fa fa-mobile-phone fa-1x"></i>${supplier.contactNumber}</p>
                        <ul class="item-meta">
                            <li>
                                <a href="" class="add-parallax-item" data_url="add_supplier" req_data="id:${supplier.id}" view="hangover/supplierForm" title="Edit Supplier">
                                    <i class="fa fa-pencil fa-2x"></i>
                                </a>
                            </li>
                            <li>
                                <a href='<c:url value="/SupplierStoreEntity/${supplier.id}/supplier.html?view=hangover/storeLayout" />' title="Show Stores">
                                    <i class="fa fa-location-arrow fa-2x"></i>
                                </a>
                            </li>
                            <li>
                                <a href="" class="add-parallax-item" data_url="add_store" req_data="storeId:${supplier.id}-companyName:${supplier.companyName}" view="hangover/storeForm" title="Add Store">
                                    <i class="fa fa-plus fa-2x"></i>
                                </a>
                            </li>
                            <li><a href="" class="delete-parallax-item" data_url="delete_supplier" data_id="${supplier.id}"
                                   title="Remove Supplier"><i class="fa fa-remove fa-2x"></i></a></li>
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
