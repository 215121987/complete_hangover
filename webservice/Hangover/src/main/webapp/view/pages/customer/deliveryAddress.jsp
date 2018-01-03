<%@ include file="/view/common/taglib.jsp" %>
<section  class="cart-content col-lg-12  clearfix">
    <c:forEach items="${addressList}" var="address" varStatus="cnt">
        <div class="address_block col-md-5">
            <div class="cpl-md-1">
                <input type="radio" name="addressId" value="${address.id}" update_url=''
                       <c:if test="${not empty deliveryAddressId && deliveryAddressId eq address.id}">checked="true"</c:if>>
            </div>
            <div class="col-md-10">
                <p> ${address.address} &nbsp; ${address.street}</p>
                <p>${address.city} &nbsp; ${address.state}</p>
                <p>${address.country} &nbsp; ${address.zipCode}</p>
                <p>${address.zipCode}</p>
                <p>${address.mobileNumber}</p>
            </div>
        </div>
    </c:forEach>
</section>
<section class="cart-content col-lg-12  clearfix">
    <h4>New Address</h4>
    <form action='<c:url value="/comm/address/save.html" />' id="address_form" method="POST">
        <p class="form-row form-row-wide">
            <textarea rows="4" cols="20" name="address" id="address" title="Address" required="true"></textarea>
        </p>
        <p class="form-row form-row-wide">
            <input class="input-text" type="text" name="street" title="Street" id="street" required="true"/>
        </p>
        <p class="form-row form-row-wide">
            <input class="input-text" type="text" name="city" id="city" title="City" readonly="true" required="true"/>
        </p>
        <p class="form-row form-row-wide">
            <input class="input-text" type="text" name="state" id="state" title="State" readonly="true" required="true"/>
        </p>
        <p class="form-row form-row-wide">
            <input class="input-text" type="text" name="country" id="country" title="Country" readonly="true" required="true"/>
        </p>

        <p class="form-row form-row-wide">
            <input class="input-text" type="tel" name="zipCode" value="${zipCode}" id="zipCode" title="ZipCode" maxlength="6" readonly="true" required="true"/>
        </p>

        <p class="form-row">
            <input type="submit" class="button" name="submit" value="Save Address" />
        </p>
    </form>
</section>

<script type="text/javascript">
      $(document).ready(function(a){
           var addr = a.cookie('customer_address');
          if(null!= addr && undefined!= addr){
              addr  = JSON.parse(addr);
              $('form#address_form input').not("input[type=submit]").each(function(){
                  $(this).val(addr[$(this).attr("name")]);
              });
          }
      });
</script>

