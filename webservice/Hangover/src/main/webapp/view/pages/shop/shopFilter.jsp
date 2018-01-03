<%@ include file="/view/common/taglib.jsp" %>
<div class="shop_category_filter_panel pull-left" id="shopFilterPanel">
<section class="category_panel ">
    <ul>
        <c:forEach items="${categories}" var="category">
            <li>
                <a href='<c:url value="/comm/category-${category.id}/shop.html" />'>
                        <%--<input type="checkbox" class="filter-value visible-filter" name="category"
                       id="category-${category.id}" value="${category.name}"/>--%>
                    <label >${category.name}</label>
                </a>
                <c:if test="${not empty category.childCategories && fn:length(category.childCategories) gt 0}">
                    <ul style="margin-left: 20px;">
                        <c:forEach items="${category.childCategories}" var="cc1">
                            <li>
                                <a href='<c:url value="/comm/category-${cc1.id}/shop.html" />'>
                                        <%--<input type="checkbox" class="filter-value visible-filter" name="category"
                                       id="category-${cc1.id}" value="${cc1.name}"/>--%>
                                    <label >${cc1.name}</label>
                                </a>
                                <c:if test="${not empty cc1.childCategories && fn:length(cc1.childCategories) gt 0}">
                                    <ul style="margin-left: 20px;">
                                        <c:forEach items="${cc1.childCategories}" var="cc2">
                                            <li>
                                                <a href='<c:url value="/comm/category-${cc2.id}/shop.html" />'>
                                                        <%--<input type="checkbox" class="filter-value visible-filter"
                                                        name="category" id="category-${cc2.id}"
                                                        value="${cc2.name}"/>--%>
                                                    <label >${cc2.name}</label>
                                                </a>
                                            </li>
                                        </c:forEach>
                                    </ul>
                                </c:if>
                            </li>
                        </c:forEach>
                    </ul>
                </c:if>
            </li>
        </c:forEach>
    </ul>
</section>

<section class="filter_panel">
    <hr/>
    <h5>Filter By</h5>

    <div data-filtername="Brand" class="filter-section ">
        <div class="filter-name-section">
            <div class="filter-type">
                <div class="filter-type-name lfloat">
                    <div class="filter-accordian">
                        <i class="hangover-icon icon-plus"></i>
                    </div>
                    Brand
                </div>
                    <span data-displaytype="checkBox" class="filter-clear rfloat hidden">
                        <label class="clear-lang">Clear</label>
                        <i class="sd-icon sd-icon-delete-sign"></i>
                    </span>
            </div>
        </div>
        <div class="clear"></div>
        <div class="filter-content">
            <input type="text" data-name="filterSearch-Brand" placeholder="Search Brand"
                   class="input-filter js-searchable-box">

            <div data-name="Brand" class="filter-inner" style="overflow-y: scroll;max-height: 200px;">
                <c:forEach items="${brands}" var="brand">
                    <div class="sdCheckbox filters-list ">
                        <input type="checkbox" value="${brand.name}" name="brandName" class="filter-value visible-filter"
                               id="Brand-${brand.name}">
                        <label for="Brand-${brand.name}">
                            <a class="filter-name hashAdded"> ${brand.name}</a></label>
                    </div>
                </c:forEach>

                <%--<div class="sdCheckbox filters-list ">
                    <input type="checkbox" value="Reebok" class="filter-value visible-filter" id="Brand-Reebok">
                    <label for="Brand-Reebok"> <a class="filter-name hashAdded"> Reebok</a><span class="filter-prod-count">(671)</span></label>
                </div>
                <div class="sdCheckbox filters-list ">
                    <input type="checkbox" value="Nike" class="filter-value visible-filter" id="Brand-Nike">
                    <label for="Brand-Nike"> <a class="filter-name hashAdded"> Nike</a><span class="filter-prod-count">(721)</span></label>
                </div>
                <div class="sdCheckbox filters-list ">
                    <input type="checkbox" value="Adidas" class="filter-value visible-filter" id="Brand-Adidas">
                    <label for="Brand-Adidas"> <a class="filter-name hashAdded"> Adidas</a><span class="filter-prod-count">(444)</span></label>
                </div>
                <div class="sdCheckbox filters-list ">
                    <input type="checkbox" value="Lotto" class="filter-value visible-filter" id="Brand-Lotto">
                    <label for="Brand-Lotto"> <a class="filter-name hashAdded"> Lotto</a><span class="filter-prod-count">(461)</span></label>
                </div>--%>
            </div>
            <%--<p class="view-more-button js-view-more">
                <a href="#bcrumbLabelId:255" class="hashAdded">View More <i class="sd-icon sd-icon-next"></i></a>
            </p>--%>
        </div>
    </div>
    <div class="clear"></div>
    <hr/>
    <%--<div data-filtername="Price" class="filter-section ">
        <div class="filter-name-section">
            <div class="filter-type">
                <div class="filter-type-name lfloat">
                    <div class="filter-accordian">
                        <i class="hangover-icon icon-plus"></i>
                    </div>
                    Price
                </div>
                    <span data-displaytype="checkBox" class="filter-clear rfloat hidden">
                        <label class="clear-lang">Clear</label>
                        <i class="sd-icon sd-icon-delete-sign"></i>
                    </span>
            </div>
        </div>
        <div class="clear"></div>
        <div class="filter-content">
            <div id="slider-range"></div>
            <div class="priceRangeInfo">
                <input type="tel" name="minPrice" id="buying_slider_min" class="minBuyingSlider" value="0"
                       min="0" max="1000"/>
                <input type="tel" name="maxPrice" id="buying_slider_max" class="maxBuyingSlider"
                       value="2000" min="0" max="2000"/>
                <input type="submit" class="button small_button" name="priceFilter" value="Go"/>
            </div>
        </div>
        <div class="clear"></div>
    </div>
    <div class="clear"></div>
    <hr/>--%>

    <div data-filtername="Price" class="filter-section ">
        <div class="filter-name-section">
            <div class="filter-type">
                <div class="filter-type-name lfloat">
                    <div class="filter-accordian">
                        <i class="hangover-icon icon-plus"></i>
                    </div>
                    Size
                </div>
                    <span data-displaytype="checkBox" class="filter-clear rfloat hidden">
                        <label class="clear-lang">Clear</label>
                        <i class="sd-icon sd-icon-delete-sign"></i>
                    </span>
            </div>
        </div>
        <div class="clear"></div>
        <div class="filter-content">
            <div data-name="discount" class="filter-inner ">
                <div class="sdCheckbox filters-list ">
                    <input type="checkbox" name="size" value="180" class="filter-value visible-filter" id="size-180ml">
                    <label for="size-180ml"> <a class="filter-name hashAdded"> 180 ML</a></label>
                </div>
                <div class="sdCheckbox filters-list ">
                    <input type="checkbox" name="size" value="375" class="filter-value visible-filter" id="size-375ml">
                    <label for="size-375ml"> <a class="filter-name hashAdded"> 375 ML</a></label>
                </div>
                <div class="sdCheckbox filters-list ">
                    <input type="checkbox" name="size" value="600" class="filter-value visible-filter" id="size-600ml">
                    <label for="size-600ml"> <a class="filter-name hashAdded"> 600 ML</a></label>
                </div>
                <div class="sdCheckbox filters-list ">
                    <input type="checkbox" name="size" value="750" class="filter-value visible-filter" id="size-750ml">
                    <label for="size-750ml"> <a class="filter-name hashAdded"> 750 ML </a></label>
                </div>
                <div class="sdCheckbox filters-list ">
                    <input type="checkbox" name="size" value="1000" class="filter-value visible-filter" id="size-1000ml">
                    <label for="size-1000ml"> <a class="filter-name hashAdded"> 1000 ML</a></label>
                </div>
            </div>
        </div>
        <div class="clear"></div>
    </div>
    <div class="clear"></div>
    <hr/>
    <div data-filtername="Price" class="filter-section ">
        <div class="filter-name-section">
            <div class="filter-type">
                <div class="filter-type-name lfloat">
                    <div class="filter-accordian">
                        <i class="hangover-icon icon-plus"></i>
                    </div>
                    Discount %
                </div>
                    <span data-displaytype="checkBox" class="filter-clear rfloat hidden">
                        <label class="clear-lang">Clear</label>
                        <i class="sd-icon sd-icon-delete-sign"></i>
                    </span>
            </div>
        </div>
        <div class="clear"></div>
        <div class="filter-content">
            <div data-name="Brand" class="filter-inner ">
                <div class="sdCheckbox filters-list ">
                    <input type="checkbox" name="discount" value="0-10" class="filter-value visible-filter" id="discount-10">
                    <label for="discount-10"> <a class="filter-name hashAdded"> 0 - 10</a></label>
                </div>
                <div class="sdCheckbox filters-list ">
                    <input type="checkbox" name="discount" value="10-20" class="filter-value visible-filter" id="discount-20">
                    <label for="discount-20"> <a class="filter-name hashAdded"> 10 - 20 </a></label>
                </div>
                <div class="sdCheckbox filters-list ">
                    <input type="checkbox" name="discount" value="20-30" class="filter-value visible-filter" id="discount-30">
                    <label for="discount-30"> <a class="filter-name hashAdded"> 20 - 30</a></label>
                </div>
                <div class="sdCheckbox filters-list ">
                    <input type="checkbox" name="discount" value="30-40" class="filter-value visible-filter" id="discount-40">
                    <label for="discount-40"> <a class="filter-name hashAdded"> 30 - 40</a></label>
                </div>
                <div class="sdCheckbox filters-list ">
                    <input type="checkbox" name="discount" value="40-50" class="filter-value visible-filter" id="discount-50">
                    <label for="discount-50"> <a class="filter-name hashAdded"> 40 - 50</a></label>
                </div>
            </div>
        </div>
        <div class="clear"></div>
    </div>
</section>
</div>