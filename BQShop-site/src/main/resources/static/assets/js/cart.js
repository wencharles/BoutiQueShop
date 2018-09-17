jQuery(document).ready(function($){
getCartItemCount();
});

	function getCartItemCount()
	{
		$.ajax (
		{
	        url: '/cart/items/count',
	        type: "GET",
	        dataType: "json",
	        contentType: "application/json",
	        complete: function(responseData, status, xhttp){
	        	$('#cart-item-count').text('('+responseData.responseJSON.count+')');//bind response dtata to id #cart-item-count
	        }
	    });
	}

	/*function getSignInUser()
    	{
    		$.ajax (
    		{
    	        url: '/myAccount',
    	        type: "GET",
    	        dataType: "json",
    	        contentType: "application/json",
    	        complete: function(responseData, status, xhttp){
    	        	$('#user').text('('+responseData.responseJSON.customer+')');//bind response dtata to id #cart-item-count
    	        }
    	    });
    	}*/

	function addItemToCart(id)
	{
		$.ajax (
		{
	        url: '/cart/items',
	        type: "POST",
	        dataType: "json",
	        contentType: "application/json",
	        data : '{"id":"'+ id +'"}"',
	        complete: function(responseData, status, xhttp){
	        	getCartItemCount();

	        	//alert("Item added");
	        }
	        /*else{
	        error: function(){
	        alert("item not added to cart")
	        }
	       }*/

	    });
	}

	function deleteLineItemFromCart(id)// passes the item id to be deleted
    	{
    		$.ajax ({
    	        url: '/cart/items/'+id,
    	        type: "DELETE",
    	        dataType: "json",
    	        contentType: "application/json",
    	        complete: function(responseData, status, xhttp){
    	        	getCartItemCount();
    	        }
    	    });
    	}
    function upadteLineItemQuantity(id, quantity) //pass the id and the quanty
	{
		$.ajax ({
	        url: '/cart/items',
	        type: "PUT",
	        dataType: "json",
	        contentType: "application/json",
	        data : '{ "product" :{ "id":"'+ id +'"},"quantity":"'+quantity+'"}',
	        complete: function(responseData, status, xhttp){
	        	getCartItemCount();
	        	location.href="/cart"
	        }
	    });
	}
	/*function upadteLineItemQuantity(){
	$.ajax( {
	url:'cart/',
	type:"PUT",
	*//*dataType:"application/json",*//*
	dataType"json",
	contentType: "applocation/json",
	data: '{}'}
	)}
*/