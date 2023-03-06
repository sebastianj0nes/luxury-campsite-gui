# Modelling & Design

All diagrams were designed and implemented with Astah.

# Use-case Diagram
The use-case diagram was designed with two main actors, the Owner and Cleaning Staff. The owner is responsible for handling guest bookings, checking in/out, and can view room information. The cleaning staff will be able to update the cleaning status and can do so by viewing room information.
![Campsite System Use-Case Diagrams](/Models/Images/UseCaseDiagram.png)

# Class Diagram
The class diagram starts with the user (either owner or cleaning staff) which interacts with the luxury campsite system. The system then pulls information from the area which holds data about accommodations. Each accommodation inherits information from separate accommodation types (GeodesicDome, ShepherdHut, Yurt & Cabin). After getting information about the accommodation the user can then create a booking with the guest information provided on input.
![Campsite System Class Diagrams](/Models/Images/ClassDiagram%20Final.png)

# Sequence Diagram 1 - Check in guest
The sequence diagram allows the owner to check in a guest, and begins with the owner accessing the system. Then the user can get areas, looping until the correct accommodation is found. After this the owner can check in a guest object, providing all guest details to the system, using the booking class and making use of the methods inside. Check in is then complete.
![Campsite System Check-in Sequence Diagram](/Models/Images/Sequence%20Diagram%20-%20Check%20In%20Usecase.png)

# Sequence Diagram 2 - Set cleaning status
This sequence diagram lets the cleaning status be changed after a guest has been checked out. The cleaning staff can access the system, loop until the correct accommodation is found, and then get the cleaning status of the accomm. If the cleaning status is outdated, the cleaning staff can update to the appropriate status.
![Campsite System Change cleaning status Sequence Diagram](/Models/Images/Sequence%20Diagram%20-%20Set%20Cleaning%20Status%20.png)

