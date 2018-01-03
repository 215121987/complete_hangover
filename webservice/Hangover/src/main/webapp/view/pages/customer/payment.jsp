<%@ include file="/view/common/taglib.jsp" %>
<style type="text/css">
    .panel{
        width: 55%!important;
    }
    #hangover_tabs ul.tabs{
        width: 25%;
    }


</style>
<div class="woocommerce-tabs" id="hangover_tabs">
<ul class="tabs">
    <li class="description_tab">
        <a href="#tab-wallet">Wallet</a>
    </li>
    <%--<li class="description_tab">
        <a href="#tab-credit_card">Credit Card</a>
    </li>
    <li class="reviews_tab">
        <a href="#tab-debit_card">Debit Card</a>
    </li>
    <li class="cherry_wc_video_tab">
        <a href="#tab-internet_banking">Internet Banking</a>
    </li>--%>
</ul>
<div class="panel entry-content" id="tab-wallet">
    <div id="wallet" class="desktop-card-detail" style="display: block;">
        <div class="step4-creditcard mobile-card-detail">
            <form action="<c:url value="/payment.html" />" method="POST" id="form-wallet" autocomplete="off"
                  name="walletForm">
                <div class="form-row ">
                    <div class="col-md-5">
                        <div class="cpl-md-1">
                            <%--<input type="radio" id="wallet_${wallet.id}" name="walletId" value="${wallet.id}" required="true">
                            <label for="wallet_${wallet.id}"><img src="<c:url value="/images/logo/paytm.gif" />" alt="Paytm"></label>--%>
                                <input type="radio" id="wallet_paytm" name="walletId" value="1" required="true">
                                <label for="wallet_paytm"><img src="<c:url value="/images/logo/paytm.gif" />" alt="Paytm"></label>
                        </div>
                    </div>
                </div>
                <div class="form-row">
                    <input type="hidden" name="amount" value="${cartSummary.netAmount}"/>
                    <input type="hidden" name="mode" value="WALLET"/>
                    <button id="pay-through_wallet" class="btn btn-payment " type="submit">Pay
                        <span class="standard-price">${cartSummary.netAmount}</span>
                    </button>
                </div>
            </form>
        </div>
        <hr/>
        <p class="step4-note">Please note: You might be redirected to 3-D secure page to complete your transaction. By placing this order, you agree to the
            <a target="_blank" href="#">Terms of Use</a>
            and <a target="_blank" href="#">Privacy Policy</a> of Hangover.com</p>
    </div>
</div>


<div class="panel entry-content" id="tab-credit_card">
    <div id="credit-card" class="desktop-card-detail" style="display: block;">
        <div class="step4-creditcard mobile-card-detail">
            <form action="/checkout/placeCreditDebitOrder/" method="POST" id="form-credit" autocomplete="off"
                  name="debitCardForm">
                <div class="form-row ">
                    <span class="visa cardIcon" style="display: none;"></span>
                    <span class="mastercard cardIcon" style="display: none;"></span>
                    <span class="maestro cardIcon" style="display: none;"></span>
                    <span class="rupay cardIcon" style="display: none;"></span>
                    <label for="ccnum">Card Number <span class="required">*</span></label>
                    <input type="tel" maxlength="19" class="form-control" id="ccnum" name="ccnum" required="true"/>
                    <p class="input MestroInfo hide">You do not need to enter Expiry Date and CVV if they are not
                        mentioned on your debit card.</p>
                </div>
                <div class="form-row ">
                    <div class="col-md-5" style="padding-left: 0px!important;">
                        <label for="ccexpmon">Month of expiry (MM) <span class="required">*</span></label>
                        <select class="form-control " id="ccexpmon" name="ccexpmon" required="true">
                            <option value="">SELECT MONTH</option>
                            <c:forEach begin="01" end="12" step="1" var="month">
                                <option value="${month}">${month}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="col-md-5">
                        <label for="ccexpyr">Year of expiry (YYYY) <span class="required">*</span></label>
                        <select class="form-control " name="ccexpyr" id="ccexpyr" required="true">
                            <option value="">SELECT YEAR</option>
                            <c:forEach begin="2016" end="2051" step="1" var="year">
                                <option value="${year}">${year}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="form-row">
                    <label for="ccname">Name On Card <span class="required">*</span></label>
                    <input type="text"autocomplete="off" required="true" class="form-control" id="ccname" name="ccname"/>
                </div>
                <div class="form-row">
                    <div class="col-md-3" style="padding-left: 0px!important;">
                        <label for="ccvv">CVV  Code</label>
                        <input type="password" autocomplete="off" maxlength="3" required="true" class="form-control" id="ccvv" name="ccvv">
                    </div>
                    <div class="content" style="display: none;">
                        <p><strong>What is CVV number?</strong></p>
                        <p>The CVV number is the last 3 digits printed on the signature panel on the back of
                            the Card.<img src="//static.jabong.com/cms/new-site/cvv.jpg"></p>
                    </div>
                </div>
                <input type="hidden" name="cardType" value="visa-master" id="cc-card-type">
                <input type="hidden" name="paymentType" id="cc-paymentType" value="D">
                <input type="hidden" value="AWUj0FxUuYYB2RDOg8fS7sSbHUOOJZ4/eNO74=" name="_csrf">
                <input type="hidden" value="abfd1ad4644bcc62675edc97c8fb55f8e172468cbf69ac952d184bd4cb397d0d" name="_key">
                <div class="form-row">
                    <input type="checkbox" value="1" name="store_cc" id="save-cc-card">
                    <label class="save-card">Save this card for future faster checkout.</label>
                        <span class="popover-options" style="display: none;">
                            <a  data-placement="right" data-trigger="hover" data-container="body" class="btn-popover learn-more"
                                href="javascript:void(0);" data-original-title="" title="">Learn More</a>
                            <p class="content">
                                Your card details are 100% safe with us.We use world-class encryption for storing card details
                                and our systems are PCI DSS compliant.We do not store your CVV no.
                            </p>
                        </span>
                </div>
                <div class="form-row">
                    <button id="pay-by-cc-card" class="btn btn-payment " type="submit">Pay
                        <span class="standard-price">1799.00</span>
                    </button>
                </div>
            </form>
        </div>
        <hr/>
        <p class="step4-note">Please note: You might be redirected to 3-D secure page to complete your transaction. By placing this order, you agree to the
            <a target="_blank" href="/terms_and_conditions">Terms of Use</a>
            and <a target="_blank" href="/privacy_policy">Privacy Policy</a> of Hangover.com</p>
    </div>
</div>
<div class="panel entry-content" id="tab-debit_card">
    <div id="debit-card" class="desktop-card-detail" style="display: block;">
        <div class="step4-creditcard mobile-card-detail">
            <form action="/checkout/placeCreditDebitOrder/" method="POST" id="form-dedit" autocomplete="off"
                  name="debitCardForm">
                <div class="form-row ">
                    <span class="visa cardIcon" style="display: none;"></span>
                    <span class="mastercard cardIcon" style="display: none;"></span>
                    <span class="maestro cardIcon" style="display: none;"></span>
                    <span class="rupay cardIcon" style="display: none;"></span>
                    <label for="dcnum">Card Number <span class="required">*</span></label>
                    <input type="tel" maxlength="19" class="form-control" id="dcnum" name="dcnum" required="true"/>
                    <p class="input MestroInfo hide">You do not need to enter Expiry Date and CVV if they are not
                        mentioned on your debit card.</p>
                </div>
                <div class="form-row ">
                    <div class="col-md-5" style="padding-left: 0px!important;">
                        <label for="dcexpmon">Month of expiry (MM) <span class="required">*</span></label>
                        <select class="form-control " id="dcexpmon" name="dcexpmon" required="true">
                            <option value="">SELECT MONTH</option>
                            <c:forEach begin="01" end="12" step="1" var="month">
                                <option value="${month}">${month}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="col-md-5">
                        <label for="dcexpyr">Year of expiry (YYYY) <span class="required">*</span></label>
                        <select class="form-control " name="dcexpyr" id="dcexpyr" required="true">
                            <option value="">SELECT YEAR</option>
                            <c:forEach begin="2016" end="2051" step="1" var="year">
                                <option value="${year}">${year}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="form-row">
                    <label for="dcname">Name On Card <span class="required">*</span></label>
                    <input type="text"autocomplete="off" required="true" class="form-control" id="dcname" name="dcname"/>
                </div>
                <div class="form-row">
                    <div class="col-md-3" style="padding-left: 0px!important;">
                        <label for="dcvv">CVV  Code</label>
                        <input type="password" autocomplete="off" maxlength="3" required="true" class="form-control" id="dcvv" name="dcvv">
                    </div>
                    <div class="content" style="display: none;">
                        <p><strong>What is CVV number?</strong></p>
                        <p>The CVV number is the last 3 digits printed on the signature panel on the back of
                            the Card.<img src="//static.jabong.com/cms/new-site/cvv.jpg"></p>
                    </div>
                </div>
                <input type="hidden" name="cardType" value="visa-master" id="dc-card-type">
                <input type="hidden" name="paymentType" id="dc-paymentType" value="D">
                <input type="hidden" value="AWUj0FxUuYYB2RDOg8fS7sSbHUOOJZ4/eNO74=" name="_csrf">
                <input type="hidden" value="abfd1ad4644bcc62675edc97c8fb55f8e172468cbf69ac952d184bd4cb397d0d" name="_key">
                <div class="form-row">
                    <input type="checkbox" value="1" name="store_dc" id="save-dc-card">
                    <label class="save-card">Save this card for future faster checkout.</label>
                        <span class="popover-options" style="display: none;">
                            <a  data-placement="right" data-trigger="hover" data-container="body" class="btn-popover learn-more"
                                href="javascript:void(0);" data-original-title="" title="">Learn More</a>
                            <p class="content">
                                Your card details are 100% safe with us.We use world-class encryption for storing card details
                                and our systems are PCI DSS compliant.We do not store your CVV no.
                            </p>
                        </span>
                </div>
                <div class="form-row">
                    <button id="pay-by-dc-card" class="btn btn-payment " type="submit">Pay
                        <span class="standard-price">1799.00</span>
                    </button>
                </div>
            </form>
        </div>
        <hr/>
        <p class="step4-note">Please note: You might be redirected to 3-D secure page to complete your transaction. By placing this order, you agree to the
            <a target="_blank" href="/terms_and_conditions">Terms of Use</a>
            and <a target="_blank" href="/privacy_policy">Privacy Policy</a> of Hangover.com</p>
    </div>
</div>
<div class="panel entry-content" id="tab-internet_banking">
    <%--<div id="iternet_banking" class="desktop-card-detail" style="display: block;">
        <div class="step4-netbanking mobile-card-detail">
            <div class="listed-bank clearfix"><h5>select from popular bank</h5>

                <div class="paymoney"><label class="">
                    <div class="custom-radio"><span></span><input type="radio" value="AXIB" class="top-banks"
                                                                  name="topBanks"></div>
                </label><img src="https://static.jabong.com/media/payment/bank/AXIS_Bank.jpg"></div>
                <div class="paymoney"><label class="">
                    <div class="custom-radio"><span></span><input type="radio" value="CBIBAN_N" class="top-banks"
                                                                  name="topBanks"></div>
                </label><img src="https://static.jabong.com/media/payment/bank/Citibank_Bank_Account_Online.jpg">
                </div>
                <div class="paymoney"><label class="">
                    <div class="custom-radio"><span></span><input type="radio" value="HDFB" class="top-banks"
                                                                  name="topBanks"></div>
                </label><img src="https://static.jabong.com/media/payment/bank/HDFC_Bank.jpg"></div>
                <div class="paymoney"><label class="">
                    <div class="custom-radio"><span></span><input type="radio" value="ICIB" class="top-banks"
                                                                  name="topBanks"></div>
                </label><img src="https://static.jabong.com/media/payment/bank/ICICI_Bank.jpg"></div>
                <div class="paymoney"><label class="">
                    <div class="custom-radio"><span></span><input type="radio" value="162B" class="top-banks"
                                                                  name="topBanks"></div>
                </label><img src="https://static.jabong.com/media/payment/bank/Kotak_Mahindra_Bank.jpg"></div>
                <div class="paymoney"><label class="">
                    <div class="custom-radio"><span></span><input type="radio" value="SBI_N" class="top-banks"
                                                                  name="topBanks"></div>
                </label><img src="https://static.jabong.com/media/payment/bank/State_Bank_of_India.jpg"></div>
            </div>
            <form id="netbankingForm" action="/checkout/placeNetBankingOrder/" method="post">
                <div class="other-bank">
                    <div class="select-box jabong-dropdown">
                        <div class="custom-select-boxs">Other Banks<span class="change-value">Select bank</span>
                        </div>
                        <select id="Netbanking" data-valid="" name="ccChoice" class="form-control " data-rule="">netbanking
                            <option value="">Select Bank</option>
                            <option value="AND_N">Andhra Bank</option>
                            <option value="AXIB">AXIS Bank</option>
                            <option value="BOBCO_N">Bank of Baroda Corporate Accounts</option>
                            <option value="BOB_N">Bank of Baroda Retail Accounts</option>
                            <option value="BOI_N">Bank of India</option>
                            <option value="BOM_N">Bank of Maharashtra</option>
                            <option value="CAN_N">Canara Bank</option>
                            <option value="CEN_N">Central Bank Of India</option>
                            <option value="CBIBAN_N">Citibank Bank Account Online</option>
                            <option value="CITIUB_N">City Union Bank</option>
                            <option value="COP_N">Corporation Bank</option>
                            <option value="DCB_N">DCB Bank ( Development Credit Bank )</option>
                            <option value="DSHB">Deutsche Bank</option>
                            <option value="DLSB">Dhanlaxmi Bank</option>
                            <option value="FDEB_N">Federal Bank</option>
                            <option value="HDFB">HDFC Bank</option>
                            <option value="ICIB">ICICI Bank</option>
                            <option value="IDBI_N">IDBI Bank</option>
                            <option value="INB_N">Indian Bank</option>
                            <option value="IOB_N">Indian Overseas Bank</option>
                            <option value="INIB">IndusInd Bank</option>
                            <option value="ING_N">ING Vysya Bank ( now Kotak )</option>
                            <option value="JKB_N">Jammu Kashmir Bank</option>
                            <option value="KTKB_N">Karnataka Bank</option>
                            <option value="KVB_N">Karur Vysya Bank</option>
                            <option value="162B">Kotak Mahindra Bank</option>
                            <option value="LVB_N">Lakshmi Vilas Bank NetBanking</option>
                            <option value="OBPRF_N">Oriental Bank of Commerce</option>
                            <option value="NPNB_N">Punjab National Bank Retail Accounts</option>
                            <option value="RYBS">Royal Bank of Scotland N.V.</option>
                            <option value="SIB_N">South Indian Bank</option>
                            <option value="SDCB">Standard Chartered Bank</option>
                            <option value="SBBJB">State Bank of Bikaner and Jaipur</option>
                            <option value="SBH_N">State Bank of Hyderabad</option>
                            <option value="SBI_N">State Bank of India</option>
                            <option value="SBM_N">State Bank of Mysore</option>
                            <option value="SBP_N">State Bank of Patiala</option>
                            <option value="SBT_N">State Bank of Travancore</option>
                            <option value="SYNBK_N">Syndicate Bank</option>
                            <option value="TNMB_N">Tamilnad Mercantile Bank</option>
                            <option value="UNI_N">Union Bank of India</option>
                            <option value="UBI_N">United Bank of India</option>
                            <option value="VJYA_N">Vijaya Bank</option>
                            <option value="YESB">YES Bank</option>
                        </select>

                        <p class="error"></p></div>
                    <div class="error-msg netbanking-error hide"></div>
                    <button data-gaq-event="Checkout~$~Pay~$~net_banking" class="btn btn-payment pay_netbanking"
                            type="submit">pay <span class="standard-price">1799.00</span></button>
                </div>
                <input type="hidden" value="netbanking" name="formType"><input type="hidden"
                                                                               value="abfd1ad4644bcc62675edc97c8fb55f8e172468cbf69ac952d184bd4cb397d0d"
                                                                               name="_key"><input type="hidden"
                                                                                                  value="AWUj0FxUuYYB2RDOg8fS7sSbHUOOJZ4/eNO74="
                                                                                                  name="_csrf"><input
                    type="hidden" value="Netbanking" name="ccRadio"><input type="hidden" value="PayU"
                                                                           name="paymentMethod"></form>
            <p class="step4-note">Please note: You will be redirected to a secure payment gateway. By placing this
                order, you agree to the <a target="_blank" href="/terms_and_conditions">Terms of Use</a> and <a
                        target="_blank" href="/privacy_policy">Privacy Policy</a> of Jabong.com</p></div>
    </div>--%>
</div>
</div>
