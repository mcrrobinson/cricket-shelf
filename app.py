from fastapi import FastAPI
from fastapi.middleware.cors import CORSMiddleware

app = FastAPI()
app.add_middleware(
    CORSMiddleware,
    allow_origins=["*"],
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)


@app.get("/api/orders/product/{product_id}")
def order(product_id: int):
    return [{"bookId": 1, "quantity": 1, "itemCostOnPurchase": 6.5, "books": {"bookId": 1, "title": "Ultimate Cricket Superstars", "edition": "1st", "salesPrice": 6.5, "publishYear": 2022, "thumbnail": None}}]


@app.get("/api/orders/list")
def orders():
    return [{"orderId": 1, "total": 6.5, "status": "Ordered", "ordered": 1680653760078, "outForDelivery": None, "delivered": None}]


@app.get('/cricket-store/api/cards')
def cards():
    return [{"cardId": 1, "name": "Matthew Robinson", "expirationMonth": 6, "expirationYear": 2023, "number": "111132123123"}]


@app.get('/cricket-store/api/addresses')
def addresses():
    # return [{"addressId": 1, "street": "Street", "city": "London", "state": "Greater London", "postcode": "LO11AP", "country": "United Kingdom"},
    # {"addressId": 431, "street": "Second Street", "city": "Not London", "state": "Less London", "postcode": "LO21AP", "country": "United Kingdom"}]
    return [{"addressId": 1, "street": "Street", "city": "London", "state": "Greater London", "postcode": "LO11AP", "country": "United Kingdom"},
            {"addressId": 431, "street": "Second Street", "city": "Not London", "state": "Less London", "postcode": "LO21AP", "country": "United Kingdom"}]
