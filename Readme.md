# Owner 
Register/Login --> Add/Update Menu --> Sinc Menu to Firebase cloud.
# Customer
Scan the Qr code --> View the menu --> add items to the cart --> Enter the mobile_Number and Name -->  Place Order.
# owner 
receives the order along with name and mobilenumber --> once food is prepared the owner will click a button and this will send message to Cutomer's mobile number.
# Customer 
receives a message "Order done!"
# Steps:
- Make a login and register page
- The owner must use apps UI to add menu items. This data will be saved locally to the apps Roomdatabase.
- make the layout.
- ![Screenshot from 2025-01-05 13-12-53](https://github.com/user-attachments/assets/71201d0c-2509-48c3-9296-178125560ad9)
- Make an Entity class.(java class) { Entity class represents a table in the database}
- Make a Dao Interface.
- Make a Database class.

## Make a new Activity for Fetching Data. name= GetMenu
- Add dependencies for recyclerview and cardView.
- Make a linear Layout file with Vertical orientation and add recyclerview.
- In GetMenu java file,make an object for recyclerview and using findViewByIdMethod, link the recyclerView to the xml file.
- set the Layput Manager for the recycler view.
```
recyclerView = findViewById(R.id.recyclerView);
recyclerView.setLayoutManager(new LinearLayoutManager(this));
```

