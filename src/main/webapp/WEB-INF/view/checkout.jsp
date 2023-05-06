<%-- 
    Document   : checkout
    Created on : 09-Mar-2023, 19:17:58
    Author     : Squash
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
  <meta charset="UTF-8" />
  <meta http-equiv="X-UA-Compatible" content="IE=edge" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <style>
    .address,
    .payment {
      display: flex;
      justify-content: space-between;
    }

    @media (min-width: 992px) {
      .container {
        width: 970px;
      }
    }

    .container {
      margin-left: auto;
      margin-right: auto;
      padding-left: 15px;
      padding-right: 15px;
    }
  </style>
</head>

<body>
  <div class="topnav">
    <a class="homeButton" href="/cricket-shelf">Home</a>
    <!-- Search bar -->
    <form class="search" action="search" method="get">
      <input type="text" name="search" id="search" placeholder="Search for a book..." />
      <input type="submit" value="Search" />
    </form>
    <a href="basket">Basket</a>
    <a href="orders">Orders</a>
    <a class="logoutButton" href="login">Logout</a>
  </div>
  <div class="container">
    <div class="address card">
      <div style="margin:20px">
        <h3>Address</h1>
      </div>
      <div style="margin: calc(20px + 1em);" id="addresses"></div>
      <div>
        <button id="changeAddressButton" disabled>Change</button>
        <button id="saveAddressButton" style="display: none;">Save</button>
        <button style="display: none;" id="closeAddressButton">Close</button>
      </div>
    </div>
    <div class="payment card">
      <div style="margin:20px">
        <h3>Payment</h1>
      </div>
      <div style="margin: calc(20px + 1em);" id="payments"></div>
      <div id="paymentButtons">
        <button id="changePaymentButton" disabled>Change</button>
        <button id="savePaymentButton" style="display: none;">Save</button>
        <button style="display: none;" id="closePaymentButton">Close</button>
      </div>
    </div>
    <input id="placeOrderButton" type="submit" value="Place Order" disabled />
  </div>
</body>
<script>
  class Address {
  /**
    Creates an address object with the given addresses.
    @param {HTMLDivElement} addresses - The div containing all the addresses.
    @param {Object} addressData - Address data, returned from API.
  */
  constructor(addresses, addressData) {
    this.addresses = addresses;
    this.addressData = addressData;
    this.savedAddressId = 0;
  }

  setAddress() {
    let label = document.createElement('label');
    label.htmlFor = 'address_' + this.addressData[this.savedAddressId].addressId;
    label.innerHTML =
      this.addressData[this.savedAddressId].street +
      ',' +
      this.addressData[this.savedAddressId].city +
      ',' +
      this.addressData[this.savedAddressId].state +
      ',' +
      this.addressData[this.savedAddressId].country +
      ',' +
      this.addressData[this.savedAddressId].postcode;
    addresses.innerHTML = label.innerHTML;
  }

  /**
    Changes the buttons to display the save and close buttons and hides the 
    change button, then displays all saved addresses with a radio button.
  */
  displayAllAddresses() {
    changeAddressButton.style.display = 'none';
    saveAddressButton.style.display = 'block';
    closeAddressButton.style.display = 'block';

    addresses.innerHTML = '';

    for (let i = 0; i < this.addressData.length; i++) {
      const address = this.addressData[i];
      let radiobutton = document.createElement('input');
      radiobutton.type = 'radio';
      radiobutton.id = i;
      radiobutton.name = `address`;
      radiobutton.value = i;

      let label = document.createElement('label');
      label.htmlFor = i;
      label.innerHTML =
        address.street + ',' + address.city + ',' + address.state + ',' + address.country + ',' + address.postcode;

      let addressDiv = document.createElement('div');
      addressDiv.appendChild(radiobutton);
      addressDiv.appendChild(label);
      addressDiv.appendChild(document.createElement('br'));
      addresses.appendChild(addressDiv);
    }

    let addressDiv = document.createElement('div');
    addressDiv.appendChild(this.generateAddressInputForm());
    addressDiv.appendChild(document.createElement('br'));
    addresses.appendChild(addressDiv);
  }

  /**
    Calls the set address to reset the address to the savedAddressId. Hides
    all the change related buttons and displays the one enabling the change.
  */
  resetToDefaultAddress() {
    this.setAddress();
    closeAddressButton.style.display = 'none';
    saveAddressButton.style.display = 'none';
    changeAddressButton.style.display = 'block';
  }

  /**
   * Upon clicking the save button, this function will update the
   * saved address button and then updates the address in the view.
   */
  updateAddress() {
    let selectedAddress = document.querySelector('input[name="address"]:checked');
    if (selectedAddress) {
      this.savedAddressId = selectedAddress.value;
      this.setAddress();

      closeAddressButton.style.display = 'none';
      saveAddressButton.style.display = 'none';
      changeAddressButton.style.display = 'block';
    }
  }
  generateAddressInputForm() {
    let form = document.createElement('div');
    form.style.marginTop = '20px';
    form.id = 'customAddress';

    let title = document.createElement('h3');
    title.innerText = 'Custom Address';
    form.appendChild(title);

    let street = document.createElement('input');
    street.id = 'street';
    street.name = 'street';

    let city = document.createElement('input');
    city.id = 'city';
    city.name = 'city';

    let state = document.createElement('input');
    state.id = 'state';
    state.name = 'state';

    let postcode = document.createElement('input');
    postcode.id = 'postcode';
    postcode.name = 'postcode';

    let country = document.createElement('input');
    country.id = 'country';
    country.name = 'country';

    let street_label = document.createElement('label');
    street_label.htmlFor = 'street';
    street_label.innerText = 'Street';

    let city_label = document.createElement('label');
    city_label.htmlFor = 'city';
    city_label.innerText = 'City';

    let state_label = document.createElement('label');
    state_label.htmlFor = 'state';
    state_label.innerText = 'State';

    let postcode_label = document.createElement('label');
    postcode_label.htmlFor = 'postcode';
    postcode_label.innerText = 'Postcode';

    let country_label = document.createElement('label');
    country_label.htmlFor = 'country';
    country_label.innerText = 'Country';

    form.appendChild(street_label);
    form.appendChild(street);
    form.appendChild(document.createElement('br'));

    form.appendChild(city_label);
    form.appendChild(city);
    form.appendChild(document.createElement('br'));

    form.appendChild(state_label);
    form.appendChild(state);
    form.appendChild(document.createElement('br'));

    form.appendChild(postcode_label);
    form.appendChild(postcode);
    form.appendChild(document.createElement('br'));

    form.appendChild(country_label);
    form.appendChild(country);
    form.appendChild(document.createElement('br'));

    let submitButton = document.createElement('button');
    submitButton.innerText = 'Add';
    submitButton.addEventListener('click', () => {
        fetch('/cricket-shelf/api/address/add', {
            method: 'POST',
            headers: {
              'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                street: street.value,
                city: city.value,
                state: state.value,
                postcode: postcode.value,
                country: country.value
            })
          }).then((response) => {
            if (response.redirected) {
              window.location.href = response.url;
            }
        });
    });

    form.appendChild(submitButton);

    return form;
  }
}

class Payment {
  /**
    Creates a payment object with the given payments.
    @param {HTMLDivElement} payments - The div containing all the payments.
    @param {Object} paymentData - Payment data, returned from API.
  */
  constructor(payments, paymentData) {
    this.payments = payments;
    this.paymentData = paymentData;
    this.savedPaymentId = 0;
  }
  
  setPayment() {
    let label = document.createElement('label');
    label.htmlFor = 'payment_' + this.paymentData[this.savedPaymentId].cardId;
    label.innerHTML =
      'Card Number: ' +
      this.paymentData[this.savedPaymentId].number +
      ',' +
      'Card Holder: ' +
      this.paymentData[this.savedPaymentId].name +
      ',' +
      'Expiry Date: ' +
      this.paymentData[this.savedPaymentId].expirationMonth +
      '/' +
      this.paymentData[this.savedPaymentId].expirationYear;
    payments.innerHTML = label.innerHTML;
  }

  updatePayment() {
    let selectedPayment = document.querySelector('input[name="payment"]:checked');
    if (selectedPayment) {
      this.savedPaymentId = selectedPayment.value;
      this.setPayment();

      closePaymentButton.style.display = 'none';
      savePaymentButton.style.display = 'none';
      changePaymentButton.style.display = 'block';
    }
  }
  generatePaymentInputForm() {
    let form = document.createElement('div');
    form.style.marginTop = '20px';
    form.id = 'customPayment';

    let title = document.createElement('h3');
    title.innerText = 'Custom Payment';
    form.appendChild(title);

    let cardNumber = document.createElement('input');
    cardNumber.id = 'cardNumber';
    cardNumber.name = 'cardNumber';

    let cardHolder = document.createElement('input');
    cardHolder.id = 'cardHolder';
    cardHolder.name = 'cardHolder';

    let expiryMonth = document.createElement('input');
    expiryMonth.id = 'expiryMonth';
    expiryMonth.name = 'expiryMonth';
    expiryMonth.type = 'number';

    let expiryYear = document.createElement('input');
    expiryYear.id = 'expiryYear';
    expiryYear.name = 'expiryYear';
    expiryYear.type = 'number';

    let cardNumber_label = document.createElement('label');
    cardNumber_label.htmlFor = 'cardNumber';
    cardNumber_label.innerText = 'Card Number';

    let cardHolder_label = document.createElement('label');
    cardHolder_label.htmlFor = 'cardHolder';
    cardHolder_label.innerText = 'Card Holder';

    let expiryMonth_label = document.createElement('label');
    expiryMonth_label.htmlFor = 'expiryMonth';
    expiryMonth_label.innerText = 'Expiry Month';

    let expiryYear_label = document.createElement('label');
    expiryYear_label.htmlFor = 'expiryYear';
    expiryYear_label.innerText = 'Expiry Year';

    form.appendChild(cardNumber_label);
    form.appendChild(cardNumber);
    form.appendChild(document.createElement('br'));

    form.appendChild(cardHolder_label);
    form.appendChild(cardHolder);
    form.appendChild(document.createElement('br'));

    form.appendChild(expiryMonth_label);
    form.appendChild(expiryMonth);
    form.appendChild(document.createElement('br'));

    form.appendChild(expiryYear_label);
    form.appendChild(expiryYear);
    form.appendChild(document.createElement('br'));

    let submitButton = document.createElement('button');
    submitButton.innerText = 'Add';
    submitButton.addEventListener('click', () => {        
        fetch('/cricket-shelf/api/card/add', {
            method: 'POST',
            headers: {
              'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                name: cardHolder.value,
                expirationMonth: expiryMonth.value,
                expirationYear: expiryYear.value,
                number: cardNumber.value,
            })
          }).then((response) => {
            if (response.redirected) {
              window.location.href = response.url;
            }
            console.log(response);
        });
    });

    form.appendChild(submitButton);

    return form;
  }
  displayAllPayments() {
    changePaymentButton.style.display = 'none';
    savePaymentButton.style.display = 'block';
    closePaymentButton.style.display = 'block';

    payments.innerHTML = '';
    for (let i = 0; i < this.paymentData.length; i++) {
      const payment = this.paymentData[i];
      let radiobutton = document.createElement('input');
      radiobutton.type = 'radio';
      radiobutton.id = i;
      radiobutton.name = `payment`;
      radiobutton.value = i;

      let label = document.createElement('label');
      label.htmlFor = i;
      label.innerHTML =
        'Card Number: ' +
        payment.number +
        ',' +
        'Card Holder: ' +
        payment.name +
        ',' +
        'Expiry Date: ' +
        payment.expirationMonth +
        '/' +
        payment.expirationYear;

      let paymentDiv = document.createElement('div');
      paymentDiv.appendChild(radiobutton);
      paymentDiv.appendChild(label);
      paymentDiv.appendChild(document.createElement('br'));
      payments.appendChild(paymentDiv);
    }

    let paymentDiv = document.createElement('div');
    paymentDiv.appendChild(this.generatePaymentInputForm());
    paymentDiv.appendChild(document.createElement('br'));
    payments.appendChild(paymentDiv);
  }

  resetToDefaultPayment() {
    this.setPayment();
    closePaymentButton.style.display = 'none';
    savePaymentButton.style.display = 'none';
    changePaymentButton.style.display = 'block';
  }
}

// Either turn into module, or use dirty trick... or create class...

Promise.all([
  fetch('/cricket-shelf/api/cards'),
  fetch('/cricket-shelf/api/addresses')
])
  .then((responses) => Promise.all(responses.map((response) => response.json())))
  .then((data) => {
    // Payment content div
    var payments = document.getElementById('payments');
    var payment = new Payment(payments, data[0]);

    const paymentData = data[0];
    const addressData = data[1];

    // If no payment methods, display form
    if (data[0].length === 0) {
      let paymentDiv = document.createElement('div');
      paymentDiv.appendChild(payment.generatePaymentInputForm());
      paymentDiv.appendChild(document.createElement('br'));
      payments.appendChild(paymentDiv);
    } else {
      // Payment Buttons
      var changePaymentButton = document.getElementById('changePaymentButton');
      var closePaymentButton = document.getElementById('closePaymentButton');
      var savePaymentButton = document.getElementById('savePaymentButton');

      changePaymentButton.disabled = false;
      changePaymentButton.addEventListener('click', payment.displayAllPayments.bind(payment));
      closePaymentButton.addEventListener('click', payment.resetToDefaultPayment.bind(payment));
      savePaymentButton.addEventListener('click', payment.updatePayment.bind(payment));

      payment.setPayment();
    }

    // Address content div
    var addresses = document.getElementById('addresses');
    var address = new Address(addresses, data[1]);

    // If no address methods, display form
    if (data[1].length === 0) {
      let addressDiv = document.createElement('div');
      addressDiv.appendChild(address.generateAddressInputForm());
      addressDiv.appendChild(document.createElement('br'));
      addresses.appendChild(addressDiv);
    } else {
      // Address Buttons
      var changeAddressButton = document.getElementById('changeAddressButton');
      var closeAddressButton = document.getElementById('closeAddressButton');
      var saveAddressButton = document.getElementById('saveAddressButton');

      changeAddressButton.disabled = false;
      changeAddressButton.addEventListener('click', address.displayAllAddresses.bind(address));
      closeAddressButton.addEventListener('click', address.resetToDefaultAddress.bind(address));
      saveAddressButton.addEventListener('click', address.updateAddress.bind(address));

      address.setAddress();
    }

     // If either address or payment is not selected, keep button disabled
     var placeOrderButton = document.getElementById('placeOrderButton');
     if (data[1].length !== 0 && paymentData.length !== 0) {
       placeOrderButton.disabled = false;
     }

     // Upon clicking place order, send a POST request to the server
     placeOrderButton.addEventListener('click', () => {
       fetch('/cricket-shelf/api/order/place', {
         method: 'POST',
         headers: {
           'Content-Type': 'application/json'
         },
         body: JSON.stringify({
           addressId: addressData[address.savedAddressId].addressId,
           paymentId: paymentData[payment.savedPaymentId].cardId
         })
       }).then((response) => {
         if (response.redirected) {
           window.location.href = response.url;
         }
       }).catch((response) => {
           console.warn(response);
       });
     });
   });
</script>
</html>