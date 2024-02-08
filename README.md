# DummyEcom_backendAPI
A dummy e commerce website using spring boot has been made.

## Functional APIs
* Register user: http://localhost:8080/api/user/register
* Login user: http://localhost:8080/api/user/login
* Adding products to the product list: http://localhost:8080/api/product/add
* Getting a list of all the products: http://localhost:8080/api/product/all
* Updating the name and price of products by providing the product id: http://localhost:8080/api/product/update/{productId}
* Adding items to the cart: http://localhost:8080/api/user/login/cart/add-cart-items
* Getting all the items from the cart: http://localhost:8080/api/user/login/cart/cart-items/get
* Checking out the products: http://localhost:8080/api/user/login/cart/checkout
* Processing Payment: http://localhost:8080/api/billing/process-payment
* Processing order: http://localhost:8080/api/billing/process-order

## Non-Functional APIs
* Searching for order history: http://localhost:8080/order/history/search
* Getting the order history by user id: http://localhost:8080/order/history/{userId}

### Note:
After proceedeing to billing once it is unable to bill again for the same checkout id, since I'm unable to clear the history of the previous checkoutid. I need to work on that as well as on the order history portion of the code. Apart from this, their might be some parts of the code which might don't logically make sense for example, I'm trying to retrieve order history just by user_id. Also another not so logical part of the code is that I have made a checkout table separately in the database which only increases the complexity of the program. So, in the future code, I will make sure the code makes more sense logically.


