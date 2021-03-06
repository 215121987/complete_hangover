jQuery(function (a) {
    if ("undefined" == typeof wc_cart_fragments_params)return!1;
    try {
        $supports_html5_storage = "sessionStorage"in window && null !== window.sessionStorage
    } catch (b) {
        $supports_html5_storage = !1
    }
    if ($fragment_refresh = {url:wc_cart_fragments_params.ajax_url, type:"POST", data:{action:"woocommerce_get_refreshed_fragments"}, success:function (b) {
        b && b.fragments && (a.each(b.fragments, function (b, c) {
            a(b).replaceWith(c)
        }), $supports_html5_storage && (sessionStorage.setItem(wc_cart_fragments_params.fragment_name, JSON.stringify(b.fragments)), sessionStorage.setItem("wc_cart_hash", b.cart_hash)), a("body").trigger("wc_fragments_refreshed"))
    }}, $supports_html5_storage) {
        a("body").bind("added_to_cart", function (a, b, c) {
            sessionStorage.setItem(wc_cart_fragments_params.fragment_name, JSON.stringify(b)), sessionStorage.setItem("wc_cart_hash", c)
        });
        try {
            var c = a.parseJSON(sessionStorage.getItem(wc_cart_fragments_params.fragment_name)), d = sessionStorage.getItem("wc_cart_hash"), e = a.cookie("woocommerce_cart_hash");
            if ((null === d || void 0 === d || "" === d) && (d = ""), (null === e || void 0 === e || "" === e) && (e = ""), !c || !c["div.widget_shopping_cart_content"] || d != e)throw"No fragment";
            a.each(c, function (b, c) {
                a(b).replaceWith(c)
            }), a("body").trigger("wc_fragments_loaded")
        } catch (b) {
            a.ajax($fragment_refresh)
        }
    } else a.ajax($fragment_refresh);
    a.cookie("woocommerce_items_in_cart") > 0 ? a(".hide_cart_widget_if_empty").closest(".widget_shopping_cart").show() : a(".hide_cart_widget_if_empty").closest(".widget_shopping_cart").hide(), a("body").bind("adding_to_cart", function () {
        a(".hide_cart_widget_if_empty").closest(".widget_shopping_cart").show()
    })
});