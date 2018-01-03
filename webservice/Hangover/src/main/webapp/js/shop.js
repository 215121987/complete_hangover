var TOKEN_SEPARATOR = "";
var processSeparator = function(){
    TOKEN_SEPARATOR =  ""==TOKEN_SEPARATOR?TOKEN_SEPARATOR="--":TOKEN_SEPARATOR;
};

var pageNumber = 2;
var requestParam = {};
var refreshFilter = function(){
    pageNumber =1;
    var brandNames = "";
    $('#shopFilterPanel input[name=brandName]:checked').each(function(){
        brandNames = brandNames+TOKEN_SEPARATOR+$(this).val();
        processSeparator();
    });
    var itemSizes = "";
    TOKEN_SEPARATOR = "";
    $('#shopFilterPanel input[name=size]:checked').each(function(){
        itemSizes = itemSizes+TOKEN_SEPARATOR+$(this).val();
        processSeparator();
    });
    var discounts = "";
    TOKEN_SEPARATOR = "";
    $('#shopFilterPanel input[name=discount]:checked').each(function(){
        discounts = discounts+TOKEN_SEPARATOR+$(this).val();
        processSeparator();
    });
    requestParam["brand"] = brandNames;
    requestParam["itemSize"] = itemSizes;
    requestParam["discount"] = discounts;
    requestParam["minPrice"] =  $('#shopFilterPanel input[name=minPrice]').val();
    requestParam["maxPrice"] =  $('#shopFilterPanel input[name=maxPrice]').val();
    requestParam["orderBy"] =  $('input[name=orderBy]').val();
};


var inProgress = false;
var noMoreItem = false;

var updateItemPaneOnScroll = function(e){
    var oTop = $('ul.products').offset().top + $('ul.products').outerHeight() - window.innerHeight;
    var pTop = $(window).scrollTop();
    console.log( pTop + ' - ' + oTop );
    if( pTop > oTop){
        oTop = $(window).scrollTop();
        if(pageNumber<=5 && !inProgress && !noMoreItem){
            inProgress =true;
            updateItemPane();
        }
        if(pageNumber>5){
            $('.moreItems').removeClass("hidden");
        }
    }
};

var updateItemPane = function(){
    requestParam["page"] = pageNumber;
    requestParam["ajax"]= 'true';
    $.get('/hangover/comm/shop.html',requestParam,function(response){
        var responseAsHTML = $('<div>').html(response);
        if($(responseAsHTML).find("li").length>0){
            $(responseAsHTML).find("li").each(function(){
                $(this).find('select').zelect();
                $("ul.products").append(this);

            });
            pageNumber=pageNumber+1;
            oTop = $('ul.products li:last').offset().top;
        }else{
            noMoreItem = true;
            $("ul.products").after($(responseAsHTML).find("div")[0].outerHTML);
        }
        inProgress = false;
    });
};



$(function(){
    $(".moreItems").on('click',function(){
        updateItemPane();
    });

    $('#shopFilterPanel input[type=checkbox], input[name=orderBy]').on("change",function(){
        refreshFilter();
        $("ul.products").html("");
        $("ul.products").parent().find('.no-more-item').remove();
        updateItemPane();
    });

    $('#shopFilterPanel input[name=priceFilter]').on("click",function(){
        refreshFilter();
        $("ul.products").html("");
        updateItemPane();
    });
});






var wishList = function(itemId){
    return $(document.createElement("div")).addClass("yith-wcwl-add-to-wishlist")
        .append($(document.createElement("div")).addClass("yith-wcwl-add-button show")
        .append($(document.createElement("a")).attr("href","#").attr("data-product-id", itemId).addClass("add_to_wishlist").append("Add To Wishlist"))
        .append($(document.createElement("img")).attr("src", "/hangover/images/banner/wpspin-light.gif").addClass("ajax-loading hide").attr("id", "add-items-ajax-loading-"+ itemId)
        .attr("alt","").attr("width", "16").attr("height", "16")))
        .append($(document.createElement("div")).addClass("yith-wcwl-wishlistaddedbrowse hide").append($(document.createElement("span")).addClass("feedback").append("Product added!"))
        .append($(document.createElement("a")).attr("href", "#").append("Browse  Wishlist")))
        .append($(document.createElement("div")).addClass("yith-wcwl-wishlistexistsbrowse hide").append($(document.createElement("span")).addClass("feedback").append("The product is already in the wishlist!"))
        .append($(document.createElement("a")).attr("href", "#").append("Browse Wishlist")))
        .append($(document.createElement("div")).addClass("clear"));
};
var addToCart = function(itemId, ItemDetailId){
    return  $(document.createElement("a")).addClass("button add_to_cart_button product_type_simple")
        .attr("href","#").attr("data-item_id", itemId).attr("data-quantity", 1).attr("data-item_detail_id", ItemDetailId).append("Add to cart");
};

var itemDetail = function(itemId, itemName, itemSize, price){
    var itemA =  '<a href="'+itemId+'">'+
        '<div class="cherry-thumb-wrap">'+
        '<span class="onsale">Sale!</span>'+
        ' <img width="300" height="300" src="http://livedemo00.template-help.com/woocommerce_53953/wp-content/uploads/2013/09/Antica-Formula-Carpano-Vermouth-1L-4-300x300.png" class="attachment-shop_catalog wp-post-image" alt="'+itemName+'"/>'+
        '<span class="btn cherry-quick-view" data-product="'+itemId+'">Quick view</span> '+
        '</div> '+
        '<h3>'+itemName+'</h3> '+
        '<div style="display: block;width: 80%;">'+
        '<div class="" style="display: block;width:30%;float: left;"> '+
        '<span class="size">'+itemSize+'</span> '+
        '</div>'+
        '<div class="" style="display: block;width: 55%; float: left;">'+
        '<span class="standard-price">'+price+'</span>'+
        '<span class="standard-price">'+price+'</span> '+
        '</div>'+
        '<div class="" style="display: block;width: 15%; float: left;">'+
        '<span class="offer">(50%)</span>'+
        '</div>'+
        ' </div>'+

        '</a>';
    return itemA;
};

var itemButtonList = function(itemId,itemDetailId){
    return $(document.createElement("div")).addClass("buttonsBlock").append(addToCart(itemId, itemDetailId)).append(wishList(itemId));
};
